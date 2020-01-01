package com.sxt.sys.service.qxs.warehouse.impl;

import com.sxt.sys.domain.User;
import com.sxt.sys.domain.hjn.Detailed;
import com.sxt.sys.domain.hjn.Orders;
import com.sxt.sys.domain.qxs.warehouse.DepotItem;
import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.zqw.Assemble;
import com.sxt.sys.domain.zqw.Picking;
import com.sxt.sys.domain.zqw.Productionplan;
import com.sxt.sys.mapper.UserMapper;
import com.sxt.sys.mapper.hjn.DetailedMapper;
import com.sxt.sys.mapper.hjn.OrderMapper;
import com.sxt.sys.mapper.qxs.warehouse.DepotHeadMapper;
import com.sxt.sys.mapper.qxs.warehouse.DepotItemMapper;
import com.sxt.sys.mapper.qxs.warehouse.MaterialsMapper;
import com.sxt.sys.mapper.winter.ApplyForMapper;
import com.sxt.sys.mapper.winter.SaleMapper;
import com.sxt.sys.mapper.zqw.AssembleMapper;
import com.sxt.sys.mapper.zqw.PickingMapper;
import com.sxt.sys.mapper.zqw.ProductionplanMapper;
import com.sxt.sys.service.qxs.warehouse.DepotHeadServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @program: xterp
 * @description: 单据主表业务实现
 * @author: snow
 * @create: 2019-12-10 10:18
 **/
@Service
public class DepotHeadServiceImpl implements DepotHeadServiceI {

    @Autowired
    private DepotHeadMapper depotHeadMapper;
    @Autowired
    private DepotItemMapper depotItemMapper;
    @Autowired
    private MaterialsMapper materialsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SaleMapper saleMapper;
    @Autowired
    private AssembleMapper assembleMapper;

    /**
     * 查询所有未删除的
     * @return
     */
    @Override
    public List<Depothead> queryNotDeleteDepotHead() {
        return depotHeadMapper.queryNotDeleteDepotHead();
    }

    /**
     * 查询所有记录
     * @return
     */
    @Override
    public List<Depothead> queryAllDepotHead() {
        return depotHeadMapper.queryAllDepotHead();
    }

    /**
     * 根据票据号查询
     * @param number
     * @return
     */
    @Override
    public List<Depothead> getOneDepotHead(String number) {
        return depotHeadMapper.getOneDepotHead(number);
    }

    /**
     * 查询所有已删除的数据
     * @return
     */
    @Override
    public List<Depothead> queryDelete() {
        return depotHeadMapper.queryDelete();
    }

    /**
     * 新增主表数据
     * @param depothead
     * @return int 0：
     */
    @Override
    public boolean addDepotHead(Depothead depothead) {
        return depotHeadMapper.addDepotHead(depothead);
    }

    /**
     * 修改主表数据
     * @param depothead
     * @return
     */
    @Override
    public boolean updateDepotHead(Depothead depothead) {
        return depotHeadMapper.updateDepotHead(depothead);
    }

    /**
     * 审批 ,通过审批对子表数据添加 / 修改
     * @param head 1:已审核 0:未审核 2：不通过 默认 0 其他数值为待审核
     * @return int 0：失败； 1:出库办理成功,通过审核；2：入库办理成功,通过审核；
     * 3：不存在此数据，需要对子表数据进行新增,通过审核; 5:子表数据添加/修改失败
     * 出库库存不够或者仓库没有此数据；零件 @return 9：转至采购；@return 8 成品：转至销售
     * 10:代表生产完成以后进行的成品入库，已经转至销售出库，与客户完成点单
     */
    @Override
    public int depotHeadExamin(Depothead head,Integer user) {
        if(head.getStatus()==1){
            //获取操作人姓名
            User users = userMapper.persUsersByDeptIdlist(user);
            //根据id查询出当前数据
            Depothead depothead = depotHeadMapper.getDepotHeadID(head.getId());
            depothead.setOperPersonName(users.getName());
            depotHeadMapper.updateDepotName(depothead);
            if(depothead!=null){
                //判断材料表中是否有此数据
                Materials materials = materialsMapper.queryOneMaterials(depothead.getMaterialId());
                if(materials!=null){
                    //获取子表的基础数量
                    DepotItem depotItem = depotItemMapper.queryDepotItemRecord(depothead.getMaterialId());
                    //判断单据主表的类型
                    if("成品出库".equals(depothead.getType()) || "零件出库".equals(depothead.getType())){
                        if(depotItem!=null && depotItem.getBasicNumber()>=depothead.getAmount() ){
                            //修改子表数量
                            int i = depotItem.getBasicNumber() - depothead.getAmount();
                            boolean updateAmount = depotItemMapper.updateAmount(new DepotItem(i,depothead.getMaterialId()));
                            if(updateAmount){
                                depotHeadMapper.depotHeadExamin(head);
                                depotHeadMapper.updateDateTime(new Depothead(head.getId()));
                                if("成品出库".equals(depothead.getType())){
                                    boolean b = saleMapper.updateSaleState(2,depothead.getOrganId());
                                }
                                return 1;
                            }else {
                                head.setStatus(2);
                                depotHeadMapper.depotHeadExamin(head);
                                return 5;
                            }
                        }else {
                            if("零件出库".equals(depothead.getType())){
                                //子表不存在这条数据，进行采购
                                int orderid = (int)(Math.random()*1000);
                                System.err.println(orderid);
                                Orders orders = new Orders(orderid,depothead.getOrganId());
                                int i = depotHeadMapper.addOrders(orders);
                                if(i>0){
                                    Orders order = depotHeadMapper.queryID(orderid);
                                    System.err.println(order);
                                    int price = (int)depothead.getChangeAmount();
                                    if(order!=null){
                                        Detailed detailed = new Detailed(null, order.getId(), 2,
                                                materials.getId(), depothead.getAmount(),
                                                null, price, null);
                                        System.err.println(detailed);
                                        if(depotItem!=null && depotItem.getBasicNumber()<depothead.getAmount()){
                                            detailed.setNumber(depothead.getAmount()-depotItem.getBasicNumber());
                                        }
                                        System.err.println(detailed.getNumber());
                                        int add = depotHeadMapper.addDetailed(detailed);
                                        if(add>0){
                                            head.setStatus(4);
                                            depotHeadMapper.depotHeadExamin(head);
                                            return 9;
                                        }
                                    }
                                }
                            }else if("成品出库".equals(depothead.getType())){
                                head.setStatus(3);
                                depotHeadMapper.depotHeadExamin(head);
                                return 8;
                            }
                        }
                    }else if("成品入库".equals(depothead.getType()) || "零件入库".equals(depothead.getType())){
                        if (depotItem!=null){
                            //修改子表数量
                            int i = depotItem.getBasicNumber() + depothead.getAmount();
                            boolean updateAmount = depotItemMapper.updateAmount
                                    (new DepotItem(i,depothead.getMaterialId()));
                            if(updateAmount){
                                depotHeadMapper.depotHeadExamin(head);
                                depotHeadMapper.updateDateTime(new Depothead(head.getId()));
                                //判断是否时成品入库，根据单据主表的销售id修改销售申请表状态
                                if("成品入库".equals(depothead.getType())){
                                    //修改生产组装状态
                                    Assemble assemble = assembleMapper.getAssembleID(depothead.getOrganId());
                                    if(assemble!=null){
                                        assembleMapper.AssemSh(assemble.getId(),3);
                                    }
                                    int number = saleMapper.getNumber(depothead.getOrganId());
                                    boolean update = depotItemMapper.updateAmount(new DepotItem((depotItem.getBasicNumber() - number), depotItem.getMaterialId()));
                                    boolean b = saleMapper.updateSaleState(2,depothead.getOrganId());
                                    if(update && b){
                                        return 10;
                                    }
                                }
                                return 2;
                            }else {
                                head.setStatus(2);
                                depotHeadMapper.depotHeadExamin(head);
                                return 5;
                            }
                        }else {
                            //子表不存在这条数据，进行新增
                            int t = materials.getMType().equals("材料")?2:1;
                            DepotItem item = new DepotItem(null, head.getId(), depothead.getMaterialId(), depothead.getAmount(),
                                    depothead.getChangeAmount(), null, null, t, "0");
                            boolean addDepotItem = depotItemMapper.addDepotItem(item);
                            if(addDepotItem){
                                depotHeadMapper.depotHeadExamin(head);
                                depotHeadMapper.updateDateTime(new Depothead(head.getId()));
                                if("成品入库".equals(depothead.getType())){
                                    //修改生产组装状态
                                    Assemble assemble = assembleMapper.getAssembleID(depothead.getOrganId());
                                    if(assemble!=null){
                                        assembleMapper.AssemSh(assemble.getId(),3);
                                    }
                                    int number = saleMapper.getNumber(depothead.getOrganId());
                                    boolean update = depotItemMapper.updateAmount(new DepotItem((depotItem.getBasicNumber() - number), depotItem.getMaterialId()));
                                    boolean b = saleMapper.updateSaleState(2,depothead.getOrganId());
                                    if(update && b){
                                        return 10;
                                    }
                                }
                                return 2;
                            }
                        }

                    }
                }else {
                    return 3;
                }
            }
        }else if (head.getStatus()==2){
            depotHeadMapper.depotHeadExamin(head);
            return 2;
        }
        return 0;
    }

    /**
     *  标记删除
     * @param depothead 1 删除 0 未删除 默认 0
     * @return
     */
    @Override
    public boolean deleteDepotHead(Depothead depothead) {
        return depotHeadMapper.deleteDepotHead(depothead);
    }

    @Override
    public List<HashMap> queryHead() {
        return depotHeadMapper.queryHead();
    }

    @Override
    public boolean updateSaleId(Integer organId, Integer id) {
        return depotHeadMapper.updateSaleId(organId, id);
    }
}

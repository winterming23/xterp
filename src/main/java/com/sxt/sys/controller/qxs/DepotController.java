package com.sxt.sys.controller.qxs;

import com.sxt.sys.domain.qxs.warehouse.Depot;
import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.service.qxs.warehouse.DepotHeadServiceI;
import com.sxt.sys.service.qxs.warehouse.DepotItemServiceI;
import com.sxt.sys.service.qxs.warehouse.DepotServiceI;
import com.sxt.sys.service.qxs.warehouse.MaterialsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @program: xterp
 * @description: 仓库视图操作
 * @author: snow
 * @create: 2019-12-16 08:49
 **/
@Controller
@RequestMapping("depot/")
public class DepotController {

    @Autowired
    private DepotServiceI depotServiceI;
    @Autowired
    private DepotHeadServiceI depotHeadServiceI;
    @Autowired
    private DepotItemServiceI depotItemServiceI;
    @Autowired
    private MaterialsServiceI materialsServiceI;

    /**
     * 跳转至仓库主页
     * @return
     */
    @RequestMapping("pageDepot")
    public String pageDepot(Model model){
        //查询未删除的仓库
        List<Depot> depots = depotServiceI.queryNotDeleteDepot();
        //HashMap<Integer, Depot> map = new HashMap<Integer, Depot>();
        /*System.out.println(depots.size());
        for (int i=0;i<depots.size();i++){
            map.put(i,depots.get(i));
        }*/
        model.addAttribute("depots",depots);
        //查询所有单据子表
        List<HashMap> depotItems = depotItemServiceI.queryAllDepotItem();
        model.addAttribute("depotItems",depotItems);
        //查询所有材料类别
        List<Materials> materials = materialsServiceI.queryAllMaterials();
        model.addAttribute("materials",materials);
        //查询所有单据
        List<Depothead> depotHeads = depotHeadServiceI.queryAllDepotHead();
        model.addAttribute("depotHeads",depotHeads);
        return "system/qxs/warehouse/depotMain";
    }

    /**
     * 文件上传
     * @param file
     * @param id
     * @return
     */
    @RequestMapping("itemFile")
    public String itemFile(HttpServletRequest request, MultipartFile file, Integer id, Model model) throws IOException {
        System.err.println(id);
        if(file.isEmpty()){
            model.addAttribute("errs","文件上传失败");
           return "redirect:/depot/pageDepot";
        }
        //获取文件名
        String img = file.getOriginalFilename();
        String suffixName=img.substring(img.lastIndexOf("."));//得到后缀名
        //设置路径
        String filePath = "/src/main/resources/static/img/";
        img = UUID.randomUUID()+suffixName;
        File path = new File(filePath);
        if(!path.getParentFile().exists()){
            path.getParentFile().mkdirs();
        }
        System.out.println(path.getPath()+"保存路径。。。。。。。。。。。。。");
        new File(path,img);
        //FileUtils.copyInputStreamToFile(file.getInputStream(),new File(path+img));
        boolean b = depotItemServiceI.updateImg(img, id);
        if(b){
            return "redirect:/depot/pageDepot";
        }
        return "error/404";
    }

    /**
     * 标记删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteItem")
    public boolean deleteItem(Integer id){
        return depotItemServiceI.deleteFlagDepotItem("1",id);
    }

    /**
     * 新增材料类别
     * @param materials
     * @return
     */
    @ResponseBody
    @RequestMapping("addMaterials")
    public boolean addMaterials(Materials materials){
        return materialsServiceI.addMaterials(materials);
    }

    /**
     * 单据主表审核
     * @param id
     * @param state
     * @return
     */
    @ResponseBody
    @RequestMapping("headState")
    public int stateHead(Integer id,Integer state){
        int i = depotHeadServiceI.depotHeadExamin(state, id);
        return i;
    }

    /**
     * 单据主表 标记删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteHead")
    public boolean deleteHead(Integer id){
        return depotHeadServiceI.deleteDepotHead("1",id);
    }

    /**
     * 报表查询
     * @return
     */
    @ResponseBody
    @RequestMapping("queryHead")
    public List queryHead(){
        return depotHeadServiceI.queryHead();
    }
}

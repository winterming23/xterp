package com.sxt.sys.mapper.vin;


import com.sxt.sys.domain.vin.Supplier;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SupplierMapper {
    /**
     * 查询所有供应商
     * @return
     */
    @Select("SELECT * from supplier where delete_Flag = '0' and type = '供应商'")
    public List<Supplier> getAllSupplierG();

    /**
     * 查询所有客户
     * @return
     */
    @Select("SELECT * from supplier where delete_Flag = '0' and type = '客户'")
    public List<Supplier> getAllSupplierK();

    /**
     * 修改供应商删除标记（删除）
     * @param id
     * @return
     */
    @Update("UPDATE supplier set delete_Flag = '1' where id = #{id}")
    public boolean deleteSupplier(long id);

    /**
     * 添加供应商信息
     * @param supplier
     * @return
     */
    @Insert("INSERT INTO `supplier` (supplier,contacts,phonenum,email,type,enabled,advanceIn,beginNeedGet,beginNeedPay,allNeedGet,allNeedPay,fax,telephone,address,taxNum,bankName,accountNumber,taxRate,delete_Flag,status)" +
            " VALUES (#{supplier}, #{contacts}, #{phonenum},#{email}, '供应商',1, #{advanceIn}, #{beginNeedGet}, #{beginNeedPay}, #{allNeedGet}, #{allNeedPay}, #{fax}, #{telephone}, #{address}, #{taxNum}, #{bankName}, #{accountNumber}, #{taxRate}, '0','0')")
    public boolean insertSupplierG(Supplier supplier);

    /**
     * 添加客户信息
     * @param supplier
     * @return
     */
    @Insert("INSERT INTO `supplier` (supplier,contacts,phonenum,email,type,enabled,advanceIn,beginNeedGet,beginNeedPay,allNeedGet,allNeedPay,fax,telephone,address,taxNum,bankName,accountNumber,taxRate,delete_Flag,status)" +
            " VALUES (#{supplier}, #{contacts}, #{phonenum},#{email}, '客户',1, #{advanceIn}, #{beginNeedGet}, #{beginNeedPay}, #{allNeedGet}, #{allNeedPay}, #{fax}, #{telephone}, #{address}, #{taxNum}, #{bankName}, #{accountNumber}, #{taxRate}, '0','0')")
    public boolean insertSupplierK(Supplier supplier);

    /**
     * 修改供应商信息
     * @param supplier
     * @return
     */
    @Update("UPDATE supplier SET supplier=#{supplier},contacts=#{contacts},phonenum= #{phonenum},email=#{email},advanceIn=#{advanceIn}," +
            " beginNeedGet=#{beginNeedGet},beginNeedPay=#{beginNeedPay},allNeedGet=#{allNeedGet},allNeedPay=#{allNeedPay},fax=#{fax},telephone=#{telephone},address= #{address}," +
            " taxNum=#{taxNum},bankName=#{bankName},accountNumber=#{accountNumber},taxRate=#{taxRate} where id=#{id}")
    public boolean updateSupplier(Supplier supplier);

    /**
     * 修改审核状态
     * @param id
     * @return
     */
    @Update("UPDATE supplier SET `status` = 1 where id = #{id}")
    public boolean updateSupplierStatus(long id);

    /**
     * 根据供应商id查询
     * @param id
     * @return
     */
    @Select("select * from supplier where id = #{id}")
    public Supplier queryBySupplierId(long id);

    /**
     * 修改供应商状态为启用
     * @param id
     * @return
     */
    @Update("UPDATE supplier SET enabled = 1 where id = #{id}")
    public boolean updateSupplierEnabledT(long id);

    /**
     * 修改供应商状态为禁用
     * @param id
     * @return
     */
    @Update("UPDATE supplier SET enabled = 0 where id = #{id}")
    public boolean updateSupplierEnabledF(long id);
}

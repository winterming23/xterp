package com.sxt.sys.domain.vin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 用户操作日志
 */
public class Log {
    private long id;//主键
    private long userID;//操作用户ID
    private String operation;//操作模块名称
    private String clientIP;//客户端IP
    private Data createtime;//创建时间
    private int status;
    private String contentdetails;//操作状态（ 0==成功，1==失败 ）
    private String remark;//备注信息
}

package com.sxt.sys.mapper.zqw;

import com.sxt.sys.domain.zqw.Assemble;

/**
 * 组装持久层操作接口
 */
public interface AssembleMapper {
    /**
     * 添加组装信息
     * @param assemble
     * @return
     */
    boolean inserAssemble(Assemble assemble);
}

package com.sxt.sys.service.zqw.impl;


import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.zqw.Assemble;
import com.sxt.sys.mapper.zqw.AssembleMapper;
import com.sxt.sys.service.zqw.AssembleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AssembleServiceImpl implements AssembleServiceI {
    @Autowired
    private AssembleMapper assembleMapper;

    @Override
    public boolean inserAssemble(Assemble assemble) {
        return assembleMapper.inserAssemble(assemble);
    }

    @Override
    public List<HashMap> seleAssem() {
        return assembleMapper.seleAssem();
    }

    @Override
    public List<HashMap> seledepothead(int id) {
        return assembleMapper.seledepothead(id);
    }

    @Override
    public List<HashMap> seleProi(int id) {
        return assembleMapper.seleProi(id);
    }

    @Override
    public List<HashMap> seleDep(int id) {
        return assembleMapper.seleDep(id);
    }

    @Override
    public boolean AssemSh(int id, int qualityTesting) {
        return assembleMapper.AssemSh(id,qualityTesting);
    }

    /**
     * 入库
     * @param depothead
     * @return
     */
    @Override
    public boolean inserDepths(Depothead depothead) {
        return  assembleMapper.inserDepths(depothead);
    }
}

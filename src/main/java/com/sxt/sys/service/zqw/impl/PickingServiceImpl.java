package com.sxt.sys.service.zqw.impl;

import com.sxt.sys.domain.zqw.Picking;
import com.sxt.sys.mapper.zqw.PickingMapper;
import com.sxt.sys.service.zqw.PickingServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 领料业务实现类
 */
@Service
public class PickingServiceImpl implements PickingServiceI {
    @Autowired
    private PickingMapper pickingMapper;
    @Override
    public List<HashMap> selePicking() {
        return pickingMapper.selePicking();
    }

    @Override
    public boolean inserPicking(Picking picking) {
        return pickingMapper.inserPicking(picking);
    }

    @Override
    public boolean updatePicking(Picking picking) {
        return pickingMapper.updatePicking(picking);
    }

    @Override
    public boolean deletePicking(int id, int deletePick) {
        return pickingMapper.deletePicking(id,deletePick);
    }
}

package com.zoc.furns.service.impl;

import com.zoc.furns.dao.FurnDAO;
import com.zoc.furns.dao.impl.FurnDAOImpl;
import com.zoc.furns.entity.Furn;
import com.zoc.furns.service.IFurnService;

import java.util.List;

public class FurnServiceImpl implements IFurnService {

    private FurnDAO furnDAO = new FurnDAOImpl();

    @Override
    public List<Furn> queryFurns() {
        return furnDAO.queryFurns();
    }

    @Override
    public boolean addFurn(Furn furn) {
        return furnDAO.addFurn(furn);
    }

    @Override
    public int delFurn(Furn furn) {
        return furnDAO.delFurn(furn);
    }
}

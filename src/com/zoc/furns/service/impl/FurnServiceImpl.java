package com.zoc.furns.service.impl;

import com.zoc.furns.dao.FurnDAO;
import com.zoc.furns.dao.impl.FurnDAOImpl;
import com.zoc.furns.entity.Furn;
import com.zoc.furns.entity.Page;
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
    public int delFurn(int id) {
        return furnDAO.delFurn(id);
    }

    @Override
    public Furn showFurn(int id) {
        return furnDAO.queryFurn(id);
    }

    @Override
    public int updateFurn(Furn furn) {
        return furnDAO.updateFurn(furn);
    }

    @Override
    public Page<Furn> page(int pageNo,int pageSize) {

        Page<Furn> page = new Page<>();

        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int totalRow = furnDAO.getTotalRow();
        page.setTotalRow(totalRow);
        int pageTotalCount = totalRow / pageSize;
        if (totalRow % pageSize > 0) {
            pageTotalCount += 1;
        }
        page.setPageTotalCount(pageTotalCount);

        // 首先理清楚pageNo和begin的关系
        int begin = (pageNo - 1)*pageSize;
        List<Furn> pageItems = furnDAO.getPageItems(begin, pageSize);
        page.setItems(pageItems);
        return page;
    }
}

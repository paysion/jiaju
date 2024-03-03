package com.zoc.furns.service;

import com.zoc.furns.entity.Furn;
import com.zoc.furns.entity.Page;

import java.util.List;

public interface IFurnService {

    public List<Furn> queryFurns();

    public boolean addFurn(Furn furn);

    public int delFurn(int id);

    /**
     * 根据id查询furn
     * @param id
     * @return
     */
    public Furn showFurn(int id);

    /**
     * 根据id修改furn
     */
    public int updateFurn(Furn furn);

    // public int getTotalRow();

    public Page<Furn> page(int pageNo,int pageSize);

    public Page<Furn> pageByName(int pageNo,int pageSize,String name);
}

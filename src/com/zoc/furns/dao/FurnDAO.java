package com.zoc.furns.dao;

import com.zoc.furns.entity.Furn;

import java.util.List;

public interface FurnDAO {

    public List<Furn> queryFurns();

    public boolean addFurn(Furn furn);

    public int delFurn(int id);

    /**
     * 前端点击修改，发送furn的id过来
     * 查询单个Furn并回显，用于修改操作
     * @return
     */
    public Furn queryFurn(int id);

    /**
     * 更新furn
     * @param furn
     * @return
     */
    public int updateFurn(Furn furn);
}

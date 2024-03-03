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

    /**
     * 获取数据库中总记录数
     * @return
     */
    public int getTotalRow();

    /**
     * 获取当前页的Furn
     * @return
     */
    public List<Furn> getPageItems(int begin,int pageSize);

    /**
     * 根据名字获取数据库中总记录数
     * @return
     */
    public int getTotalRowByName(String name);

    /**
     * 根据名字获取当前页的Furn
     * @return
     */
    public List<Furn> getPageItemsByName(int begin,int pageSize,String name);
}

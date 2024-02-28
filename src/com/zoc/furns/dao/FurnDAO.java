package com.zoc.furns.dao;

import com.zoc.furns.entity.Furn;

import java.util.List;

public interface FurnDAO {

    public List<Furn> queryFurns();

    public boolean addFurn(Furn furn);

    public int delFurn(Furn furn);
}

package com.zoc.furns.service;

import com.zoc.furns.entity.Furn;

import java.util.List;

public interface IFurnService {

    public List<Furn> queryFurns();

    public boolean addFurn(Furn furn);

    public int delFurn(Furn furn);
}

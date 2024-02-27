package com.zoc.furns.test;

import com.zoc.furns.dao.FurnDAO;
import com.zoc.furns.dao.impl.FurnDAOImpl;
import com.zoc.furns.entity.Furn;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FurnDAOTest {

    private FurnDAO furnDAO = new FurnDAOImpl();

    @Test
    public void queryFurns(){
        List<Furn> furns = furnDAO.queryFurns();
        for (Furn furn : furns) {
            System.out.println(furn);
        }
    }
}

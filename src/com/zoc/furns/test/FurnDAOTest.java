package com.zoc.furns.test;

import com.zoc.furns.dao.FurnDAO;
import com.zoc.furns.dao.impl.FurnDAOImpl;
import org.junit.jupiter.api.Test;

public class FurnDAOTest {

    private FurnDAO furnDAO = new FurnDAOImpl();

    @Test
    public void queryFurns(){
        System.out.println(furnDAO.queryFurns());
    }
}

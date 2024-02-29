package com.zoc.furns.test;

import com.zoc.furns.dao.FurnDAO;
import com.zoc.furns.dao.impl.FurnDAOImpl;
import com.zoc.furns.entity.Furn;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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

    @Test
    public void addFurnTest(){
        Furn furn = new Furn(null,"zzz","xxx",new BigDecimal("120.00"),222,333," ");
        if (furnDAO.addFurn(furn)) {
            System.out.println("添加furn:" + furn + "成功");
        } else {
            System.out.println("添加失败");
        }
    }

    @Test
    public void delFurnTest(){
        Furn furn = new Furn(8,"zzz","xxx",new BigDecimal("120.00"),222,333," ");
        if (furnDAO.delFurn(8) > 0) {
            System.out.println("删除 " + furn + " 成功");
        } else {
            System.out.println("删除失败");
        }
    }

    @Test
    public void queryFurn(){
        Furn furn = furnDAO.queryFurn(11);
        if ("".equals(furn) || furn==null) {
            System.out.println("查询失败~");
        } else {
            System.out.println(furn);
        }
    }
}

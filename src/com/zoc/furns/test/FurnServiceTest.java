package com.zoc.furns.test;

import com.zoc.furns.entity.Furn;
import com.zoc.furns.entity.Page;
import com.zoc.furns.service.IFurnService;
import com.zoc.furns.service.impl.FurnServiceImpl;
import org.junit.jupiter.api.Test;

public class FurnServiceTest {

    private IFurnService furnService = new FurnServiceImpl();

    @Test
    public void pageByName(){
        Page<Furn> furns = furnService.pageByName(1, 3, "桌子");
        for (Furn furn : furns.getItems()) {
            System.out.println(furn);
        }
    }
}

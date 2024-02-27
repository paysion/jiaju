package com.zoc.furns.web;

import com.zoc.furns.service.IFurnService;
import com.zoc.furns.service.impl.FurnServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FurnServlet extends BasicServlet{

    private IFurnService furnService = new FurnServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FurnServlet被成功调用。。。");
    }
}

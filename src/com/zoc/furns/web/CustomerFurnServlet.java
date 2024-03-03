package com.zoc.furns.web;

import com.zoc.furns.entity.Furn;
import com.zoc.furns.entity.Page;
import com.zoc.furns.service.IFurnService;
import com.zoc.furns.service.impl.FurnServiceImpl;
import com.zoc.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerFurnServlet extends BasicServlet{

    private IFurnService furnService = new FurnServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 这里业务跟furn_manage那块很相似，区别是？区别就是转发的页面不一样
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2.获取pageNo的items
        Page<Furn> page = furnService.page(pageNo, pageSize);
        // 将items放入req的域中
        req.setAttribute("page",page);
        // 请求转发到index.jsp
        req.getRequestDispatcher("/views/customer/index.jsp").forward(req, resp);
    }

}

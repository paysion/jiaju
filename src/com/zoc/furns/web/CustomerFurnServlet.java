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


    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.接收前端传递的pageNo、pageSize 和 name
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 这里漏了对name要进行非空判断
        String name = req.getParameter("name");
        if (null == name) {
            name = "";
        }
        // 2.调用furnService查询page对象
        Page<Furn> page = furnService.pageByName(pageNo, pageSize, name);
        // 构建url，便于将搜索条件name返回给前端
        StringBuilder url = new StringBuilder("customerFurnServlet?action=pageByName");
        if (!"".equals(name)) {
            url.append("&name=").append(name);
        }
        page.setUrl(url.toString());
        // 3.将page对象放入req的域中
        req.setAttribute("page",page);
        // 4.请求转发 （需要修改表单的就请求重定向，不需要修改表单的就请求转发？ 为什么不用重定向？因为重定向会丢失req的域
        req.getRequestDispatcher("/views/customer/index.jsp").forward(req, resp);
    }
}

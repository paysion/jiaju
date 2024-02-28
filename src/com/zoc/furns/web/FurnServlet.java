package com.zoc.furns.web;

import com.zoc.furns.entity.Furn;
import com.zoc.furns.service.IFurnService;
import com.zoc.furns.service.impl.FurnServiceImpl;
import com.zoc.furns.utils.DataUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FurnServlet extends BasicServlet{

    private IFurnService furnService = new FurnServiceImpl();


    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String name = req.getParameter("name");
//        String maker = req.getParameter("maker");
//        String price = req.getParameter("price");
//        String sales = req.getParameter("sales");
//        // 对sales做数据校验
//        try {
//            int i = Integer.parseInt(sales);
//        } catch (NumberFormatException e) {
//            req.setAttribute("mes", "销量数据格式不对...");
//            // 返回到furn_add.jsp
//            req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req,resp);
//            return;
//        }
//        String stock = req.getParameter("stock");
//        Furn furn = new Furn();
//        try {
//            BeanUtils.populate(furn,req.getParameterMap());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Furn furn = DataUtils.copyParamToBean(req.getParameterMap(), new Furn());
        furnService.addFurn(furn);
        resp.sendRedirect(req.getContextPath()+"/manage/furnServlet?action=list");
    }


    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 使用DataUtils工具获得相应的furn对象
        Furn furn = DataUtils.copyParamToBean(req.getParameterMap(), new Furn());
        furnService.delFurn(furn);
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req,resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Furn> furns = furnService.queryFurns();
        // 把furns集合放入request域
        req.setAttribute("furns",furns);
        // 请求转发
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req,resp);
    }
}

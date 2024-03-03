package com.zoc.furns.web;

import com.zoc.furns.entity.Furn;
import com.zoc.furns.entity.Page;
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
        resp.sendRedirect(req.getContextPath()+"/manage/furnServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }


    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 前端传递furn对象,使用DateUtils接收
        Furn furn = DataUtils.copyParamToBean(req.getParameterMap(), new Furn());
        furnService.updateFurn(furn);
        // 不管，直接重定向
        // resp.sendRedirect(req.getContextPath()+"/manage/furnServlet?action=list");
        // 1.前端点击修改，除了传递furn对象，还应该带上pageNo，而且重定向的页面也不应该是list，而是page
        resp.sendRedirect(req.getContextPath()+"/manage/furnServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        // 使用DataUtils工具获得相应的furn对象,删除时候前端传过来的文本好处理，对象处理不了
        // Furn furn = DataUtils.copyParamToBean(req.getParameterMap(), new Furn());
        furnService.delFurn(id);
        // req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req,resp);
        resp.sendRedirect(req.getContextPath()+"/manage/furnServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }


    protected void showFurn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.前端后发送一个id到后端,并用DataUtils处理为int类型
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        // 2.根据id从DB查询对应的Furn
        Furn furn = furnService.showFurn(id);
        // 3.将furn放进req的请求域中
        req.setAttribute("furn",furn);
        // 4.请求转到到furn_update页面
        req.getRequestDispatcher("/views/manage/furn_update.jsp").forward(req,resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.从前端接收pageNo和pageSize
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2.获取pageNo的items
        Page<Furn> page = furnService.page(pageNo, pageSize);
        // 将items放入req的域中
        req.setAttribute("page",page);
        // 请求转发
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

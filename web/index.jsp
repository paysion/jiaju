<%--
  Created by IntelliJ IDEA.
  User: zoc
  Date: 2024/2/21
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--直接请求CustomerFurnServlet, 获取网站首页要显示的分页数据
 类似我们网站的入口页面
--%>
<jsp:forward page="/customerFurnServlet?action=page&pageNo=1"></jsp:forward>

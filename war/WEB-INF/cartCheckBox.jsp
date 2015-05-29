<%-- 
    Document   : cartCheckBox
    Created on : 2013/12/16, 10:52:00
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
    <div align="center">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>カートの商品は以下の通りです。</h1>
    <tr>
        <th align="center">注文商品</th>
        <th align="center">価格</th>
    </tr>
    <tr>
    
        <table class="menu">
            <c:forEach items="${data}" var="rec">
                <tr>
                    <td>${rec.id}</td>
                    <td>${rec.name}</td>
                    <td class="privce">${rec.price}円</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <tr>合計金額　￥${total}</tr>
        <br>
        <a href="thankyou.jsp">この内容で注文する</a>
        <br>
        <a href="reset.do">もう一度選びなおす</a>
    
</body>
</html>
</html>

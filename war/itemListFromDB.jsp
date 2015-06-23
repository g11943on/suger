<%-- 
    Document   : itemListFromDB
    Created on : 2013/12/13, 14:06:28
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>                        
<html>
    <head>                                            
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
           <h1>Suger Pop - 商品一覧</h1>
        <div align="center">

      <h2>${user}さん、お好きなケーキを選んでください</h2>


       <form action="cart.do" method="get" class="cart">
          <table class="menu">
              <c:forEach items="${data}" var="rec">
                  <tr>
                      <td><input type="checkbox" value=${rec.id} name="shohinid" /></td>
                      <td>${rec.id}</td>
                      <td>${rec.name}</td>
                      <td class="price">${rec.price} 円</td>
                  </tr>
              </c:forEach>

          </table>
          <input type="submit" name="cart.do" value="カートの内容を確認して注文へ進む" />
      </form>



    <form action="logout.do" method="post">
        <input type="submit" name="logout" value="ログアウト" />
    </form>
    </body>
</html>

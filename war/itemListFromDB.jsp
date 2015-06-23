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
			<tr>
      			<td>商品の発送先</td>
      			<td><input type="text" name="user" size="16" value="" /></td>
    		</tr>
    		<tr>
      			<td>商品の到着日時</td>
				<td><select name="sample1">
				  <option value="">選択してください</option>
				  <option value="1">午前中</option>
				  <option value="2">12-14時</option>
				  <option value="3">14-16時</option>
				  <option value="4">16-18時</option>
				  <option value="5">18-21時</option>
				</select></td>
    		</tr>
          </table>
          <input type="submit" name="cart.do" value="カートの内容を確認して注文へ進む" />
      </form>



    <form action="logout.do" method="post">
        <input type="submit" name="logout" value="ログアウト" />
    </form>
    </body>
</html>

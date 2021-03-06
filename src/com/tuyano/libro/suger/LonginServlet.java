/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyano.libro.suger;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 
 */
public class LonginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        boolean found = false;
        
        try {
            // リクエストパラメータから入力されたパスワードを取り出し
            String password = request.getParameter("password");
            String user = request.getParameter("user");
            
            // データベースに接続
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String driverURL = "jdbc:derby://localhost:1527/shohin";
            Connection con = DriverManager.getConnection(driverURL, "db","db");
            //con = jdbctest.getConnection(); //connection pooling を使う場合
            Statement stmt = con.createStatement();
            
           //データベースに入力されたユーザ名とパスワードに一致するレコードがあるかを問い合わせる
            String sql = "select * from U_USER where USER_NAME=? and PASSWORD=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            // 一件でもレコードがあれば、ユーザ名とパスワードが正しく入力された。
            if(rs.next()) {
                found = true;
            }

            String nextJsp;
            if (!found) {
                // パスワードが正しくない場合、セッションを無効にする。
                session.invalidate();
                // ログイン失敗ページへ移動。
                nextJsp = "/loginFailed.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
                dispatcher.forward(request, response);
            } else {
                // パスワードが正しい場合          
                // ユーザ名をセッション変数に格納する
                session.setAttribute("user", user);
                // データベースに商品についての問い合わせを行う
                sql = "select * from U_SHOHIN";
                rs = stmt.executeQuery(sql);
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                while (rs.next()) {
                    Map<String, Object> record = new HashMap<String, Object>();
                    record.put("id", new Integer(rs.getInt("SHOHIN_ID")));
                    record.put("name", rs.getString("SHOHIN_NAME"));
                    record.put("price", new Integer(rs.getInt("PRICE")));
                    list.add(record);
                }
                // データベースの後始末。
                rs.close();
                stmt.close();
                con.close();
                // 商品名のリストを リクエスト変数　data にしまう。
                request.setAttribute("data", list);

                // 次のページに移動
                //RequestDispatcher rd = request.getRequestDispatcher("/loginSuccess.jsp"); 
                RequestDispatcher rd = request.getRequestDispatcher("/itemListFromDB.jsp");
                rd.forward(request, response);

            }  //end else

        } catch(Exception e){
            throw new ServletException(e);
        } 
        
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

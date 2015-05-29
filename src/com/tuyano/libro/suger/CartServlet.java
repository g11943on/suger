/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyano.libro.suger;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class CartServlet extends HttpServlet {

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
        //PrintWriter out = response.getWriter();
        
        // Check Box の情報をとりだす。
        HttpSession session = request.getSession();     
        String[] strVals = request.getParameterValues("shohinid");
        int numChecks = 0;
        if (strVals != null) {
            numChecks = strVals.length; // チェックの個数
        } else { // 何もチェックされていない。
            numChecks = 0;
        }

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String driverURL = "jdbc:derby://localhost:1527/shohin";
            Connection con = DriverManager.getConnection(driverURL, "db", "db");
            // Connection con = jdbctest.getConnection();
                    java.sql.Statement stmt = con.createStatement();

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            int total = 0;
                       
            // Checkbox の (name: shohinid)と結び付けられた value の並びをとりだす。
            for (int i = 0; i < numChecks; i++) {
                //  i番目のshohinid 項目の value をとりだす。
                int idFromCB = Integer.parseInt(strVals[i]);
              
                // その商品の情報（名前、価格）をDBから取り出す。合計金額も計算する。
                String sql = "select * from U_SHOHIN where SHOHIN_ID ="
                        + idFromCB;
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    Map<String, Object> record = new HashMap<String, Object>();
                    record.put("id", new Integer(rs.getInt("SHOHIN_ID")));
                    record.put("name", rs.getString("SHOHIN_NAME"));
                    record.put("price", new Integer(rs.getInt("PRICE")));
                    total = total + rs.getInt("PRICE");
                    list.add(record);
                } 
                
                rs.close();
            }//end for (int i チェックされた商品についての繰り返し。
            stmt.close();
            con.close();
           
            // 表示のため、結果をリクエスト変数にしまう。
           request.setAttribute("count", numChecks);
           request.setAttribute("data", list);
           request.setAttribute("total", total);

            RequestDispatcher rd = request.getRequestDispatcher("/cartCheckBox.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
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

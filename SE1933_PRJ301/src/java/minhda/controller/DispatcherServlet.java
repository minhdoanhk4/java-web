/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package minhda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author msi2k
 */
@WebServlet(name="DispatcherServlet", urlPatterns={"/DispatcherServlet"})
public class DispatcherServlet extends HttpServlet {
   private final String LOGIN_PAGE = "login.html";
   private final String LOGIN_CONTROLLER = "LoginServlet";
   private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
   private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
   private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE; // default page
        
        
        // 1. which button did user click?
        String button = request.getParameter("btAction");
        
        try {
            if (button == null) {
                // do nothing
            }else { //first request
                switch (button) {
                    case "Login":
                        url = LOGIN_CONTROLLER;
                        break;
                        
                    case "Search":
                        url = SEARCH_LASTNAME_CONTROLLER;
                        break;
                        
                    case "delete":
                        url = DELETE_ACCOUNT_CONTROLLER;
                        break;
                        
                    case "Update":
                        url = UPDATE_ACCOUNT_CONTROLLER;
                        break;
                }
            }//end proccess function
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

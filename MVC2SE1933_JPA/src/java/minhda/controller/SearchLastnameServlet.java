/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package minhda.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhda.jpa.registration.Registration;
import minhda.jpa.registration.RegistrationBLO;

/**
 *
 * @author msi2k
 */
@WebServlet(name="SearchLastnameServlet", urlPatterns={"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {
   private final String SEARCH_PAGE = "search.html";
   private final String SEARCH_RESULT = "search.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SEARCH_PAGE;
        //1. get all user's info 
        String searchValue = request.getParameter("txtSearchValue");
        try {
            if (searchValue.trim().length()>0) {
            //2. call method of controller
            //2.1. controller create new DAO obj
                RegistrationBLO blo = new RegistrationBLO();
            //2.2. controller call method of DAO obj
            List<Registration> result = blo.searchLastname(searchValue);
            //3. controller proccess result \
//            List<Registration> result = blo.getAccounts();
            request.setAttribute("SEARCH_RESULT", result);
            url = SEARCH_RESULT;
            }//when search is valid input
            
//        }catch (SQLException ex){
//            ex.printStackTrace();
//        }catch (ClassNotFoundException ex){
//            ex.printStackTrace();
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

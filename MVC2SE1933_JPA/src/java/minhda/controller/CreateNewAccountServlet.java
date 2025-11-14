/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package minhda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhda.registration.RegistrationCreateError;
import minhda.registration.RegistrationDAO;
import minhda.registration.RegistrationDTO;

/**
 *
 * @author msi2k
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
        private final String ERROR_PAGE = "createAccount.jsp";
        private final String LOGIN_PAGE = "login.html";


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        //1. get all request parameter
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");

        boolean foundErr = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        try {
            //2. verify all error of user
            if (username.trim().length() < 6
                    || username.trim().length() > 20) {
                foundErr = true;
                errors.setUsernameLenghtErr("Username is required typing from 6 to 20 chars");
            }
            if (password.trim().length() < 6
                    || password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLenghtErr("Password is required typing from 6 to 30 chars");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }
            if (fullName.trim().length() < 2
                    || fullName.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLenghtErr("Full name is required typing from 2 to 50 chars");
            }
            if (foundErr) {
                //catch errors to attribute of request scope
                request.setAttribute("CREATE_ERRORS", errors);
            } else {//no errors
                //3. controller call method of model
                //3.1. controller create new dao obj
                RegistrationDAO dao = new RegistrationDAO();
                //3.2. controller call method of dao obj
                RegistrationDTO account = new RegistrationDTO(username, password,  fullName, false);
                boolean result = dao.createAccount(account);
                //4. controller process result
                if (result) {
                    url = LOGIN_PAGE;
                }
            }
        }catch (ClassNotFoundException ex){
            log("CreateNewAccountServlet _ ClassNotFound" + ex.getMessage());
        }catch (SQLException ex){
            String errMsg = ex.getMessage();
            log("CreateNewAccountServlet _ SQL" + ex.getMessage());
            if (errMsg.contains("duplicate")) {
                errors.setUsernameIsExitErr(username + "is EXITED");
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd .forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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

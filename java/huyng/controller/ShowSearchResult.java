/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyng.controller;

import huyng.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class ShowSearchResult extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Show Search Result</title>");
            out.println("<style>table{border: 1px solid black;}"
                    + "th, td{border: 1px solid black; padding:2px;}</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowSearchResult at " + request.getContextPath() + "</h1>");
            out.println("<h1>Search Result</h1>");
            List<RegistrationDTO> list = (List<RegistrationDTO>) request.getAttribute("SEARCHRESULT");
            if (list != null) {
                out.println("<table><thead><tr>");
                out.println("<th>No.</th>");
                out.println("<th>Username</th>");
                out.println("<th>Password</th>");
                out.println("<th>LastName</th>");
                out.println("<th>Is admin</th>");
                out.println("<th>Action</th>");
                out.println("<th>Update</th>");
                out.println("</tr></thead><tbody>");
                
                int counter = 0;
                for (RegistrationDTO o : list) {
                    String urlDelete = "MainController?user=" + o.getUsername()
                            + "&btAction=Delete&lastValue=" + request.getParameter("txtSearch");
                    out.println("<form action='MainController'><tr>");
                    out.println("<td>" + ++counter + "</td>");
                    out.println("<td>" + "<input type='hidden' name='txtUser' value='" + o.getUsername() + "'/>" + o.getUsername() + "</td>");
                    out.println("<td>" + "<input type='text' name='txtPass' value='" + o.getPassword() + "'/>" +  o.getPassword() + "</td>");
                    out.println("<td>" + "<input type='text' name='txtLastName' value='" + o.getLastName()+ "'/>"+ o.getLastName() + "</td>");
                    if (o.isIsAdmin()) {
                        out.println("<td><input type='checkbox' checked='checked' name='isAdmin'/>" + o.isIsAdmin() + "</td>");
                    } else {
                        out.println("<td><input type='checkbox' name='isAdmin'/>" + o.isIsAdmin() + "</td>");
                    }
                    out.println("<td><a href='" + urlDelete + "'>delete</a></td>");
                    out.println("<input type='hidden' name='lastSearchValue' value='" + request.getParameter("txtSearchValue") + "'>");
                    out.println("<td><input type='submit' value='Update' name='btAction'></td>");
                    out.println("</tr></form>");
                }
                out.println("</tbody></table>");
                
            } else {
                out.println("<h2>No record matched</h2>");
            }
            out.println("</body>");
            out.println("</html>");
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

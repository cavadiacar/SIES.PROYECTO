/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siesjdbc.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julian_Velasco
 */
@WebServlet("/logincheck")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean authenticated = performAuthentication(email, password);

        if (authenticated) {

            response.sendRedirect("inicioExitoso.html");
        } else {

            request.setAttribute("errorEmail", "Credenciales inválidas");
            request.getRequestDispatcher("/login.html").forward(request, response);
        }
    }

    private boolean performAuthentication(String email, String password) {
        return "admin".equals(email) && "admin".equals(password);
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siesjdbc.servlets;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/save")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CrearServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del formulario de creación de persona
        String nombreApellido = request.getParameter("titulo");
        String descripcion = request.getParameter("cuerpo");
        String facultadExistente = request.getParameter("facultadExistente");
        String facultadNueva = request.getParameter("facultadNombre");

        // Obtener el archivo de imagen seleccionado
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String filePath = getUploadPath() + File.separator + fileName;
        filePart.write(filePath);

        // Aquí puedes realizar la lógica de creación de una nueva persona, guardar los datos en la base de datos, etc.

        // Redireccionar a una página de éxito o mostrar un mensaje de éxito
        response.sendRedirect("registroExitoso.html");
    }

    private String getUploadPath() throws IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        if (!Files.exists(Paths.get(uploadPath))) {
            Files.createDirectories(Paths.get(uploadPath));
        }
        return uploadPath;
    }
}
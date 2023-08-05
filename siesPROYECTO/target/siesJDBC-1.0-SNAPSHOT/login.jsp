<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Iniciar sesión</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="/static/css/styles.css" />
    <link rel="stylesheet" href="/static/css/loginStyles.css" />
    <style>
      .error-message {
        color: red;
        margin-top: 5px;
        font-size: 14px;
      }
    </style>
  </head>
  <body>
    <header id="header-navbar"></header>

    <div class="m-3">
      <form class="container-login" action="${pageContext.request.contextPath}/logincheck" method="POST">
        <h1 class="titulo-form">Iniciar sesión</h1>
        <div class="form-group">
          <div class="mb-3 mt-3">
            <input
              type="email"
              class="form-control"
              id="email"
              name="email"
              placeholder="Ingrese su email"
              required
            />
          </div>
        </div>
        <div class="form-group">
          <div class="mb-4">
            <input
              type="password"
              class="form-control"
              id="password"
              name="password"
              placeholder="Ingrese su contraseña"
              required
            />
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-10 offset-sm-2">
            <div
              id="error-email"
              class="error-message"
            ><%= request.getAttribute("errorEmail") %></div>
            <div
              id="error-password"
              class="error-message"
            ><%= request.getAttribute("errorPassword") %></div>
          </div>
        </div>
        <div class="row">
          <div class="mt-3">
            <button type="submit" class="btn btn-custom">Iniciar sesión</button>
          </div>
        </div>
      </form>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
      crossorigin="anonymous"
    ></script>
    <script src="../static/javascript/navbar.js"></script>
  </body>
</html>
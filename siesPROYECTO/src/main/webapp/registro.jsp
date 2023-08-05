<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Registro de Usuario</title>
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
      <form class="container-login" action="${pageContext.request.contextPath}/registro" method="POST">
        <h1 class="titulo-form">Registro de Usuario</h1>
        <div class="form-group">
          <div class="mb-3 mt-3">
            <input
              type="text"
              class="form-control"
              id="nombre"
              name="nombre"
              placeholder="Ingrese su nombre"
              required
            />
          </div>
          <div class="mb-3 mt-3">
            <input
              type="text"
              class="form-control"
              id="apellido"
              name="apellido"
              placeholder="Ingrese su apellido"
              required
            />
          </div>
          <div class="mb-3 mt-3">
            <label class="control-label mb-2 ms-2" for="file">Seleccione una foto de perfil:</label>
            <div class="col-sm-7 m-auto">
              <input
                type="file"
                class="form-control"
                id="file"
                name="file"
                required
              />
            </div>
          </div>
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

            <input
              type="password"
              class="form-control mt-3"
              id="password2"
              name="password2"
              placeholder="Confirme su contraseña"
              required
            />
            <div
              id="error-password2"
              class="error-message"
            ><%= request.getAttribute("errorPassword2") %></div>
          </div>
        </div>
        <div class="row">
          <div class="mt-3">
            <button type="submit" class="btn btn-custom">Registrarme</button>
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
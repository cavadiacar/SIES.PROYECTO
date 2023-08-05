<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Editar</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="/static/css/styles.css" />
  </head>
  <body>
    <header id="header-navbar"></header>
    <div class="m-3">
      <form
        class="container"
        action="${pageContext.request.contextPath}/update/${noticia.id}"
        method="post"
        enctype="multipart/form-data"
      >
        <h2>Actualizar Usuario</h2>
        <input type="hidden" name="id" value="${noticia.id}" />
        <div class="form-group">
          <label class="control-label col-sm-2 mb-2 ms-2" for="titulo"
            >Nombre Usuario:</label
          >
          <div class="col-sm-10">
            <input
              type="text"
              class="form-control"
              name="titulo"
              value="${noticia.titulo}"
              placeholder="Ingrese el nombre del usuario vinculado"
              required
              autocomplete="off"
            />
            <div id="prueba"></div>
          </div>
        </div>
        <div class="form-group mt-2">
          <label class="control-label col-sm-2 mb-2 ms-2" for="cuerpo"
            >Descripción:</label
          >
          <div class="col-sm-10">
            <textarea
              class="form-control"
              name="cuerpo"
              placeholder="Ingrese descripción, departamento y año de vinculación"
              required
              autocomplete="off"
            >
            ${noticia.cuerpo}
            </textarea>
          </div>
        </div>
        <div class="form-group text-center">
          <label class="control-label mb-2" for="descripcion">Imagen:</label>
          <div>
            <input type="file" name="file" required />
            <div id="errorArchivo" style="display: none">
              El archivo seleccionado es demasiado grande.
            </div>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2 mt-2 mb-2 ms-2" for="autorNombre"
            >Email:</label
          >
          <div class="col-sm-10">
            <input
              type="text"
              class="form-control"
              name="autor.nombre"
              value="${noticia.autor.nombre}"
              placeholder="Ingrese el email del usuario"
              required
              autocomplete="off"
            />
          </div>
        </div>
        <div class="form-group">
          <label
            class="control-label col-sm-2 mt-2 mb-2 ms-2"
            for="autorApellido"
            >Facultad:</label
          >
          <div class="col-sm-10">
            <input
              type="text"
              class="form-control"
              name="autor.apellido"
              value="${noticia.autor.apellido}"
              placeholder="Ingrese la facultad a la que pertenece"
              required
              autocomplete="off"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-sm-2 mt-3">
            <button type="submit" class="btn btn-success">Guardar</button>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Crear Persona</title>
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

    <form
      class="formulario-container"
      action="${pageContext.request.contextPath}/save"
      method="POST"
      enctype="multipart/form-data"
    >
      <h2 class="titulo-noticia-form text-center">Ingresa la nueva persona</h2>
      <div class="form-group form-create-persona">
        <div>
          <input
            type="text"
            class="form-control form-input-persona"
            name="titulo"
            placeholder="Ingrese el nombre y apellido"
            autocomplete="off"
            required
          />
        </div>
      </div>
      <div class="form-group">
        <div>
          <textarea
            class="form-control form-input-persona w-50 m-auto"
            name="cuerpo"
            placeholder="Ingrese la descripción de la persona"
            autocomplete="off"
            required
          ></textarea>
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
      <div class="autor-existente-fields">
        <label class="control-label mb-2">Facultad existente:</label>
        <select class="form-control form-input-persona" name="facultadExistente">
          <option>Seleccione una facultad</option>
          <c:forEach items="${facultad}" var="facultad">
            <option value="${facultad.id}">
              <c:out value="${facultad.nombre}" />
            </option>
          </c:forEach>
        </select>
      </div>

      <div class="form-group">
        <div>
          <label class="control-label mb-2" for="autorNombre"
            >Facultad nueva:</label
          >
          <input
            type="text"
            class="form-control form-input-noticia"
            name="facultadNombre"
            placeholder="Ingrese el nombre de la Facultad"
            autocomplete="off"
            required
          />
        </div>
      </div>
      <div class="row">
        <div class="w-50 m-auto">
          <button type="submit" class="btn btn-custom">
            <span class="glyphicon glyphicon-saved"></span> Guardar
          </button>
        </div>
      </div>
    </form>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
      crossorigin="anonymous"
    ></script>
    <script src="../static/javascript/validarPesoImagenInput.js"></script>
    <script src="../static/javascript/navbar.js"></script>
  </body>
</html>
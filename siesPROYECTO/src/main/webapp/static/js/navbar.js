document.addEventListener("DOMContentLoaded", function () {
  const agregarVavbar = () => {
    var componente = document.createElement("header");

    componente.innerHTML = `

      <nav class="navbar navbar-expand navbar-dark m-2 rounded-3">
        <div class="logo-container">
          <a class="navbar-brand logo-link" th:href="@{/}">
            Facultad
            <img src="/static/images/logoEgg.png" alt="Logo" class="logo" width=8% />
          </a>
        </div>
        <h4
          class="text-light"
        >
          Estudiantes
        </h4>
        <h4
          class="text-light"
        >
          Profesores
        </h4>
        <h4
          class="text-light"
        >
          Personal de Servicio
        </h4>
        <div class="button-container">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a
                class="text-light nav-link"
                aria-current="page"
                th:href="@{/portal}"
                sec:authorize="hasAnyRole('USER', 'ADMIN', 'PERSONAL')"
                >Inicio</a
              >
            </li>
            <li class="nav-item">
              <a
                sec:authorize="hasAnyRole('ADMIN', 'PERSONAL')"
                class="text-light nav-link"
                th:href="@{/Administración}"
                >Administración</a
              >
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle me-5"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
               

                <img
                  
                  src="/static/images/user.png"
                  width="20px"
                  alt="Imagenes"
                  class="pb-1"
                />
              </a>
              <ul class="dropdown-menu">
                <li>
                  <a
                    sec:authorize="!hasAnyRole('USER', 'ADMIN', 'PERSONAL')"
                    class="dropdown-item"
                    th:href="@{/login}"
                    >Iniciar Sesión</a
                  >
                </li>
                <li>
                  <a
                    sec:authorize="!hasAnyRole('USER', 'ADMIN', 'PERSONAL')"
                    class="dropdown-item"
                    th:href="@{/registro}"
                    >Registrarme</a
                  >
                </li>
                <li>
                  <a
                    sec:authorize="hasAnyRole('USER', 'ADMIN', 'PERSONAL')"
                    class="dropdown-item"
                    th:href="@{/perfil}"
                    >Editar Perfil</a
                  >
                </li>
                <li>
                  <a
                    sec:authorize="hasAnyRole('USER', 'ADMIN', 'PERSONAL')"
                    class="dropdown-item"
                    th:href="@{/logout}"
                    >Cerrar Sesión</a
                  >
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </nav>

`;
    return componente;
  };

  var header = document.getElementById("header-navbar");
  header.appendChild(agregarVavbar());
});

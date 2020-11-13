<div>
    <nav class="navbar navbar-expand-xl fixed-top bg-dark navbar-dark">
        <a href="${pageContext.request.contextPath}/index.jsp" class="navbar-brand">
            <i class="fas fa-dollar-sign"></i>
            El Billeton
        </a>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/gerente/cuentas/crearCuenta.jsp">Crear Cuenta</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cliente?accion=listar">Clientes</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cajero?accion=listar">Cajeros</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/LaboratoristaServlet">Gerentes</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/LaboratoristaServlet">Horarios</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/LaboratoristaServlet">Cambiar Limites</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        Reportes
                    </a>

                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/#">R1</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/#">R2</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/#">R3</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/#">R4</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/#">R5</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/#">R6</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/#">R7</a>
                    </div>
                </li>
            </ul>

            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
                        ${user.nombre}
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/user?accion=logout">Cerrar sesion</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="py-4 mb-2"></div>

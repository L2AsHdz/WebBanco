<div>
    <nav class="navbar navbar-expand-xl fixed-top bg-dark navbar-dark">
        <a href="${pageContext.request.contextPath}/index.jsp" class="navbar-brand">
            <i class="fas fa-dollar-sign"></i>
            El Billeton
        </a>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/transferencia?accion=listar">Transferir dinero</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cliente/asociar.jsp">Asociar cuenta</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/solicitud?accion=listar">Solicitudes pendientes</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        Reportes
                    </a>

                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ReportesCliente?accion=reporte1">Ultimas 15 transacciones mas grandes del ultimo año</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/cliente/reportes/reporte3.jsp">Cuenta con mas dinero con sus transacciones</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/#">R4</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/#">R5</a>
                    </div>
                </li>
            </ul>

            <ul class="navbar-nav">
                <li class="nav-item dropdown dropleft">
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

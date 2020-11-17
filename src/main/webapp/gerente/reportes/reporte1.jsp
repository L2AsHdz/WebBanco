<%-- 
    Document   : historialCambios
    Created on : 16/11/2020, 08:18:21
    Author     : asael
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${user.codigo} - ${user.nombre}</title>

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>

        <!-- MDBootstrap Datatables  -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datatables.min.css">
    </head>
    <body>

        <!--Barra de navegacion-->
        <jsp:include page="/WEB-INF/gerente/navBarGerente.jsp" />

        <!-- Cambios realizados -->
        <div class="container-fluid my-5 pl-4">
            <div class="row">
                <div class="col-xl-10">
                    <c:choose>
                        <c:when test="${!empty(cambios)}">
                            <div class="card">
                                <div class="card-header d-flex justify-content-between">
                                    <h4>Historial de cambios</h4>
                                    <a href="${pageContext.request.contextPath}/exportarRG?reporte=r1" class="btn default-color btn-sm">
                                        <i class="fas fa-download"></i> Descargar
                                    </a>
                                </div>
                                <div class="card-body">
                                    <table id="historialCambios" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>#</th>
                                                <th>Gerente</th>
                                                <th>Usuario modificado</th>
                                                <th>Tipo Usuario</th>
                                                <th>Fecha</th>
                                                <th>Hora</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="cambio" items="${cambios}" varStatus="status">
                                                <tr>
                                                    <td>${status.count}</td>
                                                    <td>${cambio.gerente.nombre}</td>
                                                    <td>${cambio.usuarioModificado.nombre}</td>
                                                    <td>${cambio.usuarioModificado.tipoUsuarioS}</td>
                                                    <td>${cambio.fecha}</td>
                                                    <td>${cambio.hora}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h4>No se ha realizado ningun cambio</h4>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- MDBootstrap Datatables  -->
        <script src="${pageContext.request.contextPath}/js/datatables.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#historialCambios').DataTable({
                    "scrollY": "200px",
                    "scrollCollapse": true,
                    "paging": false
                });
                $('.dataTables_length').addClass('bs-select');
            });
        </script>
    </body>
</html>

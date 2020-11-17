<%-- 
    Document   : reporte4
    Created on : 16/11/2020, 22:24:44
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
        <jsp:include page="/WEB-INF/cliente/navBarCliente.jsp" />

        <!-- Solicitudes recibidas -->
        <div class="container-fluid my-5 pl-4">
            <div class="row">
                <div class="col-xl-8">
                    <c:choose>
                        <c:when test="${!empty(solicitudes)}">
                            <div class="card">
                                <div class="card-header d-flex justify-content-between">
                                    <h4>Solicitudes recibidas</h4>
                                    <a href="${pageContext.request.contextPath}/exportarRC?reporte=r4" class="btn default-color btn-sm">
                                        <i class="fas fa-download"></i> Descargar
                                    </a>
                                </div>
                                <div class="card-body">
                                    <table id="reporteC4" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>#</th>
                                                <th>Cuenta solicitada</th>
                                                <th>No. Identificacion solicitante</th>
                                                <th>Nombre solicitante</th>
                                                <th>Fecha solicitud</th>
                                                <th>Estado</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="solicitud" items="${solicitudes}" varStatus="status">
                                                <tr>
                                                    <td>${status.count}</td>
                                                    <td>${solicitud.cuenta.codigo}</td>
                                                    <td>${solicitud.cliente.noIdentificacion}</td>
                                                    <td>${solicitud.cliente.nombre}</td>
                                                    <td>${solicitud.fecha}</td>
                                                    <td>${solicitud.estado}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h4>No se ha recibido ninguna solicitud</h4>
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
                $('#reporteC4').DataTable({
                    "scrollY": "200px",
                    "scrollCollapse": true,
                    "paging": false,
                    "searching": false,
                    "ordering", false
                });
                $('.dataTables_length').addClass('bs-select');
            });
        </script>
    </body>
</html>

<%-- 
    Document   : verSolicitudes
    Created on : 14/11/2020, 17:06:45
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
    </head>
    <body>

        <!--Barra de navegacion-->
        <jsp:include page="/WEB-INF/cliente/navBarCliente.jsp"/>

        <!--Listado de cajeros-->
        <div class="container-fluid mb-5">
            <div class="row">
                <div class="col-xl-10">
                    <div class="card">
                        <div class="card-header">
                            <h4>Solicitudes pendientes</h4>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>#</th>
                                        <th>No. Identificacion solicitante</th>
                                        <th>Nombre solicitante</th>
                                        <th>Fecha solicitud</th>
                                        <th>Estado</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="solicitud" items="${solicitudes}" varStatus="status">
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${solicitud.cliente.noIdentificacion}</td>
                                            <td>${solicitud.cliente.nombre}</td>
                                            <td>${solicitud.fecha}</td>
                                            <td>${solicitud.estado}</td>
                                            <td>
                                                <c:if test="${solicitud.estadoN == 0}">
                                                    <a href="${pageContext.request.contextPath}/cajero?accion=aceptar&codigo=${solicitud.id}" 
                                                       class="btn btn-outline-success">
                                                        <i class="fas fa-check"></i> Aceptar
                                                    </a>
                                                   <a href="${pageContext.request.contextPath}/cajero?accion=rechazar&codigo=${solicitud.id}" 
                                                       class="btn btn-outline-danger">
                                                        <i class="fas fa-times"></i> Rechazar
                                                    </a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

    </body>
</html>

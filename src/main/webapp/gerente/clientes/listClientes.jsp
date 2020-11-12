<%-- 
    Document   : clientes.jsp
    Created on : 12/11/2020, 10:35:32
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
        <jsp:include page="/WEB-INF/gerente/navBarGerente.jsp" />

        <!--Boton agregar cliente-->
        <div class="container-fluid py-3 mb-4 bg-secondary">
            <div class="row">
                <div class="col-xl-3">
                    <a href="#" class="btn btn-primary btn-block" data-toggle="modal" data-target="#clientModal"
                       data-controls-modal="clientModal" data-backdrop="static" data-keyboard="false">
                        <i class="fas fa-plus"></i> Agregar Cliente
                    </a>
                </div>
            </div>
        </div>

        <!--Listado de clientes-->
        <div class="container-fluid mb-5">
            <div class="row">
                <div class="col-xl-10">
                    <div class="card">
                        <div class="card-header">
                            <h4>Listado de clientes</h4>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <th>Direccion</th>
                                        <th>Fecha Nacimiento</th>
                                        <th>No. Identificacion</th>
                                        <th>Sexo</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="cliente" items="${clientes}">
                                        <tr>
                                            <td>${cliente.codigo}</td>
                                            <td>${cliente.nombre}</td>
                                            <td>${cliente.direccion}</td>
                                            <td>${cliente.birth}</td>
                                            <td>${cliente.noIdentificacion}</td>
                                            <td>${cliente.sexo}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/cliente?accion=editar&codigo=${cliente.codigo}" 
                                                   class="btn btn-info">
                                                    <i class="fas fa-angle-double-right"></i> Editar
                                                </a>
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
        
        <%@include file="formCliente.jsp"%>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
        
    </body>
</html>

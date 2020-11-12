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

        <c:if test="${empty(cliente)}">
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
                <c:if test="${!empty(success)}">
                    <div class="row">
                        <div class="col-3">
                            <div class="alert alert-success alert-dismissible">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                ${success}
                            </div>
                        </div>
                    </div>
                </c:if>
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

            <div class="modal" id="clientModal">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">

                        <div class="modal-header bg-dark text-white">
                            <h5 class="modal-title">Agregar Cliente</h5>
                            <button class="close" data-dismiss="modal" onclick="$('#limpiar').click()">
                                <span>&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="form-cliente" action="${pageContext.request.contextPath}/cliente?accion=agregar" method="POST" enctype="multipart/form-data">
                            <%@include file="formCliente.jsp"%>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" form="form-cliente">Agregar</button>
                            <button id="limpiar" type="reset" class="btn btn-info" form="form-cliente">Limpiar</button>
                        </div>

                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${!empty(cliente)}">
            <div class="container-fluid mt-4">
                <div class="row d-flex justify-content-center">
                    <div class="col-xl-4">
                        <div class="card">
                            <div class="card-header">
                                <h5>Editar cliente</h5>
                            </div>
                            <div class="card-body">
                                <form id="form-cliente" action="${pageContext.request.contextPath}/cliente?accion=update" method="POST" enctype="multipart/form-data">
                                <%@include file="formCliente.jsp"%>
                            </div>
                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary btn-block" form="form-cliente">Guardar cambios</button>
                                <a href="${pageContext.request.contextPath}/cliente?accion=listar" class="btn btn-danger btn-block">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
        <script src="${pageContext.request.contextPath}/js/personalized-messages.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validarCliente.js"></script>

    </body>
</html>

<%-- 
    Document   : listGerentes
    Created on : 13/11/2020, 00:13:32
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

        <c:if test="${empty(gerente)}">
            <!--Boton agregar gerente-->
            <div class="container-fluid py-3 mb-4 stylish-color">
                <div class="row">
                    <div class="col-xl-3">
                        <a href="#" class="btn btn-primary btn-block" data-toggle="modal" data-target="#gerenteModal"
                           data-controls-modal="gerenteModal" data-backdrop="static" data-keyboard="false">
                            <i class="fas fa-plus"></i> Agregar Gerente
                        </a>
                    </div>
                </div>
            </div>

            <!--Listado de gerentes-->
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
                                <h4>Listado de gerentes</h4>
                            </div>
                            <div class="card-body">
                                <table id="listGerentes" class="table table-striped table-sm" cellspacing="0" width="100%">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>Codigo</th>
                                            <th>Nombre</th>
                                            <th>Direccion</th>
                                            <th>No. Identificacion</th>
                                            <th>Sexo</th>
                                            <th>Turno</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="gerente" items="${gerentes}">
                                            <tr>
                                                <td>${gerente.codigo}</td>
                                                <td>${gerente.nombre}</td>
                                                <td>${gerente.direccion}</td>
                                                <td>${gerente.noIdentificacion}</td>
                                                <td>${gerente.sexo}</td>
                                                <td>${gerente.turno.nombre}</td>
                                                <td>
                                                    <c:if test="${gerente.codigo == user.codigo}">
                                                        <a href="${pageContext.request.contextPath}/gerente?accion=editar&codigo=${gerente.codigo}" 
                                                           class="btn btn-info btn-sm">
                                                            <i class="fas fa-edit"></i> Editar
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

            <div class="modal" id="gerenteModal">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">

                        <div class="modal-header bg-dark text-white">
                            <h5 class="modal-title">Agregar Gerente</h5>
                            <button class="close" data-dismiss="modal" onclick="$('#limpiar').click()">
                                <span>&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="form-gerente" action="${pageContext.request.contextPath}/gerente?accion=agregar" method="POST">
                                <%@include file="formGerente.jsp"%>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" form="form-gerente">Agregar</button>
                            <button id="limpiar" type="reset" class="btn btn-info" form="form-gerente">Limpiar</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${!empty(gerente)}">
            <div class="container-fluid mt-4">
                <div class="row d-flex justify-content-center">
                    <div class="col-xl-4">
                        <div class="card">
                            <div class="card-header">
                                <h5>Editar gerente</h5>
                            </div>
                            <div class="card-body">
                                <form id="form-gerente" action="${pageContext.request.contextPath}/gerente?accion=update" method="POST">
                                    <%@include file="formGerente.jsp"%>
                            </div>
                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary btn-block" form="form-gerente">Guardar cambios</button>
                                <a href="${pageContext.request.contextPath}/gerente?accion=listar" class="btn btn-danger btn-block">Cancelar</a>
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
        <script src="${pageContext.request.contextPath}/js/validaciones/validarGerente.js"></script>
        
        <!-- MDBootstrap Datatables  -->
        <script src="${pageContext.request.contextPath}/js/datatables.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#listGerentes').DataTable({
                    "scrollY": "200px",
                    "scrollCollapse": true,
                    "paging": false
                });
                $('.dataTables_length').addClass('bs-select');
            });
        </script>

    </body>
</html>

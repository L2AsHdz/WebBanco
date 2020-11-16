<%-- 
    Document   : limites
    Created on : 13/11/2020, 10:58:28
    Author     : asael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_GT" />
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


        <!--Boton modificar limtes-->
        <div class="container-fluid py-3 mb-4 stylish-color">
            <div class="row">
                <div class="col-xl-3">
                    <a href="#" class="btn btn-primary btn-block" data-toggle="modal" data-target="#modal-limite"
                       data-controls-modal="modal-limite" data-backdrop="static" data-keyboard="false">
                        <i class="fas fa-edit"></i> Modificar limites
                    </a>
                </div>
            </div>
        </div>

        <!-- Limites de reportes -->
        <div class="container-fluid my-5 pl-4">
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
                <div class="col-xl-6">
                    <div class="card">
                        <div class="card-header">
                            <h4>Limites de reportes</h4>
                        </div>
                        <div class="card-body">
                            <table id="limites" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Valor</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="limite" items="${limites}">
                                        <tr>
                                            <td>${limite.id}</td>
                                            <td>${limite.nombre}</td>
                                            <td><fmt:formatNumber value="${limite.valor}" type="currency" /></td></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>	
                        </div>
                    </div>

                    <div class="modal" id="modal-limite">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <form action="${pageContext.request.contextPath}/limite?accion=update"
                                      id="form-limite"  method="POST">
                                    <div class="modal-body">
                                        <button type="button" form="form-limite" class="close" data-dismiss="modal" onclick="$('#limpiar').click()">&times;</button>
                                        <h5>Modificar limites</h5>

                                        <c:forEach var="limite" items="${limites}">
                                            <input type="text" class="form-control d-none" name="idLimite${limite.id}" value="${limite.id}">
                                            <div class="form-group">
                                                <label for="valor${limite.id}">*Limite reporte ${limite.id + 1}:</label>
                                                <input type="text" class="form-control" name="valor${limite.id}" id="valor${limite.id}" value="${limite.valor}">
                                            </div>
                                        </c:forEach>

                                        <div class="modal-footer">
                                            <button type="submit" class="btn success-color btn-block">Guardar cambios</button>
                                            <button id="limpiar" type="reset" class="btn btn-primary d-none">Limpiar</button>
                                            <a href="${pageContext.request.contextPath}/limite?accion=listar" class="btn danger-color btn-block">
                                                Cancelar
                                            </a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
        <script src="${pageContext.request.contextPath}/js/personalized-messages.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validarLimite.js"></script>

        <!-- MDBootstrap Datatables  -->
        <script src="${pageContext.request.contextPath}/js/datatables.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#limites').DataTable({
                    "scrollY": "200px",
                    "scrollCollapse": true,
                    "paging": false,
                    "searching": false
                });
                $('.dataTables_length').addClass('bs-select');
            });
        </script>
        
    </body>
</html>

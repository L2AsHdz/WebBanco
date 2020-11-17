<%-- 
    Document   : clientesConTrGreaterThanLimite
    Created on : 16/11/2020, 10:36:34
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

        <!-- Clientes con transacciones mayores a un limite -->
        <div class="container-fluid my-5 pl-4">
            <div class="row">
                <div class="col-xl-10">
                    <c:choose>
                        <c:when test="${!empty(clientes)}">
                            <div class="card">
                                <div class="card-header d-flex justify-content-between">
                                    <h4>Clientes con transacciones mayores a Q.${limite}</h4>
                                    <a href="${pageContext.request.contextPath}/exportarRG?reporte=r2" class="btn default-color btn-sm">
                                        <i class="fas fa-download"></i> Descargar
                                    </a>
                                </div>
                                <div class="card-body">
                                    <table id="reporte2" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Codigo</th>
                                                <th>Nombre</th>
                                                <th>Direccion</th>
                                                <th>Fecha Nacimiento</th>
                                                <th>No. Identificacion</th>
                                                <th>Sexo</th>
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
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${wasSetted}">
                            <h4>No se han establecido los limites</h4>
                        </c:when>
                        <c:otherwise>
                            <h4>No hay clientes con transacciones mayores a Q.${limite}</h4>
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
                $('#reporte2').DataTable({
                    "scrollY": "200px",
                    "scrollCollapse": true,
                    "paging": false
                });
                $('.dataTables_length').addClass('bs-select');
            });
        </script>
    </body>
</html>

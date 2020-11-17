<%-- 
    Document   : reporte1
    Created on : 16/11/2020, 16:21:48
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
        <jsp:include page="/WEB-INF/cajero/navBarCajero.jsp" />

        <div class="container-fluid my-5 pl-4">
            <div class="row h-100">
                <div class="col-xl-8">
                    <!-- Retiros -->
                    <c:choose>
                        <c:when test="${!empty(retiros)}">
                            <div class="card">
                                <div class="card-body">
                                    <h3>Retiros realizados en el turno</h3>
                                    <table id="reporte1R" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Codigo</th>
                                                <th>Codigo Cuenta</th>
                                                <th>Fecha</th>
                                                <th>Hora</th>
                                                <th>Monto</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="retiro" items="${retiros}">
                                                <tr>
                                                    <td>${retiro.codigo}</td>
                                                    <td>${retiro.cuenta.codigo}</td>
                                                    <td>${retiro.fecha}</td>
                                                    <td>${retiro.hora}</td>
                                                    <td>${retiro.monto}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h4>No se ha realizado ningun retiro en el turno</h4>
                        </c:otherwise>
                    </c:choose>

                    <!-- Debitos -->
                    <c:choose>
                        <c:when test="${!empty(depositos)}">
                            <div class="card mt-4">
                                <div class="card-body">
                                    <h3>Depositos realizados en el turno</h3>
                                    <table id="reporte1D" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Codigo</th>
                                                <th>Codigo Cuenta</th>
                                                <th>Fecha</th>
                                                <th>Hora</th>
                                                <th>Monto</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="deposito" items="${depositos}">
                                                <tr>
                                                    <td>${deposito.codigo}</td>
                                                    <td>${deposito.cuenta.codigo}</td>
                                                    <td>${deposito.fecha}</td>
                                                    <td>${deposito.hora}</td>
                                                    <td>${deposito.monto}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h4 class="mt-4">No se ha realizado ningun deposito en el turno</h4>
                        </c:otherwise>
                    </c:choose>

                </div>
                <div class="col-xl-3 my-auto">
                    <div class="card text-center success-color-dark text-white">
                        <div class="card-body">
                            <h3>Total depositado</h3>
                            <h4 class="display-4">
                                Q.${totalD}
                            </h4>
                        </div>
                    </div>
                    <div class="card text-center danger-color text-white mt-3">
                        <div class="card-body">
                            <h3>Total retirado</h3>
                            <h4 class="display-4">
                                Q.${totalR}
                            </h4>
                        </div>
                    </div>

                    <div class="card text-center unique-color text-white mt-3">
                        <div class="card-body">
                            <h3>Total en caja</h3>
                            <h4 class="display-4">
                                Q.${totalD-totalR}
                            </h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- MDBootstrap Datatables  -->
        <script src="${pageContext.request.contextPath}/js/datatables.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#reporte1R').DataTable({
                    "scrollY": "200px",
                    "scrollCollapse": true,
                    "paging": false,
                    "searching": false
                });
                $('.dataTables_length').addClass('bs-select');

                $('#reporte1D').DataTable({
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
<%-- 
    Document   : reporte1
    Created on : 16/11/2020, 18:42:04
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

        <!-- 15 transacciones mas grandes -->
        <div class="container-fluid my-5 pl-4">
            <div class="row">
                <div class="col-xl-8">
                    <c:choose>
                        <c:when test="${!empty(cuentas)}">
                            <c:forEach var="cuenta" items="${cuentas}">
                                <div class="card mb-5">
                                    <div class="card-header">
                                        <h4>Transacciones de cuenta: ${cuenta.codigo}</h4>
                                    </div>
                                    <div class="card-body">
                                        <table class="table table-striped table-bordered table-sm tabla" cellspacing="0" width="100%">
                                            <thead class="thead-dark">
                                                <tr>
                                                    <th>Codigo</th>
                                                    <th>Tipo</th>
                                                    <th>Fecha</th>
                                                    <th>Hora</th>
                                                    <th>Monto</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <c:forEach var="transaccion" items="${cuenta.transacciones}">
                                                    <tr>
                                                        <td>${transaccion.codigo}</td>
                                                        <td>${transaccion.tipo}</td>
                                                        <td>${transaccion.fecha}</td>
                                                        <td>${transaccion.hora}</td>
                                                        <td>${transaccion.monto}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>	
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h4>No hay transacciones realizadas en la cuenta</h4>
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
                $('.tabla').DataTable({
                    "scrollY": "200px",
                    "scrollCollapse": true,
                    "paging": false,
                    "searching": false,
                    "ordering": false
                });
                $('.dataTables_length').addClass('bs-select');
            });
        </script>
    </body>
</html>
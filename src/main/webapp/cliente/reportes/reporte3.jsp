<%-- 
    Document   : reporte3
    Created on : 16/11/2020, 20:51:56
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

        <!-- Cuenta con mas dinero con sus transacciones -->
        <div class="container-fluid my-5 pl-4">
            <div class="row">
                <div class="col-xl-8">
                    <c:choose>
                        <c:when test="${!empty(cuenta)}">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Cuenta con mas dinero</h4>
                                </div>
                                <div class="card-body">
                                    <div class="card-title">Cuenta: ${cuenta.codigo}   -   Saldo: ${cuenta.saldo}</div>
                                    <table id="reporteC3" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Codigo</th>
                                                <th>Tipo</th>
                                                <th>Fecha</th>
                                                <th>Hora</th>
                                                <th>Monto</th>
                                                <th>Saldo</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="transaccion" items="${cuenta.transacciones}">
                                                <tr>
                                                    <td>${transaccion.codigo}</td>
                                                    <td>${transaccion.tipo}</td>
                                                    <td>${transaccion.fecha}</td>
                                                    <td>${transaccion.hora}</td>
                                                    <td>Q.${transaccion.monto}</td>
                                                    <td>${transaccion.saldoCuenta}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="mb-4">
                                <h3><strong>Intervalo de fecha</strong></h3>
                            </div>
                            <form class="form-inline ml-3" action="${pageContext.request.contextPath}/ReportesCliente?accion=reporte3" method="POST"> 
                                <label for="fechaInicial" class="mr-sm-2">Fecha inicial</label>
                                <input type="date" class="form-control mb-2 mr-sm-2"  name="fechaInicial" id="fechaInicial" value="${fechaInicial}" autofocus>
                                <button type="reset" class="btn default-color mb-2 ml-2" onclick="$('#fechaInicial').focus()">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                                <button type="submit" class="btn btn-primary mb-2 ml-2">Ver cuenta</button>
                            </form>
                            <c:if test="${!empty(noData)}">
                                <h4>No se han realizado transacciones en el intervalo ingresado</h4>
                            </c:if>
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
                $('#reporteC3').DataTable({
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

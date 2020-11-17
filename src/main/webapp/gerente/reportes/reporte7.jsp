<%-- 
    Document   : reporte7
    Created on : 16/11/2020, 15:01:02
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

        <!-- Cajero con mas transacciones en un intervalo de tiempo -->
        <div class="container-fluid my-5 pl-4">
            <div class="row">
                <div class="col-xl-10">
                    <c:choose>
                        <c:when test="${!empty(cajero)}">
                            <div class="card">
                                <div class="card-header">
                                    <c:choose>
                                        <c:when test="${empty(fechaInicial)}">
                                            <h4>Cajero con mas transacciones</h4>
                                        </c:when>
                                        <c:otherwise>
                                            <h4>Cajero con mas transacciones del ${fechaInicial} al ${fechaFinal}</h4>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="card-body">
                                    <table id="reporte7" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Codigo</th>
                                                <th>Nombre</th>
                                                <th>Direccion</th>
                                                <th>No. Identificacion</th>
                                                <th>Sexo</th>
                                                <th>Turno</th>
                                                <th> Total Transacciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>${cajero.codigo}</td>
                                                <td>${cajero.nombre}</td>
                                                <td>${cajero.direccion}</td>
                                                <td>${cajero.noIdentificacion}</td>
                                                <td>${cajero.sexo}</td>
                                                <td>${cajero.turno.nombre}</td>
                                                <td>${cajero.totalTransacciones}</td>
                                            </tr>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="mb-4">
                                <h4><strong>Cajero con mas transacciones en un intervalo de tiempo</strong></h4>
                            </div>
                            <form class="form-inline ml-3" action="${pageContext.request.contextPath}/ReportesGerente?accion=reporte7" method="POST"> 
                                <label class="mr-sm-4 font-weight-bold">
                                    <h5>Intervalo de fecha:</h5>
                                </label>
                                <label for="fechaInicial" class="mr-sm-2">Fecha inicial</label>
                                <input type="date" class="form-control mb-2 mr-sm-2"  name="fechaInicial" id="fechaInicial" value="${fechaInicial}" autofocus>
                                <label for="fechaFinal" class="mr-sm-2">Fecha final:</label>
                                <input type="date" class="form-control mb-2 mr-sm-2" name="fechaFinal" value="${fechaFinal}">
                                <button type="reset" class="btn default-color mb-2 ml-2" onclick="$('#fechaInicial').focus()">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                                <button type="submit" class="btn btn-primary mb-2 ml-2">Ver informacion del cajero</button>
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
                                        $('#reporte5').DataTable({
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
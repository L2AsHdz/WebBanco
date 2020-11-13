<%-- 
    Document   : horarios
    Created on : 13/11/2020, 09:49:37
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

        <!-- Turnos disponibles -->
        <div class="container-fluid my-5 pl-4">
            <div class="row">
                <div class="col-xl-6">
                    <div class="card">
                        <div class="card-header">
                            <h4>Turnos disponbles</h4>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Hora Entrada</th>
                                        <th>Hora Salida</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach var="turno" items="${turnos}">
                                        <tr>
                                            <td>${turno.id}</td>
                                            <td>${turno.nombre}</td>
                                            <td>${turno.horaEntrada}</td>
                                            <td>${turno.horaSalida}</td>
                                            <td>
                                                <a class="btn btn-secondary btn-info" data-toggle="modal" data-target="#modal-informe" 
                                                   data-controls-modal="modal-informe" data-backdrop="static" data-keyboard="false"
                                                   onclick="$('#idTurno').val(${turno.id}">
                                                    <i class="fas fa-angle-double-right"></i> Editar
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>	
                        </div>
                    </div>

                    <div class="modal" id="modal-informe">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <form action="${pageContext.request.contextPath}/turno?accion=update"
                                      id="form-turno"  method="POST">
                                    <div class="modal-body">
                                        <button type="button" form="form-turno" class="close" data-dismiss="modal" onclick="$('#limpiar').click()">&times;</button>
                                        <h5>Editar turno</h5>
                                        <input type="text" class="form-control d-none" name="idTurno" id="idTurno">
                                        <div class="form-group">
                                            <label for="horaEntrada">*Hora Entrada:</label>
                                            <input type="time" class="form-control" name="horaEntrada">
                                        </div>
                                        <div class="form-group">
                                            <label for="horaSalida">*Hora Salida:</label>
                                            <input type="time" class="form-control" name="horaSalida">
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary btn-block">Guardar cambios</button>
                                            <button id="limpiar" type="reset" class="btn btn-primary d-none">Limpiar</button>
                                            <a href="${pageContext.request.contextPath}/turno?accion=listar" class="btn btn-secondary btn-block">
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
        <script src="${pageContext.request.contextPath}/js/validaciones/validarTurno.js"></script>
    </body>
</html>

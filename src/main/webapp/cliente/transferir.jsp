<%-- 
    Document   : transferir
    Created on : 14/11/2020, 22:50:34
    Author     : asael
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${user.codigo} - ${user.nombre}</title>

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
    </head>
    <body>

        <!-- Barra de navegacion -->
        <%@include file="/WEB-INF/cliente/navBarCliente.jsp" %>

        <div class="container-fluid mt-5">
            <c:if test="${!empty(success)}">
                <div class="row d-flex justify-content-center">
                    <div class="col-3">
                        <div class="alert alert-success alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            ${success}
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="row d-flex justify-content-center">
                <div class="col-xl-4">
                    <div class="card">
                        <div class="card-header">
                            <h5>Transferir dinero</h5>
                        </div>
                        <div class="card-body">
                            <form id="form-transferencia" action="${pageContext.request.contextPath}/transferencia?accion=transferir" method="POST">
                                <div class="form-group">
                                    <label for="cuentaO">*Cuenta origen:</label>
                                    <select class="form-control" name="cuentaO" id="cuentaO">
                                        <option value="">Seleccione cuenta origen...</option>
                                        <c:forEach var="cuenta" items="${cuentasPropias}">
                                            <option value="${cuenta.codigo}" id="${cuenta.codigo}">${cuenta.codigo} - ${cuenta.saldo}</option>
                                            <script>
                                                $("#${cuenta.codigo}").click(function () {
                                                    $('#saldo').val(${cuenta.saldo});
                                                });
                                            </script>
                                        </c:forEach>
                                    </select>
                                    <input type="text" class="form-control" name="saldo" id="saldo" readonly>

                                    <label for="cuentaD">*Cuenta destino:</label>
                                    <select class="form-control" name="cuentaD">
                                        <option value="">Seleccione cuenta origen...</option>
                                        <option value="">---Cuentas propias---</option>
                                        <c:forEach var="cuenta" items="${cuentasPropias}">
                                            <option value="${cuenta.codigo}">${cuenta.codigo} - ${cuenta.saldo}</option>
                                        </c:forEach>
                                        <option value="">---Cuentas de terceros---</option>
                                        <c:forEach var="asociada" items="${cuentasTerceros}">
                                            <option value="${asociada.cuenta.codigo}">${asociada.cuenta.codigo} - ${asociada.cliente.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="monto">*Monto:</label>
                                    <input type="text" class="form-control" name="monto">
                                </div>
                            </form>
                        </div>
                        <div class="card-footer">
                            <button type="submit" form="form-transferencia" class="btn btn-primary btn-block">Transferir</button>
                        </div>
                        <c:if test="${!empty(error)}" >
                            <div class="alert alert-danger alert-dismissible mt-2">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                ${error}
                            </div>
                        </c:if>
                        <c:if test="${!empty(success)}" >
                            <div class="alert alert-success alert-dismissible mt-2">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                ${success}
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
        <script src="${pageContext.request.contextPath}/js/personalized-messages.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validarTransferencia.js"></script>

    </body>
</html>

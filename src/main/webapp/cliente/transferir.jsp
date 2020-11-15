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
                            <form id="form-transferir" action="${pageContext.request.contextPath}/transaccion?accion=transferir" method="POST">
                                <div class="form-group">
                                    <label for="cuentaO">*Cuenta origen:</label>
                                    <select class="form-control" name="cunetaO">
                                        <option value="">Seleccione cuenta origen...</option>
                                        <c:forEach var="cuenta" items="${cuentasPropias}">
                                            <option>${cuenta.codigo}</option>
                                        </c:forEach>
                                    </select>
                                    <span class="float-xl-right">Saldo: Q.${cuenta.saldo}</span><br>

                                    <label for="cuentaO">*Cuenta destino:</label>
                                    <select class="form-control" name="cunetaO">
                                        <option value="">Seleccione cuenta origen...</option>
                                        <option value="">---Cuentas propias---</option>
                                        <c:forEach var="cuenta" items="${cuentasPropias}">
                                            <option>${cuenta.codigo}</option>
                                        </c:forEach>
                                        <option value="">---Cuentas de terceros---</option>
                                        <c:forEach var="cuenta" items="${cuentasTerceros}">
                                            <option value="${cuenta.codigo}">${cuenta.codigo} - ${cuenta.cliente.nombre}</option>
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
                            <button type="submit" form="form-transferir" class="btn btn-primary btn-block">Transferir</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

    </body>
</html>

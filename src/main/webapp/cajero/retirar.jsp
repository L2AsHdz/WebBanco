<%-- 
    Document   : retirar
    Created on : 13/11/2020, 15:21:17
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
        <jsp:include page="/WEB-INF/cajero/navBarCajero.jsp"/>


        <c:choose>
            <c:when test="${!empty(cuenta)}">
                <div class="container-fluid mt-4">
                    <div class="row d-flex justify-content-center">
                        <div class="col-xl-4">
                            <div class="card">
                                <div class="card-header">
                                    <h5>Retiro</h5>
                                </div>
                                <div class="card-body">
                                    <form id="form-retiro" action="${pageContext.request.contextPath}/transaccion?accion=retirar" method="POST">
                                        <div class="form-group">
                                            <a href="${pageContext.request.contextPath}/cliente?accion=dpiPDF&codCliente=${cuenta.cliente.codigo}" 
                                               class="btn btn-secondary btn-block" target="_blank">
                                                <i class="fas fa-file-pdf"></i> Ver DPI
                                            </a>
                                        </div>
                                        <div class="form-group">
                                            <label for="nombre">Titular de la cuenta:</label>
                                            <input type="text" class="form-control" name="nombre" value="${cuenta.cliente.nombre}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="codCuenta">Codigo de cuenta:</label>
                                            <input type="text" class="form-control" name="codCuenta" value="${cuenta.codigo}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="saldo">Saldo disponible:</label>
                                            <input type="text" class="form-control" id="saldo" name="saldo" value="${cuenta.saldo}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="monto">Cantidad a retirar:</label>
                                            <input type="text" class="form-control" name="monto" autofocus>
                                        </div>
                                    </form>
                                </div>
                                <div class="card-footer">
                                    <button type="submit" class="btn btn-primary btn-block" form="form-retiro">Realizar retiro</button>
                                    <a href="${pageContext.request.contextPath}/cajero/retirar.jsp" class="btn btn-danger btn-block">
                                        Cancelar
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-xl-4"></div>
                        <div class="col-xl-4">
                            <h3 class="text-center mt-5 mb-3">Retirar</h3>

                            <form id="form-infoR" action="${pageContext.request.contextPath}/transaccion?accion=infoRetiro" method="POST">
                                <div class="form-group">
                                    <label for="codCuenta">Codigo de la cuenta</label>
                                    <input type="text" class="form-control" name="codCuenta" autofocus value="${codigo}">
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">Continuar</button>
                                </div>
                            </form>
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
            </c:otherwise>
        </c:choose>


        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
        <script src="${pageContext.request.contextPath}/js/personalized-messages.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validarRetiro.js"></script>

    </body>
</html>

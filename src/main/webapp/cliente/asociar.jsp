<%-- 
    Document   : asociar
    Created on : 14/11/2020, 07:53:52
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
        <jsp:include page="/WEB-INF/cliente/navBarCliente.jsp"/>
        
        <c:choose>
            <c:when test="${!empty(cuenta)}">
                <div class="container-fluid mt-4">
                    <div class="row d-flex justify-content-center">
                        <div class="col-xl-4">
                            <div class="card">
                                <div class="card-header">
                                    <h5>Solicitar asociacion</h5>
                                </div>
                                <div class="card-body">
                                    <form id="form-deposito" action="${pageContext.request.contextPath}/solicitud?accion=solicitar" method="POST">
                                        <div class="form-group">
                                            <label for="noId">No. identificacion del titular:</label>
                                            <input type="text" class="form-control" name="noId" value="${cuenta.cliente.noIdentificacion}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="nombre">Titular de la cuenta:</label>
                                            <input type="text" class="form-control" name="nombre" value="${cuenta.cliente.nombre}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="codCuenta">Codigo de cuenta:</label>
                                            <input type="text" class="form-control" name="codCuenta" value="${cuenta.codigo}" readonly>
                                        </div>
                                    </form>
                                </div>
                                <div class="card-footer">
                                    <button type="submit" class="btn btn-primary btn-block" form="form-deposito">Enviar solicitud</button>
                                    <a href="${pageContext.request.contextPath}/cliente/asociar.jsp" class="btn btn-danger btn-block">
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
                            <h3 class="text-center mt-5 mb-4">Solicitar asociacion</h3>

                            <form id="form-infoD" action="${pageContext.request.contextPath}/solicitud?accion=verInfo" method="POST">
                                <div class="form-group text-center">
                                    <label for="codCuenta">Ingrese el codigo de la cuenta</label>
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
        
    </body>
</html>

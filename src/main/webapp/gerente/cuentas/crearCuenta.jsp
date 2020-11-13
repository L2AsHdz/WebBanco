<%-- 
    Document   : crearCuenta
    Created on : 12/11/2020, 17:39:17
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
        <!-- Barra de navegacion -->
        <jsp:include page="/WEB-INF/gerente/navBarGerente.jsp"/>

        <c:choose>
            <c:when test="${crearCliente}">
                <div class="container-fluid mt-4">
                    <div class="row d-flex justify-content-center">
                        <div class="col-xl-4">
                            <div class="card">
                                <div class="card-header">
                                    <h5>Agregar cliente</h5>
                                </div>
                                <div class="card-body">
                                    <form id="form-cliente" action="${pageContext.request.contextPath}/cuenta?accion=agregar" method="POST" enctype="multipart/form-data">
                                        <%@include file="../clientes/formCliente.jsp"%>
                                </div>
                                <div class="card-footer">
                                    <button type="submit" class="btn btn-primary btn-block" form="form-cliente">Agregar</button>
                                    <button id="limpiar" type="reset" class="btn btn-info btn-block" form="form-cliente">Limpiar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:when test="${success}">
                <div class="container-fluid mt-4">
                    <div class="row d-flex justify-content-center">
                        <div class="col-xl-4">
                            <div class="card">
                                <div class="card-header">
                                    <h5>Resumen de datos</h5>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="codigo">Codigo de usuario:</label>
                                        <input type="text" class="form-control" name="codigo" value="${cuenta.cliente.codigo}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="codigo">Nombre del cliente:</label>
                                        <input type="text" class="form-control" name="codigo" value="${cuenta.cliente.nombre}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="codigo">Codigo de cuenta:</label>
                                        <input type="text" class="form-control" name="codigo" value="${cuenta.codigo}" readonly>
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <a href="${pageContext.request.contextPath}/gerente/cuentas/crearCuenta.jsp" class="btn btn-primary btn-block">
                                        Aceptar
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
                            <h3 class="text-center mt-5 mb-3">Crear nueva cuenta</h3>

                            <form id="form-login" action="${pageContext.request.contextPath}/cuenta?accion=verificarUser" method="POST" class="was-validated">
                                <div class="form-group">
                                    <label for="noIdentificacion">No identificacion del cliente</label>
                                    <input type="text" class="form-control" name="noIdentificacion" placeholder="Ingrese identificacion" autofocus required>
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">Continuar</button>
                                </div>
                            </form>
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
        <script src="${pageContext.request.contextPath}/js/validaciones/validarCliente.js"></script>

    </body>
</html>

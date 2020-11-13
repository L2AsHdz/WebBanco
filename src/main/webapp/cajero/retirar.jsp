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
        <jsp:include page="/WEB-INF/gerente/navBarGerente.jsp"/>


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
                                <div class="form-group">
                                    <a href="${pageContext.request.contextPath}/cliente?accion=dpiPDF&${cuenta.cliente.id}" class="class"></a>
                                </div>
                                <div class="form-group">
                                    <label for="codigo">Saldo de la cuenta: ${cuenta.saldo}</label>
                                </div>
                                <div class="form-group">
                                    <label for="monto">Cantidad a retirar:</label>
                                    <input type="text" class="form-text" name="monto">
                                </div>
                            </div>
                            <div class="card-footer">
                                <a href="${pageContext.request.contextPath}/transaccion?accion=retirar" class="btn btn-primary btn-block">
                                    Realizar retiro
                                </a>
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

                        <form id="form-login" action="${pageContext.request.contextPath}/transaccion?accion=infoRetiro" method="POST">
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
                                ${error}
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>


    <!--JS--> 
    <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

</body>
</html>

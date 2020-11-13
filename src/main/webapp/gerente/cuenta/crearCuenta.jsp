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

        <div class="container mt-5">
            <div class="row">
                <div class="col-xl-3"></div>
                <div class="col-xl-6">
                    <h3 class="text-center mt-5 mb-3">Crear nueva cuenta</h3>

                    <form id="form-login" action="${pageContext.request.contextPath}/cuenta?accion=verificarUser" method="POST">
                        <div class="form-group">
                            <label for="noIdentificacion">No identificacion del cliente</label>
                            <input type="text" class="form-control" name="noIdentificacion" placeholder="Ingrese identificacion" autofocus>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Verificar si tiene usuario</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>       

    </body>
</html>

<%-- 
    Document   : historialCambios
    Created on : 16/11/2020, 08:18:21
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
        
        <div class=""></div>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
    </body>
</html>

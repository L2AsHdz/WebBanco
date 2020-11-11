<%-- 
    Document   : lecturaArchivo
    Created on : 10/11/2020, 23:38:53
    Author     : asael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Seleccion de archivos</title>

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
    </head>
    <body>

        <jsp:include page="/WEB-INF/navBar.jsp"/>


        <div class="container-fluid">
            <div class="row py-2 bg-light">
                <div class="col-xl-3">
                    <h3 class="mt-2 ml-3">Seleccion de archivos</h3>
                </div>
            </div>

            <form id="form-archivo" action="${pageContext.request.contextPath}/lecturaArchivo" method="POST" enctype="multipart/form-data">
                <div class="row pl-5">
                    <div class="col-xl-6">
                        <div class="my-2">
                            <label for="archivoEntrada" class="font-weight-bold">Seleccione el archivo de entrada:</label>
                            <input type="file" class="form-control-file border" name="archivoEntrada" accept=".xml">
                        </div>
                    </div>
                </div>
                <div class="row pl-5">
                    <div class="col-xl-6">
                        <div class="mt-4 mb-2">
                            <label for="archivos" class="font-weight-bold">Seleccione los dpi's escaneados de los clientes:</label>
                            <input type="file" class="form-control-file border" name="archivos" accept=".pdf" multiple>
                        </div>                        
                    </div>
                </div>
                <div class="row pl-5">
                    <div class="col-xl-4"></div>
                    <div class="col-xl-2">
                        <button type="submit" class="btn btn-primary btn-block mt-2">Continuar</button>
                    </div>
                </div>
            </form>
        </div>


        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validarArchivoEntrada.js"></script>

    </body>
</html>

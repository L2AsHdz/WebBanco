<div class="modal" id="clientModal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <div class="modal-header bg-dark text-white">
                <h5 class="modal-title">Agregar Cliente</h5>
                <button class="close" data-dismiss="modal" onclick="$('#limpiar').click()">
                    <span>&times;</span>
                </button>
            </div>

            <form id="form-cliente" action="${pageContext.request.contextPath}/ServletControlador?accion=insertar" method="POST" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">*Nombre:</label>
                        <input type="text" class="form-control" name="nombre" value="${cliente.nombre}">

                    </div>
                    <div class="form-group">
                        <label for="direccion">*Direccion:</label>
                        <input type="text" class="form-control" name="direccion" value="${cliente.direccion}">
                    </div>
                    <div class="form-group">
                        <label for="birth">*Fecha Nacimiento:</label>
                        <input type="date" class="form-control" name="birth" value="${cliente.birth}">
                    </div>
                    <div class="form-group">
                        <label for="noIdentificacion">*No identificacion:</label>
                        <input type="text" class="form-control" name="noIdentificacion" value="${cliente.noIdentificacion}">
                    </div>

                    <c:choose>
                        <c:when test="${!empty(cliente)}">
                            <div>
                                <button type="button" class="btn btn-secondary btn-block" onclick="$('#newDPI').show()">Subir nuevo DPI escaneado</button>
                            </div>
                            <div id="newDPI">
                                <div class="form-group">
                                    <label for="dpiPDF">DPI escaneado:</label>
                                    <input type="file" class="form-control-file border" name="dpiPDF" accept=".pdf">
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group">
                                <label for="dpiPDF">DPI escaneado:</label>
                                <input type="file" class="form-control-file border" name="dpiPDF" accept=".pdf">
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <div class="form-group">
                        <label for="sexo">*Sexo:</label>
                        <select class="form-control" name="sexo">
                            <c:choose>
                                <c:when test="${cliente.sexo == 'Masculino'}">
                                    <option>Masculino</option>
                                    <option>Femenino</option>
                                </c:when>
                                <c:when test="${cliente.sexo == 'Femenino'}">
                                    <option>Femenino</option>
                                    <option>Masculino</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="">Seleccione...</option>
                                    <option>Masculino</option>
                                    <option>Femenino</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                    <c:if test="${empty(cliente)}">
                    <div class="form-group">
                        <label for="password">*Contraseña:</label>
                        <input type="password" class="form-control" name="password">
                    </div>
                    </c:if>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Agregar</button>
                    <button id="limpiar" type="reset" class="btn btn-info">Limpiar</button>
                </div>
            </form>

        </div>
    </div>
</div>
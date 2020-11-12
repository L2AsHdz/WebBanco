


    <c:if test="${!empty(cliente)}">
        <div class="form-group">
            <label for="codigo">Codigo:</label>
            <input type="text" class="form-control" name="codigo" value="${cliente.codigo}" readonly>
        </div>
    </c:if>
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

    <c:choose>
        <c:when test="${!empty(cliente)}">
            <div>
                <button type="button" class="btn btn-light btn-block mb-3" onclick="$('#newDPI').removeAttr('hidden')">Subir nuevo DPI escaneado</button>
            </div>
            <div id="newDPI" hidden>
                <div class="form-group">
                    <label for="dpiPDF">*DPI escaneado:</label>
                    <input type="file" class="form-control-file border" name="dpiPDF" accept=".pdf">
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="form-group">
                <label for="dpiPDF">*DPI escaneado:</label>
                <input type="file" class="form-control-file border" name="dpiPDF" accept=".pdf">
            </div>
        </c:otherwise>
    </c:choose>
            
    <c:if test="${empty(cliente)}">
        <div class="form-group">
            <label for="password">*Contraseña:</label>
            <input type="password" class="form-control" name="password">
        </div>
    </c:if>
</form>


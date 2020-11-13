


    <c:if test="${!empty(cajero)}">
        <div class="form-group">
            <label for="codigo">Codigo:</label>
            <input type="text" class="form-control" name="codigo" value="${cajero.codigo}" readonly>
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
        <label for="noIdentificacion">*No identificacion:</label>
        <input type="text" class="form-control" name="noIdentificacion" value="${cliente.noIdentificacion}">
    </div>

    <div class="form-group">
        <label for="sexo">*Sexo:</label>
        <select class="form-control" name="sexo">
            <c:choose>
                <c:when test="${cajero.sexo == 'Masculino'}">
                    <option>Masculino</option>
                    <option>Femenino</option>
                </c:when>
                <c:when test="${cajero.sexo == 'Femenino'}">
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
                    
    <div class="form-group">
        <label for="turno">*Turno:</label>
        <select class="form-control" name="turno">
            <c:choose>
                <c:when test="${cajero.turno.nombre == 'Matutino'}">
                    <option>Matutino</option>
                    <option>Vespertino</option>
                </c:when>
                <c:when test="${cajero.turno.nombre == 'Vespertino'}">
                    <option>Vespertino</option>
                    <option>Matutino</option>
                </c:when>
                <c:otherwise>
                    <option value="">Seleccione...</option>
                    <option>Matutino</option>
                    <option>Vespertino</option>
                </c:otherwise>
            </c:choose>
        </select>
    </div>
            
    <c:if test="${empty(cajero)}">
        <div class="form-group">
            <label for="password">*Contraseña:</label>
            <input type="password" class="form-control" name="password">
        </div>
    </c:if>
</form>


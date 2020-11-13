


    <c:if test="${!empty(gerente)}">
        <div class="form-group">
            <label for="codigo">Codigo:</label>
            <input type="text" class="form-control" name="codigo" value="${gerente.codigo}" readonly>
        </div>
    </c:if>
    <div class="form-group">
        <label for="nombre">*Nombre:</label>
        <input type="text" class="form-control" name="nombre" value="${gerente.nombre}">
    </div>
    <div class="form-group">
        <label for="direccion">*Direccion:</label>
        <input type="text" class="form-control" name="direccion" value="${gerente.direccion}">
    </div>
    <div class="form-group">
        <label for="noIdentificacion">*No identificacion:</label>
        <input type="text" class="form-control" name="noIdentificacion" value="${gerente.noIdentificacion}">
    </div>

    <div class="form-group">
        <label for="sexo">*Sexo:</label>
        <select class="form-control" name="sexo">
            <c:choose>
                <c:when test="${gerente.sexo == 'Masculino'}">
                    <option>Masculino</option>
                    <option>Femenino</option>
                </c:when>
                <c:when test="${gerente.sexo == 'Femenino'}">
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
                <c:when test="${gerente.turno.id == 1}">
                    <option>Matutino</option>
                    <option>Vespertino</option>
                </c:when>
                <c:when test="${gerente.turno.id == 2}">
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
            
    <c:if test="${empty(gerente)}">
        <div class="form-group">
            <label for="password">*Contraseņa:</label>
            <input type="password" class="form-control" name="password">
        </div>
    </c:if>
</form>


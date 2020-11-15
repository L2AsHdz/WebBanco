$('document').ready(function () {
    
    $.validator.addMethod("distinct", function(value, element) {
        var cuentaO = $('#cuentaO').val();
        
        return cuentaO !== value;
    }, "La cuenta destino no puede ser la misma que la cuenta origen");
    
    $("#form-transferencia").validate({

        rules: {
            monto: {
                required: true,
                number: true,
                min: 1
            },
            cuentaO: {
                required: true,
                minlength: 1
            },
            cuentaD: {
                required: true,
                minlength: 1,
                distinct: true
            }
        }
    });
});
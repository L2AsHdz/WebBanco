$('document').ready(function () {
    
    $.validator.addMethod("limite", function(value, element) {
        var valor1 = $("#valor1").val();
        
        return (value-valor1) > 0;
    }, "Este valor debe ser mayor al valor del limite 1");
    
    $("#form-limite").validate({

        rules: {
            valor1: {
                required: true,
                number: true,
                min: 0
            },
            valor2: {
                required: true,
                number: true,
                limite: true
            }
        }
    });
});
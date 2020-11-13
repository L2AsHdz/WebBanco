$('document').ready(function () {
    
    $("#form-retiro").validate({

        rules: {
            monto: {
                required: true,
                number: true,
                max : parseFloat($('#saldo').val())
            }
        },
        messages: {
            monto: {
                max: "Saldo insuficiente!"
            }
        }
    });
});
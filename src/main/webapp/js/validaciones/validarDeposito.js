$('document').ready(function () {
    
    $("#form-deposito").validate({

        rules: {
            monto: {
                required: true,
                number: true,
                min: 1
            }
        }
    });
});
$('document').ready(function () {
    
    $("#form-turno").validate({

        rules: {
            horaEntrada: {
                required: true
            },
            horaSalida: {
                required: true
            }
        }
    });
});
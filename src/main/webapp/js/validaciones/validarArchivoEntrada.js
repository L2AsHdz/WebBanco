$('document').ready(function () {
    $("#form-archivo").validate({

        rules: {
            archivoEntrada: {
                required: true
            },
            archivos: {
                required: true
            }
        },
        messages: {
            archivoEntrada: {
                required: "No ha seleccionado el archivo de entrada"
            },
            archivos: {
                required: "No ha seleccionado los dpi's de los clientes"
            }
        }
    });
});
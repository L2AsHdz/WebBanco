$('document').ready(function () {
    
    $.validator.addMethod("notEmpty", function(value, element) {
        return value.trim().length !== 0;
    }, "El campo no puede contener solo espacios en blanco");
    
    $("#form-cliente").validate({

        rules: {
            nombre: {
                required: true,
                maxlength: 45,
                notEmpty: true
            },
            direccion: {
                required: true,
                maxlength: 250,
                notEmpty: true
            },
            birth: {
                required: true
            },
            noIdentificacion: {
                required: true,
                maxlength: 15,
                digits: true
            },
            dpiPDF: {
                required: true
            },
            sexo: {
                required: true,
                minlength: 1
            },
            password: {
                required: true,
                minlength: 8,
                notEmpty: true
            }
        }
    });
});
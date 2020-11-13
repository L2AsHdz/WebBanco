jQuery.extend(jQuery.validator.messages, {
    required: "Este campo es obligatorio.",
    email: "Ingrese un email valido.",
    number: "Ingrese un numero valido.",
    digits: "Ingrese solo digitos.",
    maxlength: jQuery.validator.format("No se permiten mas de {0} caracteres."),
    minlength: jQuery.validator.format("Ingrese al menos {0} caracteres."),
    range: jQuery.validator.format("Please enter a value between {0} and {1}."),
    max: jQuery.validator.format("Ingrese un valor menor o igual a {0}."),
    min: jQuery.validator.format("Ingrese un valor mayor o igual a {0}.")
});
document.addEventListener('DOMContentLoaded', function () {
    'use strict';
    var forms = document.getElementsByClassName('form');
    Array.prototype.forEach.call(forms, function (form) {
        form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
                form.querySelectorAll('.invalid-feedback').forEach(function (feedback) {
                    feedback.classList.add('active');
                });
            } else {
                form.querySelectorAll('.invalid-feedback').forEach(function (feedback) {
                    feedback.classList.remove('active');
                });
            }
            form.classList.add('was-validated');
        }, false);
    });
});
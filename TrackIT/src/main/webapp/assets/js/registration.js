var form = document.getElementById('addNewUserForm');

$( "#submit_reg" ).click(function() {
    addNewUser();
});

function addNewUser() {
    // collect the form data while iterating over the inputs
    var regDTO = {};

    regDTO['email'] = document.getElementById('email').value;
    regDTO['password'] = document.getElementById('password').value;
    regDTO['password2'] = document.getElementById('password2').value;
    regDTO['nickname'] = document.getElementById('nickname').value;
    regDTO['phoneNumber'] = document.getElementById('number').value;

    jQuery.ajax({
        type: "POST",
        url: form.getAttribute('action'),
        data: JSON.stringify(regDTO),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            window.location.replace("/cabinet");
        },
        error: function (errorMsg) {
            if (errorMsg.responseText.includes('message')) {
                messageBox('Warning', JSON.parse(errorMsg.responseText).message, 'warning');
            } else {
                messageBox('Error', errorMsg.errorText, 'danger');
            }
        }
    });
}
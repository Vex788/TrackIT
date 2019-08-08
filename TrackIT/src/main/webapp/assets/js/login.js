var form = document.getElementById('loginForm');

var google_url = null;
var facebook_url = null;

$(document).ready(function() {
    $.getJSON('/oauth_login', function (data) {
        // data is the JSON string LoginViaDTO
        google_url = data.google_url;
        facebook_url = data.facebook_url;
    }).fail(function (jqXHR, textStatus, errorThrown) {
        if (jqXHR.status == 400) {
            messageBox('Error', jqXHR.status + '<br>' + textStatus, 'danger');
        }
    });
});

function googleLogin() {
    location.href = google_url;
}

function facebookLogin() {
    location.href = facebook_url;
}

function login() {
    var loginDTO = {};
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    loginDTO['email'] = email;
    loginDTO['password'] = password;
    loginDTO['isOnline'] = false;

    jQuery.ajax({
        type: "POST",
        url: form.getAttribute('action'),
        data: JSON.stringify(loginDTO),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            window.location.replace('/cabinet');
        },
        error: function (errorMsg) {
            if (errorMsg.responseText.includes('message')) {
                messageBox('Warning', JSON.parse(errorMsg.responseText).message, 'warning');
            } else {
                messageBox('Warning', errorMsg.errorText, 'warning');
            }
        }
    });
}

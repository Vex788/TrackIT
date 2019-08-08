function messageBox(title, content, type) {
    $.alert({
        theme: 'dark alert_theme',
        title: title,
        content: content,
        buttons: {
            info: {
                text: 'ok',
                btnClass: 'btn btn-' + type + ' btn-sm bg-' + type + ' border rounded border-' + type + ' shadow btn_alert',
                action: function () {
                }
            }
        }
    });
}

function messageBoxAutoClose(title, content, type, time) {
    $.alert({
        theme: 'dark alert_theme',
        title: title,
        content: content,
        autoClose: 'info|' + time,
        buttons: {
            info: {
                text: 'close',
                btnClass: 'btn btn-' + type + ' btn-sm bg-' + type + ' border rounded border-' + type + ' shadow btn_alert',
                action: function () {
                }
            }
        }
    });
}

function editUserDataWindow() {
    $.confirm({
        theme: 'dark alert_theme',
        title: 'Personal profile change',
        content: '<br>' +
            '<form action="" id="editData" class="editUserData">' +
            '<div class="form-group col-lg-12 offset-lg-0 text-dark justify-content-lg-center">' +
            '<div class="row"><div class="col"><input class="bg-dark border-dark shadow form-control" type="nickname" id="nicknamef" class="nickname" placeholder="Nickname" inputmode="nickname" style="font-family: Roboto, sans-serif;color: rgb(255,255,255);margin-bottom: 5px;margin-top: 0px;"></div></div>' +
            '<div class="row"><div class="col"><input class="bg-dark border-dark shadow form-control" type="email" id="emailf" class="email" placeholder="Email" inputmode="email" style="font-family: Roboto, sans-serif;color: rgb(255,255,255);margin-bottom: 5px;margin-top: 0px;"></div></div>' +
            '<div class="row"><div class="col"><input class="bg-dark border-dark shadow form-control" type="tel" pattern="[0-9]{12}" title="Phone format 380123456789" id="numberf" class="number" placeholder="38 012 345 67 89" inputmode="number" style="font-family: Roboto, sans-serif;color: rgb(255,255,255);margin-bottom: 5px;margin-top: 0px;"></div></div>' +
            '</div>' +
            '</form>',
        buttons: {
            formSubmit: {
                text: 'Submit',
                btnClass: 'btn btn-success btn-sm bg-success border rounded border-success shadow btn_alert',
                action: function () {
                    var regDTO = {};

                    regDTO['email'] = $('#emailf').val();
                    regDTO['password'] = null;
                    regDTO['password2'] = null;
                    regDTO['nickname'] = $('#nicknamef').val();
                    regDTO['phoneNumber'] = $('#numberf').val();

                    jQuery.ajax({
                        type: "POST",
                        url: '/cabinet/edit',
                        data: JSON.stringify(regDTO),
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        success: function(data){
                            loadCabinetDTO();
                            messageBox('Success', 'Data changed', 'success');
                        },
                        error: function(errorMsg) {
                            if (errorMsg.status == 400) {
                                messageBox('Warning', 'Empty fields', 'warning');
                            } else {
                                if (errorMsg.responseText.includes('message')) {
                                    messageBox('Error', JSON.parse(errorMsg.responseText).message, 'warning');
                                } else {
                                    messageBox('Error',errorMsg.errorText, 'warning');
                                }
                            }
                        }
                    });
                }
            },
            cancel: function () {
                // close
            },
        },
        onContentReady: function () {
            // bind to events
            var jc = this;
            this.$content.find('form').on('submit', function (e) {
                // if the user submits the form by pressing enter in the field.
                e.preventDefault();
                jc.$$formSubmit.trigger('click'); // reference the button and click it
            });

            // get user data
            $.getJSON('/cabinet/get', function (data) {
                // data is the JSON string CabinetDTO
                $('#emailf').val(data.email);
                $('#nicknamef').val(data.nickname);
                $('#numberf').val(data.phoneNumber);
            });
        }
    });
}
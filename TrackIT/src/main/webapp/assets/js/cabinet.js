$(document).ready(function() {
    loadCabinetDTO();
});

$("#currency_codes").click(function() {
    $('#url').val('');
    $('#final_price').val('');
});

$("#url").click(function() {
    $('#currency_codes').val('');
    $('#final_price').val('');
});

function loadCabinetDTO() {
    $.getJSON('/cabinet/get', function (data) {
        // data is the JSON string CabinetDTO
        if (data.email != null) {
            $('#email').text(data.email);

            if (data.role == 'VIP' || data.role == 'ADMIN') {
                $("#plus_account").empty();
                $("#plus_account").append('<button class=\"btn btn-warning btn-block btn-lg bg-warning border rounded border-dark shadow\" type=\"button\">plus account</button>');
            }

            if (data.siteDataCollection.length == 0) {
                $("table > tbody").empty();
                $("#empty_field").append('<h5 style=\"text-align: center;\">EMPTY</h5>');
            } else {
                $("#empty_field").empty();
                $("table > tbody").empty();

                for (var i = 0; i < data.siteDataCollection.length; i++) {
                    $('tbody').append(
                        '<tr>'
                        + '<td>' + (i + 1) + '</td>'
                        + '<td><a href=\"' + data.siteDataCollection[i].siteUrl + '\">' + data.siteDataCollection[i].siteTitle + '</a></td>'
                        + '<td style=\"width: 250px;\">' + data.siteDataCollection[i].startedPrice + ' ➡ ' + getFinishedPriceStr(data.siteDataCollection[i].finishPrice, data.siteDataCollection[i].increase) + '</td>'
                        + '<td><button class=\"btn btn-dark btn-sm bg-success border rounded border-success shadow\" type=\"button\" onclick=\"onDeleteButton(' + i + ')\" id=\"report_a_drop_' + i + '\" style=\"font-family: \'Roboto\', sans-serif;font-style: normal;font-weight: normal;width: 60px;font-size: 15px;color: rgb(0,0,0);\">✖</button></td>'
                        + '</tr>'
                    );
                }
            }
        }
        if (data.status == 400) {
            messageBox('Warning', 'Email confirmation required.', 'warning');
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        if (jqXHR.status == 400) {
            messageBox('Warning', 'Email confirmation required.', 'warning');
        }
    });
}

function onDeleteButton(index) {
    var posting = $.post('/cabinet/delete_task?index=' + index);
    sleepFor(350);
    loadCabinetDTO();
    messageBox('Success', 'Task #' + (index + 1) + ' deleted.', 'success');
}

function getFinishedPriceStr(finishedPrice, increase) {
    if (finishedPrice == '' || finishedPrice == null || finishedPrice == 0.0) {
        if (increase == true) increase = 'UP';
        if (increase == false) increase = 'DOWN';
        return increase;
    } else {
        return finishedPrice;
    }
    return null;
}

function sleepFor(sleepDuration) {
    var now = new Date().getTime();
    while (new Date().getTime() < now + sleepDuration) {
    }
}

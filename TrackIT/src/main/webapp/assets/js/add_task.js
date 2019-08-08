var form = document.getElementById('addTaskForm');
var increase = false;

function increaseToggle(val) {
    increase = val;
    addTask();
}

function addTask() {
    // collect the form data while iterating over the inputs
    var siteData = {};

    siteData['siteTitle'] = null;
    siteData['siteUrl'] = document.getElementById('url').value;
    siteData['currencyCodes'] = document.getElementById('currency_codes').value;
    siteData['startedPrice'] = null;
    siteData['finishPrice'] = document.getElementById('final_price').value;
    siteData['increase'] = increase;

    jQuery.ajax({
        type: "POST",
        url: form.getAttribute('action'),
        data: JSON.stringify(siteData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data){
            loadCabinetDTO();
            messageBox('Success',data.message + 'Task added.', 'success');
        },
        error: function(errorMsg) {
            if (errorMsg.status == 400) {
                messageBox('Warning', 'You need to provide a link to the product or currency codes [ eur/usd ].<br>' +
                    'Required fields:<br>' +
                    '📝 Enter url<br>' +
                    '📝 Currency codes [ btc/usd ]',
                    'warning');
            } else {
                if (errorMsg.responseText.includes('message')) {
                    messageBox('Warning',JSON.parse(errorMsg.responseText).message, 'warning');
                } else {
                    messageBox('Error',errorMsg.errorText, 'warning');
                }
            }
        }
    });
}
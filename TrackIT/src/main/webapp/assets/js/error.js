$(document).ready(function () {
    sleepFor(3500);
    window.location.href = "/login";
});

function sleepFor(sleepDuration) {
    var now = new Date().getTime();
    while (new Date().getTime() < now + sleepDuration) {
    }
}

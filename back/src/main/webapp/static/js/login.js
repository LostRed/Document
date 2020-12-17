let $captcha = $('#code-img>img');
$(function () {
    $('#login').click(function () {
        let username = $('#username').val();
        let password = $('#password').val();
        let captcha = $('#captcha').val();
        $.ajax({
            url: 'admin/doLogin',
            type: 'post',
            dataType: 'json',
            data: {
                username, password, captcha
            },
            success(resp) {
                changeCaptcha();
                if (resp.errCode === 0) {
                    location.href = resp.data.location;
                    return;
                }
                layer.alert(resp.errMsg);
            }
        });
    });
    $captcha.click(changeCaptcha);
});

function changeCaptcha() {
    $captcha.prop('src', 'captcha?' + Math.random());
}

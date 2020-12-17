$(function () {
    $.ajax({
        url: 'edu/findAll',
        type: 'get',
        dataType: 'json',
        success(resp) {
            refreshEduSelect(resp.data);
        }
    });
    $.ajax({
        url: 'prof/findAll',
        type: 'get',
        dataType: 'json',
        success(resp) {
            refreshProfSelect(resp.data);
        }
    });
    let $input = $('input[type="text"],[type="password"]');
    $input.blur(function () {
        if ($(this).val() === "") {
            $(this).addClass('input-error');
        }
    });
    $input.focus(function () {
        if ($(this).val() === "") {
            $(this).removeClass('input-error');
        }
    });
});

$('#register').click(function () {
    let $username = $('#username');
    let $password1 = $('#password1');
    let $password2 = $('#password2');
    let $sex = $('input[name="sex"]:checked');
    let $mobilePhone = $('#mobile-phone');
    let $email = $('#email');
    let $edu = $('#edu option:selected');
    let $prof = $('#prof option:selected');
    if ($username.val().length < 6) {
        $username.addClass('input-error');
        return;
    }
    if ($password1.val().length < 6) {
        $password1.addClass('input-error');
        return;
    }
    if ($password1.val() !== $password2.val()) {
        $password2.addClass('input-error');
        return;
    }
    if (!/^1[0-9]{10}$/.test($mobilePhone.val())) {
        $mobilePhone.addClass('input-error');
        return;
    }
    if (!/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test($email.val())) {
        $email.addClass('input-error');
        return;
    }
    $.ajax({
        url: 'user/doRegister',
        type: 'post',
        dataType: 'json',
        data: {
            username: $username.val(),
            password: $password1.val(),
            sex: $sex.val(),
            mobilePhone: $mobilePhone.val(),
            email: $email.val(),
            eduId: $edu.val(),
            profId: $prof.val(),
        },
        success(resp) {
            if (resp.errCode === 0) {
                layer.alert("注册成功！");
                resetForm();
                return;
            }
            layer.alert(resp.errMsg);
        }
    });
});

function refreshEduSelect(data) {
    let $select = $('#edu');
    $select.empty();
    for (let arg of data) {
        let $option = $('<option>');
        $option.val(arg.eduId);
        $option.html(arg.eduName);
        $select.append($option);
    }
}

function refreshProfSelect(data) {
    let $select = $('#prof');
    $select.empty();
    for (let arg of data) {
        let $option = $('<option>');
        $option.val(arg.profId);
        $option.html(arg.profName);
        $select.append($option);
    }
}

function resetForm() {
    $('input[type="text"],[type="password"]').val("");
    $('input[name="sex"]:first').prop('checked', 'checked');
    $('#edu option:first').prop('selected', 'selected');
    $('#prof option:first').prop('selected', 'selected');
}
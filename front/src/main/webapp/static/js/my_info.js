//初始化
$(function () {
    $.ajax({
        url: 'edu/findAll',
        type: 'get',
        dataType: 'json',
        success(resp) {
            eduList = resp.data;
            refreshEduSelect($('#update-edu'), eduList);
        }
    });
    $.ajax({
        url: 'prof/findAll',
        type: 'get',
        dataType: 'json',
        success(resp) {
            profList = resp.data;
            refreshProfSelect($('#update-prof'), profList);
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

function refreshEduSelect($select, data) {
    $select.empty();
    for (let arg of data) {
        let $option = $('<option>');
        $option.val(arg.eduId);
        $option.html(arg.eduName);
        $select.append($option);
    }
}

function refreshProfSelect($select, data) {
    $select.empty();
    for (let arg of data) {
        let $option = $('<option>');
        $option.val(arg.profId);
        $option.html(arg.profName);
        $select.append($option);
    }
}

function updateUser() {
    $('#update-name').val(loginUser.name);
    $(`#update-sex input[name="sex"][value=${loginUser.sex}]`).prop('checked', true);
    $('#update-mobilePhone').val(loginUser.mobilePhone);
    $('#update-email').val(loginUser.email);
    $('#update-edu').val(loginUser.edu.eduId);
    $('#update-prof').val(loginUser.prof.profId);
}

function updateCommit() {
    let $name = $('#update-name');
    let $sex = $('#update-sex input[name="sex"]:checked');
    let $mobilePhone = $('#update-mobilePhone');
    let $email = $('#update-email');
    let $edu = $('#update-edu');
    let $prof = $('#update-prof');
    if ($name.val() === "") {
        $name.addClass('input-error');
        return;
    }
    $.ajax({
        url: 'user/modify',
        type: 'post',
        dataType: 'json',
        data: {
            name: $name.val(),
            sex: $sex.val(),
            mobilePhone: $mobilePhone.val(),
            email: $email.val(),
            eduId: $edu.val(),
            profId: $prof.val()
        },
        success(resp) {
            if (resp.errCode === 0) {
                layer.alert("修改成功！");
                $('#update').modal('hide');
                getLoginUser();
                return;
            }
            layer.alert(resp.errMsg);
        }
    });
}
$(function () {
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

function save() {
    let $point = $('#point');
    if (!/^\d*$/.test($point.val())) {
        $point.addClass('input-error');
        return;
    }
    $.ajax({
        url: 'param/modify',
        type: 'post',
        dataType: 'json',
        data: {
            paramName: "注册奖励",
            paramValue: $point.val()
        },
        success(resp) {
            if (resp.errCode === 0) {
                layer.alert("保存成功！");
                $point.val("");
                return;
            }
            layer.alert(resp.errMsg);
        }
    });
}

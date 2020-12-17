let userList = [];
let eduList = [];
let profList = [];
let stateList = [];
let $pager;
let pager;
let modifyUser;
//初始化
$(function () {
    $.ajax({
        url: 'edu/findAll',
        type: 'get',
        dataType: 'json',
        success(resp) {
            eduList = resp.data;
            refreshSearchEduSelect($('#edu'), eduList);
            refreshEduSelect($('#update-edu'), eduList);
        }
    });
    $.ajax({
        url: 'state/findAll',
        type: 'get',
        dataType: 'json',
        success(resp) {
            stateList = resp.data;
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
    $pager = $('#pager');
    pager = $pager.data('zui.pager');
    pager.set({
        page: 1,
        recTotal: 0,
        recPerPage: 5
    });
    $pager.on('onPageChange', function (e, state, oldState) {
        if (state.page !== oldState.page) {
            search();
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
    search();
});

function search() {
    let username = $('#username').val();
    let mobilePhone = $('#mobilePhone').val();
    let sex = $('#sex input[name="sex"]:checked').val();
    let eduId = $('#edu').val();
    let conditions = {};
    conditions['username'] = username;
    conditions['mobilePhone'] = mobilePhone;
    conditions['sex'] = sex;
    conditions['eduId'] = eduId;
    $.ajax({
        url: "user/pageFindByCondition",
        type: "get",
        dataType: "json",
        data: {
            page: pager.state.page,
            recPerPage: pager.state.recPerPage,
            conditions: JSON.stringify(conditions)
        },
        success(resp) {
            if (resp.errCode === 0) {
                pager.set({
                    recTotal: resp.data.recTotal
                });
                userList = resp.data.list;
                showData();
            }
        }
    });
}

function showData() {
    let $data = $('#data');
    $data.empty();
    for (let user of userList) {
        let str = `
        <tr>
            <td>${user.userId}</td>
            <td>${user.username}</td>
            <td>${user.mobilePhone}</td>
            <td>${user.sex}</td>
            <td>${user.edu.eduName}</td>
            <td>${user.prof.profName}</td>
            <td>${user.email}</td>
            <td>${user.state.stateName}</td>
            <td>
                <button class="btn btn-sm btn-info" data-moveable="inside" data-toggle="modal" data-target="#update" 
                onclick="updateUser(${user.userId})">编辑</button>
                <button class="btn btn-sm btn-danger" onclick="deleteUser(${user.userId})">删除</button>
                <button class="btn btn-sm btn-warning" onclick="forbidUser(${user.userId})">禁用</button>
            </td>
        </tr>
        `;
        $data.append(str);
    }
    let remainder = pager.state.recPerPage - userList.length;
    for (let i = 0; i < remainder; i++) {
        let tr = $('<tr> </tr>');
        for (let j = 0; j < 9; j++) {
            let $td = $('<td> </td>');
            tr.append($td);
        }
        $data.append(tr);
    }
}

function refreshEduSelect($select, data) {
    $select.empty();
    for (let arg of data) {
        let $option = $('<option>');
        $option.val(arg.eduId);
        $option.html(arg.eduName);
        $select.append($option);
    }
}

function refreshSearchEduSelect($select, data) {
    $select.empty();
    let $option = $('<option>');
    $option.val("");
    $option.html("全部");
    $select.append($option);
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

function updateUser(userId) {
    for (let user of userList) {
        if (user.userId === userId) {
            modifyUser = user;
            $('#update-name').val(user.name);
            $(`#update-sex input[name="sex"][value=${user.sex}]`).prop('checked', true);
            $('#update-mobilePhone').val(user.mobilePhone);
            $('#update-email').val(user.email);
            $('#update-edu').val(user.edu.eduId);
            $('#update-prof').val(user.prof.profId);
            break;
        }
    }
}

function deleteUser(userId) {
    layer.confirm('确定删除吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: 'user/delete',
            type: 'post',
            dataType: 'json',
            data: {
                userId: userId
            },
            success(resp) {
                if (resp.errCode === 0) {
                    layer.alert("删除成功！");
                    search();
                    return;
                }
                layer.alert(resp.errMsg);
            }
        });
    });
}

function forbidUser(userId) {
    layer.confirm('确定禁用吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: 'user/modifyState',
            type: 'post',
            dataType: 'json',
            data: {
                userId: userId
            },
            success(resp) {
                if (resp.errCode === 0) {
                    layer.alert("禁用成功！");
                    search();
                    return;
                }
                layer.alert(resp.errMsg);
            }
        });
    });
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
            userId: modifyUser.userId,
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
                search();
                return;
            }
            layer.alert(resp.errMsg);
        }
    });
}


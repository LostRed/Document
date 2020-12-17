let adminList = [];
let roleList = [];
let stateList = [];
let $pager;
let pager;
let modifyAdmin;
//初始化
$(function () {
    $.ajax({
        url: 'role/findAll',
        type: 'get',
        dataType: 'json',
        success(resp) {
            roleList = resp.data;
            refreshRoleSelect($('#insert-role'), roleList);
            refreshRoleSelect($('#update-role'), roleList);
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
    $.ajax({
        url: "admin/pageFindByCondition",
        type: "get",
        dataType: "json",
        data: {
            page: pager.state.page,
            recPerPage: pager.state.recPerPage,
            username: username
        },
        success(resp) {
            if (resp.errCode === 0) {
                pager.set({
                    recTotal: resp.data.recTotal
                });
                adminList = resp.data.list;
                showData();
            }
        }
    });
}

function showData() {
    let $data = $('#data');
    $data.empty();
    for (let admin of adminList) {
        let str = `
        <tr>
            <td>${admin.adminId}</td>
            <td>${admin.username}</td>
            <td>${admin.name}</td>
            <td>${admin.role.roleName}</td>
            <td>${admin.state.stateName}</td>
            <td>
                <button class="btn btn-sm btn-info" data-moveable="inside" data-toggle="modal" data-target="#update" 
                onclick="updateAdmin(${admin.adminId})">编辑</button>
                <button class="btn btn-sm btn-danger" onclick="deleteAdmin(${admin.adminId})">删除</button>
                <button class="btn btn-sm btn-warning" onclick="forbidAdmin(${admin.adminId})">禁用</button>
                <button class="btn btn-sm btn-primary" onclick="resetPassword(${admin.adminId})">重置密码</button>
            </td>
        </tr>
        `;
        $data.append(str);
    }
    let remainder = pager.state.recPerPage - adminList.length;
    for (let i = 0; i < remainder; i++) {
        let tr = $('<tr> </tr>');
        for (let j = 0; j < 6; j++) {
            let $td = $('<td> </td>');
            tr.append($td);
        }
        $data.append(tr);
    }
}

function refreshRoleSelect($select, data) {
    $select.empty();
    for (let arg of data) {
        let $option = $('<option>');
        $option.val(arg.roleId);
        $option.html(arg.roleName);
        $select.append($option);
    }
}

function updateAdmin(adminId) {
    for (let admin of adminList) {
        if (admin.adminId === adminId) {
            modifyAdmin = admin;
            $('#update-name').val(admin.name);
            $('#update-role').val(admin.role.roleId);
            break;
        }
    }
}

function resetForm() {
    $('#insert-username').val("");
    $('#insert-password').val("");
    $('#insert-name').val("");
    $('#insert-role option:first').prop('selected', true);
}

function deleteAdmin(adminId) {
    layer.confirm('确定删除吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: 'admin/delete',
            type: 'post',
            dataType: 'json',
            data: {
                adminId: adminId
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

function forbidAdmin(adminId) {
    layer.confirm('确定禁用吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: 'admin/modifyState',
            type: 'post',
            dataType: 'json',
            data: {
                adminId: adminId
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

function resetPassword(adminId) {
    layer.confirm('确定重置吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: 'admin/modifyPassword',
            type: 'post',
            dataType: 'json',
            data: {
                adminId: adminId
            },
            success(resp) {
                if (resp.errCode === 0) {
                    layer.alert("重置成功！");
                    search();
                    return;
                }
                layer.alert(resp.errMsg);
            }
        });
    });
}

function insertCommit() {
    let $username = $('#insert-username');
    let $password = $('#insert-password');
    let $name = $('#insert-name').val();
    let $role = $('#insert-role option:selected');
    if ($username.val().length < 6) {
        $username.addClass('input-error');
        return;
    }
    if ($password.val().length < 6) {
        $password.addClass('input-error');
        return;
    }
    if ($name.val() === "") {
        $name.addClass('input-error');
        return;
    }
    $.ajax({
        url: 'admin/add',
        type: 'post',
        dataType: 'json',
        data: {
            username: $username.val(),
            password: $password.val(),
            name: $name.val(),
            roleId: $role.val()
        },
        success(resp) {
            if (resp.errCode === 0) {
                layer.alert("新增成功！");
                resetForm();
                $('#insert').modal('hide');
                search();
                return;
            }
            layer.alert(resp.errMsg);
        }
    });
}

function updateCommit() {
    let $name = $('#update-name');
    let $role = $('#update-role option:selected');
    if ($name.val() === "") {
        $name.addClass('input-error');
        return;
    }
    $.ajax({
        url: 'admin/modify',
        type: 'post',
        dataType: 'json',
        data: {
            adminId: modifyAdmin.adminId,
            name: $name.val(),
            roleId: $role.val()
        },
        success(resp) {
            if (resp.errCode === 0) {
                layer.alert("修改成功！");
                resetForm();
                $('#update').modal('hide');
                search();
                return;
            }
            layer.alert(resp.errMsg);
        }
    });
}

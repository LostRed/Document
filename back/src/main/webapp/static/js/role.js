let roleList = [];
let modifyRole;
//初始化
$(function () {
    search();
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

function search() {
    $.ajax({
        url: 'role/findAll',
        type: 'get',
        dataType: 'json',
        success(resp) {
            roleList = resp.data;
            showData();
        }
    });
}

function showData() {
    let $data = $('#data');
    $data.empty();
    for (let role of roleList) {
        let str = `
        <tr>
            <td>${role.roleId}</td>
            <td>${role.roleName}</td>
            <td>
                <button class="btn btn-sm btn-info" data-moveable="inside" data-toggle="modal" data-target="#update" 
                onclick="updateRole(${role.roleId})">编辑</button>
                <button class="btn btn-sm btn-danger" onclick="deleteRole(${role.roleId})">删除</button>
            </td>
        </tr>
        `;
        $data.append(str);
    }
}

function updateRole(roleId) {
    for (let role of roleList) {
        if (role.roleId === roleId) {
            modifyRole = role;
            $('#update-roleName').val(role.roleName);
            break;
        }
    }
}

function resetForm() {
    $('#insert-roleName').val("");
}

function insertCommit() {
    let $roleName = $('#insert-roleName');
    if ($roleName.val() === "") {
        $roleName.addClass('input-error');
        return;
    }
    $.ajax({
        url: 'role/add',
        type: 'post',
        dataType: 'json',
        data: {
            roleName: $roleName.val()
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
    let $roleName = $('#update-roleName');
    if ($roleName.val() === "") {
        $roleName.addClass('input-error');
        return;
    }
    $.ajax({
        url: 'role/modify',
        type: 'post',
        dataType: 'json',
        data: {
            roleId: modifyRole.roleId,
            roleName: $roleName.val()
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

function deleteRole(roleId) {
    layer.confirm('确定删除吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        for (let role of roleList) {
            if (role.roleId === roleId) {
                modifyRole = role;
                break;
            }
        }
        $.ajax({
            url: 'role/delete',
            type: 'post',
            dataType: 'json',
            data: {
                roleId: roleId
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

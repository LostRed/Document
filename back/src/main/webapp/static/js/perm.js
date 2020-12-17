let roleList = [];
let menuList = [];
let modifyRoleId;
let $role = $('#role-list');

let access = new Access({
    el: 'menus', // 要把菜单打印到哪个节点去, 使用id值
    accessName: 'menuName', // 菜单实体类的菜单名称属性
    accessId: 'menuId', // 菜单实体类的id属性
    parentId: 'parentId', // 菜单实体类的父级属性
    fkAccessId: 'menuId' // 角色菜单中间实体的菜单id(外键)
})

$(function () {
    $.ajax({
        url: 'role/findAll',
        type: 'get',
        dataType: 'json',
        success(resp) {
            roleList = resp.data;
            refreshRoleSelect($role, roleList);
        }
    });
    $.ajax({
        url: 'menu/findAll',
        type: 'get',
        dataType: 'json',
        success(resp) {
            menuList = resp.data;
            access.setAccessList(menuList);
            access.renderAccess();
        }
    });
    $role.change(function () {
        let roleId = $(this).val();
        modifyRoleId = roleId;
        if (!isNaN(roleId)) {
            findPerm(roleId);
        }
    });
});

function refreshRoleSelect($select, data) {
    $select.empty();
    let $option = $('<option>');
    $option.val(0);
    $option.html('请选择角色');
    $select.append($option);
    for (let arg of data) {
        let $option = $('<option>');
        $option.val(arg.roleId);
        $option.html(arg.roleName);
        $select.append($option);
    }
}

function findPerm(roleId) {
    $.ajax({
        url: 'menu/findByRoleId',
        type: 'get',
        dataType: 'json',
        data: {
            roleId: roleId
        },
        success(resp) {
            let list = resp.data;
            access.setRoleAccess(list);
            access.renderAccess();
        }
    });
}

function save() {
    let menuIds = access.getValue();
    $.ajax({
        url: 'role/modifyPermByRoleId',
        type: 'post',
        dataType: 'json',
        data: {
            roleId: modifyRoleId,
            menuIds: JSON.stringify(menuIds)
        },
        success(resp) {
            if (resp.errCode === 0) {
                layer.alert("修改成功！");
                return;
            }
            layer.alert(resp.errMsg);
        }
    });
}
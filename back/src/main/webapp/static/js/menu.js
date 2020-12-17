let menuList = [];
let modifyMenu;
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
        url: 'menu/findAll',
        type: 'get',
        dataType: 'json',
        success(resp) {
            menuList = resp.data;
            refreshMenuSelect($('#insert-parentMenu'), menuList);
            refreshMenuSelect($('#update-parentMenu'), menuList);
            showData();
        }
    });
}

function showData() {
    let $data = $('#data');
    $data.empty();
    for (let menu of menuList) {
        let parentMenuName = "";
        let url = "";
        if (menu.url !== undefined) {
            url = menu.url;
        }
        if (menu.parentId !== 0) {
            for (let parentMenu of menuList) {
                if (parentMenu.menuId === menu.parentId) {
                    parentMenuName = parentMenu.menuName;
                    break;
                }
            }
        }
        let str = `
         <tr>
            <td>
                <input class="form-control input-sm" type="text">
            </td>
            <td>${menu.menuId}</td>
            <td>${menu.menuName}</td>
            <td>${parentMenuName}</td>
            <td><i class="icon ${menu.iconClass}"></i></td>
            <td>${url}</td>
            <td>
               <button class="btn btn-sm btn-info" data-moveable="inside" data-toggle="modal" data-target="#update" 
                onclick="updateMenu(${menu.menuId})">编辑</button>
                <button class="btn btn-sm btn-danger" onclick="deleteMenu(${menu.menuId})">删除</button>
            </td>
        </tr>
        `;
        $data.append(str);
    }
    let str = `
        <tr>
            <td>
                <button class="btn btn-primary">排序</button>
            </td>
            <td colspan="6"></td>
        </tr>
    `;
    $data.append(str);
}

function refreshMenuSelect($select, data) {
    $select.empty();
    let $option = $('<option>');
    $option.val(0);
    $option.html('无');
    $select.append($option);
    for (let arg of data) {
        if (arg.parentId === 0 && arg.url == null) {
            let $option = $('<option>');
            $option.val(arg.menuId);
            $option.html(arg.menuName);
            $select.append($option);
        }
    }
}

function updateMenu(menuId) {
    for (let menu of menuList) {
        if (menu.menuId === menuId) {
            modifyMenu = menu;
            $('#update-menuName').val(menu.menuName);
            $('#update-parentMenu').val(menu.parentId);
            $('#update-iconClass').val(menu.iconClass);
            $('#update-url').val(menu.url);
            break;
        }
    }
}

function resetForm() {
    $('#insert-menuName').val("");
    $('#insert-parentMenu option:first').prop('selected', true);
    $('#insert-iconClass').val("");
    $('#insert-url').val("");
}

function insertCommit() {
    let $menuName = $('#insert-menuName');
    let $parentMenu = $('#insert-parentMenu');
    let $iconClass = $('#insert-iconClass');
    let $url = $('#insert-url');
    if ($menuName.val() === "") {
        $menuName.addClass('input-error');
        return;
    }
    $.ajax({
        url: 'menu/add',
        type: 'post',
        dataType: 'json',
        data: {
            menuName: $menuName.val(),
            parentId: $parentMenu.val(),
            iconClass: $iconClass.val(),
            url: $url.val()
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
    let $menuName = $('#update-menuName');
    let $parentMenu = $('#update-parentMenu');
    let $iconClass = $('#update-iconClass');
    let $url = $('#update-url');
    if ($menuName.val() === "") {
        $menuName.addClass('input-error');
        return;
    }
    $.ajax({
        url: 'menu/modify',
        type: 'post',
        dataType: 'json',
        data: {
            menuId: modifyMenu.menuId,
            menuName: $menuName.val(),
            parentId: $parentMenu.val(),
            iconClass: $iconClass.val(),
            url: $url.val()
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

function deleteMenu(menuId) {
    layer.confirm('确定删除吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: 'menu/delete',
            type: 'post',
            dataType: 'json',
            data: {
                menuId: menuId
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

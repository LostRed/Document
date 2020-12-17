let docTypeList = [];
let modifyDocType;
//初始化
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
    search();
});

function search() {
    $.ajax({
        url: "docType/findAll",
        type: "get",
        dataType: "json",
        success(resp) {
            if (resp.errCode === 0) {
                docTypeList = resp.data;
                showData();
            }
        }
    });
}

function showData() {
    let $data = $('#data');
    $data.empty();
    for (let docType of docTypeList) {
        let str = `
        <tr>
            <td>${docType.typeId}</td>
            <td>${docType.typeName}</td>
            <td>${docType.uploadPoint}</td>
            <td>${docType.state.stateName}</td>
            <td>
                <button class="btn btn-sm btn-info" data-moveable="inside" data-toggle="modal" data-target="#update" 
                onclick="updateDocType(${docType.typeId})">编辑</button>
                <button class="btn btn-sm btn-danger" onclick="deleteDocType(${docType.typeId})">删除</button>
                <button class="btn btn-sm btn-warning" onclick="forbidDocType(${docType.typeId})">禁用</button>
            </td>
        </tr>
        `;
        $data.append(str);
    }
}

function updateDocType(typeId) {
    for (let docType of docTypeList) {
        if (docType.typeId === typeId) {
            modifyDocType = docType;
            $('#update-typeName').val(docType.typeName);
            $('#update-uploadPoint').val(docType.uploadPoint);
            break;
        }
    }
}

function resetForm() {
    $('#insert-typeName').val("");
    $('#insert-uploadPoint').val("");
}

function deleteDocType(typeId) {
    layer.confirm('确定删除吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: 'doc/delete',
            type: 'post',
            dataType: 'json',
            data: {
                typeId: typeId
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

function forbidDocType(typeId) {
    layer.confirm('确定禁用吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: 'docType/modifyState',
            type: 'post',
            dataType: 'json',
            data: {
                typeId: typeId
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

function insertCommit() {
    let $typeName = $('#insert-typeName');
    let $uploadPoint = $('#insert-uploadPoint');
    if ($typeName.val() === "") {
        $typeName.addClass('input-error');
        return;
    }
    if (!/^\d*$/.test($uploadPoint.val())) {
        $uploadPoint.addClass('input-error');
        return;
    }
    $.ajax({
        url: 'docType/add',
        type: 'post',
        dataType: 'json',
        data: {
            typeName: $typeName.val(),
            uploadPoint: $uploadPoint.val()
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
    let $typeName = $('#update-typeName');
    let $uploadPoint = $('#update-uploadPoint');
    if ($typeName.val() === "") {
        $typeName.addClass('input-error');
        return;
    }
    if (!/^\d*$/.test($uploadPoint.val())) {
        $uploadPoint.addClass('input-error');
        return;
    }
    $.ajax({
        url: 'docType/modify',
        type: 'post',
        dataType: 'json',
        data: {
            typeId: modifyDocType.typeId,
            typeName: $typeName.val(),
            uploadPoint: $uploadPoint.val()
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

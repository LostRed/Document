//初始化
function initUploadRecord() {
    $.ajax({
        url: "docType/findAll",
        type: "get",
        dataType: "json",
        success(resp) {
            if (resp.errCode === 0) {
                docTypeList = resp.data;
                refreshDocTypeSelect($('#upload-docType'), docTypeList);
                refreshDocTypeSelect($('#uploadDoc-docType'), docTypeList);
            }
        }
    });
    $.ajax({
        url: "state/findAll",
        type: "get",
        dataType: "json",
        success(resp) {
            if (resp.errCode === 0) {
                stateList = resp.data;
                refreshStateSelect($('#state'), stateList);
            }
        }
    });
    $pager1 = $('#pager1');
    pager1 = $pager1.data('zui.pager');
    pager1.set({
        page: 1,
        recTotal: 0,
        recPerPage: 5
    });
    $pager1.on('onPageChange', function (e, state, oldState) {
        if (state.page !== oldState.page) {
            searchDoc();
        }
    });
    searchDoc();
}

function refreshDocTypeSelect($select, data) {
    $select.empty();
    let $option = $('<option>');
    $option.val('');
    $option.html('全部');
    $select.append($option);
    for (let arg of data) {
        let $option = $('<option>');
        $option.val(arg.typeId);
        $option.html(arg.typeName);
        $select.append($option);
    }
}

function refreshStateSelect($select, data) {
    $select.empty();
    let $option = $('<option>');
    $option.val('');
    $option.html('全部');
    $select.append($option);
    for (let arg of data) {
        if (arg.stateType === "文档状态") {
            let $option = $('<option>');
            $option.val(arg.stateId);
            $option.html(arg.stateName);
            $select.append($option);
        }
    }
}

function searchDoc() {
    let startTime = $('#upload-startTime').val();
    let endTime = $('#upload-endTime').val();
    let docTypeId = $('#upload-docType').val();
    let stateId = $('#state').val();
    let conditions = {};
    conditions['startTime'] = new Date(startTime);
    conditions['endTime'] = new Date(endTime);
    conditions['docTypeId'] = docTypeId;
    conditions['stateId'] = stateId;
    conditions['userId'] = loginUser.userId;
    $.ajax({
        url: "doc/pageFindByCondition",
        type: "get",
        dataType: "json",
        data: {
            page: pager1.state.page,
            recPerPage: pager1.state.recPerPage,
            conditions: JSON.stringify(conditions)
        },
        success(resp) {
            if (resp.errCode === 0) {
                pager1.set({
                    recTotal: resp.data.recTotal
                });
                docList = resp.data.list;
                showDocData();
            }
        }
    });
}

function showDocData() {
    let $data = $('#upload-table tbody');
    $data.empty();
    for (let doc of docList) {
        let str = `
            <tr>
                <td>${doc.docTitle}</td>
                <td style="padding: 2px;">
                   <div class="row" style="width: 90px;">
                        <div class="col-xs-12">
                            <img src="${doc.docCover}" alt="图片"/>
                        </div>
                    </div>
                </td>
                <td class="hidden-xs">${new Date(doc.uploadTime).format('yyyy-MM-dd HH:mm:ss')}</td>
                <td class="hidden-xs">${doc.downloadPoint}</td>
                <td class="hidden-xs">${doc.docType.typeName}</td>
                <td>${doc.state.stateName}</td>
            </tr>
        `;
        $data.append(str);
    }
    let remainder = pager1.state.recPerPage - docList.length;
    for (let i = 0; i < remainder; i++) {
        let tr = $('<tr> </tr>');
        for (let j = 0; j < 3; j++) {
            let $td = $('<td> </td>');
            tr.append($td);
        }
        for (let j = 0; j < 3; j++) {
            let $td = $('<td class="hidden-xs"> </td>');
            tr.append($td);
        }
        $data.append(tr);
    }
}

Date.prototype.format = function (fmt) {
    let o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "H+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function uploadDoc() {
    let docTitle = $('#uploadDoc-docTitle').val();
    let downloadPoint = $('#uploadDoc-downloadPoint').val();
    let docDesc = $('#uploadDoc-docDesc').val();
    let cover = $('#cover').prop('files')[0];
    let file = $('#file').prop('files')[0];
    let formData = new FormData();
    //key是后端要接受的变量名，类似于表单的name
    //value是值
    formData.append('docTitle', docTitle);
    formData.append('downloadPoint', downloadPoint);
    formData.append('docDesc', docDesc);
    formData.append('cover', cover);
    formData.append('document', file);
    $.ajax({
        url: 'doc/uploadFile',
        type: 'post',
        dataType: 'json',
        contentType: false,
        processData: false,
        data: formData,
        success: function (resp) {
            if (resp.errCode === 0) {
                layer.alert("上传成功！");
                $('#uploadDoc').modal('hide');
                searchDoc();
                return;
            }
            layer.alert(resp.errMsg);
        }
    });
}

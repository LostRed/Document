let docTypeList = [];
let docList = [];
let $pager;
let pager;

//初始化
$(function () {
    $.ajax({
        url: 'state/findAll',
        type: 'get',
        dataType: 'json',
        success(resp) {
            stateList = resp.data;
            refreshStateSelect($('#state'), stateList);
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
    search();
});

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

function search() {
    let docTitle = $('#docTitle').val();
    let username = $('#username').val();
    let startTime = $('#startTime').val();
    let endTime = $('#endTime').val();
    let stateId = $('#state').val();
    let conditions = {};
    conditions['docTitle'] = docTitle;
    conditions['username'] = username;
    conditions['startTime'] = new Date(startTime);
    conditions['endTime'] = new Date(endTime);
    conditions['stateId'] = stateId;
    $.ajax({
        url: "doc/pageFindByCondition",
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
                docList = resp.data.list;
                showData();
            }
        }
    });
}

function showData() {
    let $data = $('#data');
    $data.empty();
    for (let doc of docList) {
        let str = `
        <tr>
            <td>${doc.docId}</td>
            <td>${doc.uploadUser.username}</td>
            <td>${doc.docTitle}</td>
            <td>${doc.docType.typeName}</td>
            <td style="padding: 2px;">
                <div class="row" style="width: 90px;">
                    <div class="col-xs-12">
                        <img src="${doc.docCover}" alt="图片"/>
                    </div>
                </div>
            </td>
            <td>${doc.downloadPoint}</td>
            <td>${new Date(doc.uploadTime).format('yyyy-MM-dd HH:mm:ss')}</td>
            <td>${doc.state.stateName}</td>
            <td>
                <button class="btn btn-sm btn-info" onclick="pass(${doc.docId})"><i class="icon icon-check"></i></button>
                <button class="btn btn-sm btn-danger" onclick="unPass(${doc.docId})"><i class="icon icon-times"></i></button>
                <button class="btn btn-sm btn-warning" onclick="deleteDoc(${doc.docId})"><i class="icon icon-trash"></i></button>
            </td>
        </tr>
        `;
        $data.append(str);
    }
    let remainder = pager.state.recPerPage - docList.length;
    for (let i = 0; i < remainder; i++) {
        let tr = $('<tr> </tr>');
        for (let j = 0; j < 9; j++) {
            let $td = $('<td> </td>');
            tr.append($td);
        }
        $data.append(tr);
    }
}

function pass(docId) {
    layer.confirm('确定通过吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: 'doc/pass',
            type: 'post',
            dataType: 'json',
            data: {
                docId: docId
            },
            success(resp) {
                if (resp.errCode === 0) {
                    layer.alert("通过成功！");
                    search();
                    return;
                }
                layer.alert(resp.errMsg);
            }
        });
    });
}

function unPass(docId) {
    layer.confirm('确定退回吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: 'doc/unPass',
            type: 'post',
            dataType: 'json',
            data: {
                docId: docId
            },
            success(resp) {
                if (resp.errCode === 0) {
                    layer.alert("退回成功！");
                    search();
                    return;
                }
                layer.alert(resp.errMsg);
            }
        });
    });
}

function deleteDoc(docId) {
    layer.confirm('确定删除吗？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: 'doc/delete',
            type: 'post',
            dataType: 'json',
            data: {
                docId: docId
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
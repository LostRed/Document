//初始化
function initDownloadRecord() {
    $.ajax({
        url: "docType/findAll",
        type: "get",
        dataType: "json",
        success(resp) {
            if (resp.errCode === 0) {
                docTypeList = resp.data;
                refreshDocTypeSelect($('#download-docType'), docTypeList);
            }
        }
    });
    $pager2 = $('#pager2');
    pager2 = $pager2.data('zui.pager');
    pager2.set({
        page: 1,
        recTotal: 0,
        recPerPage: 5
    });
    $pager2.on('onPageChange', function (e, state, oldState) {
        if (state.page !== oldState.page) {
            searchDownloadRecord();
        }
    });
    searchDownloadRecord();
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

function searchDownloadRecord() {
    let startTime = $('#download-startTime').val();
    let endTime = $('#download-endTime').val();
    let docTitle = $('#docTitle').val();
    let docTypeId = $('#download-docType').val();
    let conditions = {};
    conditions['startTime'] = new Date(startTime);
    conditions['endTime'] = new Date(endTime);
    conditions['docTitle'] = docTitle;
    conditions['docTypeId'] = docTypeId;
    conditions['userId'] = loginUser.userId;
    $.ajax({
        url: "downloadRecord/pageFindByCondition",
        type: "get",
        dataType: "json",
        data: {
            page: pager2.state.page,
            recPerPage: pager2.state.recPerPage,
            conditions: JSON.stringify(conditions)
        },
        success(resp) {
            if (resp.errCode === 0) {
                pager2.set({
                    recTotal: resp.data.recTotal
                });
                downloadRecordList = resp.data.list;
                showDownloadRecordData();
            }
        }
    });
}

function showDownloadRecordData() {
    let $data = $('#download-table tbody');
    $data.empty();
    for (let downloadRecord of downloadRecordList) {
        let str = `
            <tr>
                <td>${downloadRecord.doc.docTitle}</td>
                <td style="padding: 2px;">
                    <div class="row" style="width: 90px;">
                        <div class="col-xs-12">
                            <img src="${downloadRecord.doc.docCover}" alt="图片"/>
                        </div>
                    </div>
                </td>
                <td class="hidden-xs">${downloadRecord.doc.uploadUser.username}</td>
                <td class="hidden-xs">${new Date(downloadRecord.downloadTime).format('yyyy-MM-dd HH:mm:ss')}</td>
                <td class="hidden-xs">${downloadRecord.doc.downloadPoint}</td>
                <td class="hidden-xs">${downloadRecord.doc.docType.typeName}</td>
                <td>
                    <button class="btn btn-success btn-sm" type="button" data-moveable="inside" data-toggle="modal"
                        data-target="#docInfo" onclick="docInfo(${downloadRecord.recordId})">查看</button>
                </td>
            </tr>
        `;
        $data.append(str);
    }
    let remainder = pager2.state.recPerPage - downloadRecordList.length;
    for (let i = 0; i < remainder; i++) {
        let tr = $('<tr> </tr>');
        for (let j = 0; j < 3; j++) {
            let $td = $('<td> </td>');
            tr.append($td);
        }
        for (let j = 0; j < 4; j++) {
            let $td = $('<td class="hidden-xs"> </td>');
            tr.append($td);
        }
        $data.append(tr);
    }
}

function docInfo(recordId) {
    for (let downloadRecord of downloadRecordList) {
        if (downloadRecord.recordId === recordId) {
            $('#docInfo-docTitle').val(downloadRecord.doc.docTitle);
            $('#docInfo-docType').val(downloadRecord.doc.docType.typeName);
            $('#docInfo-downloadPoint').val(downloadRecord.doc.downloadPoint);
            $('#docInfo-downloadTime').val(new Date(downloadRecord.downloadTime).format('yyyy-MM-dd HH:mm:ss'));
            $('#docInfo-uploadUser').val(downloadRecord.doc.uploadUser.username);
            $('#docInfo-docDesc').val(downloadRecord.doc.docDesc);
            break;
        }
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

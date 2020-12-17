//初始化
function initPointRecord() {
    $pager3 = $('#pager3');
    pager3 = $pager3.data('zui.pager');
    pager3.set({
        page: 1,
        recTotal: 0,
        recPerPage: 5
    });
    $pager3.on('onPageChange', function (e, state, oldState) {
        if (state.page !== oldState.page) {
            searchPointRecord();
        }
    });
    searchPointRecord();
}

function searchPointRecord() {
    let startTime = $('#occur-startTime').val();
    let endTime = $('#occur-endTime').val();
    let type = $('#type').val();
    let conditions = {};
    conditions['startTime'] = new Date(startTime);
    conditions['endTime'] = new Date(endTime);
    conditions['type'] = type;
    conditions['userId'] = loginUser.userId;
    $.ajax({
        url: "pointRecord/pageFindByCondition",
        type: "get",
        dataType: "json",
        data: {
            page: pager3.state.page,
            recPerPage: pager3.state.recPerPage,
            conditions: JSON.stringify(conditions)
        },
        success(resp) {
            if (resp.errCode === 0) {
                pager3.set({
                    recTotal: resp.data.recTotal
                });
                pointRecordList = resp.data.list;
                showPointRecordData();
            }
        }
    });
}

function showPointRecordData() {
    let $data = $('#point-table tbody');
    $data.empty();
    for (let pointRecord of pointRecordList) {
        let str = `
            <tr>
                <td>${new Date(pointRecord.time).format("yyyy-MM-dd HH:mm:ss")}</td>
                <td>${pointRecord.name}</td>
                <td>${pointRecord.type}</td>
                <td>${pointRecord.point}</td>
            </tr>
        `;
        $data.append(str);
    }
    let remainder = pager3.state.recPerPage - pointRecordList.length;
    for (let i = 0; i < remainder; i++) {
        let tr = $('<tr> </tr>');
        for (let j = 0; j < 4; j++) {
            let $td = $('<td> </td>');
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

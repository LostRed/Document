let logList = [];
let $pager;
let pager;
//初始化
$(function () {
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

function search() {
    let $startTime = $('#startTime');
    let $endTime = $('#endTime');
    let $username = $('#username');
    let conditions = {};
    conditions['startTime'] = new Date($startTime.val());
    conditions['endTime'] = new Date($endTime.val());
    conditions['username'] = $username.val();
    $.ajax({
        url: 'log/pageFindByCondition',
        type: 'get',
        dataType: 'json',
        data: {
            page: pager.state.page,
            recPerPage: pager.state.recPerPage,
            conditions: JSON.stringify(conditions)
        },
        success(resp) {
            pager.set({
                recTotal: resp.data.recTotal
            });
            logList = resp.data.list;
            showData();
        }
    });
}

function showData() {
    let $data = $('#data');
    $data.empty();
    for (let log of logList) {
        let username;
        if (log.user != null) {
            username = log.user.username;
        } else {
            username = log.admin.username;
        }
        let str = `
         <tr>
            <td>${username}</td>
            <td>${log.operation}</td>
            <td>${new Date(log.time).format("yyyy-MM-dd HH:mm:ss")}</td>
        </tr>
        `;
        $data.append(str);
    }
    let remainder = pager.state.recPerPage - logList.length;
    for (let i = 0; i < remainder; i++) {
        let tr = $('<tr> </tr>');
        for (let j = 0; j < 3; j++) {
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

let hotDocList = [];
let newDocList = [];
let docList = [];
let $pager;
let pager;
let loginUser;
//初始化
$(function () {
    getLoginUser();
    $pager = $('#pager');
    pager = $pager.data('zui.pager');
    pager.set({
        page: 1,
        recTotal: 0,
        recPerPage: 12
    });
    $pager.on('onPageChange', function (e, state, oldState) {
        if (state.page !== oldState.page) {
            search();
        }
    });
    $('#search-block').hide();
    $('#mobile-nav a').click(function () {
        $(this).addClass('active');
        $(this).siblings().removeClass('active');
    });
    $('#btn-search').click(search);
    $('#doc-list').click(list);
    $('#list').click(list);
    list();
});

function getLoginUser() {
    $.ajax({
        url: 'loginUser',
        type: 'get',
        dataType: 'json',
        success(resp) {
            if (resp.errCode === 0) {
                loginUser = resp.data.loginUser;
                showLoginUser(loginUser);
            }
        }
    });
}

function showLoginUser(loginUser) {
    if (loginUser != null) {
        str = `
            <li><a onclick="list()">文档列表</a></li>
            <li><a href="personal">个人中心</a></li>
            <li><a>欢迎回来，${loginUser.username}</a></li>
        `;
        $('#right-list').html(str);
    }
}

function list() {
    searchHotDoc();
    searchNewDoc();
}

function search() {
    let $docTitle = $('#doc-title');
    let conditions = {};
    conditions['docTitle'] = $docTitle.val();
    conditions['stateId'] = 10;
    $.ajax({
        url: "doc/pageFindByCondition",
        type: "get",
        dataType: "json",
        data: {
            page: pager.state.page,
            recPerPage: pager.state.recPerPage,
            conditions: JSON.stringify(conditions),
            orderField: "download_total",
            orderType: "desc"
        },
        success(resp) {
            if (resp.errCode === 0) {
                pager.set({
                    recTotal: resp.data.recTotal
                });
                docList = resp.data.list;
                $('#hot-block').hide();
                $('#new-block').hide();
                $('#search-block').show();
                showData($('#search-doc'), docList);
            }
        }
    });
}

function searchHotDoc() {
    let conditions = {};
    conditions['stateId'] = 10;
    $.ajax({
        url: "doc/pageFindByCondition",
        type: "get",
        dataType: "json",
        data: {
            conditions: JSON.stringify(conditions),
            page: 1,
            recPerPage: 12,
            orderField: "download_total",
            orderType: "desc"
        },
        success(resp) {
            if (resp.errCode === 0) {
                hotDocList = resp.data.list;
                $('#hot-block').show();
                $('#search-block').hide();
                showData($('#hot-doc'), hotDocList);
            }
        }
    });
}

function searchNewDoc() {
    let conditions = {};
    conditions['stateId'] = 10;
    $.ajax({
        url: "doc/pageFindByCondition",
        type: "get",
        dataType: "json",
        data: {
            conditions: JSON.stringify(conditions),
            page: 1,
            recPerPage: 12,
            orderField: "upload_time",
            orderType: "desc"
        },
        success(resp) {
            if (resp.errCode === 0) {
                newDocList = resp.data.list;
                $('#new-block').show();
                $('#search-block').hide();
                showData($('#new-doc'), newDocList);
            }
        }
    });
}

function showData($data, list) {
    $data.empty();
    for (let doc of list) {
        let str = `
        <a href="doc_info?docId=${doc.docId}">
            <div class="col-xs-6 col-sm-4 col-md-3">
                <div class="panel">
                    <div class="panel-body">
                        <img src="${doc.docCover}">
                        <h4 class="title">${doc.docTitle}</h4>
                        <div class="info">
                            <span class="author">${doc.uploadUser.username}</span>
                            <span class="download">下载次数：${doc.downloadTotal}</span>
                        </div>
                    </div>
                </div>
            </div>
        </a>
        `;
        $data.append(str);
    }
}

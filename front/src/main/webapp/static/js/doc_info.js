let docId;
let doc;
let loginUser;
//初始化
$(function () {
    getLoginUser();
    docId = getUrlParam('docId');
    findDoc();
});

function findDoc() {
    $.ajax({
        url: "doc/findById",
        type: "get",
        dataType: "json",
        data: {
            docId: docId
        },
        success(resp) {
            if (resp.errCode === 0) {
                doc = resp.data;
                showData($('#pc'));
                showMobileData($('#mobile'));
            }
        }
    });
}

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
            <li><a href="main.html">文档列表</a></li>
            <li><a href="personal.html">个人中心</a></li>
            <li><a>欢迎回来，${loginUser.username}</a></li>
        `;
        $('#right-list').html(str);
    }
}

function getUrlParam(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    let r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

function showData($data) {
    $data.empty();
    let str = `
        <div class="row content-wrapper">
            <div class="col-xs-6">
                <img src="${doc.docCover}" alt="图片"/>
            </div>
            <div class="col-xs-6 doc-desc">
                <h2>${doc.docTitle}</h2>
                <div class="doc-point">
                    <p>下载积分：${doc.downloadPoint}</p>
                    <p>累计下载次数：${doc.downloadTotal}</p>
                </div>
                <div>
                    <p>上传者：${doc.uploadUser.username}</p>
                </div>
                <button class="btn btn-primary" type="button" onclick="doDownload()">立即下载</button>
            </div>
        </div>
        <div class="row content-wrapper">
            <div class="col-xs-12">
                <h2 class="doc-info">文档详情</h2>
                <hr>
                <p>
                    ${doc.docDesc}
                </p>
            </div>
        </div>
        `;
    $data.append(str);
}

function showMobileData($data) {
    $data.empty();
    let str = `
        <div class="row">
            <img src="${doc.docCover}" alt="图片"/>
        </div>
        <br>
        <div class="row">
            <div class="col-xs-12 doc-desc">
                <h2>${doc.docTitle}</h2>
                <div class="doc-point">
                    <p>下载积分：${doc.downloadPoint}</p>
                    <p>累计下载次数：${doc.downloadTotal}</p>
                </div>
                <div>
                    <p>上传者：${doc.uploadUser.username}</p>
                </div>
                <button class="btn btn-primary" type="button" onclick="doDownload()">立即下载</button>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-xs-12 doc-desc">
                <h2 class="doc-info">文档详情</h2>
                <hr>
                <p>
                     ${doc.docDesc}
                </p>
            </div>
        </div>
        <br>
        <br>
        <br>
    `;
    $data.append(str);
}

function doDownload() {
    $.ajax({
        url: 'doc/findDownloadRecordByUserIdAndDocId',
        type: 'get',
        dataType: 'json',
        data: {
            docId: docId
        },
        success(resp) {
            if (resp.errCode === 0) {
                open('doc/downloadFile?docId=' + docId);
                return;
            } else if (resp.errCode === 40001) {
                console.log("enter")
                layer.alert(resp.errMsg);
                return;
            }
            layer.confirm(resp.errMsg, {
                btn: ['确定', '取消'] //按钮
            }, function () {
                layer.closeAll();
                open('doc/downloadFile?docId=' + docId);
                findDoc();
            });
        }
    });
}
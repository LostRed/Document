let loginUser;
let eduList = [];
let profList = [];
let docList = [];
let stateList = [];
let docTypeList = [];
let downloadRecordList = [];
let pointRecordList = [];
let $pager1;
let pager1;
let $pager2;
let pager2;
let $pager3;
let pager3;
//初始化
$(function () {
    getLoginUser();
    $('#mobile-nav a').click(function () {
        $(this).addClass('active');
        $(this).siblings().removeClass('active');
    });
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
                myInfo();
            }
        }
    });
}

function showLoginUser(loginUser) {
    if (loginUser != null) {
        let str = `
            <li><a href="main">文档列表</a></li>
            <li><a href="personal">个人中心</a></li>
            <li><a>欢迎回来，${loginUser.username}</a></li>
        `;
        $('#right-list').html(str);
    }
}

function myInfo() {
    let $info = $('#info');
    $info.empty();
    let str = `
    <tbody>
    <tr>
        <td>用户名</td>
        <td>${loginUser.username}</td>
    </tr>
    <tr>
        <td>性别</td>
        <td>${loginUser.sex}</td>
    </tr>
    <tr>
        <td>学历</td>
        <td>${loginUser.edu.eduName}</td>
    </tr>
    <tr>
        <td>职业</td>
        <td>${loginUser.prof.profName}</td>
    </tr>
    <tr>
        <td>手机号</td>
        <td>${loginUser.mobilePhone}</td>
    </tr>
    <tr>
        <td>Email</td>
        <td>${loginUser.email}</td>
    </tr>
    </tbody>
    `;
    $info.html(str);
}

function uploadHistory() {
    initUploadRecord();
}

function downloadHistory() {
    initDownloadRecord();
}

function myPoint() {
    initPointRecord();
}

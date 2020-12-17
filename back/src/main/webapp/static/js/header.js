let loginAdmin;
let menuList = [];
$(function () {
    $.ajax({
        url: 'loginAdmin',
        type: 'get',
        dataType: 'json',
        success(resp) {
            if (resp.errCode === 0) {
                loginAdmin = resp.data.loginAdmin;
                showLoginUser(loginAdmin);
                $.ajax({
                    url: 'menu/findByRoleId',
                    type: 'get',
                    dataType: 'json',
                    data: {
                        roleId: loginAdmin.role.roleId
                    },
                    success(resp) {
                        menuList = resp.data;
                        //菜单数据
                        let tree = [];
                        parseTree(tree, menuList, 0);
                        //获取tree实例
                        let myTree = $('#treeMenu').data('zui.tree');
                        //更新数据
                        myTree.reload(tree);
                        myTree.toggle();
                        addEffect();
                    }
                });
            }
        }
    });
});

function showLoginUser(loginAdmin) {
    if (loginAdmin != null) {
        let str = `
            <li><a>欢迎回来，${loginAdmin.username}</a></li>
            <li><a href="logout">退出</a></li>
        `;
        $('#right-list').html(str);
    }
}

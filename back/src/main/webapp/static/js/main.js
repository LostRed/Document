function addEffect() {
    // 手动通过点击模拟高亮菜单项
    $('#treeMenu').on('click', 'a', function () {
        $('#treeMenu li.active').removeClass('active');
        $(this).closest('li').addClass('active');
    });
    $('#treeMenu a').click(function () {
        let url = $(this).attr('url');
        if (url !== 'undefined') {
            $('#iframe').prop('src', url);
        }
    });
}

function parseTree(tree, menuList, id) {
    for (let menu of menuList) {
        if (menu.parentId === id) {
            let obj = {
                html: `
                <a href="#" url="${menu.url}"> 
                    <i class="icon ${menu.iconClass}"></i>${menu.menuName}
                </a>
                `,
                children: []
            };
            tree.push(obj);
            parseTree(obj.children, menuList, menu.menuId);
        }
    }
}

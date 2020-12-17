
class Access {
    /**
     * @param {Object} obj
     * 构造函数 
     */
    constructor(obj) {
        //根节点类名
        this.config = {};
        this.config.rootClassName = 'cjl-class-root';
        //父节点类名
        this.config.parentClassName = 'cjl-class-parent';
        //子节点类名
        this.config.childClassName = 'cjl-class-child';
        //name名
        this.config.checkboxName = 'cjl-checkbox-name';

        if (!obj || typeof obj != 'object') return console.error('构造函数的的参数必须是一个对象');
        if (!obj.el) return console.error('参数必须传el属性,值是要渲染到页面的id值');
        if (!obj.accessName) return console.error('参数必须传accessName属性。值是权限对象的权限名');
        if (!obj.accessId) return console.error('参数必须传accessId属性。值是权限对象的权限id名');
        if (!obj.parentId) return console.error('参数必须传parentId属性。值是权限对象的父级id名');
        if (!obj.fkAccessId) return console.error('参数必须传fkAccessId属性。值是角色权限对象的权限id名');

        Object.assign(this.config, obj);
    }

    /**
     * 
     * @param {Array} accessList 
     * 设置权限列表
     */
    setAccessList(accessList) {
        if (!Array.isArray(accessList)) return console.error('setAccessList的参数必须是一个数组');
        const c = this.config;
        let b = accessList.every(v => v.hasOwnProperty(c.accessName) && v.hasOwnProperty(c.accessId) && v.hasOwnProperty(c.parentId));
        if (!b) return console.error(`accessList对象必须要有${c.accessId}、${c.accessName}和${c.parentId}字段 `);
        this.accessList = accessList.slice();
    }


    /**
     * 
     * @param {Array} roleAccess 
     * 设置角色-权限列表
     */
    setRoleAccess(roleAccess) {
        if (!Array.isArray(roleAccess)) return console.error('setRoleAccess的参数必须是一个数组');
        const c = this.config;
        let b = roleAccess.every(v => v.hasOwnProperty(c.fkAccessId));
        if (!b) return console.error(`roleAccess对象必须要有${c.fkAccessId}字段`);
        this.roleAccess = roleAccess.slice();
    }
    /**
     * 获取所有权限的值
     */
    getValue() {
        const node = this._getCheckboxNode();
        const arr = [];
        Array.prototype.forEach.call(node, v => {
            if (v.checked) arr.push(v.value);
        });
        return arr;
    }
    /**
     * 打印html结构
     */
    renderAccess() {
        this._removeEvent();
        let str = '';
        const access = this.accessList || [];
        const c = this.config;
        for (let i = 0; i < access.length; i++) {
            const a = access[i];
            if (a[c.parentId] !== 0) {
                continue;
            }
            str += ``;
            str += `<div class="${c.rootClassName}">
                        <label>
                            <input 
                                type="checkbox" 
                                data-group="${i}"
                                name="${c.checkboxName}" 
                                ${ this._inArray(a[c.accessId]) ? ' checked ' : ''}  
                                class="${c.parentClassName}" 
                                value="${a[c.accessId]}"  /> ${a[c.accessName]}
                        </label>
                        <div>`;
            for (let j = 0; j < access.length; j++) {
                const ch = access[j];
                if (ch[c.parentId] == a[c.accessId]) {
                    str += `<div>
                                <label>
                                    &nbsp;&nbsp;&nbsp;&nbsp;<input 
                                        type="checkbox"
                                        data-group="${i}"
                                        name="${c.checkboxName}" 
                                        ${ this._inArray(ch[c.accessId]) ? ' checked ' : ''}  
                                        class="${c.childClassName}" 
                                        value="${ch[c.accessId]}"  /> ${ch[c.accessName]}
                                </label>
                            </div>
                    `;
                }

            }
            str += '</div></div>';
        }
        document.getElementById(c.el).innerHTML = str;
        this._addEvent();
    }

    _inArray(id) {
        const r = this.roleAccess || [];
        return r.find(v => v[this.config.fkAccessId] == id);
    }

    /**
     * 获取全部的 父级节点
     */
    _getParentNode() {
        return document.querySelectorAll('#' + this.config.el + ' .' + this.config.parentClassName);
    }
    /**
     * 获取全部的子节点
     */
    _getChildNode() {
        return document.querySelectorAll('#' + this.config.el + ' .' + this.config.childClassName);
    }
    /**
     * 获取全部的input框
     */
    _getCheckboxNode() {
        return document.querySelectorAll('#' + this.config.el + ' [name=' + this.config.checkboxName + ']');
    }


    /**
     * 删除事件
     */
    _removeEvent() {
        const p = this._getParentNode();
        for (let i = 0; i < p.length; i++) {
            p[i].removeEventListener('change', this._parentEvent);
        }
        const c = this._getChildNode();
        for (let i = 0; i < c.length; i++) {
            c[i].removeEventListener('change', this._childEvent);
        }
    }

    /**
     * 添加事件
     */
    _addEvent() {
        const p = this._getParentNode();

        for (let i = 0; i < p.length; i++) {
            p[i].addEventListener('change', () => this._parentEvent(p[i]))
        }
        const c = this._getChildNode();
        for (let i = 0; i < c.length; i++) {
            c[i].addEventListener('change', () => this._childEvent(c[i]));
        }

    }

    /**
     * 
     * @param {父节点} parentNode 
     * 父节点change事件
     */
    _parentEvent(parentNode) {
        Array.prototype.forEach.call(
            Array.prototype.filter.call(
                this._getChildNode(), v => v.dataset.group == parentNode.dataset.group),
            v => v.checked = parentNode.checked
        );
    }
    /**
     * 
     * @param {Object 子节点} childNode 
     * 子节点change事件
     */
    _childEvent(childNode) {
        const p = Array.prototype.find.call(this._getParentNode(), v => v.dataset.group == childNode.dataset.group);
        if (childNode.checked && p.checked) return;
        if (childNode.checked && !p.checked) {
            p.checked = true;
            return;
        }
        const siblings = Array.prototype.filter.call(this._getChildNode(), v => v.dataset.group == childNode.dataset.group);
        let flag = false;
        Array.prototype.forEach.call(siblings, v => { if (v.checked) flag = true });
        p.checked = flag;

    }
}
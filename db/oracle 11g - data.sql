/*==============================================================*/
/* Table: T_ADMIN                                               */
/*==============================================================*/
insert into T_ADMIN (ADMIN_ID,USERNAME,PASSWORD,NAME,ROLE_ID,STATE_ID) values (seq_t_admin.nextval,'zhangsan','123456','张三',1,1);
insert into T_ADMIN (ADMIN_ID,USERNAME,PASSWORD,NAME,ROLE_ID,STATE_ID) values (seq_t_admin.nextval,'laoganma','123456','老干妈',2,1);
insert into T_ADMIN (ADMIN_ID,USERNAME,PASSWORD,NAME,ROLE_ID,STATE_ID) values (seq_t_admin.nextval,'wangwu','123456','王五',3,1);
insert into T_ADMIN (ADMIN_ID,USERNAME,PASSWORD,NAME,ROLE_ID,STATE_ID) values (seq_t_admin.nextval,'laoliu','123456','老六',4,1);
insert into T_ADMIN (ADMIN_ID,USERNAME,PASSWORD,NAME,ROLE_ID,STATE_ID) values (seq_t_admin.nextval,'zhaolei','123456','赵磊',5,1);
insert into T_ADMIN (ADMIN_ID,USERNAME,PASSWORD,NAME,ROLE_ID,STATE_ID) values (seq_t_admin.nextval,'qianhenduo','123456','钱很多',1,1);
/*==============================================================*/
/* Table: T_DOC                                                 */
/*==============================================================*/
--insert into T_DOC (DOC_ID,DOC_TITLE,DOC_TYPE_ID,DOC_COVER,DOC_URL,DOWNLOAD_TOTAL,DOWNLOAD_POINT,UPLOAD_TIME,UPLOAD_USER_ID,STATE_ID,DOC_DESC,ADMIN_ID,AUDIT_TIME) values (seq_t_doc.nextval,'测试文档1',1,'/WEB-INF/uploads/timg.jpg','/WEB-INF/uploads/test.txt',1025,10,sysdate-543,1,10,'这是一个测试文档',1,sysdate-543);

/*==============================================================*/
/* Table: T_DOC_TYPE                                            */
/*==============================================================*/
insert into T_DOC_TYPE (TYPE_ID,TYPE_NAME,UPLOAD_POINT,STATE_ID) values (seq_t_doc_type.nextval,'doc',30,7);
insert into T_DOC_TYPE (TYPE_ID,TYPE_NAME,UPLOAD_POINT,STATE_ID) values (seq_t_doc_type.nextval,'txt',10,7);
insert into T_DOC_TYPE (TYPE_ID,TYPE_NAME,UPLOAD_POINT,STATE_ID) values (seq_t_doc_type.nextval,'xls',10,7);
insert into T_DOC_TYPE (TYPE_ID,TYPE_NAME,UPLOAD_POINT,STATE_ID) values (seq_t_doc_type.nextval,'ppt',10,7);
/*==============================================================*/
/* Table: T_DOWNLOAD_RECORD                                     */
/*==============================================================*/
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,1,1,sysdate-123);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,1,2,sysdate-25);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,1,3,sysdate-120);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,1,4,sysdate-65);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,1,5,sysdate-45);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,1,6,sysdate-25);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,1,7,sysdate-86);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,1,8,sysdate-91);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,1,9,sysdate-44);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,2,1,sysdate-55);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,2,2,sysdate-56);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,2,3,sysdate-66);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,2,12,sysdate-56);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,2,13,sysdate-47);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,3,6,sysdate-58);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,3,7,sysdate-98);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,3,8,sysdate-78);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,3,9,sysdate-35);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,4,10,sysdate-65);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,4,11,sysdate-63);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,4,12,sysdate-57);
insert into T_DOWNLOAD_RECORD (RECORD_ID,USER_ID,DOC_ID,DOWNLOAD_TIME) values (seq_t_download_record.nextval,4,13,sysdate-86);
/*==============================================================*/
/* Table: T_EDU                                                 */
/*==============================================================*/
insert into T_EDU (EDU_ID,EDU_NAME) values (seq_t_edu.nextval,'博士');
insert into T_EDU (EDU_ID,EDU_NAME) values (seq_t_edu.nextval,'硕士');
insert into T_EDU (EDU_ID,EDU_NAME) values (seq_t_edu.nextval,'本科');
insert into T_EDU (EDU_ID,EDU_NAME) values (seq_t_edu.nextval,'专科');
/*==============================================================*/
/* Table: T_LOG                                                 */
/*==============================================================*/
/* create table T_LOG 
(
   LOG_ID               NUMBER               not null,
   USER_ID              NUMBER,
   ADMIN_ID             NUMBER,
   OPERATION            VARCHAR2(30),
   TIME                 DATE,
   constraint PK_T_LOG primary key (LOG_ID)
); */

/*==============================================================*/
/* Table: T_MENU                                                */
/*==============================================================*/
insert into T_MENU (MENU_ID,MENU_NAME,PARENT_ID,ICON_CLASS,URL) values (1,'后台管理',0,'icon-sitemap',null);
insert into T_MENU (MENU_ID,MENU_NAME,PARENT_ID,ICON_CLASS,URL) values (2,'文档管理',0,'icon-folder-close',null);
insert into T_MENU (MENU_ID,MENU_NAME,PARENT_ID,ICON_CLASS,URL) values (3,'用户管理',0,'icon-user','user');
insert into T_MENU (MENU_ID,MENU_NAME,PARENT_ID,ICON_CLASS,URL) values (4,'日志管理',0,'icon-server','log');
insert into T_MENU (MENU_ID,MENU_NAME,PARENT_ID,ICON_CLASS,URL) values (5,'系统配置',0,'icon-cog','system');

insert into T_MENU (MENU_ID,MENU_NAME,PARENT_ID,ICON_CLASS,URL) values (6,'人员管理',1,null,'admin');
insert into T_MENU (MENU_ID,MENU_NAME,PARENT_ID,ICON_CLASS,URL) values (7,'角色管理',1,null,'role');
insert into T_MENU (MENU_ID,MENU_NAME,PARENT_ID,ICON_CLASS,URL) values (8,'菜单管理',1,null,'menu');
insert into T_MENU (MENU_ID,MENU_NAME,PARENT_ID,ICON_CLASS,URL) values (9,'权限管理',1,null,'perm');

insert into T_MENU (MENU_ID,MENU_NAME,PARENT_ID,ICON_CLASS,URL) values (10,'文档审核',2,null,'doc_audit');
insert into T_MENU (MENU_ID,MENU_NAME,PARENT_ID,ICON_CLASS,URL) values (11,'文档配置',2,null,'doc_config');
/*==============================================================*/
/* Table: T_PARAM                                               */
/*==============================================================*/
insert into T_PARAM (PARAM_ID,PARAM_NAME,PARAM_VALUE) values (seq_t_param.nextval,'注册奖励',50);
/*==============================================================*/
/* Table: T_PERM                                                */
/*==============================================================*/
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,1,1);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,1,2);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,1,3);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,1,4);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,1,5);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,1,6);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,1,7);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,1,8);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,1,9);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,1,10);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,1,11);

insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,2,1);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,2,6);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,2,7);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,2,2);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,2,10);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,2,11);

insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,3,3);

insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,4,2);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,4,3);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,4,10);

insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,5,1);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,5,7);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,5,8);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,5,2);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,5,11);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,5,3);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,5,4);
insert into T_PERM (PERM_ID,ROLE_ID,MENU_ID) values (seq_t_perm.nextval,5,5);
/*==============================================================*/
/* Table: T_POINT_RECORD                                        */
/*==============================================================*/
insert into T_POINT_RECORD (RECORD_ID,USER_ID,NAME,POINT,TYPE,TIME) values (seq_t_point_record.nextval,1,'下载文档',20,'扣除',SYSDATE-10);
insert into T_POINT_RECORD (RECORD_ID,USER_ID,NAME,POINT,TYPE,TIME) values (seq_t_point_record.nextval,2,'上传文档',15,'增加',SYSDATE-10);
insert into T_POINT_RECORD (RECORD_ID,USER_ID,NAME,POINT,TYPE,TIME) values (seq_t_point_record.nextval,2,'下载文档',25,'扣除',SYSDATE-10);
insert into T_POINT_RECORD (RECORD_ID,USER_ID,NAME,POINT,TYPE,TIME) values (seq_t_point_record.nextval,1,'上传文档',20,'增加',SYSDATE-10);
insert into T_POINT_RECORD (RECORD_ID,USER_ID,NAME,POINT,TYPE,TIME) values (seq_t_point_record.nextval,3,'下载文档',20,'扣除',SYSDATE-10);
insert into T_POINT_RECORD (RECORD_ID,USER_ID,NAME,POINT,TYPE,TIME) values (seq_t_point_record.nextval,4,'下载文档',20,'扣除',SYSDATE-10);
insert into T_POINT_RECORD (RECORD_ID,USER_ID,NAME,POINT,TYPE,TIME) values (seq_t_point_record.nextval,2,'上传文档',20,'增加',SYSDATE-10);
insert into T_POINT_RECORD (RECORD_ID,USER_ID,NAME,POINT,TYPE,TIME) values (seq_t_point_record.nextval,3,'上传文档',10,'增加',SYSDATE-10);
insert into T_POINT_RECORD (RECORD_ID,USER_ID,NAME,POINT,TYPE,TIME) values (seq_t_point_record.nextval,1,'下载文档',20,'扣除',SYSDATE-10);

/*==============================================================*/
/* Table: T_PROF                                                */
/*==============================================================*/
insert into T_PROF (PROF_ID,PROF_NAME) values (seq_t_prof.nextval,'程序员');
insert into T_PROF (PROF_ID,PROF_NAME) values (seq_t_prof.nextval,'工程师');
insert into T_PROF (PROF_ID,PROF_NAME) values (seq_t_prof.nextval,'教师');
insert into T_PROF (PROF_ID,PROF_NAME) values (seq_t_prof.nextval,'医生');
/*==============================================================*/
/* Table: T_ROLE                                                */
/*==============================================================*/
insert into T_ROLE (ROLE_ID,ROLE_NAME) values (seq_t_role.nextval,'超级管理员');
insert into T_ROLE (ROLE_ID,ROLE_NAME) values (seq_t_role.nextval,'总经理');
insert into T_ROLE (ROLE_ID,ROLE_NAME) values (seq_t_role.nextval,'客服人员');
insert into T_ROLE (ROLE_ID,ROLE_NAME) values (seq_t_role.nextval,'编辑人员');
insert into T_ROLE (ROLE_ID,ROLE_NAME) values (seq_t_role.nextval,'技术人员');
/*==============================================================*/
/* Table: T_STATE                                               */
/*==============================================================*/
insert into T_STATE (STATE_ID,STATE_NAME,STATE_TYPE) values (seq_t_state.nextval,'启用','管理员状态');
insert into T_STATE (STATE_ID,STATE_NAME,STATE_TYPE) values (seq_t_state.nextval,'禁用','管理员状态');
insert into T_STATE (STATE_ID,STATE_NAME,STATE_TYPE) values (seq_t_state.nextval,'删除','管理员状态');
insert into T_STATE (STATE_ID,STATE_NAME,STATE_TYPE) values (seq_t_state.nextval,'启用','用户状态');
insert into T_STATE (STATE_ID,STATE_NAME,STATE_TYPE) values (seq_t_state.nextval,'禁用','用户状态');
insert into T_STATE (STATE_ID,STATE_NAME,STATE_TYPE) values (seq_t_state.nextval,'删除','用户状态');
insert into T_STATE (STATE_ID,STATE_NAME,STATE_TYPE) values (seq_t_state.nextval,'启用','文档类型状态');
insert into T_STATE (STATE_ID,STATE_NAME,STATE_TYPE) values (seq_t_state.nextval,'禁用','文档类型状态');
insert into T_STATE (STATE_ID,STATE_NAME,STATE_TYPE) values (seq_t_state.nextval,'待审核','文档状态');
insert into T_STATE (STATE_ID,STATE_NAME,STATE_TYPE) values (seq_t_state.nextval,'已审核','文档状态');
insert into T_STATE (STATE_ID,STATE_NAME,STATE_TYPE) values (seq_t_state.nextval,'已删除','文档状态');
/*==============================================================*/
/* Table: T_USER                                                */
/*==============================================================*/
insert into T_USER (USER_ID,USERNAME,PASSWORD,NAME,SEX,MOBILE_PHONE,EMAIL,EDU_ID,PROF_ID,POINT,REG_TIME,STATE_ID) 
values (seq_t_user.nextval,'yiwanning','123456','尹宛凝','女','13850632573','yiwanning@126.com',3,3,0,SYSDATE-13,4);
insert into T_USER (USER_ID,USERNAME,PASSWORD,NAME,SEX,MOBILE_PHONE,EMAIL,EDU_ID,PROF_ID,POINT,REG_TIME,STATE_ID) 
values (seq_t_user.nextval,'huyongxin','123456','胡永新','男','14758623698','huyongxin@qq.com',3,1,0,SYSDATE-30,4);
insert into T_USER (USER_ID,USERNAME,PASSWORD,NAME,SEX,MOBILE_PHONE,EMAIL,EDU_ID,PROF_ID,POINT,REG_TIME,STATE_ID) 
values (seq_t_user.nextval,'wugang','123456','吴刚','男','18953217096','wugang@163.com',1,4,0,SYSDATE-65,4);
insert into T_USER (USER_ID,USERNAME,PASSWORD,NAME,SEX,MOBILE_PHONE,EMAIL,EDU_ID,PROF_ID,POINT,REG_TIME,STATE_ID) 
values (seq_t_user.nextval,'liangting','123456','梁婷','女','18953217096','liangting@163.com',2,4,0,SYSDATE-25,4);
commit;
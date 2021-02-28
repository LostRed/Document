/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2020/12/15 21:22:50                          */
/*==============================================================*/


drop table T_ADMIN cascade constraints;

drop table T_DOC cascade constraints;

drop table T_DOC_TYPE cascade constraints;

drop table T_DOWNLOAD_RECORD cascade constraints;

drop table T_EDU cascade constraints;

drop table T_LOG cascade constraints;

drop table T_MENU cascade constraints;

drop table T_PARAM cascade constraints;

drop table T_PERM cascade constraints;

drop table T_POINT_RECORD cascade constraints;

drop table T_PROF cascade constraints;

drop table T_ROLE cascade constraints;

drop table T_STATE cascade constraints;

drop table T_USER cascade constraints;

drop sequence SEQ_T_ADMIN;

drop sequence SEQ_T_DOC;

drop sequence SEQ_T_DOC_TYPE;

drop sequence SEQ_T_DOWNLOAD_RECORD;

drop sequence SEQ_T_EDU;

drop sequence SEQ_T_LOG;

drop sequence SEQ_T_MENU;

drop sequence SEQ_T_PARAM;

drop sequence SEQ_T_PERM;

drop sequence SEQ_T_POINT_RECORD;

drop sequence SEQ_T_PROF;

drop sequence SEQ_T_ROLE;

drop sequence SEQ_T_STATE;

drop sequence SEQ_T_USER;

create sequence SEQ_T_ADMIN
nocache;

create sequence SEQ_T_DOC
nocache;

create sequence SEQ_T_DOC_TYPE
nocache;

create sequence SEQ_T_DOWNLOAD_RECORD
nocache;

create sequence SEQ_T_EDU
nocache;

create sequence SEQ_T_LOG
nocache;

create sequence SEQ_T_MENU
start with 12
 nocache;

create sequence SEQ_T_PARAM
nocache;

create sequence SEQ_T_PERM
nocache;

create sequence SEQ_T_POINT_RECORD
nocache;

create sequence SEQ_T_PROF
nocache;

create sequence SEQ_T_ROLE
nocache;

create sequence SEQ_T_STATE
nocache;

create sequence SEQ_T_USER
nocache;

/*==============================================================*/
/* Table: T_ADMIN                                               */
/*==============================================================*/
create table T_ADMIN 
(
   ADMIN_ID             NUMBER               not null,
   USERNAME             VARCHAR2(30),
   PASSWORD             VARCHAR2(30),
   NAME                 VARCHAR2(30),
   ROLE_ID              NUMBER,
   STATE_ID             NUMBER,
   constraint PK_T_ADMIN primary key (ADMIN_ID)
);

/*==============================================================*/
/* Table: T_DOC                                                 */
/*==============================================================*/
create table T_DOC 
(
   DOC_ID               NUMBER               not null,
   DOC_TITLE            VARCHAR2(100),
   DOC_TYPE_ID          NUMBER,
   DOC_COVER            VARCHAR2(999),
   DOC_URL              VARCHAR2(999),
   DOWNLOAD_TOTAL       NUMBER,
   DOWNLOAD_POINT       NUMBER,
   UPLOAD_USER_ID       NUMBER,
   UPLOAD_TIME          DATE,
   ADMIN_ID             NUMBER,
   AUDIT_TIME           DATE,
   STATE_ID             NUMBER,
   DOC_DESC             VARCHAR2(999),
   constraint PK_T_DOC primary key (DOC_ID)
);

/*==============================================================*/
/* Table: T_DOC_TYPE                                            */
/*==============================================================*/
create table T_DOC_TYPE 
(
   TYPE_ID              NUMBER               not null,
   TYPE_NAME            VARCHAR2(30),
   UPLOAD_POINT         NUMBER,
   STATE_ID             NUMBER,
   constraint PK_T_DOC_TYPE primary key (TYPE_ID)
);

/*==============================================================*/
/* Table: T_DOWNLOAD_RECORD                                     */
/*==============================================================*/
create table T_DOWNLOAD_RECORD 
(
   RECORD_ID            NUMBER               not null,
   USER_ID              NUMBER,
   DOC_ID               NUMBER,
   DOWNLOAD_TIME        DATE,
   constraint PK_T_DOWNLOAD_RECORD primary key (RECORD_ID)
);

/*==============================================================*/
/* Table: T_EDU                                                 */
/*==============================================================*/
create table T_EDU 
(
   EDU_ID               NUMBER               not null,
   EDU_NAME             VARCHAR2(30),
   constraint PK_T_EDU primary key (EDU_ID)
);

/*==============================================================*/
/* Table: T_LOG                                                 */
/*==============================================================*/
create table T_LOG 
(
   LOG_ID               NUMBER               not null,
   USER_ID              NUMBER,
   ADMIN_ID             NUMBER,
   OPERATION            VARCHAR2(100),
   TIME                 DATE,
   constraint PK_T_LOG primary key (LOG_ID)
);

/*==============================================================*/
/* Table: T_MENU                                                */
/*==============================================================*/
create table T_MENU 
(
   MENU_ID              NUMBER               not null,
   MENU_NAME            VARCHAR2(30),
   PARENT_ID            NUMBER,
   ICON_CLASS           VARCHAR2(30),
   URL                  VARCHAR2(30),
   constraint PK_T_MENU primary key (MENU_ID)
);

/*==============================================================*/
/* Table: T_PARAM                                               */
/*==============================================================*/
create table T_PARAM 
(
   PARAM_ID             NUMBER               not null,
   PARAM_NAME           VARCHAR2(30),
   PARAM_VALUE          NUMBER,
   constraint PK_T_PARAM primary key (PARAM_ID)
);

/*==============================================================*/
/* Table: T_PERM                                                */
/*==============================================================*/
create table T_PERM 
(
   PERM_ID              NUMBER               not null,
   ROLE_ID              NUMBER,
   MENU_ID              NUMBER,
   constraint PK_T_PERM primary key (PERM_ID)
);

/*==============================================================*/
/* Table: T_POINT_RECORD                                        */
/*==============================================================*/
create table T_POINT_RECORD 
(
   RECORD_ID            NUMBER               not null,
   USER_ID              NUMBER,
   NAME                 VARCHAR2(30),
   POINT                NUMBER,
   TYPE                 VARCHAR2(30),
   TIME                 DATE,
   constraint PK_T_POINT_RECORD primary key (RECORD_ID)
);

/*==============================================================*/
/* Table: T_PROF                                                */
/*==============================================================*/
create table T_PROF 
(
   PROF_ID              NUMBER               not null,
   PROF_NAME            VARCHAR2(30),
   constraint PK_T_PROF primary key (PROF_ID)
);

/*==============================================================*/
/* Table: T_ROLE                                                */
/*==============================================================*/
create table T_ROLE 
(
   ROLE_ID              NUMBER               not null,
   ROLE_NAME            VARCHAR2(30),
   constraint PK_T_ROLE primary key (ROLE_ID)
);

/*==============================================================*/
/* Table: T_STATE                                               */
/*==============================================================*/
create table T_STATE 
(
   STATE_ID             NUMBER               not null,
   STATE_NAME           VARCHAR2(30),
   STATE_TYPE           VARCHAR2(30),
   constraint PK_T_STATE primary key (STATE_ID)
);

/*==============================================================*/
/* Table: T_USER                                                */
/*==============================================================*/
create table T_USER 
(
   USER_ID              NUMBER               not null,
   USERNAME             VARCHAR2(30),
   PASSWORD             VARCHAR2(30),
   NAME                 VARCHAR2(30),
   SEX                  VARCHAR2(5),
   MOBILE_PHONE         VARCHAR2(11),
   EMAIL                VARCHAR2(30),
   EDU_ID               NUMBER,
   PROF_ID              NUMBER,
   POINT                NUMBER,
   REG_TIME             DATE,
   STATE_ID             NUMBER,
   constraint PK_T_USER primary key (USER_ID)
);


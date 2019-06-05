/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     2012-1-15 22:56:05                           */
/*==============================================================*/


drop index "T_Message_PK";

drop table "T_Message";

drop index "Project_Include_Picture_FK";

drop index "T_Picture_PK";

drop table "T_Picture";

drop index "T_Project_PK";

drop table "T_Project";

drop index "T_User_PK";

drop table "T_User";

/*==============================================================*/
/* Table: "T_Message"                                           */
/*==============================================================*/
create table "T_Message" (
   "MessageId"          SERIAL               not null,
   "MessageTitle"       VARCHAR(15)          not null,
   "MessageName"        VARCHAR(5)           not null,
   "MessageGender"      BOOL                 null,
   "MessageTelephone"   VARCHAR(20)          not null,
   "MessageEmail"       VARCHAR(20)          null,
   "MessageContent"     VARCHAR(200)         not null,
   "MessageReply"       VARCHAR(200)         null,
   "MessageTime"        TIMESTAMP            null,
   "MessageIp"          VARCHAR(15)          null,
   constraint PK_T_MESSAGE primary key ("MessageId")
);

/*==============================================================*/
/* Index: "T_Message_PK"                                        */
/*==============================================================*/
create unique index "T_Message_PK" on "T_Message" (
"MessageId"
);

/*==============================================================*/
/* Table: "T_Picture"                                           */
/*==============================================================*/
create table "T_Picture" (
   "PictureId"          SERIAL               not null,
   "ProjectId"          INT4                 not null,
   "PictureDescribe"    VARCHAR(200)         null,
   "PictureLink"        VARCHAR(25)          not null,
   "PictureCase"        BOOL                 not null default FALSE,
   "PictureDate"        VARCHAR(8)           null,
   constraint PK_T_PICTURE primary key ("PictureId")
);

/*==============================================================*/
/* Index: "T_Picture_PK"                                        */
/*==============================================================*/
create unique index "T_Picture_PK" on "T_Picture" (
"PictureId"
);

/*==============================================================*/
/* Index: "Project_Include_Picture_FK"                          */
/*==============================================================*/
create  index "Project_Include_Picture_FK" on "T_Picture" (
"ProjectId"
);

/*==============================================================*/
/* Table: "T_Project"                                           */
/*==============================================================*/
create table "T_Project" (
   "ProjectId"          SERIAL               not null,
   "ProjectName"        VARCHAR(20)          not null,
   "ProjectDescribe"    VARCHAR(200)         not null,
   "ProjectParent"      VARCHAR(20)          not null,
   constraint PK_T_PROJECT primary key ("ProjectId")
);

/*==============================================================*/
/* Index: "T_Project_PK"                                        */
/*==============================================================*/
create unique index "T_Project_PK" on "T_Project" (
"ProjectId"
);

/*==============================================================*/
/* Table: "T_User"                                              */
/*==============================================================*/
create table "T_User" (
   "UserId"             SERIAL               not null,
   "Username"           CHAR(32)             not null,
   "Password"           CHAR(32)             not null,
   constraint PK_T_USER primary key ("UserId")
);

/*==============================================================*/
/* Index: "T_User_PK"                                           */
/*==============================================================*/
create unique index "T_User_PK" on "T_User" (
"UserId"
);

alter table "T_Picture"
   add constraint FK_T_PICTUR_PROJECT_I_T_PROJEC foreign key ("ProjectId")
      references "T_Project" ("ProjectId")
      on delete restrict on update restrict;


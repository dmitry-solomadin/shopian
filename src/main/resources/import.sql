insert into APP_USER(USERNAME, PASSWORD) values('admin', md5('admin'));
insert into APP_USER(USERNAME, PASSWORD) values('user',md5('user'));
insert into APP_USER(USERNAME, PASSWORD) values('u',md5('u'));
insert into APP_USER(USERNAME, PASSWORD) values('uu',md5('u'));

insert into APP_ROLE(ROLENAME) values('ROLE_ADMIN');
insert into APP_ROLE(ROLENAME) values('ROLE_USER');
insert into APP_USER_ROLE(USERNAME, ROLENAME) values('admin', 'ROLE_ADMIN');
# insert into APP_USER_ROLE(USERNAME, ROLENAME) values('admin', 'ROLE_USER');
insert into APP_USER_ROLE(USERNAME, ROLENAME) values('user', 'ROLE_USER');
insert into APP_USER_ROLE(USERNAME, ROLENAME) values('u', 'ROLE_USER');
insert into APP_USER_ROLE(USERNAME, ROLENAME) values('uu', 'ROLE_USER');
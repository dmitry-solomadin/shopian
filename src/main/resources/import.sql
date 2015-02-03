-- insert into APP_USER(USERNAME, PASSWORD, FIRST_NAME, LAST_NAME) values('admin', '21232f297a57a5a743894a0e4a801fc3', 'System', 'Admin')
-- insert into APP_USER(USERNAME, PASSWORD, FIRST_NAME, LAST_NAME) values('user','ee11cbb19052e40b07aac0ca060c23ee', 'General', 'User')
-- insert into APP_ROLE(ROLENAME) values('ROLE_ADMIN')
-- insert into APP_ROLE(ROLENAME) values('ROLE_USER')
-- insert into APP_USER_ROLE(USERNAME, ROLENAME) values('admin', 'ROLE_ADMIN')
-- insert into APP_USER_ROLE(USERNAME, ROLENAME) values('admin', 'ROLE_USER')
-- insert into APP_USER_ROLE(USERNAME, ROLENAME) values('user', 'ROLE_USER')

-- insert into users(login, password) values('admin', md5('admin'));
-- insert into users(login, password) values('user', md5('user'));
-- insert into roles(role) values('ROLE_ADMIN')
-- insert into roles(role) values('ROLE_USER')
-- insert into user_roles(user_id, role_id) select u.id, r.id from users u, roles r where u.login='admin' or r.role='ROLE_ADMIN'
-- insert into user_roles(user_id, role_id) select u.id, r.id from users u, roles r where u.login='admin' or r.role='ROLE_USER'
-- insert into user_roles(user_id, role_id) select u.id, r.id from users u, roles r where u.login='user' or r.role='ROLE_ADMIN'


insert into APP_USER(USERNAME, PASSWORD) values('admin', '21232f297a57a5a743894a0e4a801fc3');
insert into APP_USER(USERNAME, PASSWORD) values('user','ee11cbb19052e40b07aac0ca060c23ee');
insert into APP_ROLE(ROLENAME) values('ROLE_ADMIN');
insert into APP_ROLE(ROLENAME) values('ROLE_USER');
insert into APP_USER_ROLE(USERNAME, ROLENAME) values('admin', 'ROLE_ADMIN');
insert into APP_USER_ROLE(USERNAME, ROLENAME) values('admin', 'ROLE_USER');
insert into APP_USER_ROLE(USERNAME, ROLENAME) values('user', 'ROLE_USER');
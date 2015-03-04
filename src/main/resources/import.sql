insert into APP_USER(USERNAME, PASSWORD, token) values('admin', md5('admin'), '9c0b06063c2686c45843b907549b6444');
insert into APP_USER(USERNAME, PASSWORD, token) values('user'  ,md5('user') , 'cb9c806465abbde8df83ab9f638824d3');
insert into APP_USER(USERNAME, PASSWORD, token) values('u'     ,md5('u')    , '9e0874b7bb65988aec6a5646d58b05dd');
insert into APP_USER(USERNAME, PASSWORD, token) values('uu'    ,md5('u')    , '1cace9f11738ab8ae60924755ff08d42');

insert into APP_ROLE(ROLENAME) values('ROLE_ADMIN');
insert into APP_ROLE(ROLENAME) values('ROLE_USER');
insert into APP_USER_ROLE(USERNAME, ROLENAME) values('admin', 'ROLE_ADMIN');
# insert into APP_USER_ROLE(USERNAME, ROLENAME) values('admin', 'ROLE_USER');
insert into APP_USER_ROLE(USERNAME, ROLENAME) values('user',  'ROLE_USER');
insert into APP_USER_ROLE(USERNAME, ROLENAME) values('u',     'ROLE_USER');
insert into APP_USER_ROLE(USERNAME, ROLENAME) values('uu',    'ROLE_USER');

INSERT INTO events(title) VALUES('CAMPAIGN_RECIEVE'),('CAMPAIGN_VIEW'),('CAMPAIGN_REDEEM'),('BEACON_REGION'),('BEACON_ZONE_1'),('BEACON_ZONE_2'),('BEACON_ZONE_3'),('BEACON_LOST');

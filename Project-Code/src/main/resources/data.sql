insert into Category values (1,'Dutch Films');
insert into Category values (2,'Dutch Series');
insert into Category values (3,'International Films');

insert into User (id, email, name, password, token, timestamp) values (1,'john@sogeti.com','John','Password@123', '886e596c-6b47-4e28-960f-7caf7452d37e', '11-02-19 16:47:35,985 +0000');
insert into User (id, email, name, password, token, timestamp) values (2,'corwin@sogeti.com','Corwin','Hash@123', '5038b2f1-51c3-4a9e-8c4c-a6c85088de0a', '11-02-19 16:47:35,985 +0000');
insert into User (id, email, name, password, token, timestamp) values (3,'bill@sogeti.com','Bill','Original@123', '2e47f1ed-d3a2-4fa7-b209-34c5105873cd', '11-02-19 16:47:35,985 +0000');
insert into User (id, email, name, password, token, timestamp) values (4,'luke@sogeti.com','Luke','Devil@123', '22934918-d8aa-44b7-9bd9-a46236cf14c1', '11-02-19 16:47:35,985 +0000');
insert into User (id, email, name, password, token, timestamp) values (5,'gaurav@sogeti.com','Gaurav','Hash@123', 'bf463497-a15e-47b9-a634-58541c01a721', '11-02-19 16:47:35,985 +0000');

insert into SUBSCRIPTION(ID, CATEGORY_ID, USER_ID, LOCAL_DATE, IS_SUBSCRIBED) values (1,3,1, '2018-07-10', true);
insert into SUBSCRIPTION(ID, CATEGORY_ID, USER_ID, LOCAL_DATE, IS_SUBSCRIBED) values (2,3,2, '2019-07-10', true);
insert into SUBSCRIPTION(ID, CATEGORY_ID, USER_ID, LOCAL_DATE, IS_SUBSCRIBED) values (3,1,4, '2017-07-10', true);
insert into SUBSCRIPTION(ID, CATEGORY_ID, USER_ID, LOCAL_DATE, IS_SUBSCRIBED) values (4,2,4, '2019-07-10', true);
insert into SUBSCRIPTION(ID, CATEGORY_ID, USER_ID, LOCAL_DATE, IS_SUBSCRIBED) values (5,3,4, '2016-07-10', true);
insert into SUBSCRIPTION(ID, CATEGORY_ID, USER_ID, LOCAL_DATE, IS_SUBSCRIBED) values (6,1,2, '2019-07-10', true);

insert into Content values (1,'Alissa in Concert',0.65,1);
insert into Content values (2,'Crocodiles in Amsterdam',0.80,1);
insert into Content values (3,'In Krakende Welstand',0.25,1);
insert into Content values (4,'Het Phoenix Mysterie',0.35,1);
insert into Content values (5,'Spelen of Sterven',0.25,1);
insert into Content values (6,'NOS Jeugdjournaal',0.80,2);
insert into Content values (7,'Sesamstraat',0.95,2);
insert into Content values (8,'Studio Snugger',0.35,2);
insert into Content values (9,'Buurman & Buurman',1.15,2);
insert into Content values (10,'Salt',1.25,3);
insert into Content values (11,'Avengers: Endgame',0.95,3);

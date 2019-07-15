
use reddiu;

-- insert users
-- password is 12345 (bcrypt encoded) 
insert into security_user (username, password, first_name, last_name) values 
	('admin', '$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi', 'Admin', 'Admin');
-- password is abcdef (bcrypt encoded)
insert into security_user (username, password, first_name, last_name) values 
	('petar', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Petar', 'Petrovic');
    
select * from security_user;

-- insert authorities
insert into security_authority (name) values ('ROLE_ADMIN'); -- super user
insert into security_authority (name) values ('ROLE_USER'); -- normal user

-- insert mappings between users and authorities
insert into security_user_authority (user_id, authority_id) values (1, 1); -- admin has ROLE_ADMIN
insert into security_user_authority (user_id, authority_id) values (1, 2); -- admin has ROLE_USER too
insert into security_user_authority (user_id, authority_id) values (2, 2); -- petar has ROLE_USER



insert into category (name) values ("Category 1");
insert into category (name) values ("Category 2");
insert into category (name) values ("Category 3");



insert into message (text, title, category_id, user_id, score) values ("this is my question etc etc etc",
"a title", 1, 1, 1);
insert into message (text, title, category_id, user_id, score) values ("this is a story about something and a complaint
about someone", "complaint", 1, 2, 1);
insert into message (text, title, category_id, user_id, score) values ("text text text", "zz ff gg", 1, 2,1);

insert into message (text, title, category_id, user_id, score) values ("texty text text", "btitle", 2, 1, 1);
insert into message (text, title, category_id, user_id, score) values ("text text more text", "vfrgi", 2, 1, 1);

insert into message (text, title, category_id, user_id, score) values ("test message text", "testing", 3, 2, 1);
insert into message (text, title, category_id, user_id, score) values ("another test message", "test again", 3, 1, 1);



insert into comment (text, message_id, parent_id, user_id, score) values ("a parent comment on msg id 1", 1, null, 1, 1);
insert into comment (text, message_id, parent_id, user_id, score) values ("a child comment on 1st comm", 1, 1, 2, 1);
insert into comment (text, message_id, parent_id, user_id, score) values ("a 2nd child comment on 1st comm", 1, 1, 1, 1);
insert into comment (text, message_id, parent_id, user_id, score) values ("a child comment of a child comment", 1, 2, 1, 1);
insert into comment (text, message_id, parent_id, user_id, score) values ("a new parent comment", 1, null, 2, 1);


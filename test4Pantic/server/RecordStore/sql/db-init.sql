-- insert users
-- password is 12345 (bcrypt encoded) 
insert into security_user (username, password, first_name, last_name) values 
	('admin', '$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi', 'Admin', 'Admin');
-- password is abcdef (bcrypt encoded)
insert into security_user (username, password, first_name, last_name) values 
	('petar', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Petar', 'Petrovic');

-- insert authorities
insert into security_authority (name) values ('ROLE_ADMIN'); -- super user
insert into security_authority (name) values ('ROLE_USER'); -- normal user

-- insert mappings between users and authorities
insert into security_user_authority (user_id, authority_id) values (1, 1); -- admin has ROLE_ADMIN
insert into security_user_authority (user_id, authority_id) values (1, 2); -- admin has ROLE_USER too
insert into security_user_authority (user_id, authority_id) values (2, 2); -- petar has ROLE_USER

insert into project (name, description, readme) values ("Project 1", "description 1", "readme 1");
insert into project (name, description, readme) values ("Project 2", "description 2", "readme 2");
insert into project (name, description, readme) values ("Project 3", "description 3", "readme 3");
insert into project (name, description, readme) values ("Project 4", "description 4", "readme 4");
insert into project (name, description, readme) values ("Project 5", "description 5", "readme 5");

insert into issue (username, text, votes, project_id) values ("user1", "issue 1 on project 1", 0, 1);
insert into issue (username, text, votes, project_id) values ("user1", "issue 2 on project 1", 5, 1);
insert into issue (username, text, votes, project_id) values ("user1", "issue 3 on project 1", -2, 1);

insert into issue (username, text, votes, project_id) values ("user2", "issue 1 on project 2", 0, 2);
insert into issue (username, text, votes, project_id) values ("user2", "issue 2 on project 2", -8, 2);
insert into issue (username, text, votes, project_id) values ("user2", "issue 3 on project 2", 10, 2);

insert into issue (username, text, votes, project_id) values ("user3", "issue 1 on project 3", 0, 3);

insert into issue (username, text, votes, project_id) values ("user4", "issue 1 on project 4", 0, 4);

insert into issue (username, text, votes, project_id) values ("user5", "issue 1 on project 5", 0, 2);
insert into issue (username, text, votes, project_id) values ("user5", "issue 2 on project 5", 0, 2);
insert into issue (username, text, votes, project_id) values ("user5", "issue 3 on project 5", 0, 2);
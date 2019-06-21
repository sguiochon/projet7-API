#SECURITY ;

INSERT INTO privilege (id, name) VALUES (1, 'USER');

INSERT INTO privilege (id, name) VALUES (2, 'ADMIN');

INSERT INTO role (id, name) VALUES (1, 'visiteur');

INSERT INTO role (id, name) VALUES (2, 'administrateur');

INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1, 1);

INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2, 1);

INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2, 2);

INSERT INTO user_account(id,email,first_name, last_name,password, enabled)
VALUES (1, 'inferno@hell.com', 'Dr', 'Sam', '$2a$11$xc8x65LwMEFB2zugF8h5vOHWojwGpp3Om9OXVw1YJWvckR1uyXrVq', TRUE);

INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);

INSERT INTO user_account (id,email,first_name, last_name,password, enabled)
VALUES (3, 'user@hell.com', 'Jack', 'Bauer', '$2a$11$xc8x65LwMEFB2zugF8h5vOHWojwGpp3Om9OXVw1YJWvckR1uyXrVq',TRUE);
INSERT INTO user_account (id,email,first_name, last_name,password, enabled)
VALUES (4, 'arthur@hell.com', 'Arthur', 'Morgan', '$2a$11$xc8x65LwMEFB2zugF8h5vOHWojwGpp3Om9OXVw1YJWvckR1uyXrVq', TRUE);
INSERT INTO user_account (id,email,first_name, last_name,password, enabled)
VALUES (5, 'harry@hell.com', 'Harry', 'Callahan', '$2a$11$xc8x65LwMEFB2zugF8h5vOHWojwGpp3Om9OXVw1YJWvckR1uyXrVq', TRUE);

INSERT INTO users_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (5, 1);


# LIBRARY RELATED;

INSERT INTO member (id, membership_start_date, user_id) VALUES (1, '2009-09-17', 3);
INSERT INTO member (id, membership_start_date, user_id) VALUES (2, '2009-09-17', 4);
INSERT INTO member (id, membership_start_date, user_id) VALUES (3, '2009-09-17', 5);


INSERT INTO document (id, title, author, description) VALUES (1, 'Le petit chaperon rouge', 'Charles Perrault', 'Conte de tradition orale');
INSERT INTO document (id, title, author, description) VALUES (2, 'Bilbo le hobbit', 'J.R.R. Tolkien', 'Célèbre roman fantastique');

INSERT INTO copy (code, status) VALUE (3, 'available');
INSERT INTO copy (code, status) VALUE (4, 'available');
INSERT INTO copy (code, status) VALUE (5, 'available');
INSERT INTO copy (code, status) VALUE (6, 'available');


INSERT INTO document_copies (document_id, copies_code) VALUES (1,3);
INSERT INTO document_copies (document_id, copies_code) VALUES (1,4);
INSERT INTO document_copies (document_id, copies_code) VALUES (2,5);
INSERT INTO document_copies (document_id, copies_code) VALUES (2,6);


INSERT INTO lending (id, start, end, nb_postponement, copy_code) VALUE (1, '2019-01-01', '2019-02-01', 0, 3);
INSERT INTO lending (id, start, end, nb_postponement, copy_code) VALUE (2, '2019-06-01', '2019-08-01', 1, 4);
INSERT INTO lending (id, start, end, nb_postponement, copy_code) VALUE (3, '2019-06-01', '2019-08-01', 1, 6);


INSERT INTO MEMBER_LENDINGS (MEMBER_ID, LENDING_ID) VALUES (1, 1);
INSERT INTO MEMBER_LENDINGS (MEMBER_ID, LENDING_ID) VALUES (1, 2);
INSERT INTO MEMBER_LENDINGS (MEMBER_ID, LENDING_ID) VALUES (2, 3);


#ALTER SEQUENCE hibernate_sequence RESTART WITH 100;
UPDATE hibernate_sequence SET next_val=6;
-- ***************************
-- Security domain related data:

-- Représente les 'authority' de Spring Security:
INSERT INTO privilege (id, name) VALUES (1, 'USER');
INSERT INTO privilege (id, name) VALUES (2, 'ADMIN');

INSERT INTO role (id, name) VALUES (1, 'visiteur');
INSERT INTO role (id, name) VALUES (2, 'administrateur');

INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1, 1);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2, 1);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2, 2);

-- Création d'un ADMIN
INSERT INTO user_account(id,email,first_name, last_name,password, enabled)
VALUES (1, 'inferno@hell.com', 'Dr', 'Sam', '$2a$11$xc8x65LwMEFB2zugF8h5vOHWojwGpp3Om9OXVw1YJWvckR1uyXrVq', TRUE);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);

-- Création d'un USER
INSERT INTO user_account (id,email,first_name, last_name,password, enabled)
VALUES (3, 'user@hell.com', 'Jack', 'Bauer', '$2a$11$xc8x65LwMEFB2zugF8h5vOHWojwGpp3Om9OXVw1YJWvckR1uyXrVq',TRUE);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 1);

-- Création d'un USER
INSERT INTO user_account (id,email,first_name, last_name,password, enabled)
VALUES (4, 'arthur@hell.com', 'Arthur', 'Morgan', '$2a$11$xc8x65LwMEFB2zugF8h5vOHWojwGpp3Om9OXVw1YJWvckR1uyXrVq', TRUE);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 1);

-- Création d'un USER
INSERT INTO user_account (id,email,first_name, last_name,password, enabled)
VALUES (5, 'harry@hell.com', 'Harry', 'Callahan', '$2a$11$xc8x65LwMEFB2zugF8h5vOHWojwGpp3Om9OXVw1YJWvckR1uyXrVq', TRUE);
INSERT INTO users_roles (user_id, role_id) VALUES (5, 1);

-- ***************************
-- Library domain related data:

-- Création des Membres en lien avec les Users
INSERT INTO member (id, membership_start_date, user_id) VALUES (1, '2009-09-17', 3);
INSERT INTO member (id, membership_start_date, user_id) VALUES (2, '2009-09-17', 4);
INSERT INTO member (id, membership_start_date, user_id) VALUES (3, '2009-09-17', 5);

-- Créaton d'un document et de ses exemplaires:
INSERT INTO document (id, title, author, description, image) VALUES (1, 'Le petit chaperon rouge', 'Charles Perrault', 'Conte de tradition orale', 'chaperon_rouge.jpg');
INSERT INTO copy (id, status, document_id) VALUES (2, 'AVAILABLE', 1);


INSERT INTO copy (id, status, document_id) VALUES (3, 'AVAILABLE', 1);
INSERT INTO lending (id, start, end, nb_postponement, copy_id) VALUES (1, '2019-01-01', '2019-02-01', 0, 3);
INSERT INTO MEMBER_LENDINGS (MEMBER_ID, LENDING_ID) VALUES (1, 1);

INSERT INTO copy (id, status, document_id) VALUES (4, 'AVAILABLE', 1);
INSERT INTO lending (id, start, end, nb_postponement, copy_id) VALUES (2, '2019-06-01', '2019-08-01', 1, 4);
INSERT INTO MEMBER_LENDINGS (MEMBER_ID, LENDING_ID) VALUES (1, 2);

-- Créaton d'un document et de ses exemplaires:
INSERT INTO document (id, title, author, description, image) VALUES (2, 'Bilbo le hobbit', 'J.R.R. Tolkien', 'Célèbre roman fantastique', 'biblo.jpg');
INSERT INTO copy (id, status, document_id) VALUES (5, 'AVAILABLE', 2);

INSERT INTO copy (id, status, document_id) VALUES (6, 'AVAILABLE', 2);
INSERT INTO lending (id, start, end, nb_postponement, copy_id) VALUES (3, '2019-06-01', '2019-08-01', 1, 6);
INSERT INTO MEMBER_LENDINGS (MEMBER_ID, LENDING_ID) VALUES (2, 3);

-- Créaton d'un document et de ses exemplaires:
INSERT INTO document (id, title, author, description, image) VALUES (3, 'On a marché sur la lune', 'Hergé', 'Tintin', 'on_a_marche_sur_la_lune.jpg');
INSERT INTO copy (id, status, document_id) VALUES (7, 'AVAILABLE', 3);
INSERT INTO lending (id, start, end, nb_postponement, copy_id) VALUES (4, '2019-08-01', '2019-09-01', 1, 7);
INSERT INTO MEMBER_LENDINGS (MEMBER_ID, LENDING_ID) VALUES (2, 4);

INSERT INTO copy (id, status, document_id) VALUES (8, 'AVAILABLE', 3);
INSERT INTO lending (id, start, end, nb_postponement, copy_id) VALUES (5, '2019-08-01', '2019-09-01', 1, 8);
INSERT INTO MEMBER_LENDINGS (MEMBER_ID, LENDING_ID) VALUES (2, 5);

INSERT INTO copy (id, status, document_id) VALUES (9, 'AVAILABLE', 3);
INSERT INTO lending (id, start, end, nb_postponement, copy_id) VALUES (6, '2019-08-01', '2019-09-01', 1, 9);
INSERT INTO MEMBER_LENDINGS (MEMBER_ID, LENDING_ID) VALUES (2, 6);


-- Créaton d'un document et de ses exemplaires:
INSERT INTO document (id, title, author, description, image) VALUES (4, 'Objectif lune', 'Hergé', 'Tintin','objectif_lune.jpg');
INSERT INTO copy (id, status, document_id) VALUES (10, 'AVAILABLE', 4);
INSERT INTO copy (id, status, document_id) VALUES (11, 'AVAILABLE', 4);
INSERT INTO copy (id, status, document_id) VALUES (12, 'AVAILABLE', 4);
--INSERT INTO document_copies (document_id, copies_id) VALUES (4,10);
--INSERT INTO document_copies (document_id, copies_id) VALUES (4,11);
--INSERT INTO document_copies (document_id, copies_id) VALUES (4,12);

-- Créaton d'un document et de ses exemplaires:
INSERT INTO document (id, title, author, description, image) VALUES (5, 'Le Dirdir', 'Jack Vance', 'Le cycle de Tschaï', 'cycle_de_tschai.jpg');
INSERT INTO copy (id, status, document_id) VALUES (13, 'AVAILABLE', 5);
INSERT INTO copy (id, status, document_id) VALUES (14, 'AVAILABLE', 5);
INSERT INTO copy (id, status, document_id) VALUES (15, 'AVAILABLE', 5);
--INSERT INTO document_copies (document_id, copies_id) VALUES (5,13);
--INSERT INTO document_copies (document_id, copies_id) VALUES (5,14);
--INSERT INTO document_copies (document_id, copies_id) VALUES (5,15);

INSERT INTO document (id, title, author, description, image) VALUES (6, 'Les voies d`Anubis', 'Tim Powers', 'Un monument', 'anubis.jpg');
INSERT INTO copy (id, status, document_id) VALUES (16, 'AVAILABLE', 6);
INSERT INTO copy (id, status, document_id) VALUES (17, 'AVAILABLE', 6);
INSERT INTO copy (id, status, document_id) VALUES (18, 'AVAILABLE', 6);
--INSERT INTO document_copies (document_id, copies_id) VALUES (6,16);
--INSERT INTO document_copies (document_id, copies_id) VALUES (6,17);
--INSERT INTO document_copies (document_id, copies_id) VALUES (6,18);

-- Création des prêts









INSERT INTO lending (id, start, end, nb_postponement, copy_id) VALUES (7, '2019-08-01', '2019-09-01', 1, 11);
INSERT INTO MEMBER_LENDINGS (MEMBER_ID, LENDING_ID) VALUES (3, 7);

INSERT INTO lending (id, start, end, nb_postponement, copy_id) VALUES (8, '2019-08-01', '2019-09-01', 1, 13);
INSERT INTO MEMBER_LENDINGS (MEMBER_ID, LENDING_ID) VALUES (3, 8);

INSERT INTO lending (id, start, end, nb_postponement, copy_id) VALUES (9, '2019-08-01', '2019-09-01', 1, 16);
INSERT INTO MEMBER_LENDINGS (MEMBER_ID, LENDING_ID) VALUES (3, 9);


ALTER SEQUENCE hibernate_sequence RESTART WITH 100;
--UPDATE hibernate_sequence SET next_val=30;
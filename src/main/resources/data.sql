INSERT INTO PERFIL VALUES (1, 'ROLE_ALUNO');
INSERT INTO PERFIL VALUES (2, 'ROLE_MODERADOR');

INSERT INTO USUARIO(nome, email, senha) VALUES('Moderador', 'moderador@email.com', '$2a$10$/mYur4zFSjkrYzsrlu.4NeGd2THq21.TpgdsnB0BAUWrTh2/iv96W');

INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$/mYur4zFSjkrYzsrlu.4NeGd2THq21.TpgdsnB0BAUWrTh2/iv96W');

INSERT INTO USUARIO_PERFIS VALUES (1,2);
INSERT INTO USUARIO_PERFIS VALUES (2,1);

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);
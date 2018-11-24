CREATE SEQUENCE seq_usuario_id INCREMENT 1 START 5;

CREATE TABLE usuario (
	id BIGINT DEFAULT seq_usuario_id.NEXTVAl PRIMARY KEY,
	nome VARCHAR(70),
	email VARCHAR(50)
);

INSERT INTO usuario VALUES(1, 'Otavio', 'otavio@familia.com');
INSERT INTO usuario VALUES(2, 'Gabriela', 'gabriela@familia.com');
INSERT INTO usuario VALUES(3, 'Manuella', 'manuella@familia.com');
INSERT INTO usuario VALUES(4, 'Júlia', 'julia@familia.com');
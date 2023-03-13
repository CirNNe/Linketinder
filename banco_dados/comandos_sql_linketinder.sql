-- Pais:
-- CREATE TABLE "pais" (
--  "id" SERIAL PRIMARY KEY,
--  "nome" varchar(15) NOT NULL
-- );

-- Candidatos:
-- CREATE TABLE "candidatos" (
--  "id" SERIAL PRIMARY KEY,
--  "nome" varchar(100) NOT NULL,
--  "data_nascimento" DATE NOT NULL,
--  "email" varchar(100) NOT NULL,
--  "cpf" bigint NOT NULL,
--  "id_pais" INT REFERENCES pais(id)NOT NULL,
--  "cep" int NOT NULL,
--  "descricao_pessoal" varchar(250) NOT NULL,
--  "senha" varchar(50) NOT NULL
-- );

-- Empresas:
--CREATE TABLE "empresas" (
--  "id" SERIAL PRIMARY KEY,
--  "nome" varchar(100) NOT NULL,
--  "cnpj" bigint NOT NULL,
--  "email" varchar(100) NOT NULL,
--  "descricao_empresa" varchar(250) NOT NULL,
--  "id_pais" INT REFERENCES pais(id) NOT NULL,
--  "cep" int NOT NULL,
--  "senha" varchar(50) NOT NULL
-- );

-- Vagas:
-- CREATE TABLE "vagas" (
--  "id" SERIAL PRIMARY KEY,
--  "nome" varchar(50) NOT NULL,
--  "descricao_vaga" varchar(250) NOT NULL,
--  "id_empresa" INT REFERENCES empresas(id) NOT NULL,
--  "id_pais" INT REFERENCES pais(id) NOT NULL
-- );

-- Competências:
-- CREATE TABLE "competencias" (
--  "id" SERIAL PRIMARY KEY,
--  "nome" varchar(100) NOT NULL,
--  "id_candidato" INT REFERENCES candidatos(id) NOT NULL,
--  "id_vagas" INT REFERENCES vagas(id) NOT NULL
-- );

-- Modelo de Trabalho:
-- CREATE TABLE "modelo_trabalho" (
--  "id" SERIAL PRIMARY KEY,
--  "nome" varchar(10) NOT NULL,
--  "id_vaga" INT REFERENCES vagas(id) NOT NULL
-- );

-- Curtida Candidato:
-- CREATE TABLE "curtida_candidato" (
--  "id" SERIAL PRIMARY KEY,
--  "id_candidato" INT REFERENCES candidatos(id) NOT NULL,
--  "id_empresa" INT REFERENCES empresas(id) NOT NULL
-- );

-- Curtida Empresa:
-- CREATE TABLE "curtida_empresa" (
--  "id" SERIAL PRIMARY KEY,
--  "id_empresa" INT REFERENCES empresas(id) NOT NULL,
--  "id_candidato" INT REFERENCES candidatos(id) NOT NULL
-- );

-- Match:
-- CREATE TABLE "match" (
--  "id" SERIAL PRIMARY KEY,
--  "id_candidato" INT REFERENCES candidatos(id) NOT NULL,
--  "id_empresa" INT REFERENCES empresas(id) NOT NULL
-- );

-- Inserindo Lista Países:
-- INSERT INTO pais(nome) VALUES('Brasil');
-- INSERT INTO pais(nome) VALUES('Argentina');
-- INSERT INTO pais(nome) VALUES('Estados Unidos');
-- INSERT INTO pais(nome) VALUES('Portugual');
-- INSERT INTO pais(nome) VALUES('Canada');

-- Inserindo Candidatos:
-- INSERT INTO candidatos(nome, data_nascimento, email, cpf, id_pais, cep, descricao_pessoal, senha) VALUES('Candidato Um', '1991-01-01', 'candidatoum@acelerazg.com.br', 11111111111111, 1, 11111111, 'descrição candidato 1', '@senhacandidato1');
-- INSERT INTO candidatos(nome, data_nascimento, email, cpf, id_pais, cep, descricao_pessoal, senha) VALUES('Candidato Dois', '1982-02-02', 'candidato2@acelerazg.com.br', 22222222222, 2, 22222222, 'descrição candidato 2', '!candidato2senha');
-- INSERT INTO candidatos(nome, data_nascimento, email, cpf, id_pais, cep, descricao_pessoal, senha) VALUES('Candidato Três', '1973-03-03', '3candidato@acelerazg.com.br', 33333333333, 3, 33333333, 'descrição candidato 3', 'Candidato_Tres3');
-- INSERT INTO candidatos(nome, data_nascimento, email, cpf, id_pais, cep, descricao_pessoal, senha) VALUES('Candidato Quatro', '2004-04-04', 'candidatoQuatro@acelerazg.com.br', 44444444444, 4, 44444444, 'descrição candidato 4', 'SenhaCANDIDATO_4');
-- INSERT INTO candidatos(nome, data_nascimento, email, cpf, id_pais, cep, descricao_pessoal, senha) VALUES('Candidato Cinco', '2005-05-05', 'Cincocandidato@acelerazg.com.br', 55555555555, 5, 55555555, 'descrição candidato 5', 'SENHAcandidato@five5');

-- Inserindo Empresas:
-- INSERT INTO empresas(nome, cnpj, email, descricao_empresa, id_pais, cep, senha) VALUES('Empresa Um', 11111111111111, 'empresa1@acelerazg.com.br', 'descrição empresa 1', 1, 12345678, 'Senha@empresa1');
-- INSERT INTO empresas(nome, cnpj, email, descricao_empresa, id_pais, cep, senha) VALUES('Empresa Dois', 22222222222222, '2empresa2@acelerazg.com.br', 'descrição empresa 2', 1, 87654321, 'senhao@2empresa2');
-- INSERT INTO empresas(nome, cnpj, email, descricao_empresa, id_pais, cep, senha) VALUES('Empresa Três', 33333333333333, 'tresdeempresa@acelerazg.com.br', 'descrição empresa 3', 1, 15935782, '3senha!empresa');
-- INSERT INTO empresas(nome, cnpj, email, descricao_empresa, id_pais, cep, senha) VALUES('Empresa Quarto', 44444444444444, 'Aempresa4@acelerazg.com.br', 'descrição empresa 4', 1, 95175328, 'four@empresa4');
-- INSERT INTO empresas(nome, cnpj, email, descricao_empresa, id_pais, cep, senha) VALUES('Empresa Cinco', 55555555555555, 'EmpresaCINCO@acelerazg.com.br', 'descrição empresa 5', 1, 31649746, 'empresa5@Cinco');

-- Inserindo Curtida do Candidato:
-- INSERT INTO curtida_candidato(id_candidato, id_empresa) VALUES(1, 2);
-- INSERT INTO curtida_candidato(id_candidato, id_empresa) VALUES(4, 1);

-- SELECT c.nome AS "Nome Candidato", e.nome AS "Nome Empresa"
--	FROM candidatos AS c, empresas AS e, curtida_candidato AS ca
--		WHERE ca.id_candidato = c.id  AND ca.id_empresa = e.id;

-- Inserindo Curtida da Empresa:
-- INSERT INTO curtida_empresa(id_empresa, id_candidato) VALUES(2, 1);
-- INSERT INTO curtida_empresa(id_empresa, id_candidato) VALUES(1, 4);

--SELECT e.nome AS "Nome Empresa", c.nome AS "Nome Candidato"
--	FROM curtida_empresa AS ce, empresas AS e, candidatos AS c
--		WHERE ce.id_empresa = e.id AND ce.id_candidato = c.id;

-- Inserindo Match:
-- INSERT INTO match(id_candidato, id_empresa) VALUES(1, 2);
-- INSERT INTO match(id_candidato, id_empresa) VALUES(4, 1);

-- SELECT c.nome AS "Nome Candidato", e.nome AS "Nome Empresa"
-- FROM match AS m, candidatos AS c, empresas AS e
-- WHERE m.id_candidato = c.id AND m.id_empresa = e.id;

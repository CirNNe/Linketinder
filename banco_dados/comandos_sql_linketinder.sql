-- Candidatos:
-- CREATE TABLE "candidatos" (
--  "id" SERIAL PRIMARY KEY,
--  "nome" varchar(100) NOT NULL,
--  "data_nascimento" DATE NOT NULL,
--  "email" varchar(100) NOT NULL,
--  "cpf" bigint NOT NULL,
--  "pais" varchar(20) NOT NULL,
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
--  "pais" varchar(20) NOT NULL,
--  "cep" int NOT NULL,
--  "descricao_empresa" varchar(250) NOT NULL,
--  "senha" varchar(50) NOT NULL
-- );

-- Vagas:
-- CREATE TABLE "vagas" (
--  "id" SERIAL PRIMARY KEY,
--  "nome" varchar(50) NOT NULL,
--  "pais" varchar(20) NOT NULL,
--  "descricao_vaga" varchar(250) NOT NULL,
--  "id_empresa" INT REFERENCES empresas(id) NOT NULL
-- );

-- Competências:
-- CREATE TABLE "competencias" (
--  "id" SERIAL PRIMARY KEY,
--  "nome" varchar(100) NOT NULL,
--  "id_candidato" INT REFERENCES candidatos(id),
--  "id_vaga" INT REFERENCES vagas(id)
-- );

-- Curtida CandidatoEntidade:
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

-- Inserindo Candidatos:
-- INSERT INTO candidatos(nome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES('CandidatoEntidade Um', '1991-01-01', 'candidatoum@acelerazg.com.br', 11111111111111, 'Brasil', 11111111, 'descrição candidato 1', '@senhacandidato1');
-- INSERT INTO candidatos(nome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES('CandidatoEntidade Dois', '1982-02-02', 'candidato2@acelerazg.com.br', 22222222222, 2, 'Argentina, 'descrição candidato 2', '!candidato2senha');
-- INSERT INTO candidatos(nome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES('CandidatoEntidade Três', '1973-03-03', '3candidato@acelerazg.com.br', 33333333333, 3, 'Estados Unidos', 'descrição candidato 3', 'Candidato_Tres3');
-- INSERT INTO candidatos(nome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES('CandidatoEntidade Quatro', '2004-04-04', 'candidatoQuatro@acelerazg.com.br', 44444444444, 4, 'Portugual', 'descrição candidato 4', 'SenhaCANDIDATO_4');
-- INSERT INTO candidatos(nome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) VALUES('CandidatoEntidade Cinco', '2005-05-05', 'Cincocandidato@acelerazg.com.br', 55555555555, 5, 'Canada', 'descrição candidato 5', 'SENHAcandidato@five5');

-- Inserindo Empresas:
-- INSERT INTO empresas(nome, cnpj, email, descricao_empresa, pais, cep, senha) VALUES('Empresa Um', 11111111111111, 'empresa1@acelerazg.com.br', 'descrição empresa 1', 'Brasil', 12345678, 'Senha@empresa1');
-- INSERT INTO empresas(nome, cnpj, email, descricao_empresa, pais, cep, senha) VALUES('Empresa Dois', 22222222222222, '2empresa2@acelerazg.com.br', 'descrição empresa 2', 'Argentina', 87654321, 'senhao@2empresa2');
-- INSERT INTO empresas(nome, cnpj, email, descricao_empresa, pais, cep, senha) VALUES('Empresa Três', 33333333333333, 'tresdeempresa@acelerazg.com.br', 'descrição empresa 3', 'Estados Unidos', 15935782, '3senha!empresa');
-- INSERT INTO empresas(nome, cnpj, email, descricao_empresa, pais, cep, senha) VALUES('Empresa Quarto', 44444444444444, 'Aempresa4@acelerazg.com.br', 'descrição empresa 4', 1, 'Portugual', 'four@empresa4');
-- INSERT INTO empresas(nome, cnpj, email, descricao_empresa, pais, cep, senha) VALUES('Empresa Cinco', 55555555555555, 'EmpresaCINCO@acelerazg.com.br', 'descrição empresa 5', 'Canada', 31649746, 'empresa5@Cinco');

-- Inserindo Curtida do CandidatoEntidade:
-- INSERT INTO curtida_candidato(id_candidato, id_empresa) VALUES(1, 2);
-- INSERT INTO curtida_candidato(id_candidato, id_empresa) VALUES(4, 1);

-- SELECT c.nome AS "Nome CandidatoEntidade", e.nome AS "Nome Empresa"
--	FROM candidatos AS c, empresas AS e, curtida_candidato AS ca
--		WHERE ca.id_candidato = c.id  AND ca.id_empresa = e.id;

-- Inserindo Curtida da Empresa:
-- INSERT INTO curtida_empresa(id_empresa, id_candidato) VALUES(2, 1);
-- INSERT INTO curtida_empresa(id_empresa, id_candidato) VALUES(1, 4);

--SELECT e.nome AS "Nome Empresa", c.nome AS "Nome CandidatoEntidade"
--	FROM curtida_empresa AS ce, empresas AS e, candidatos AS c
--		WHERE ce.id_empresa = e.id AND ce.id_candidato = c.id;

<h1 align="center">Linketinder</h1>

<p align="center">Programa baseado na fusão do Linkedln com o Tinder para matchs entre candidatos e empresas</p>

![Apache Groovy](https://img.shields.io/badge/Apache%20Groovy-4298B8.svg?style=for-the-badge&logo=Apache+Groovy&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![Version](https://img.shields.io/badge/version-v3.1.0-blue) 


Indice
=================
 * [Sobre](#-sobre)
 * [Versão](#-versão)
 * [Tecnologias Utilizadas](#-tecnologias-utilizadas)
 * [Pré-requisitos](#-pré-requisitos)
 * [Como Executar o Projeto](#-como-executar-o-projeto)
 * [Autor](#-autor)

## 🔖&nbsp; Sobre

O projeto **Linketinder** é um programa onde simula a lógica de match do tinder, onde empresas e candidatos podem interagir com curtidas. 
Nele podemos cadastrar um candidato a vagas e empresas que procuram por candidatos para suas vagas. Usando a funcionalidade do Tinder, uma empresa 
pode curtir o perfil de um candidato e, também, o candidato pode curtir uma vaga cadastrada por uma empresa. Com isso, é gerado um match entre candidato e empresa.

---

## 🔖&nbsp; Versão

![Version](https://img.shields.io/badge/version-v3.1.0-blue)

- Código refatorado com Clean Code
  - Pontos de melhorias notadas:
    - Código mais legível
    - Projeto mais estruturado
    - Classes e métodos bem mais desacopladas por utilizar interfaces
    - Menos repetição de código
    - Segurança dos testes unitários e facilidade de fazê-los por usar abstrações

![Version](https://img.shields.io/badge/version-v3.0.0-blue)

Gradle:
- Implementação do Gradle ao sistema.
- Como utilizar o builder:
  - no terminal:
    - gradle build (buildar o programa);
    - gradle run (rodar o programa principal);
    - gradle test (rodar os testes unitários).
  - no menu Gradle na IDE:
    - Tasks -> build -> build (buildar o programa);
    - Tasks -> application -> run (rodar o programa principal);
    - Tasks -> verification -> test (rodar os testes unitários).

![Version](https://img.shields.io/badge/version-v2.0.0-blue)

Banco de dados:
- Adição dos arquivos relacionados ao banco de dados (pasta banco_dados):
  - Comandos SQL da criação das tabelas e inserção de dados
  - Modelagem do banco de dados feito no site https://dbdiagram.io/
- Tabelas e suas funcionalidades:
  - tabela candidatos: dados dos candidatos;
  - tabela empresas: dados das empresas;
  - tabela vagas: dados das vagas cadastradas vinculadas as empresas;
  - tabela pais: lista de países para cadastro de novos usuários e vagas;
  - tabela curtida_candidato: quando o candidato curtir uma vaga cadastrada por uma empresa, será criado um novo dado, 
                              contendo o id do candidato e o id da empresa, que será salvo nessa tabela;
  - tabela curtida_empresa: ao curtir um candidato, será criado um novo dado, contendo o id da empresa e o id do candidato
                            que foi curtido, que será salvo nessa tabela;
  - tabela pais: contém uma lista de paises.
- Lógica do Match:
  - Havendo campos com id do candidato e o id da empresa em ambas as duas tabelas acima, a
    lista de match's poderá ser acessada na opção listar match no menu do candidato ou
    empresa.

![Version](https://img.shields.io/badge/version-v1.3.0-blue)

Regex:
- Validação dos Dados dos Usuários Recebidos com Regex.

![Version](https://img.shields.io/badge/version-v1.2.0-blue)

Frontend:
- Telas de Usuários sem Link com o Backend.

![Version](https://img.shields.io/badge/version-v1.1.0-blue)

Testes Unitários:
- Implementado testes unitários.

![Version](https://img.shields.io/badge/version-v1.0.0-blue)
- Definida a versão base do sistema.

---

## 🚀 Tecnologias utilizadas

O projeto foi desenvolvido com as tecnologias

- Apache Groovy
- TypeScript
- PostgreSQL
- HTML5
- CSS3

---

## 🗂 Pré-requisitos

Se deseja baixar o projeto para utiliza-lo, você vai precisar ter instalado em sua máquina a seguinte ferramenta:
[Git](https://git-scm.com) para fazer o clone do repositório para sua máquina local. 
Além disto é bom ter um editor para trabalhar com o código como [ItelliJ IDEA](https://www.jetbrains.com/pt-br/idea/)

## 🎲 Como executar o projeto

```bash
# Clone este repositório
git clone https://github.com/CirNNe/ZG-HERO-Project.git
# Abra o arquivo com sua IDE de preferencia e execute os comandos que desejar
# O menu no terminal é bem intuitivo
```

## 👨‍💻 Autor

<a href="https://github.com/CirNNe">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/98779843?s=400&u=0acf3d526d374b620501ea180d5c81c3ff998c42&v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Higor Cirne</b></sub></a> <a href="https://github.com/CirNNe" title="GitHub"></a>

👋🏽 Entre em contato!

[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/higorcirne/)
[![Instagram](https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white)](https://www.instagram.com/higordev_/)

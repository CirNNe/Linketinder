<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>

    <form class="formulario" action="">



    </form>

    <div class="conteudo" id="conteudo-cadastro-candidato">
      <h1>Cadastrar Candidato:</h1>
      <div id="tela-cadastro-candidato">
        <ul>
          <p>Nome:</p>
          <li><input class="input-barra" id="nome-novo-candidato" type="text"></li>
          <p>E-mail:</p>
          <li><input class="input-barra" id="email-novo-candidato" type="email"></li>
          <p>Data de Nascimento:</p>
          <li><input class="input-barra" id="idade-novo-candidato" type="date"></li>
          <p>CPF:</p>
          <li><input class="input-barra" id="cpf-novo-candidato" type="number" placeholder="Somente números"></li>
          <p>CEP:</p>
          <li><input class="input-barra" id="cep-novo-candidato" type="number" placeholder="Somente números"></li>
          <p>Descrição Pessoal</p>
          <li><input class="input-barra" id="descricao-pessoal-novo-candidato" type="text"></li>
          <p>Linkedin</p>
          <li><input class="input-barra" id="linkedin-novo-candidato" type="url" placeholder="linkedin.com/in/higorcirne"></li>
          <p>Competências:</p>
          <br>
          <ul id="lista-competencias">
            <li><label for="competencia-git"><input id="competencia-git" type="checkbox" name="competencias-novo-candidato" value="Git">Git</label></li>
            <li><label for="competencia-metodologias-ageis"><input id="competencia-metodologias-ageis" type="checkbox" name="competencias-novo-candidato" value="Metodologias Ageis">Metodologias Ágeis</label></li>
            <li><label for="competencia-testes"><input id="competencia-testes" type="checkbox" name="competencias-novo-candidato" value="Testes">Testes</label></li>
            <li><label for="competencia-java"><input id="competencia-java" type="checkbox" name="competencias-novo-candidato" value="Java">Java</label></li>
            <li><label for="competencia-Groovy"><input id="competencia-Groovy" type="checkbox" name="competencias-novo-candidato" value="Groovy">Groovy</label></li>
            <li><label for="competencia-javascript"><input id="competencia-javascript" type="checkbox" name="competencias-novo-candidato" value="JavaScript">JavaScript</label></li>
            <li><label for="competencia-typescript"><input id="competencia-typescript" type="checkbox" name="competencias-novo-candidato" value="TypeScript">TypeScript</label></li>
          </ul>
          <br>
          <button class="botao-cadastrar" id="botao-cadastrar-novo-candidato">Cadastrar</button>
          <button class="botao-voltar"><a href="candidato.html">Voltar</a></button>
        </ul>
      </div>
    </div>

  </body>
</html>
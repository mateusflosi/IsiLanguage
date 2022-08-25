<h2>Compilador</h2>

Este projeto é uma implementação do compilador da linguagem isiLanguage, para conclusão da matéria de Compiladores da Univeridade Federal do ABC, utilizando as tecnologias ANTLR e Java 17.

## Requisitos: </h2>

<table>
  <tr>
    <td>Itens</td>
    <td>Feito</td>
  </tr>
  <tr>
    <td>Possuir 2 tipos de dados (pelo menos 1 String)</td>
    <td>✅</td>
  </tr>
  <tr>
    <td>Possuir a instrução de decisão (if/else)</td>
    <td>✅</td>
  </tr>
  <tr>
    <td>Pelo menos 1 estrutura de repetição</td>
    <td>✅</td>
  </tr>
  <tr>
    <td>Verificar Atribuições com compatibilidade de tipos (semântica)</td>
    <td>✅</td>
  </tr>
  <tr>
    <td>Possuir operações de Entrada e Saída</td>
    <td>✅</td>
  </tr>
  <tr>
    <td>Verificar declaração de variávies (não usar variáveis que não foram declaradas)</td>
    <td>✅</td>
  </tr>
  <tr>
    <td>Verificar se há variáveis declaradas e não-utilizadas (warning)</td>
    <td>✅</td>
  </tr>
  <tr>
    <td>Geração de pelo menos 1 linguagem destino</td>
    <td> C  <br>Java ✅ <br>Python </td>
  </tr>
</table>

<h4>Elementos adicionais:</h4>

<table>
  <tr>
    <td>Itens</td>
    <td>Feito</td>
  </tr>
  <tr>
    <td>Nova instrução para Switch/Case (escolha/caso)</td>
    <td>-</td>
  </tr>
  <tr>
    <td>Mais tipos de dados</td>
    <td>✅</td>
  </tr>
  <tr>
    <td>Inclusão de novos operadores (exponenciação, raiz quadrada, logaritmos)</td>
    <td>✅</td>
  </tr>
  <tr>
    <td>Geração de código para mais de uma linguagem diferente</td>
    <td>-</td>
  </tr>
</table>

#### Elementos extraordinários:</h4>

<table>
  <tr>
    <td>Itens</td>
    <td>Feito</td>
  </tr>
  <tr>
    <td>Criar um interpretador a partir da AST</td>
    <td>-</td>
  </tr>
  <tr>
    <td>Criar um editor com Highlights de palavras reservadas (editor Desktop)</td>
    <td>-</td>
  </tr>
  <tr>
    <td>Criar um editor Web para o código</td>
    <td>-</td>
  </tr>
  <tr>
    <td>Tornar o compilador um Webservice para receber programas e enviar respostas de possíveis erros</td>
    <td>-</td>
  </tr>
</table>

## Instalação: </h2>

* Instalar a biblioteca .jar no link: https://www.antlr.org/download/antlr-4.7.1-complete.jar
* Após esse processo, adicione o jar na raiz do diretório do projeto. A adição não irá aparecer como uma alteração, pois o jar está no .gitignore
* Com a biblioteca instalada, vá ao "Explorador" e procure por "JAVA PROJECTS". Vá em "Referenced Libraries", clique no icone "+" e selecione o jar baixado anteriormente.
* Execute o seguinte comando no terminal do próprio VS CODE:
~~~
java -cp antlr-4.7.1-complete.jar org.antlr.v4.Tool IsiLang.g4 -package br.com.professorisidro.isilanguage.parser -o ./src/br/com/professorisidro/isilanguage/parser/
~~~
* Por fim, rode o código MainClass.java que está dentro da pasta ./src

## Contribuidores: </h2>

* Fernando Gabriel Chacon Fernandes Teruel do Prado - 11201811700 - https://github.com/Gabriel-Chacon
* Guilherme Cesario Scagnolato - 11201812319 - https://github.com/guiscan
* Kaio Vinicius Souza Santos - 11201810069 - https://github.com/Kaio1505
* Laryssa da Silva de Souza - 11201810068 - https://github.com/131819
* Matheus Flosi Molero - 11201811546 - https://github.com/mateusflosi

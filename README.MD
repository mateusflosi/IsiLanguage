Instalar a extensão: Extension Pack for Java da Microsoft


Instalar a biblioteca .jar no link abaixo:
https://www.antlr.org/download/antlr-4.7.1-complete.jar


Após esse processo, adicione o jar na raiz do diretório do projeto. 
A adição não irá aparecer como uma alteração, pois o jar está no .gitignore


Com a biblioteca instalada, vá ao "Explorador" e procure por "JAVA PROJECTS".
Vá em "Referenced Libraries", clique no icone "+" e selecione o jar baixado anteriormente.
Com esse passo feito os erros do projeto deveriam desaparecer.

Execute o seguinte comando no terminal do próprio VS CODE:

java -cp antlr-4.7.1-complete.jar org.antlr.v4.Tool IsiLang.g4 -package br.com.professorisidro.isilanguage.parser -o ./src/br/com/professorisidro/isilanguage/parser/

Por fim, rode o código MainClass.java que está dentro da pasta ./src



COMANDOS PARA ADD ALTERAÇÕES NO GIT:

git add ./

git commit -m "descrição do commit"

git push -u origin master


COMANDOS PARA ATUALIZAR O DIRETORIO:

git pull

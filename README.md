# XY Inc. 

Plataforma baseada em serviços para busca de Pontos de Interesse (POIs).

## Pré-requisitos:

Deve-se intalar e configurar na máquina local:

Java 1.8+
PostgreSQL 9.6
Apache Tomcat 9
Apache Maven 3.5
Eclipse Java EE IDE for Web Developers. Neon.2 Release (4.6.2)


## Banco de dados

Executar o arquivo 'criacaoBaseDeDados.sql' para criar o banco de dados xy_inc, a tabela POI e popular a tabela com os dados iniciais.


## Projeto no Eclipse

Importar o projeto como um projeto Maven. 
Alterar o arquivo 'hibernate.cfg.xml' com as informações do seu banco de dados.
Configurar o servidor Tomcat para executar o projeto.
Baixar as dependência pelo Maven.
Iniciar o servidor Tomcat.

## Serviços

Cadastrar:
URI:http://localhost:{port}/XyInc/rest/service/cadastrar 
Método: POST 
Parâmetros: { "nome_poi" : "NOME_DO_POI", "coord_x" : COORDENADA_X, "coord_y" : COORDENADA_Y }

Listar Todos
URI:http://localhost:{port}/XyInc/rest/service/listarTodos
Método: GET 

Buscar POIs dada as coordenadas (X,Y) e uma distância máxima (DISTANCIA)
URI:http://localhost:{port}/XyInc/rest/service/buscarPerto?coordX=X&coordY=Y&distMax=DISTANCIA
Método: GET


## Testes

Executar as classes de testes (src/test/java) com o JUnit Test que já vem integrado com o Eclipse.
Teste Integração: classe TesteIntegracao.java
Teste Unitário: classe TesteUnitario.java

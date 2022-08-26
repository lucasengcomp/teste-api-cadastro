# api-cadastro

Aplicação Backend para efetuar o cadastro de Clientes e Pedidos.
## Endpoints
### Clientes
* `/clientes/{id}` Com o método GET -> Lista o Cliente baseado no ID informado
* `/clientes?page=0&linesPerPage=5&direction=DESC&orderBy=id` Com o método GET -> Busca os Clientes paginados
* `/clientes/{id}` Com o método PUT -> Atualiza o Cliente baseado no ID informado
* `/clientes` Com o método POST -> Cadastra um Cliente
* `/clientes/{id}` Com o método DELETE -> Deleta um Cliente

### Pedido
* `/pedidos/pedido-cliente` Com o método POST -> Cadastra um Pedido com um novo Cliente
* `/pedidos/pedido` Com o método POST -> Cadastra um Pedido e vincula Cliente por ID
* `/pedidos/{id}` Com o método DELETE -> Deleta um Cliente

## Requisitos

Para realizar o build e executar a aplicação você irá precisar de:

- [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven](https://maven.apache.org)

## Base de dados

Foi decidido utilizar a base de dados H2 em memória pela praticidade, em um ambiente produção o ideal seria optar por uma base de dados relacional.

Caso haja preferencia de persistir os dados em um banco real, abra o arquivo application.properties no campo *test*, altere para *dev* sendo assim, 
o banco de dados será o postgres com as configurações do arquivo *application-dev.properties*
spring.profiles.active=*test*

Quando a aplicação for executada será automaticamente executado um script com as informações para a população da base, que está presente na pasta resources/import.sql, 
você pode acessar uma interface para a manipulação dessa base no * `http://localhost:8080/h2-console`.
##### LEMBRE-SE QUE PARA FUNCIONAR O H2 (BANCO EM MEMÓRIA) É NECESSÁRIO QUE O ARQUIVO application.properties DEVE TER A CONFIGURAÇÃO spring.profiles.active=test
![image](https://user-images.githubusercontent.com/19320921/122404813-eb31a080-cf55-11eb-820e-7632c564e5e9.png)

## Executar a aplicação localmente

Há diversas maneiras de se executar uma aplicação Spring Boot localmente. Uma delas é executando o método `main` em `com/apicadastro/ApiCadastroApplication.java` a partir da sua IDE.

Você também pode utilizar o [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) da seguinte maneira:

```shell
mvn spring-boot:run
```

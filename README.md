# API Entregas

Esta API oferece funcionalidade para manter o cadastro de entregas.

Especificação macro do projeto:

    - SpringBoot - 3.5.4;
    - Maven;
    - Segurança para acesso via token JWT;
    - Cache de entregas;
    - Banco de dados Postgres 15;
    
Especificação funcional:

	- Cadastrar;
	- Atualizar;
	- Consultar por identificador;
	- Excluir;
	
Os verbos http e respectivos parâmetros possíveis para as requisições estão disponíveis em:

/api/entregas/swagger-ui/index.html
 
# Construção e execução da aplicação.
 
 Esta API pode ser containerizada. A seguir, a sequência de passos para gerar a imagem e executá-la.
 
	1. A partir do Maven, execute o comando: package (para gerar o arquivo .jar que será a base para geração da imagem)
	2. No docker, no diretório do projeto, executar o comando:
	docker-compose up --build
	
 Para acessar a aplicação é necessário realizar o login. 
 
 * Não há implementação de controle de acessos, a partir de um IDP ou controle de permissões nativo na aplicação.
 * Para fins de testes, foi disponibilizado o endpoint:
 
    /auth/login
 
 Exemplo de chamada:
 
    curl --location --request POST 'http://localhost:8080/auth/login' \
    -header 'Content-Type: application/json' \
    -data-raw '{
     "username": "testuser",
     "password": "testpass"
    '
	
Após obter o token de acesso, pode-se acessar demais endpoints:
 
    /api/entregas
 
 Exemplo de chamada:
 
    curl --location --request POST 'http://localhost:8080/api/entregas' \
    -header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlhdCI6MTc1NTE4Mzk1MywiZXhwIjoxNzU1MjcwMzUzfQ.QylHtQdv9gXhzXqaeW33kLF0-sF-mE3Ft2eMqDCnkQo' \
    -header 'Content-Type: application/json' \
    -data-raw '{
       "quantidadePacotes": 8,
       "dataLimiteEntrega": "2025-08-12T22:11:51",
       "nomeCliente": "Jose Maria",
       "cpfCliente": "323.619.977-20",
       "endereco":{
           "logradouro": "RUA AMAZONAS",
           "numero": "1000",
           "complemento": "FUNDOS",
           "bairro": "FLORESTA",
           "cidade": "Franca",
           "estado": "SP",
           "cep": "14406012"
       }
    '
 
	
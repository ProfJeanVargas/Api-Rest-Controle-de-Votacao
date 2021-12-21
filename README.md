# Api-Rest-Controle-de-Votacao

<h2>Objetivo:</h2> Gerenciar um sistema de Votação de Pautas

<b>Regras de negócio:</b>
  - Um usuário não pode votar mais que uma vez na mesma sessão de votação.
  - A sessão deverá ter um tempo pré definido em 1 minuto ou com o tempo informado pelo usuário.
  - Ao final da votação permitir que seja exibido o total de votos da pauta na sessão corrente.

<b>Regras de negócio - Casos Secundários</b>
  - Poderá existir mais de uma sessão aberta para a mesma pauta, mas cada sessão possui seu tempo de votação individual.
  - Numa primeira versão de votação, é exigida uma validação do única para o id do usuário, aqui representado pelo CPF, mas sem uma validação do documento em si.
  - Numa segunda versão de votação, o id do usuário é representado também pelo CPF mas, nesse caso, existe a validação do mesmo, utilizando uma integração com uma api externa.
  - Strings aceitas para o voto: SIM/NÃO, SIM/NAO, sim/não, sim/nao

<h2>EndPoints:</h2>

<b>- GET ​/api​/v1​/pautas: </b> (obtem toda a lista de pautas cadastradas no sistema)

<b>- POST​/api​/v1​/pautas: </b> (cria uma nova pauta)

<b>- GET​/api​/v1​/pautas​/{id}:</b> (busca uma pauta por seu id)

<b>- GET ​/api​/v1​/pautas​/{idpauta}​/{idsessao}​/votos:</b> (busca o total da votacao em determinada pauta e sessao)  

<b>- POST ​/api​/v1​/votacao​/sessao:</b> (abre uma sessão de votação para uma pauta especifica

<b>- POST ​/api​/v1​/votacao​/voto:</b> (cadastra o voto numa sessão de votação SEM VALIDAR o documento (CPF)

<b>- POST ​/api​/v2​/votacao​/voto:</b> (cadastra o voto numa sessão de votação VALIDANDO O DOCUMENTO com uma API Externa (CPF)

<h2>Versionamento</h2>
- Adicionado ao caminho relativo no path da URL: Exemplo: 

  - POST ​/api​/v1​/votacao​/voto:</b> (cadastra o voto numa sessão de votação SEM VALIDAR o documento (CPF)
 
  - POST ​/api​/v2​/votacao​/voto:</b> (cadastra o voto numa sessão de votação VALIDANDO O DOCUMENTO com uma API Externa (CPF)

<h2>Tecnologias</h2>

- Linguagem: Java

- Framework: String Boot, Spring Web, Spring Data.

- Banco de Dados: PostgreSQL

- Gerenciamento de Dependencias: Maven

- Gerenciamento de Deploy: GitHub

- Servidor Hospedagem API: Heroku

- URL: https://appsisvotacao.herokuapp.com/swagger-ui/index.html  
  

Feature: Desafio

  Scenario Outline: Consulta ao cadastro
    Given o acesso ao <Endpoint>
    And a <porta>
    When clicar em pesquisar
    Then deverá aoresentar o <codigo>
    And retornar o metodo POST com os dados cadastrais <codigo>, <nome>, <cpf>, <logradouro>, <numero>, <complemento>, <bairro>, <cidade>, <estado>, <ddd>, o <telefone>
    Examples:
      | Endpoint                      | porta | codigo | nome            | cpf         | logradouro          | numero | complemento | bairro                | cidade    | estado | ddd | telefone  |
      | http://localhost:8080/pessoas | 8080  | 0      | Rafael Teixeira | 12345678909 | Rua Alexandre Dumas | 123    | Empresa     | Chacara Santo Antonio | Sao Paulo | SP     | 11  | 985388877 |



  Scenario Outline: Efetuar a pesquisa cadastral considerando o DDD e número de telefone
    Given o acesso ao <Endpoint>
    When pesquisar pelo <ddd>, <telefone>
    Then deverá retornar o metodo GET com o <StatusCode> e os dados cadastrais <codigo>, <nome>, <cpf>, <logradouro>, <numero>, <complemento>, <bairro>, <cidade>, <estado>
    Examples:
      | Endpoint                      | porta | ddd | telefone  | StatusCode | codigo | nome            | cpf         | logradouro          | numero | complemento | bairro                | cidade    | estado |
      | http://localhost:8080/pessoas | 8080  | 11  | 985388877 | 201        | 0      | Rafael Teixeira | 12345678909 | Rua Alexandre Dumas | 123    | Empresa     | Chacara Santo Antonio | Sao Paulo | SP     |



  Scenario Outline: Efetuar a pesquisa por telefone inexistente
    Given o acesso ao <Endpoint>
    When pesquisar pelo <ddd>, <telefone>
    And e o número for inexistente no cadastro
    Then deverá retornar o método GET com o <StatusCode> e a <mensagem> Número de telefone inexistente
    Examples:
      | Endpoint                      | ddd | telefone  | StatusCode | mensagem                       |
      | http://localhost:8080/pessoas | 11  | 985389999 | 400        | Número de telefone inexistente |



  Scenario Outline:  Efetuar um novo cadastro no sistema
    Given o cadastro de um novo usuário no sistema
    When informar o <codigo>,  <nome>, <cpf>, <logradouro>, <numero>, <complemento>, <bairro>, <cidade>, <estado>, <ddd>, <telefone>
    Then as informações deverão ser salvas e retornar o metodo post com o <StatusCode>
    Examples:
      | codigo | nome          | cpf         | logradouro     | numero | complemento | bairro | cidade              | estado | ddd | telefone  |
      | 1      | Angela Tomino | 01234567890 | Rua das Flores | 1      | Casa        | Centro | Sao José dos Campos | SP     | 12  | 999999990 |



  Scenario Outline: Não permitir cadastrar mais de uma pessoa com o mesmo CPF
    Given o cadastro de um novo usuário no sistema
    When informar um <cpf> já cadastrado no sistema
    Then deverá retornar o método post com o <StatusCode> e a <mensagem>
    Examples:
      | cpf         | StatusCode | mensagem                     |
      | 01234567890 | 400        | CPF já cadastrado no sistema |



  Scenario Outline: Não permitir cadastrar mais de uma pessoa com o mesmo número de telefone
    Given o cadastro de um novo usuário no sistema
    When informar <ddd> e o número <telefone> já cadastrado no sistema
    Then deverá retornar o método GET com o <StatusCode> e a <mensagem>
    Examples:
      | ddd | telefone  | StatusCode | mensagem                                    |
      | 12  | 999999990 | 400        | Número de telefone já cadastrado no sistema |
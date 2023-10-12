# *Esse projeto tem como finalidade apresentar a implementação de um many to many (muitos para muitos) utilizando spring data JPA* ✌️

### Contexto

Vamos considerar o seguinte contexto do projeto. Temos uma aplicação que possui *empresas* e *funcionários*. 

Ou seja, uma empresa poderá ter muitos funcionários assim como um funcionário pode fazer parte de muitas empresas.

Veja abaixo como teremos o mapeamento da classe Empresa.

![image](https://github.com/matheuspieropan/spring-data-many-to-many/assets/56203846/2d897335-b7de-4055-8d26-570d24633c8c)


------------

A classe  possui um atributo List<Funcionario> funcionarios e nele estamos fazendo devido mapeamento @ManyToMany

Junto com essa anotação, também temos @JoinTable que funciona da seguinte forma:

name = "*EMPRESA_FUNCIONARIO*" 

Define o nome da tabela intermediária que será criada para representar a associação entre empresas e funcionários. Nesse caso, demos o nome de empresa_funcionario

------------

joinColumns = @JoinColumn(name = "EMPRESA_ID")

Indica a coluna que representa a chave estrangeira da tabela Empresa na tabela intermediária, neste caso empresa_id

------------

inverseJoinColumns = @JoinColumn(name = "FUNCIONARIO_ID" )

Indica a coluna que representa a chave estrangeira da tabela Funcionario na tabela intermediária, neste caso funcionario_id

------------

# *Starter Config*

Foi criada uma classe StarterConfig que foi anotada com @Configuration para ser gerenciada pelo Spring e também foi definido um @Bean que é invocado ao inicializar a aplicação.

Esse @Bean tem como finalidade verificar se no banco de dados possui algum registro na tabela empresa. Se não houver ele cria um funcionário, uma empresa, atrela esse funcionário a esta empresa e salva no banco de dados.

![image](https://github.com/matheuspieropan/spring-data-many-to-many/assets/56203846/be04fb1d-a84e-4b0a-a1e7-5c22b36d7f20)

# Uso do @Bean

Esse @Bean tem como finalidade verificar se no banco de dados possui algum registro na tabela empresa. Se não houver ele cria um funcionário, uma empresa, atrela esse funcionário a esta empresa e salva no banco de dados.

Vale ressaltar que esse código só funciona pois no mapeamento dos funcionarios na classe Empresa, implementei a anotação *@CASCADE(CASCADETYPE.ALL)*

# O que ela faz? 🔖

Essa anoção faz com que o Spring Data persista primeiramente o funcionário para que então eu consiga salvar a empresa.  Se não houvesse essa anotação, teríamos que salvar primeiramente no banco de dados o usuário para depois salvarmos a empresa.

# Testando aplicação 

Foi implementado dois endpoints na aplicação (ambos são GET)

*1. /empresas* <- Tem como finalidade retornar apenas dados da empresa

*2. /empresas/com-funcionarios* <- Tem como finalidade retornar a empresa e os funcionários associados a ela.

------------
O endpoint que retorna apenas as empresas não traz os dados do funcionário uma vez que o seu fetch é definido como LAZY ( default ). 

Ou seja, ao usar o método findAll() do CrudRepository, ele por default irá trazer todos os atributos da empresa, porém a lista de  funcionários não será carregada automaticamente. A menos que seja explicitamente solicitado.

------------

Já o endpoint que retorna as empresas com os funcionários usa outra abordagem. No caso, temos uma implementação própria de como será feito o select.

![image](https://github.com/matheuspieropan/spring-data-many-to-many/assets/56203846/46241432-7e15-4e7d-ac6f-1d509551d4ce)

Como podemos ver acima, usamos o JOIN FETCH no atributo de funcionários para que o Spring ao trazer a lista de empresa, possa trazer também os seus funcionários.

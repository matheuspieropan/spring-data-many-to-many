# *Esse projeto tem como finalidade apresentar a implementação de um many to many (muitos para muitos) utilizando spring data JPA*

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


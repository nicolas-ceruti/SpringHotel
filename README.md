# Instruções para Configuração do Banco de Dados:

- Faça o clone do projeto ou instale como .zip;
- Abra o projeto preferencialmente com o IntelliJ;
- Registre um servidor PostgreSQL. Durante o desenvolvimento do projeto foi utilizada a versão 16.

<br/>

## Configurações de Conexão:

 - Abra o arquivo application.properties.
 - Insira as informações de conexão com o banco de dados, como URL, usuário e senha.

   <br/>
   
## Configurações do Hibernate e JPA:

**O Hibernate abstrai o seu código SQL, toda a camada JDBC e o SQL será gerado em tempo de execução**
2. **Configurações do Hibernate e JPA:**
   - Verifique as configurações relacionadas ao Hibernate e JPA no mesmo arquivo.
   - Certifique-se de que as seguintes propriedades estejam para que o Hibernate possa gerenciar automaticamente o esquema do banco de dados:


     ```properties
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
     spring.jpa.hibernate.ddl-auto=create
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.format_sql=true
     ```


     <br/>

**Observações:**
   - A propriedade `spring.jpa.hibernate.ddl-auto=create` instruirá o Hibernate a criar automaticamente as tabelas no banco de dados. Utilize isso durante o desenvolvimento, mas considere alternativas como `update` ou `validate` em ambientes de produção para evitar a perda de dados.
<br/>

## Testando os endpoints
Disponibilizei um arquivo nomeado como **SpringHotel.postman_collection** no diretório raiz do projeto. Basta importar o arquivo para o Postman e seu ambiente estará configurado.

<br/>

## Requisitos funcionais:
- Armazenar de forma persiste o cadastro de hóspedes (Informações mínimas:
Nome, documento, telefone);
* Armazenar de forma persistente as reservas geradas;
* Deve ser possível localizar hóspedes por: nome, documento e telefone;
* Localizar hóspedes que ainda estão no hotel;
* Localizar hóspedes que tem reservas, mas ainda não realizaram o check-in.
* Permitir ao atendente realizar o check-in;
* Permitir ao atendente realizar o checkout;
<br/>

## Regras de negócio:
* Diárias de segunda à sexta-feira terão um valor fixo de R$ 120,00;
* Diárias em finais de semana terão um valor fixo de R$ 180,00;
* Caso o hóspede tenha carro e necessite utilizar as vagas disponíveis no
estabelecimento, será cobrado uma taxa adicional de R$ 15,00 de segunda à
sexta-feira e R$ 20,00 nos finais de semana;
* O horário para a realização do check-in será a partir das 14h00min. Ao tentar
realizar o procedimento antes do horário prévio, o sistema deverá emitir um
alerta
* O horário para a realização do checkout será até as 12h00min. Caso o
procedimento seja realizado posterior, deverá ser cobrada uma taxa adicional de
50% do valor da diária (Respeitando a variação para dias úteis e finais de
semana)
* Durante o processo de checkout, deverá ser exibido em detalhes o total geral da
reserva a ser paga;


## Features:
- Paginação e ordenação


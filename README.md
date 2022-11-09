## Golden Raspberry Awards - API

----

# Executar a aplicação após instalar as dependências do projeto:

No diretóro raiz executar:
<br>
```mvn spring-boot:run```

# Acessar informações da API

Após subir a aplicação o swagger com a especificação dos endpoints pode ser acessado através do endereço:
<br>
http://localhost:8080/swagger-ui.html

----

# Executando os testes

Executar, na raiz da aplicação:
<br>
```mvn test</b>```

----

# Requisitos da API

Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que
obteve dois prêmios mais rápido, seguindo a especificação de formato definida.
<br>
Rota relacionada ao teste: ```GET```
<br>
```/producers/bestandworst```
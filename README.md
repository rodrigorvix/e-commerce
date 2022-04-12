<h1 align="center">
    E-commerce-API
</h1>





# ℹ️ Sobre

<p>
Projeto de construção de uma API de um e-commerce. 
</p>
  <br>

# 🛠 Tecnologias

As seguintes tecnologias foram utilizadas na construção do projeto:
  
- Maven
- Java 11
- Spring Boot
- Spring Data
- Spring Security
- Spring JPA
- Lombok
- Actuator
- PostgreSQL
  <br><br>

# 🚀 Como executar

Clone o projeto e acesse a pasta.

```
$ git clone https://github.com/rodrigorvix/e-commerce.git
$ cd e-commerce
```

Atualize o arquivo application.properties com a sua configuração do banco de dados. 


# ℹ️ Rotas

Segue abaixo os endpoints disponíveis no projeto.
Para acessar algumas rotas é necessário o token de autenticação. Para realizar esse procedimento crie um novo usuário, acesse a rota Login e utilize o token disponibilizado para efetuar as requisições. 

<details><summary><b>Rota de Login (Clique aqui)</b></summary>

1- POST - http://localhost:8080/login

```
{
	"username":"ola",
	"password":"123456"
}
```
</details>

<details><summary><b>Rotas de usuário (Clique aqui)</b></summary>

1 - POST - http://localhost:8080/api/users

```
{
	"name":"Teste",
	"username":"teste",
	"password":"123456",
	"function":"ADMIN",
	"birthDate":"1996-05-30"
}
```
2- GET - http://localhost:8080/api/users

```

```

3 - PATCH - http://localhost:8080/api/users/{user_id}

```
{
	"name":"Teste-update",
	"username":"teste",
	"password":"123456",
	"function":"ADMIN",
	"birthDate":"1996-05-30"
}
```

4 - DELETE - http://localhost:8080/api/users/{user_id}

```

```
</details>

<details><summary><b>Rotas de produtos (Clique aqui)</b></summary>

1 - POST - http://localhost:8080/api/products/

```
{
	"name":"Camisa",
	"description":"Camisa branca ...",
	"price":88.00
}
```
2- GET - http://localhost:8080/api/products

```

```

3 - PATCH - http://localhost:8080/api/products/{product_id}

```
{
	"name":"Produto3",
	"description":"Desecrição do produto 2 att...",
	"price":200.00
}
```

4 - DELETE - http://localhost:8080/api/products/{product_id}

```

```
</details>

<details><summary><b>Rotas de pedidos (Clique aqui)</b></summary>

1 - POST - http://localhost:8080/api/users/{user_id}/orders

```
{

}
```
2- GET - http://localhost:8080/api/users/{user_id}/orders

```

```

3 - PATCH Alterar status(open) - http://localhost:8080/api/users/{user_id}/orders/{order_id}/open

```

```

4 - PATCH Alterar status(closed) - http://localhost:8080/api/users/{user_id}/orders/{order_id}/closed

```

```

5 - DELETE - http://localhost:8080/api/users/{user_id}/orders/{order_id}

```

```
</details>

<details><summary><b>Rotas de items do pedido (Clique aqui)</b></summary>

1 - POST - http://localhost:8080/api/users/{user_id}/orders/{order_id}/order_items/{product_id}

```
{
	"quantity": 2	
}

```
2- GET - http://localhost:8080/api/users/{user_id}/orders/{order_id}/order_items

```

```

3 - PATCH  - http://localhost:8080/api/users/{user_id}/orders/{order_id}/order_items/{order_item_id}

```
{
	"quantity": 15,
	"product": {
		"id": 5,
		"name": "Iphone",
		"description": "Celular ...",
		"price": 5000.21,
		"createdAt": "2022-04-11T19:36:35.192905-03:00",
		"updatedAt": "2022-04-11T19:36:35.192943-03:00"
	},
	"order": {
		"id": 7,
		"amount": 0.00,
		"user": {
			"id": 1,
			"name": "Fulando",
			"username": "fulaninho",
			"birthDate": "1968-12-18",
			"createdAt": "2022-04-11T19:32:57.676254-03:00",
			"updatedAt": "2022-04-11T19:32:57.676291-03:00"
		},
		"createdAt": "2022-04-11T19:37:15.231704-03:00",
		"updatedAt": "2022-04-11T19:37:15.23172-03:00"
	}

}
```

5 - DELETE - http://localhost:8080/api/users/{user_id}/orders/{order_id}/order_items/{order_item_id}

```

```
</details>


# To-Do Application API
A permission based authorization system for API access control
___

## Installation and Execution
To run the project locally, follow these steps:
- Clone this repository
```bash
$ git clone https://github.com/schmeekygeek/todo-api.git && cd todo-api/ 
```

- Create database 'todoapp' in MySQL
```sql
SQL> CREATE DATABASE todoapp;
```

- Run the project using
	- The maven wrapper:
	```bash
	$ ./mvnw spring-boot:run
	```
	- OR using the JAR file provided in the root directory of the repository
	```bash
	$ java -jar todoApp-1.0.jar
	```

- Make a request to `http://localhost:8080/api/v1/hello` using **cURL** or any other client.
```bash
▶ curl -i --request GET --url http://localhost:8080/api/v1/hello
HTTP/1.1 403
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 0
Date: Sun, 15 May 2022 19:58:20 GMT
```
> The response must return a 403: Forbidden, since we haven't yet authenticated ourselves.

___
## Using the API
Base URL: `http://localhost:8080/api/v1`

### Register a new User
**Method:** `POST`

**Endpoint:** `/createAccount`

**Description:**
To create a new user, a json body for the user is required in the body.
**cURL** example:
```bash
▶ curl --request POST \
  --url http://localhost:8080/api/v1/createAccount \
  --header 'Content-Type: application/json' \
  --data '{
	"name": "Jason Brody",
	"username": "jasonBro",
	"email": "jaybro@company.com",
	"password": "jason123",
	"about": "likes food"
}'
```

> Data validation will be done so, bogus data won't be accepted.

___
### Authenticate or Login existing User:
**Method:** `POST`

**Endpoint:** `/authenticate`

**Description:**
To login or authenticate an existing user, the username and password is needed that will be validated.
After successful validation, a JWT will be returned in the response.
The client is supposed to hold on to the JWT for requests to other endpoints other than the afore-mentioned.
**cURL** example
```bash
▶ curl --request POST \
  --url http://localhost:8080/api/v1/authenticate \
  --header 'Content-Type: application/json' \
  --data '{
	"username": "jasonBro",
	"password": "jason123"
}'

...
{
  "authenticationSuccess": true,
  "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYXNvbkJybyIsImlhdCI6MTY1MjY0Mzc3MywiZXhwIjoxNjUyNjc5NzczfQ.JFckrLlA1E-8Qk5FJTxG6nO_tBi7EhS9OZvfVJ0Ugo0"
}

```

___
### Authorization for other endpoints example:
**Method:** `GET`

**Endpoint:** `/hello`

**Headers:**

| Title         | Body                 |
| ------------- | -------------------- |
| Authorization | Bearer \<your-token> |


**Description:**
To access other endpoints of the api, the jwt token provided upon authentication will be needed in the header called, **Authorization** followed by the token.

**cURL** example:
```bash
▶ curl --request GET \
  --url http://localhost:8080/api/v1/user/17 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYXNvbkJybyIsImlhdCI6MTY1MjY0Mzc3MywiZXhwIjoxNjUyNjc5NzczfQ.JFckrLlA1E-8Qk5FJTxG6nO_tBi7EhS9OZvfVJ0Ugo0'

...
HTTP/1.1 200
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: text/plain;charset=UTF-8
Content-Length: 20
Date: Sun, 15 May 2022 19:53:23 GMT

<h1>Hello World</h1>
```
A **Hello World** should be visible in the response.

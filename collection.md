# Project: Task Manager [UNESP]
# 📁 Collection: auth 


## End-point: login
### Method: POST
>```
>{{baseUrl}}/auth/login
>```
### Body (**raw**)

```json
{
    "login": "mock@mock.com",
    "password": "mock123"
}
```

### 🔑 Authentication noauth

|Param|value|Type|
|---|---|---|


### Response: 200
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6InRlc3QxMUB0ZXN0LmNvbSIsImV4cCI6MTcxMTY5NzkwMn0.mILfskZgbqFNCdrEWbfnJxXTi51eR3B5nFb-4UqpOuM"
}
```

### Response: 401
```json

```

### Response: 400
```json
{
    "status": 400,
    "message": "Invalid email format"
}
```

### Response: 400
```json
{
    "status": 400,
    "message": "Field 'password' is mandatory"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: register
### Method: POST
>```
>{{baseUrl}}/auth/register
>```
### Body (**raw**)

```json
{
    "name": "New Test",
    "login": "new@test.com",
    "password": "test123",
    "role": "USER"
}
```

### 🔑 Authentication noauth

|Param|value|Type|
|---|---|---|


### Response: 201
```json

```

### Response: 400
```json
{
    "status": 400,
    "message": "E-mail already exists!"
}
```

### Response: 400
```json
{
    "status": 400,
    "message": "Invalid Role value!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
# 📁 Collection: user 


## End-point: getById
### Method: GET
>```
>{{baseUrl}}/user/:id
>```
### Response: 200
```json
{
    "id": 1,
    "name": "Mr. Mock",
    "email": "mock@mock.com",
    "role": "ADMIN",
    "enabled": true,
    "username": "mock@mock.com"
}
```

### Response: 404
```json
{
    "status": 404,
    "message": "User not found!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: getAll
### Method: GET
>```
>{{baseUrl}}/user/
>```
### Response: 200
```json
[
    {
        "id": 1,
        "name": "Mr. Mock",
        "email": "mock@mock.com",
        "role": "ADMIN",
        "enabled": true,
        "username": "mock@mock.com"
    },
    {
        "id": 2,
        "name": "Testerson",
        "email": "test@test.com",
        "role": "USER",
        "enabled": true,
        "username": "test@test.com"
    }
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: updateById
### Method: PATCH
>```
>{{baseUrl}}/user/:id
>```
### Body (**raw**)

```json
{
    "name": "Testerson",
    "email": "test@test.com",
    "password": "testerson1234"
}
```

### Response: 200
```json
{
    "id": 2,
    "name": "Testerson",
    "email": "test@test.com.br",
    "roleNames": [
        "USER"
    ]
}
```

### Response: 200
```json
{
    "id": 2,
    "name": "Testerson Jr.",
    "email": "test@test.com.br",
    "roleNames": [
        "USER"
    ]
}
```

### Response: 200
```json
{
    "id": 2,
    "name": "Testerson Jr.",
    "email": "test@test.com.br",
    "roleNames": [
        "USER"
    ]
}
```

### Response: 400
```json
{
    "status": 400,
    "message": "Password must have at least 6 characters"
}
```

### Response: 400
```json
{
    "status": 400,
    "message": "E-mail already in use by another user!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: deleteById
### Method: DELETE
>```
>{{baseUrl}}/user/:id
>```
### Response: 204
```json

```

### Response: 404
```json
{
    "status": 404,
    "message": "User not found!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
# 📁 Collection: list 


## End-point: create
### Method: POST
>```
>{{baseUrl}}/list/
>```
### Body (**raw**)

```json
{
    "title": "New List",
    "previousListId": 5 // optional
}
```

### Response: 201
```json
{
    "id": 6,
    "title": "Last List",
    "position": 6
}
```

### Response: 201
```json
{
    "id": 7,
    "title": "Middle List",
    "position": 3.5
}
```

### Response: 404
```json
{
    "status": 404,
    "message": "Previous List ID not found!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: getById
### Method: GET
>```
>{{baseUrl}}/list/:id
>```
### Response: 200
```json
{
    "id": 1,
    "title": "To Do",
    "position": 1,
    "tasks": [
        {
            "id": 2,
            "title": "Card 1",
            "userName": "Testerson",
            "startDate": "24/03/2015",
            "endDate": "27/03/2015"
        },
        {
            "id": 52,
            "title": "Card 52",
            "userName": "Testerson",
            "startDate": "24/03/2015",
            "endDate": "27/03/2015"
        }
    ]
}
```

### Response: 404
```json
{
    "status": 404,
    "message": "List not found!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: getAll
### Method: GET
>```
>{{baseUrl}}/list/
>```
### Response: 200
```json
[
    {
        "id": 1,
        "title": "To Do",
        "position": 1.0
    },
    {
        "id": 2,
        "title": "Development",
        "position": 2.0
    },
    {
        "id": 3,
        "title": "Blocked",
        "position": 3.0
    },
    {
        "id": 4,
        "title": "Testing",
        "position": 4.0
    },
    {
        "id": 5,
        "title": "Done",
        "position": 5.0
    },
    {
        "id": 6,
        "title": "Last List",
        "position": 6.0
    }
]
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: updateTitle
### Method: PUT
>```
>{{baseUrl}}/list/:id/title/:newTitle
>```
### Response: 200
```json
{
    "id": 2,
    "title": "Dev",
    "position": 2
}
```

### Response: 404
```json
{
    "status": 404,
    "message": "List not found!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: deleteById
### Method: DELETE
>```
>{{baseUrl}}/list/:id
>```
### Response: 204
```json

```

### Response: 404
```json
{
    "status": 404,
    "message": "List not found!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
# 📁 Collection: task 


## End-point: create
### Method: POST
>```
>{{baseUrl}}/task/
>```
### Body (**raw**)

```json
{
    "title": "Task Title",
    "description": "creating task",
    "usersIds": [1,2],
    "listId": 1,
    "startDate": "01/01/2024",
    "endDate": "30/12/2024"
}
```

### Response: 201
```json
{
    "id": 52,
    "title": "10",
    "description": "creating task",
    "users": [
        {
            "id": 1,
            "name": "Mr. Mock"
        },
        {
            "id": 2,
            "name": "Testerson"
        }
    ],
    "list": {
        "id": 1,
        "title": "To Do"
    },
    "startDate": "01/01/2024",
    "endDate": "30/12/2024"
}
```

### Response: 404
```json
{
    "status": 404,
    "message": "User(s) not found!"
}
```

### Response: 404
```json
{
    "status": 404,
    "message": "List not found!"
}
```

### Response: 422
```json
{
    "status": 422,
    "message": "Invalid date '00/01/2024'"
}
```

### Response: 422
```json
{
    "status": 422,
    "message": "Invalid date '32/12/2024'"
}
```

### Response: 422
```json
{
    "status": 422,
    "message": "Start date cannot be later than end date!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: getById
### Method: GET
>```
>{{baseUrl}}/task/:id
>```
### Response: 200
```json
{
    "id": 1,
    "title": "Card 1",
    "description": "desc card",
    "user": {
        "id": 2,
        "name": "Testerson"
    },
    "list": {
        "id": 1,
        "title": "To Do"
    },
    "startDate": "24/03/2015",
    "endDate": "27/03/2015"
}
```

### Response: 404
```json
{
    "status": 404,
    "message": "Task not found!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: moveToList
### Method: PUT
>```
>{{baseUrl}}/task/:taskId/move-to-list/:listId
>```
### Response: 200
```json
{
    "id": 2,
    "title": "Card 1",
    "description": "desc card",
    "user": {
        "id": 2,
        "name": "Testerson"
    },
    "list": {
        "id": 3,
        "title": "Blocked"
    },
    "startDate": "24/03/2015",
    "endDate": "27/03/2015"
}
```

### Response: 404
```json
{
    "status": 404,
    "message": "Task not found!"
}
```

### Response: 404
```json
{
    "status": 404,
    "message": "List not found!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: deleteById
### Method: DELETE
>```
>{{baseUrl}}/task/:id
>```
### Response: 204
```json

```

### Response: 404
```json
{
    "status": 404,
    "message": "Task not found!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
# 📁 Collection: subtask 


## End-point: create
### Method: POST
>```
>{{baseUrl}}/subtask/
>```
### Body (**raw**)

```json
{
    "title": "Sub Subtask Title",
    "description": "creating subtask",
    "usersIds": [1,2],
    "listId": 3,
    "startDate": "01/01/2025",
    "endDate": "30/12/2025",
    "parentTaskId": 1
}
```

### Response: 201
```json
{
    "id": 52,
    "title": "Sub Subtask Title",
    "description": "creating subtask",
    "users": [
        {
            "id": 1,
            "name": "Mr. Mock"
        },
        {
            "id": 2,
            "name": "Testerson"
        }
    ],
    "list": {
        "id": 3,
        "title": "Blocked"
    },
    "startDate": "01/01/2025",
    "endDate": "30/12/2025",
    "parentTaskId": 1
}
```

### Response: 422
```json
{
    "status": 422,
    "message": "Parent ID cannot be a subtask!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: getById
### Method: GET
>```
>{{baseUrl}}/subtask/:id
>```
### Response: 200
```json
{
    "id": 2,
    "title": "Sub Subtask Title",
    "description": "creating subtask",
    "users": [
        {
            "id": 2,
            "name": "Testerson"
        }
    ],
    "list": {
        "id": 3,
        "title": "Blocked"
    },
    "startDate": "01/01/2025",
    "endDate": "30/12/2025",
    "parentTaskId": 1
}
```

### Response: 404
```json
{
    "status": 404,
    "message": "Subtask not found!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
_________________________________________________
Powered By: [postman-to-markdown](https://github.com/bautistaj/postman-to-markdown/)

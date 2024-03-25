# Project: Task Manager [UNESP]
# 📁 Collection: user 


## End-point: create
### Method: POST
>```
>{{baseUrl}}/user/
>```
### Body (**raw**)

```json
{
    "name": "Mary Mock",
    "email": "mary@mock.com",
    "password": "marymock123",
    "roleNames": ["USER"]
}
```

### Response: 201
```json
{
    "id": 52,
    "name": "Mary Mock",
    "email": "mary@mock.com",
    "roleNames": [
        "USER"
    ]
}
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
    "message": "Password must have at least 6 characters"
}
```

### Response: 400
```json
{
    "status": 400,
    "message": "E-mail already exists!"
}
```

### Response: 404
```json
{
    "status": 404,
    "message": "Role not found with name: MASTER"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: getById
### Method: GET
>```
>{{baseUrl}}/user/:id
>```
### 🔑 Authentication noauth

|Param|value|Type|
|---|---|---|


### Response: 200
```json
{
    "id": 1,
    "name": "Mr. Mock",
    "email": "mock@mock.com",
    "roleNames": [
        "MANAGER"
    ]
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
        "roleNames": [
            "MANAGER"
        ]
    },
    {
        "id": 2,
        "name": "Testerson",
        "email": "test@test.com",
        "roleNames": [
            "USER"
        ]
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
### 🔑 Authentication noauth

|Param|value|Type|
|---|---|---|


### Response: 204
```json
null
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
null
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
    "title": 10,
    "description": "creating task",
    "userId": 1,
    "listId": 1,
    "startDate": "01/01/2024",
    "endDate": "30/12/2024"
}
```

### Response: 201
```json
{
    "id": 554,
    "title": "Task Title",
    "description": "creating task",
    "user": {
        "id": 1,
        "name": "Mr. Mock"
    },
    "list": {
        "id": 1,
        "title": "To Do"
    },
    "startDate": "24/03/2024",
    "endDate": "30/03/2024"
}
```

### Response: 404
```json
{
    "status": 404,
    "message": "User not found!"
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
null
```

### Response: 404
```json
{
    "status": 404,
    "message": "Task not found!"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
_________________________________________________
Powered By: [postman-to-markdown](https://github.com/bautistaj/postman-to-markdown/)

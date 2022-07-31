# Project Management Tool

This is a simple project management application running in Java spring boot backed by Postgres.

## Quick Start Guide

- Start the Postgres server,

```bash
docker run --name postgresql -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -v /var/lib/postgresql/data -d postgres
```

- Create a database project_management

```bash
docker exec -it postgresql psql -U postgres
CREATE DATABASE project_management;
```

- Build and Run the application

- The Server will be running in post 8080

## API Details

### Create a User,

POST - http://localhost:8080/api/v1/user

Body:

```json
{
  "name": "test-user",
  "role": "DEV"
}
```

Response:

```json
{
  "id": 1,
  "name": "test-user",
  "role": "DEV",
  "status": "ACTIVE",
  "board": null,
  "tasks": null,
  "comments": null
}
```

### Create a Project,

POST - http://localhost:8080/api/v1/project

Body:

```json
{
  "title": "Project Beta"
}
```

Response:

```json
{
  "id": 1,
  "title": "Project Beta",
  "status": "ACTIVE",
  "boards": null
}
```

### Create a Board,

POST - http://localhost:8080/api/v1/project/1/board

Body:

```json
{
  "name": "Test Board"
}
```

Response:

```json
{
  "id": 1,
  "name": "Test Board",
  "owners": null,
  "sprints": null,
  "users": null
}
```

### Create a Task,

POST - http://localhost:8080/api/v1/board/1/task

Body:

```json
{
  "name": "Test task for reference",
  "description": "This is a test description",
  "estimation": 2,
  "priority": "P3",
  "type": "FEATURE",
  "dueDate": "2022-08-02T13:20:36.707+00:00",
  "label": "test-feature"
}
```

Response:

```json
{
  "id": 1,
  "name": "Test task for reference",
  "description": "This is a test description",
  "estimation": 2,
  "totalEstination": null,
  "priority": "P3",
  "type": "FEATURE",
  "dueDate": "2022-08-02T13:20:36.707+00:00",
  "label": "test-feature",
  "assigneeName": null,
  "comments": null,
  "linkedTasks": null
}
```

### Assign Task 1 to User 1,

POST - http://localhost:8080/api/v1/tasks/1/assign_task?user=1

Response:

```json
{
  "id": 1,
  "name": "Test task for reference",
  "description": "This is a test description",
  "estimation": 2,
  "totalEstination": 2,
  "priority": "P3",
  "type": "FEATURE",
  "dueDate": "2022-08-02T13:20:36.707+00:00",
  "label": "test-feature",
  "assigneeName": "test-user",
  "comments": [],
  "linkedTasks": []
}
```

### Link the task to another task,

POST - http://localhost:8080/api/v1/tasks/1/link_task?task=2&type=RELATES_TO

Response:

```json
{
  "id": 1,
  "linkType": "RELATES_TO"
}
```

### Create a comment,

POST - http://localhost:8080/api/v1/tasks/1/comment?user=1

Body:

```json
{
  "description": "Please provide more information"
}
```

Response:

```json
{
  "id": 1,
  "description": "Please provide more information",
  "likes": 0
}
```

### Check the Task,

GET - http://localhost:8080/api/v1/task/1

Response:

```json
{
  "id": 1,
  "name": "Test task for reference",
  "description": "This is a test description",
  "estimation": 2,
  "totalEstination": 4,
  "priority": "P3",
  "type": "FEATURE",
  "dueDate": "2022-08-02T13:20:36.707+00:00",
  "label": "test-feature",
  "assigneeName": "test-user",
  "comments": [
    {
      "id": 1,
      "description": "Please provide more information",
      "likes": 0
    }
  ],
  "linkedTasks": [
    {
      "id": 1,
      "linkType": "RELATES_TO"
    }
  ]
}
```

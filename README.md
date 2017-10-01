#SumekChat_Java_Api

Java Chat Api with firebase as database.
Builded with Maven.

[Server works on](http://localhost:8080)

##Postman Query Examples:
x-Room number

###Get:
Url:http://localhost:8080/Chat/x

-Required:
--Basic Auth (user, password)

###Put:
Url:http://localhost:8080/Chat/x

-Required:
--Basic Auth (user, password)
--Xrsf Tokken ( example Tokken: 5185ead1-8630-470e-9e9b-8bae9b8d16f3)
Body:
```
{
  "username":"xxx",
  "text":"yyy"
  }
```

# Web-Programming
# Cinema Application

## Description
This Cinema Application is a Spring Boot project that manages movie data and user interactions. It includes features for both administrators and regular users, following the Model-View-Controller (MVC) design pattern. The application uses Thymeleaf for views and provides CRUD functionalities for managing movies.

## Features
- **Authentication Manager**: Custom authentication for users.
- **Entities and Relations**: Defined entities and relationships for movie data.
- **Repositories**: JPA Repository for database operations.
- **Services**: Business logic layer.
- **Controller**: Manages HTTP requests and application flow.
- **CRUD Functionalities**: Create, read, update, and delete operations for movies.
- **MVC Architecture**: Organized structure with models, views, and controllers.
- **Thymeleaf Views**: HTML templates for UI.
- **Admin Role**: Add, edit, and delete movies.
- **Regular User Role**: Add movies to the cart and remove them.
- **Database**: Movies are saved with attributes: name, price, description, rating and @ManyToOne relation with the Production Entity.

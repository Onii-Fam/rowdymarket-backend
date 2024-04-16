
# Rowdyback Online Shopping Backend

## Overview

This repository contains the backend implementation for the Rowdyback Online Shopping platform. It provides RESTful APIs to manage users, items, orders, and shopping carts.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What you need to install the software:

- JDK 17
- Gradle 7.x (or use the included Gradle wrapper)
- MySQL Server (version 8.x recommended)

### Installation

1. **Clone the repository**

   ```bash
   git clone https://yourrepository.com/rowdyback.git
   cd rowdyback
   ```

2. **Set up MySQL**

   - Create a database for the project:

     ```sql
     CREATE DATABASE rowdydb;
     ```

   - Update `src/main/resources/application.properties` to reflect your database settings:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/rowdydb
     spring.datasource.username=root
     spring.datasource.password=yourPassword
     ```

3. **Build the application**

   ```bash
   ./gradlew build
   ```

4. **Run the application**

   ```bash
   ./gradlew bootRun
   ```

   The server will start at <http://localhost:8080>.

## API Endpoints

All URIs are relative to <http://localhost:8080>

### User Management

- **POST /api/users** - Register a new user.
- **GET /api/users/{userId}** - Retrieve a specific user by their ID.

### Item Management

- **POST /api/items** - Add a new item for sale.
- **GET /api/items** - Retrieve all items.

### Order Management

- **POST /api/orders** - Create a new order.
- **GET /api/orders/{orderId}** - Retrieve a specific order by ID.

### Shopping Cart

- **POST /api/carts** - Create a shopping cart.
- **GET /api/carts/{cartId}** - Retrieve a specific cart by ID.

## Usage

Here is an example of how you might call the API using cURL:

### Create an Order

```bash
curl -X POST http://localhost:8080/api/orders \
     -H 'Content-Type: application/json' \
     -d '{
           "userId": 1,
           "itemIds": [101, 102],
           "quantities": [1, 2],
           "totalPrice": 29.99
         }'
```

### Get an Item

```bash
curl -X GET http://localhost:8080/api/items/101
```

## Testing

Run the following command to execute the automated tests:

```bash
./gradlew test
```



## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.


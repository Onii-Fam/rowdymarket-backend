# Rowdyback API Documentation

## User Management

### Register User
- **Endpoint**: `POST /api/Users/register`
- **Description**: Register a new user.
- **Request Body**: JSON object containing username, password, email, address (optional), and phone number (optional).
- **Responses**:
  - **200 OK**: Returns the newly created user.
  - **Example Response**: `{"userId": 1, "username": "Dune", "email": "dune@example.com"}`

### Get User by ID
- **Endpoint**: `GET /api/Users/{id}`
- **Description**: Retrieves a user by their ID.
- **Path Variables**:
  - `id`: User's identifier.
- **Responses**:
  - **200 OK**: Returns the user details.
  - **404 Not Found**: If no user is found.

### Get All Users
- **Endpoint**: `GET /api/Users`
- **Description**: Retrieves all users.
- **Responses**:
  - **200 OK**: Returns a list of all users.

### Update User
- **Endpoint**: `PUT /api/Users/{id}`
- **Description**: Updates an existing user.
- **Path Variables**:
  - `id`: User's identifier.
- **Request Body**: JSON object containing updated user details.
- **Responses**:
  - **200 OK**: Returns the updated user.
  - **404 Not Found**: If the user is not found.

### Delete User
- **Endpoint**: `DELETE /api/Users/{id}`
- **Description**: Deletes a user by their ID.
- **Path Variables**:
  - `id`: User's identifier.
- **Responses**:
  - **200 OK**: User deleted successfully.
  - **404 Not Found**: If the user is not found.

## Authentication

### Login
- **Endpoint**: `POST /api/login`
- **Description**: Authenticates a user and returns their ID.
- **Request Body**: JSON object containing username and password.
- **Responses**:
  - **200 OK**: Returns a JSON object with the user ID.
    - **Example**: `{"userId": 1}`
  - **400 Bad Request**: Returns an error message if no user is found or if the password is incorrect.
    - **Example**: `{"error": "No user found with username: Dune"}` or `{"error": "Incorrect password"}`

## Item Management

### Get All Items
- **Endpoint**: `GET /api/items`
- **Description**: Retrieves a list of all items. Supports sorting by name, price, and quantity available.
- **Query Parameters**:
  - `sort`: Specifies the field to sort by (e.g., `name`, `price`, `quantityAvailable`).
  - `direction`: Specifies the direction of the sort (`asc` for ascending, `desc` for descending).
- **Responses**:
  - **200 OK**: Returns a list of items sorted based on the specified criteria.
  - **Example**: 
    ```
    GET /api/items?sort=price&direction=desc
    ```
    This retrieves all items sorted by price in descending order.
  - **500 Internal Server Error**: If an error occurs during retrieval.

### Get Item by ID
- **Endpoint**: `GET /api/items/{itemId}`
- **Description**: Retrieves detailed information about a specific item by its ID.
- **Path Variables**:
  - `itemId`: The unique identifier of the item.
- **Responses**:
  - **200 OK**: Returns the detailed information of the item.
  - **404 Not Found**: If no item is found with the given ID.

### Add New Item
- **Endpoint**: `POST /api/items`
- **Description**: Adds a new item to the inventory.
- **Request Body**:
  - **Required**: JSON object representing the item to add, including `name`, `description`, `price`, `quantityAvailable`, and `imageUrl`.
- **Responses**:
  - **201 Created**: Returns the details of the newly created item.
  - **400 Bad Request**: If the item details are incomplete or invalid.

### Update Item
- **Endpoint**: `PUT /api/items/{itemId}`
- **Description**: Updates the information of an existing item.
- **Path Variables**:
  - `itemId`: The unique identifier of the item to update.
- **Request Body**:
  - **Required**: JSON object containing the updated attributes of the item.
- **Responses**:
  - **200 OK**: Returns the updated item details.
  - **404 Not Found**: If no item is found with the given ID.

### Delete Item
- **Endpoint**: `DELETE /api/items/{itemId}`
- **Description**: Deletes an item from the inventory.
- **Path Variables**:
  - `itemId`: The unique identifier of the item to delete.
- **Responses**:
  - **204 No Content**: Indicates successful deletion of the item.
  - **404 Not Found**: If no item is found with the given ID.

## Shopping Cart Management

**Add Item to Cart**
   - `POST /api/Cart/add`
   - Adds a specified item and quantity to the user's shopping cart and applies a discount if provided.
   - **Query Parameters**:
     - `userId`: User's identifier.
     - `itemId`: Item's identifier.
     - `quantity`: Number of items to add.
     - `discountPercent`: Discount percentage on the item.
   - **Success Response**: 200 OK with updated shopping cart details.
   - **Error Response**: 500 Internal Server Error if the user or item is not found.

**Remove Item from Cart**
   - `POST /api/Cart/remove`
   - Removes a specified item from the user's shopping cart.
   - **Query Parameters**:
     - `userId`: User's identifier.
     - `itemId`: Item's identifier.
   - **Success Response**: 200 OK with updated shopping cart details.
   - **Error Response**: 500 Internal Server Error if the cart is not found.

**Get Cart for User**
   - `GET /api/Cart/{userId}`
   - Retrieves the shopping cart details for a specified user.
   - **Path Variables**:
     - `userId`: User's identifier.
   - **Success Response**: 200 OK with cart details.
   - **No Data Response**: 404 Not Found if no cart exists for the user.

**Clear Cart**
   - `POST /api/Cart/clear`
   - Clears all items from the user's shopping cart.
   - **Query Parameters**:
     - `userId`: User's identifier.
   - **Success Response**: 200 OK with a confirmation that the cart has been cleared.
   - **Error Response**: 500 Internal Server Error if the cart is not found.

**Get Cart Total**
   - `GET /api/Cart/total/{userId}`
   - Retrieves the total cost of the cart including tax for a specified user.
   - **Path Variables**:
     - `userId`: User's identifier.
   - **Success Response**: 200 OK with total cost.
   - **No Data Response**: 404 Not Found if no cart exists for the user.

**Get Cart Subtotal**
   - `GET /api/Cart/subtotal/{userId}`
   - Retrieves the subtotal of the cart before tax for a specified user.
   - **Path Variables**:
     - `userId`: User's identifier.
   - **Success Response**: 200 OK with subtotal amount.
   - **No Data Response**: 404 Not Found if no cart exists for the user.

**Get Cart Tax**
   - `GET /api/Cart/tax/{userId}`
   - Retrieves the tax amount for the cart of a specified user.
   - **Path Variables**:
     - `userId`: User's identifier.
   - **Success Response**: 200 OK with tax amount.
   - **No Data Response**: 404 Not Found if no cart exists for the user.

### **Example Usage**

**Curl Command to Add an Item to the Cart:**

```bash
curl -X POST "http://localhost:8080/api/Cart/add?userId=1&itemId=101&quantity=2&discountPercent=10" -H "accept: application/json"
```

**Curl Command to Get Cart Total:**

```bash
curl -X GET "http://localhost:8080/api/Cart/total/1" -H "accept: application/json"
```

## Order Management

Here is the updated API documentation for order management, including sorting options:

---

## Orders API

### **Endpoints Overview**

**Create Order**
   - `POST /api/Orders`
   - Creates a new order based on the provided order details.
   - **Request Body**: JSON object representing the new order.
   - **Success Response**: 200 OK with order details.
   - **Error Response**: 400 Bad Request if data is invalid.

**Get Order By ID**
   - `GET /api/Orders/{id}`
   - Retrieves detailed information about an order by its ID.
   - **Path Variables**:
     - `id`: The ID of the order.
   - **Success Response**: 200 OK with order details.
   - **Error Response**: 404 Not Found if the order does not exist.

**Get All Orders**
   - `GET /api/Orders`
   - Retrieves all orders, optionally sorted by a specified field.
   - **Query Parameters**:
     - `sortBy`: Optional. Criteria to sort the returned orders (e.g., `price_asc`, `price_desc`, `username`, `date`).
   - **Success Response**: 200 OK with a list of orders.
   - **No Data Response**: 204 No Content if no orders exist.

**Update Order**
   - `PUT /api/Orders/{id}`
   - Updates the specified fields of an order.
   - **Path Variables**:
     - `id`: The ID of the order to update.
   - **Request Body**: JSON object with fields to update.
   - **Success Response**: 200 OK with updated order details.
   - **Error Response**: 404 Not Found if the order does not exist.

**Cancel Order**
   - `DELETE /api/Orders/{id}`
   - Cancels an existing order.
   - **Path Variables**:
     - `id`: The ID of the order to cancel.
   - **Success Response**: 200 OK.
   - **Error Response**: 404 Not Found if the order does not exist.

**Get Orders by User ID**
   - `GET /api/Orders/user/{userId}`
   - Retrieves all orders placed by a specific user.
   - **Path Variables**:
     - `userId`: The ID of the user.
   - **Success Response**: 200 OK with a list of orders.
   - **No Data Response**: 404 Not Found if no orders are found for the user.

### **Sorting Options**

You can sort the orders using the `sortBy` parameter in the `GET /api/Orders` endpoint. Available sorting options include:

- `price_asc`: Sorts orders by price in ascending order.
- `price_desc`: Sorts orders by price in descending order.
- `username`: Sorts orders by the username of the user associated with the order (if applicable).
- `date`: Sorts orders by order date in ascending order.

To use a descending order date sort, specify `date_desc` in the `sortBy` parameter.

### **Example Usage**

**Curl Command to Retrieve All Orders Sorted by Date (Descending):**

```bash
curl -X GET "http://localhost:8080/api/Orders?sortBy=date_desc" -H "accept: application/json"
```


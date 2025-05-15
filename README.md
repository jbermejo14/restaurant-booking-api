# 🍽️ Restaurant API

A RESTful API for managing restaurants, orders, and customers. This API allows CRUD operations on restaurant data, customer records, and order information.

## 🌐 Base URL
https://restaurantapi.com

---

## 📖 Endpoints

### 🏪 Restaurants

#### `GET /restaurants`

Returns a list of all restaurants.

- **Responses**:
  - `200 OK`: Array of `RestaurantOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `POST /restaurants`

Creates a new restaurant.

- **Request Body**:
  - `RestaurantInDto`
- **Responses**:
  - `201 Created`: `RestaurantOutDto`
  - `400 Bad Request`
  - `500 Internal Server Error`

---

#### `GET /restaurants/{restaurantId}`

Returns details of a specific restaurant by ID.

- **Parameters**:
  - `restaurantId`: *int64* (required)
- **Responses**:
  - `200 OK`: `RestaurantOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `DELETE /restaurants/{restaurantId}`

Deletes a specific restaurant by ID.

- **Parameters**:
  - `restaurantId`: *int64* (required)
- **Responses**:
  - `204 No Content`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `PUT /restaurants/{restaurantId}`

Updates a specific restaurant by ID.

- **Parameters**:
  - `restaurantId`: *int64* (required)
- **Request Body**:
  - `RestaurantInDto`
- **Responses**:
  - `200 OK`: `RestaurantOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

### 📦 Orders

#### `GET /orders`

Returns a list of all orders.

- **Responses**:
  - `200 OK`: Array of `OrderOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `POST /orders`

Creates a new order.

- **Request Body**:
  - `OrderInDto`
- **Responses**:
  - `201 Created`: `OrderOutDto`
  - `400 Bad Request`
  - `500 Internal Server Error`

---

#### `GET /orders/{orderId}`

Returns order details by ID.

- **Parameters**:
  - `orderId`: *int64* (required)
- **Responses**:
  - `200 OK`: `OrderOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `DELETE /orders/{orderId}`

Deletes a specific order by ID.

- **Parameters**:
  - `orderId`: *int64* (required)
- **Responses**:
  - `204 No Content`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `PUT /orders/{orderId}`

Updates an order by ID.

- **Parameters**:
  - `orderId`: *int64* (required)
- **Request Body**:
  - `OrderInDto`
- **Responses**:
  - `200 OK`: `OrderOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

### 👥 Customers

#### `GET /customers`

Returns a list of all customers.

- **Responses**:
  - `200 OK`: Array of `CustomerOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `POST /customers`

Creates a new customer.

- **Request Body**:
  - `CustomerInDto`
- **Responses**:
  - `201 Created`: `CustomerOutDto`
  - `400 Bad Request`
  - `500 Internal Server Error`

---

#### `GET /customers/{customerId}`

Returns customer details by ID.

- **Parameters**:
  - `customerId`: *int64* (required)
- **Responses**:
  - `200 OK`: `CustomerOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `DELETE /customers/{customerId}`

Deletes a customer by ID.

- **Parameters**:
  - `customerId`: *int64* (required)
- **Responses**:
  - `204 No Content`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `PUT /customers/{customerId}`

Updates a customer by ID.

- **Parameters**:
  - `customerId`: *int64* (required)
- **Request Body**:
  - `CustomerInDto`
- **Responses**:
  - `200 OK`: `CustomerOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

### 🍽️ Menu Items

#### `GET /menuitems`

Returns a list of all menu items.

- **Responses**:
  - `200 OK`: Array of `MenuItemOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `POST /menuitems`

Creates a new menu item.

- **Request Body**:
  - `MenuItemInDto`
- **Responses**:
  - `201 Created`: `MenuItemOutDto`
  - `400 Bad Request`
  - `500 Internal Server Error`

---

#### `GET /menuitems/{menuitemId}`

Returns details of a specific menu item by ID.

- **Parameters**:
  - `menuitemId`: *int64* (required)
- **Responses**:
  - `200 OK`: `MenuItemOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `PUT /menuitems/{menuitemId}`

Updates a menu item by ID.

- **Parameters**:
  - `menuitemId`: *int64* (required)
- **Request Body**:
  - `MenuItemInDto`
- **Responses**:
  - `200 OK`: `MenuItemOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `DELETE /menuitems/{menuitemId}`

Deletes a specific menu item by ID.

- **Parameters**:
  - `menuitemId`: *int64* (required)
- **Responses**:
  - `204 No Content`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

### 🍹 Beverages

#### `GET /beverages`

Returns a list of all beverages.

- **Responses**:
  - `200 OK`: Array of `BeverageOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `POST /beverages`

Creates a new beverage.

- **Request Body**:
  - `BeverageInDto`
- **Responses**:
  - `201 Created`: `BeverageOutDto`
  - `400 Bad Request`
  - `500 Internal Server Error`

---

#### `GET /beverages/{beverageId}`

Returns details of a specific beverage by ID.

- **Parameters**:
  - `beverageId`: *int64* (required)
- **Responses**:
  - `200 OK`: `BeverageOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `PUT /beverages/{beverageId}`

Updates a beverage by ID.

- **Parameters**:
  - `beverageId`: *int64* (required)
- **Request Body**:
  - `BeverageInDto`
- **Responses**:
  - `200 OK`: `BeverageOutDto`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

#### `DELETE /beverages/{beverageId}`

Deletes a specific beverage by ID.

- **Parameters**:
  - `beverageId`: *int64* (required)
- **Responses**:
  - `204 No Content`
  - `400 Bad Request`
  - `404 Not Found`
  - `500 Internal Server Error`

---

## 📦 Schemas

This API uses standard data transfer objects:
- `RestaurantInDto`, `RestaurantOutDto`
- `OrderInDto`, `OrderOutDto`
- `CustomerInDto`, `CustomerOutDto`
- Error responses: `BadRequest`, `NotFound`, `InternalServerError`

---

## 🔒 Error Handling

| Code | Meaning                   |
|------|---------------------------|
| 200  | OK                        |
| 204  | No Content                |
| 400  | Bad Request               |
| 404  | Not Found                 |
| 500  | Internal Server Error     |

---

## 📌 Version

**v0.1.9**

---

## 📄 License

MIT License

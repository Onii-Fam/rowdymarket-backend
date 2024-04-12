
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20)
);

CREATE TABLE Items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,  
    name VARCHAR(100) NOT NULL, 
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    quantity_available INT NOT NULL,
    image_url VARCHAR(255)
);

CREATE TABLE ShoppingCarts (
    cart_id INT AUTO_INCREMENT PRIMARY KEY, -- Each shopping cart has to be unique
    user_id INT NOT NULL, -- Id of User 
    total_amount DECIMAL(10,2) NOT NULL,
    tax_amount DECIMAL(10,2) NOT NULL,
    discount_code VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES Users(user_id) -- Relation between user_id table
);

CREATE TABLE ItemsInCarts (
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY, 
    cart_id INT NOT NULL, -- Id of the cart the item is in 
    item_id INT NOT NULL, -- id of the item
    quantity INT NOT NULL, -- number of items 
    FOREIGN KEY (cart_id) REFERENCES ShoppingCarts(cart_id), -- Relation with cart_id
    FOREIGN KEY (item_id) REFERENCES Items(item_id) -- Relation with item_id
);

CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY, -- Unieque order ids 
    user_id INT NOT NULL, 
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP, -- to keep track of when the order was placed
    total_amount DECIMAL(10,2) NOT NULL, -- Amount of items in order
    tax_amount DECIMAL(10,2) NOT NULL, 
    discount_code VARCHAR(50),
    order_status VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (user_id) REFERENCES Users(user_id) -- Relation to user_id
);
-- similar to cart just when checking out
CREATE TABLE ItemsInOrders (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (item_id) REFERENCES Items(item_id)
);

CREATE TABLE DiscountCodes (
    code_id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) UNIQUE NOT NULL,
    discount_percentage DECIMAL(5,2) NOT NULL,
    expiry_date DATE
);


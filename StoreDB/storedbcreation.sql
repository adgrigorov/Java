CREATE DATABASE store;

USE store;


CREATE TABLE clients (
	client_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    middle_name VARCHAR(30) NOT NULL,
    family_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (client_id)
    );
    
 #one order can have more than one product inside and many clients can have
 #one client can have more than one order
CREATE TABLE orders (
	order_id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY (client_id) REFERENCES clients (client_id)
    );


CREATE TABLE products (
	product_id INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (product_id)
    );


#mid-table for the many-to-many relation
CREATE TABLE products_to_orders (
	order_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders (order_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
    );
-- Initial data for PostgreSQL database (postgres profile)

-- Insert categories
INSERT INTO categories (id, name, description) VALUES 
(1, 'Electronics', 'Electronic devices and gadgets'),
(2, 'Clothing', 'Apparel and fashion items'),
(3, 'Books', 'Books and publications'),
(4, 'Home & Kitchen', 'Home appliances and kitchen items');

-- Insert products
INSERT INTO products (id, name, description, price, category_id) VALUES 
(1, 'Smartphone', 'Latest model smartphone with advanced features', 699.99, 1),
(2, 'Laptop', 'High-performance laptop for work and gaming', 1299.99, 1),
(3, 'T-shirt', 'Cotton t-shirt, comfortable fit', 19.99, 2),
(4, 'Jeans', 'Classic blue jeans, straight cut', 49.99, 2),
(5, 'Programming Book', 'Learn programming with this comprehensive guide', 39.99, 3),
(6, 'Coffee Maker', 'Automatic coffee maker with timer', 89.99, 4);

-- Insert inventory items
INSERT INTO inventory (id, product_id, quantity, location, stock_minimum, last_updated) VALUES 
(1, 1, 25, 'Warehouse A', 10, CURRENT_TIMESTAMP),
(2, 2, 15, 'Warehouse A', 5, CURRENT_TIMESTAMP),
(3, 3, 100, 'Warehouse B', 20, CURRENT_TIMESTAMP),
(4, 4, 75, 'Warehouse B', 15, CURRENT_TIMESTAMP),
(5, 5, 50, 'Warehouse C', 10, CURRENT_TIMESTAMP),
(6, 6, 30, 'Warehouse C', 5, CURRENT_TIMESTAMP);

-- Reset sequences
SELECT setval('categories_id_seq', 5, false);
SELECT setval('products_id_seq', 7, false);
SELECT setval('inventory_id_seq', 7, false);
insert into category (id, name, description) values
(nextval('category_seq'), 'Electronics', 'Devices and gadgets'),
(nextval('category_seq'), 'Books', 'Printed and digital books'),
(nextval('category_seq'), 'Clothing', 'Apparel and accessories');




insert into product (id, name, available_quantity, description, price, category_id) values
(nextval('product_seq'), 'Smartphone', 50, 'Latest model smartphone with advanced features', 699.99, (select id from category where name = 'Electronics')),
(nextval('product_seq'), 'Laptop', 30, 'High-performance laptop for work and gaming', 1299.99, (select id from category where name = 'Electronics')),
(nextval('product_seq'), 'Novel', 100, 'Bestselling fiction novel', 19.99, (select id from category where name = 'Electronics')),
(nextval('product_seq'), 'T-Shirt', 200, 'Comfortable cotton t-shirt', 9.99, (select id from category where name = 'Electronics'));


insert into product (id, name, available_quantity, description, price, category_id) values
(nextval('product_seq'), 'Iphone15', 50, 'Latest model smartphone with advanced features', 699.99, (select id from category where name = 'Clothing')),
(nextval('product_seq'), 'Imac', 30, 'High-performance laptop for work and gaming', 1299.99, (select id from category where name = 'Clothing')),
(nextval('product_seq'), 'Ipod', 100, 'Bestselling fiction novel', 19.99, (select id from category where name = 'Books')),
(nextval('product_seq'), 'IWatch', 200, 'Comfortable cotton t-shirt', 9.99, (select id from category where name = 'Books'));
DROP DATABASE IF EXISTS ota;

CREATE DATABASE ota;

CREATE TABLE products (
  product_id SERIAL PRIMARY KEY,
  product_name VARCHAR(255) NOT NULL UNIQUE,
  category_id INTEGER NOT NULL,
  product_image VARCHAR(255),
  destination_id
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE',
  FOREIGN KEY(category_id) REFERENCES categories(category_id),
  FOREIGN KEY(destination_id) REFERENCES destinations(destination_id)
);

CREATE TABLE product_details (
  product_detail_id SERIAL PRIMARY KEY,
  product_id INTEGER NOT NULL,
  image VARCHAR(255) NOT NULL,
  article_title VARCHAR(255) NOT NULL,
  article_text VARCHAR(2000) NOT NULL,
  price INTEGER NOT NOT DEFAULT 100,
  FOREIGN KEY(product_id) REFERENCES products(product_id)
)
SELECT * FROM product_details;

CREATE TABLE categories (
  category_id SERIAL PRIMARY KEY,
  category_name VARCHAR(255) NOT NULL UNIQUE,
  category_image VARCHAR(255),
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE'
);

CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  user_name VARCHAR(32) NOT NULL UNIQUE,
  password VARCHAR(16) NOT NULL,
  email VARCHAR(100) NOT NULL,
  family_name VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  gender CHAR (1) NOT NULL,
  user_img BYTEA DEFAULT '',
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE'
);

DROP TABLE carts;
DROP TABLE users;

CREATE TABLE carts (
  category_id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  product_count INTEGER NOT NULL,
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE',
  FOREIGN KEY(product_id) REFERENCES products(product_id),
  FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE destinations (
  destination_id SERIAL PRIMARY KEY,
  destination_name VARCHAR(255) NOT NULL UNIQUE,
  destination_image VARCHAR(255),
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE'
)

CREATE TABLE testimonials (
  testimonial_id SERIAL PRIMARY KEY,
  testimonial_image VARCHAR(255) NOT NULL,
  testimonial_title VARCHAR(255) NOT NULL,
  testimonial_text VARCHAR(2000) NOT NULL,
  testimonial_review INTEGER NOT NULL,
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE'
)


CREATE TABLE destination_details (
  destination_detail_id SERIAL PRIMARY KEY,
  destination_id INTEGER NOT NULL,
  image1 VARCHAR(255) NOT NULL,
  article_title1 VARCHAR(255) NOT NULL,
  article_text1 VARCHAR(2000) NOT NULL,
  image2 VARCHAR(255) NOT NULL,
  article_title2 VARCHAR(255) NOT NULL,
  article_text2 VARCHAR(2000) NOT NULL,
  image3 VARCHAR(255) NOT NULL,
  article_title3 VARCHAR(255) NOT NULL,
  article_text3 VARCHAR(2000) NOT NULL,
  FOREIGN KEY(destination_id) REFERENCES destinations(destination_id)
)

CREATE TABLE category_details (
  category_detail_id SERIAL PRIMARY KEY,
  category_id INTEGER NOT NULL,
  image1 VARCHAR(255) NOT NULL,
  article_title1 VARCHAR(255) NOT NULL,
  article_text1 VARCHAR(2000) NOT NULL,
  image2 VARCHAR(255) NOT NULL,
  article_title2 VARCHAR(255) NOT NULL,
  article_text2 VARCHAR(2000) NOT NULL,
  image3 VARCHAR(255) NOT NULL,
  article_title3 VARCHAR(255) NOT NULL,
  article_text3 VARCHAR(2000) NOT NULL,
  FOREIGN KEY(category_id) REFERENCES categories(category_id)
)

CREATE TABLE reservations (
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  date VARCHAR (16) NOT NULL,
  count INTEGER NOT NULL,
  valid_flag BOOLEAN NOT NULL DEFAULT 'TRUE',
  FOREIGN KEY(user_id) REFERENCES users(user_id),
  FOREIGN KEY(product_id) REFERENCES products(product_id)
)

CREATE TABLE bookmarks (
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  FOREIGN KEY(user_id) REFERENCES users(user_id),
  FOREIGN KEY(product_id) REFERENCES products(product_id)
)









INSERT INTO reservations (user_id, product_id, date, count) VALUES (1, 1, '2020-12-30', 4);
INSERT INTO reservations (user_id, product_id, date, count) VALUES (1, 2, '2020-12-31', 2);
SELECT  * FROM reservations;
delete from reservations;

SELECT p.product_image, p.product_name, pd.price, r.count, r.date, r.user_id
FROM reservations AS r
INNER JOIN products AS p
ON r.product_id = p.product_id
INNER JOIN product_details AS pd
ON r.product_id = pd.product_id
WHERE r.user_id = 1;

SELECT p.product_id, p.product_image, p.product_name, c.category_name, d.destination_name
FROM products AS p
INNER JOIN categories AS c ON p.category_id = c.category_id
INNER JOIN destinations AS d ON p.destination_id = d.destination_id
WHERE p.product_name LIKE '%Tokyo%'
OR c.category_name LIKE '%Tokyo%'
OR d.destination_name LIKE '%Tokyo%'
ORDER BY p.product_id
LIMIT 10 OFFSET 2;


SELECT b.id, b.product_id, b.user_id, p.product_image, p.product_name
FROM bookmarks AS b
INNER JOIN products AS p   ON b.product_id = p.product_id
WHERE b.user_id = 1;

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
  user_img BYTEA,
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE'
);

DROP TABLE carts;
DROP TABLE users;

CREATE TABLE files (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  type VARCHAR,
  data BYTEA
);
Drop TABLE files;


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

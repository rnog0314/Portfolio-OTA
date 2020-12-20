DROP DATABASE IF EXISTS ota;

CREATE DATABASE ota;

CREATE TABLE products (
  product_id SERIAL PRIMARY KEY,
  product_name VARCHAR(255) NOT NULL UNIQUE,
  category_id INTEGER NOT NULL,
  product_image VARCHAR(255),
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE',
  FOREIGN KEY(category_id) REFERENCES categories(category_id)
);

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
  profile_picture VARCHAR(255),
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE'
);

CREATE TABLE carts (
  category_id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  product_count INTEGER NOT NULL,
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE',
  FOREIGN KEY(product_id) REFERENCES products(product_id),
  FOREIGN KEY(user_id) REFERENCES users(user_id)
);

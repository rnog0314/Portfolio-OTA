-- DROP DATABASE IF EXISTS ota;

-- CREATE DATABASE ota;

CREATE TABLE categories (
  category_id SERIAL PRIMARY KEY,
  category_name VARCHAR(255) NOT NULL UNIQUE,
  category_image VARCHAR(255),
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE'
);

CREATE TABLE destinations (
  destination_id SERIAL PRIMARY KEY,
  destination_name VARCHAR(255) NOT NULL UNIQUE,
  destination_image VARCHAR(255),
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE'
);

CREATE TABLE products (
  product_id SERIAL PRIMARY KEY,
  product_name VARCHAR(255) NOT NULL UNIQUE,
  category_id INTEGER NOT NULL,
  product_image VARCHAR(255),
  destination_id INTEGER NOT NULL,
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
  price INTEGER NOT NULL DEFAULT 100,
  FOREIGN KEY(product_id) REFERENCES products(product_id)
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

CREATE TABLE carts (
  category_id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  product_count INTEGER NOT NULL,
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE',
  FOREIGN KEY(product_id) REFERENCES products(product_id),
  FOREIGN KEY(user_id) REFERENCES users(user_id)
);


CREATE TABLE testimonials (
  testimonial_id SERIAL PRIMARY KEY,
  testimonial_image VARCHAR(255) NOT NULL,
  testimonial_title VARCHAR(255) NOT NULL,
  testimonial_text VARCHAR(2000) NOT NULL,
  testimonial_review INTEGER NOT NULL,
  delete_flag BOOLEAN NOT NULL DEFAULT 'FALSE'
);


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
  movie_path VARCHAR(255) DEFAULT 'https://www.youtube.com/embed/CNeakmoER7Q',
  FOREIGN KEY(destination_id) REFERENCES destinations(destination_id)
);

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
);

CREATE TABLE reservations (
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  title VARCHAR (64) NOT NULL,
  start_date VARCHAR (16) NOT NULL,
  end_date VARCHAR (16) NOT NULL,
  count INTEGER NOT NULL,
  valid_flag BOOLEAN NOT NULL DEFAULT 'FALSE',
  FOREIGN KEY(user_id) REFERENCES users(user_id),
  FOREIGN KEY(product_id) REFERENCES products(product_id)
);

CREATE TABLE bookmarks (
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  FOREIGN KEY(user_id) REFERENCES users(user_id),
  FOREIGN KEY(product_id) REFERENCES products(product_id)
);

CREATE TABLE notices (
  id SERIAL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  text VARCHAR(2000) NOT NULL,
  visible_flag BOOLEAN DEFAULT 'TRUE',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE admin (
  id SERIAL PRIMARY KEY,
  admin_name VARCHAR(32) NOT NULL,
  password VARCHAR(64) NOT NULL
);


-- -- MySQLの「ON UPDATE TIMESTAMP」はPOSTGRESではこの関数を設定しておいて、
-- CREATE OR REPLACE FUNCTION trigger_set_timestamp()
-- RETURNS TRIGGER AS $$
--   BEGIN NEW.updated_at = NOW();
--   RETURN NEW;
-- END;
-- $$ LANGUAGE 'plpgsql';

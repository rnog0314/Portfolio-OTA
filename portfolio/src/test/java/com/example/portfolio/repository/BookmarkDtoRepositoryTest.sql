INSERT INTO users (user_id, user_name, password, email, family_name, first_name, gender) VALUES (1, 'dummyUser', 'password', 'example@gamil.com', 'Jones', 'Mark', 'M');

INSERT INTO categories (category_id, category_name) VALUES (1, 'dummyCategory1');
INSERT INTO categories (category_id, category_name) VALUES (2, 'dummyCategory2');

INSERT INTO destinations (destination_id, destination_name) VALUES (1, 'dummyDest1');
INSERT INTO destinations (destination_id, destination_name) VALUES (2, 'dummyDest2');

INSERT INTO products (product_id, product_name, product_image, category_id, destination_id) VALUES (1, 'dummyName1', 'dummyImage1',  1, 1);
INSERT INTO products (product_id, product_name, product_image, category_id, destination_id) VALUES (2, 'dummyName2', 'dummyImage2', 2, 2);

INSERT INTO bookmarks (user_id, product_id) VALUES (1, 1);
INSERT INTO bookmarks (user_id, product_id) VALUES (1, 2);

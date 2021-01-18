INSERT INTO users (id, user_name, password, email, family_name, first_name, gender) VALUES (1, 'dummyUser', 'password', 'example@gamil.com', 'Jones', 'Mark', 'M');

INSERT INTO categories (category_id, category_name) VALUES (1, 'dummyCategory1');
INSERT INTO categories (category_id, category_name) VALUES (2, 'dummyCategory2');
INSERT INTO categories (category_id, category_name) VALUES (3, 'dummyCategory3');

INSERT INTO destinations (destination_id, destination_name) VALUES (1, 'dummyDest1');
INSERT INTO destinations (destination_id, destination_name) VALUES (2, 'dummyDest2');

INSERT INTO products (id, product_name, product_image, category_id, destination_id, delete_flag, price) VALUES (1, 'dummyName1', 'dummyImage1',  1, 1, false, 1000);
INSERT INTO products (id, product_name, product_image, category_id, destination_id, delete_flag, price) VALUES (2, 'dummyName2', 'dummyImage2', 2, 2, false, 1000);
INSERT INTO products (id, product_name, product_image, category_id, destination_id, delete_flag, price) VALUES (3, 'dummyName3', 'dummyImage3', 3, 1, false, 1000);
INSERT INTO products (id, product_name, product_image, category_id, destination_id, delete_flag, price) VALUES (4, 'dummyName4', 'dummyImage4', 3, 2, false, 1500);

INSERT INTO bookmarks (user_id, product_id) VALUES (1, 1);
INSERT INTO bookmarks (user_id, product_id) VALUES (1, 2);

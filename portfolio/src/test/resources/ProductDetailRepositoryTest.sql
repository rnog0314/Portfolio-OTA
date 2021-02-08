INSERT INTO categories (category_id, category_name, category_image) VALUES (1, 'dummyCategoryName', 'dummyCategoryImgPath');

INSERT INTO destinations (destination_id, destination_name, destination_image) VALUES (1, 'dummyDestinationName', 'dummyDestinationImgPath');

INSERT INTO products (product_name, category_id, product_image, destination_id, price) VALUES ('dummyProductName', 1, 'dummyProductImgPath', 1, 1000);

INSERT INTO product_details (product_id, image, article_title, article_text, price) VALUES (1, 'dummyImg', 'dummyArticleTitle', 'dummyArticleText', 1000);

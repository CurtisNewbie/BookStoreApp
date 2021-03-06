/*

    This script is used for testing. Remember to update test cases in OrderRepositoryTest if this is modified.


*/
INSERT INTO BS_HomeNew 
VALUES 
    (11, 'Sale! Sale! Sale! We are on sale now!! Buy some and make us rich!', '2020-02-01', 'SALE!'),
    (12, 'New animation books are now available! Go have a look and become an animator!', '2020-02-02', 'NEW ANIMATION BOOKS');

INSERT INTO BS_Book 
VALUES 
    (11, 'Richard E. Williams','In this book, based on his sold-out Animation Masterclass in the United States and across Europe, Williams provides the underlying principles of animation that very animator - from beginner to expert, classic animator to computer animation whiz - needs.', '2009-11-05', 'https://images-na.ssl-images-amazon.com/images/I/51YvgAKQYRL._SX421_BO1,204,203,200_.jpg', 21.95, 'The Animator''s Survival Kit'),
    (22, 'Simple Storyboards','By the end of the storyboarding notebook you will be able to go through your storyboard writing examples and track your progress and you will have a record of what works best for you.', '2020-02-01', 'https://images-na.ssl-images-amazon.com/images/I/51vJRPD1sxL._SX385_BO1,204,203,200_.jpg', 6.78, 'Storyboard Notebook 4:3 Panels');

INSERT INTO DeliveryOption
VALUES
    (11, 'Next Day Delivery', 5),
    (22, '3 - 5 Days Delivery', 3.2),
    (33, 'One Week Delivery', 2.6);

INSERT INTO BS_Order
VALUES
    (100, 'Sheffield', 'Yorkshare', 'Flat no1, 1st Street', 'SH1 HS1', NULL, '2020-01-01', 'CurtisNewbie', 'Z', 26.95, 11);

INSERT INTO BS_Book_Order
VALUES
    (123, 1, 11, 100);


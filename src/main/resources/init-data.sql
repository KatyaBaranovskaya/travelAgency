-- INSERT INTO country;
INSERT INTO country VALUES (1, 'Belarus');
INSERT INTO country VALUES (2, 'Russia');
INSERT INTO country VALUES (3, 'Poland');
INSERT INTO country VALUES (4, 'Ukraine');
INSERT INTO country VALUES (5, 'Lithuania');



-- INSERT INTO hotel;
INSERT INTO hotel VALUES (1, 'Europe', '8034588467', 4);
INSERT INTO hotel VALUES (2, 'Plaza', '8029476432', 5);
INSERT INTO hotel VALUES (3, 'MarcoPolo House', '8036652478', 3);
INSERT INTO hotel VALUES (4, 'Fortuna', '8048561203', 4);
INSERT INTO hotel VALUES (5, 'Gabi B&B', '8033156783', 5);



-- INSERT INTO tour;
INSERT INTO tour VALUES (1, '/image1.jpg', '2018-03-11', 7, 1, 1, 'BUSINESS', 'Great tour', 115.50);
INSERT INTO tour VALUES (2, '/image2.jpg', '2018-02-21', 11, 2, 2, 'VACATION', 'Great tour', 317.50);
INSERT INTO tour VALUES (3, '/image3.jpg', '2018-03-31', 5, 3, 3, 'SHOPPING', 'Good tour', 225.00);
INSERT INTO tour VALUES (4, '/image4.jpg', '2018-03-01', 14, 4, 4, 'HONEYMOON', 'Great tour', 515.50);
INSERT INTO tour VALUES (5, '/image5.jpg', '2018-04-15', 7, 5, 5, 'HOT_TOUR', 'Good tour', 716.50);



-- INSERT INTO users;
INSERT INTO users VALUES (1, 'katya1997', 'fjGDchf8483');
INSERT INTO users VALUES (2, 'diana383', '766Fdsas4');
INSERT INTO users VALUES (3, 'dima84', 'jdhdGF654');
INSERT INTO users VALUES (4, 'liza1998', 'fdf5DSD');
INSERT INTO users VALUES (5, 'maxim99', 'Fd5GG4gas');



-- INSERT INTO review;
INSERT INTO review VALUES (1, 1, 1, 'Good');
INSERT INTO review VALUES (2, 2, 2, 'Bad');
INSERT INTO review VALUES (3, 3, 3, 'Great');
INSERT INTO review VALUES (4, 4, 4, 'Awful');
INSERT INTO review VALUES (5, 5, 5, 'Good');



-- INSERT INTO tour_user;
INSERT INTO tour_user VALUES (1, 5);
INSERT INTO tour_user VALUES (2, 3);
INSERT INTO tour_user VALUES (3, 4);
INSERT INTO tour_user VALUES (4, 2);
INSERT INTO tour_user VALUES (5, 1);
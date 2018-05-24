-- Table: country

-- DROP TABLE country;

CREATE TABLE IF NOT EXISTS country
(
    id_country serial NOT NULL,
    name varchar(80) NOT NULL,
    CONSTRAINT country_pkey PRIMARY KEY (id_country),
    CONSTRAINT country_name_uq UNIQUE (name)
);



-- Table: hotel

-- DROP TABLE hotel;

CREATE TABLE IF NOT EXISTS hotel
(
    id_hotel serial NOT NULL,
    name varchar(255) NOT NULL,
    phone varchar(50) NOT NULL,
    stars smallint NOT NULL,
    CONSTRAINT hotel_pkey PRIMARY KEY (id_hotel)
);



-- Table: tour

-- DROP TABLE tour;

CREATE TABLE IF NOT EXISTS tour
(
    id_tour serial NOT NULL,
    photo varchar(255) NOT NULL,
    date date NOT NULL,
    duration smallint NOT NULL,
    id_country int NOT NULL,
    id_hotel int NOT NULL,
    type varchar(50) NOT NULL,
    description text NOT NULL,
    cost decimal NOT NULL,
    CONSTRAINT tour_pkey PRIMARY KEY (id_tour),
    CONSTRAINT id_countr_fk FOREIGN KEY (id_country)
        REFERENCES country (id_country)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT id_hotel_fk FOREIGN KEY (id_hotel)
        REFERENCES hotel (id_hotel)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);



-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE IF NOT EXISTS users
(
    id_user serial NOT NULL,
    login varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id_user),
    CONSTRAINT login_uq UNIQUE (login)
);



-- Table: tour_user

-- DROP TABLE tour_user;

CREATE TABLE IF NOT EXISTS tour_user
(
    id_user int NOT NULL,
    id_tour int NOT NULL,
    CONSTRAINT user_tour_pkey PRIMARY KEY (id_user, id_tour),
    CONSTRAINT user_id_fk FOREIGN KEY (id_user)
        REFERENCES users (id_user)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT tour_id_fk FOREIGN KEY (id_tour)
        REFERENCES tour (id_tour)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);



-- Table: review

-- DROP TABLE review;

CREATE TABLE IF NOT EXISTS review
(
    id_review serial NOT NULL,
    id_tour int NOT NULL,
    id_user int NOT NULL,
    content text NOT NULL,
    CONSTRAINT review_pkey PRIMARY KEY (id_review),
    CONSTRAINT tour_fk FOREIGN KEY (id_tour)
        REFERENCES tour (id_tour)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
  	CONSTRAINT user_fk FOREIGN KEY (id_user)
        REFERENCES users (id_user)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
INSERT INTO genre (name, description)
VALUES ('Action', 'Boom!');

INSERT INTO movie (id, title, genre_name, description, release_date, length, image_path)
VALUES (5, 'Trigger Point', 'Action', 'Barry Pepper liefert ab', '2015-11-23', 120, 'path');

INSERT INTO address(id, zip_code, city, street, number, additional_information)
VALUES (10, 12345, 'Mannheim', 'Bahnhofstrasse', 1, 'none');

INSERT INTO cinema(id, address_id)
VALUES (15, 10);

INSERT INTO cinema_hall(id, name, cinema_id)
VALUES (20, 'Saal 1', 15);

INSERT INTO seating_plan_template(id, rows, seats_per_row, cinema_hall_id)
VALUES (25, 5, 5, 20);

INSERT INTO seat_number(id, line, number, seating_plan_template_id)
VALUES (100, 'A', 1, 25);
INSERT INTO seat_number(id, line, number, seating_plan_template_id)
VALUES (101, 'A', 2, 25);
INSERT INTO seat_number(id, line, number, seating_plan_template_id)
VALUES (102, 'B', 1, 25);
INSERT INTO seat_number(id, line, number, seating_plan_template_id)
VALUES (103, 'B', 2, 25);

INSERT INTO showing(id, title, starts_at, ends_at, cinema_hall_id, movie_id)
VALUES (30, 'Trigger Point Showing', '2023-02-07T20:00', '2023-02-07T22:00', 20, 5);

INSERT INTO seating_plan(id, showing_id)
VALUES (35, 30);

INSERT INTO seat(id, occupied, price, seat_number_id, seating_plan_id)
VALUES (200, 0, 10, 100, 35);
INSERT INTO seat(id, occupied, price, seat_number_id, seating_plan_id)
VALUES (201, 0, 10, 101, 35);
INSERT INTO seat(id, occupied, price, seat_number_id, seating_plan_id)
VALUES (202, 1, 10, 102, 35);
INSERT INTO seat(id, occupied, price, seat_number_id, seating_plan_id)
VALUES (203, 1, 10, 103, 35);

INSERT INTO "user"(id, first_name, last_name, dob, email, password, address_id)
VALUES (40, 'Hans', 'Meier', '1959-04-23', 'hans@meier.de', 'hans123', 10);
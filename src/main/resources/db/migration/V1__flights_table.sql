DROP TABLE if EXISTS flightssearch.flights;
CREATE TABLE flightssearch.flights(
    flight_id INT primary key not null AUTO_INCREMENT,
    departure_from varchar(255),
    arrival_to varchar(255),
    departure_date date,
    number_of_seats INT,
    price decimal(19,2)
);
alter table flightssearch.flights add airport_id int null;
alter table flightssearch.flights add foreign key (airport_id) references flightssearch.airport(id);
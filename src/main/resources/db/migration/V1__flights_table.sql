CREATE TABLE flightssearch.airport(
    id INT primary key not null AUTO_INCREMENT,
    location varchar(255),
    country varchar(255),
    name varchar(255),
    iata_code varchar(255),
    latitude double,
    longitude double
);
DROP TABLE if EXISTS flightssearch.flights;
CREATE TABLE flightssearch.flights(
    id bigint primary key not null AUTO_INCREMENT,
    departure_to varchar(255),
    arrival_to varchar(255),
    departure_date datetime,
    number_of_seats INT,
    price decimal(19,2)
);
alter table flightssearch.flights add airport_id int null;
alter table flightssearch.flights add foreign key (airport_id) references flightssearch.airport(id);
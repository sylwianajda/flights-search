alter table flightssearch.flights add column flight_number varchar(255);
alter table flightssearch.flights RENAME COLUMN number_of_seats TO number_of_seats_available;
--alter table flightssearch.flights RENAME COLUMN departure_date TO depart_date;
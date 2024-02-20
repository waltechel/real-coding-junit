INSERT INTO rc_flight (flight_id, flight_name, source_name, target_name, type, status) VALUES
('FLIGHT-ov3bks8mbqhbbggekduevduomi', 'US999', '서울', '캘리포니아', 'BUSINESS', 'CREATED'),
('FLIGHT-bp2cvqov33019rpjik00cu497g', 'KR234', '부산', '서울', 'ECONOMY', 'CREATED');

INSERT INTO rc_passenger (passenger_id, passenger_rrn, name, type, status) VALUES
('PASSENGER-v8a1uk039ovuj6ikokbqe0qid2', '910101-1234567', '홍길동', 'VIP', 'CREATED'),
('PASSENGER-5q1h51aa9mc5p2fb7ki86t78fp', '910427-2222222', '영희', 'REGULAR', 'CREATED');

INSERT INTO rc_travel (travel_id, flight_id, passenger_id, status) VALUES
('TRAVEL-95bnglap38sds9ovhflc744ben', 'FLIGHT-ov3bks8mbqhbbggekduevduomi', 'PASSENGER-v8a1uk039ovuj6ikokbqe0qid2', 'CREATED'),
('TRAVEL-63testd285p8lfiq6n6kgnkpp9', 'FLIGHT-bp2cvqov33019rpjik00cu497g', 'PASSENGER-5q1h51aa9mc5p2fb7ki86t78fp', 'CREATED');

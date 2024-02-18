-- noinspection SqlNoDataSourceInspectionForFile
-- noinspection SqlDialectInspectionForFile
DROP TABLE IF EXISTS rc_travel;

CREATE TABLE rc_travel (
    travel_id varchar(200) NOT NULL,
    flight_id varchar(200) NOT NULL,
    passenger_id varchar(200) NOT NULL,
    status varchar(200) NULL,
    created_dt timestamp NULL,
    created_by varchar(200) NULL,
    modified_dt timestamp NULL,
    modified_by varchar(200) NULL,
    CONSTRAINT rc_travel_pkey PRIMARY KEY(travel_id)
);

-- noinspection SqlNoDataSourceInspectionForFile
-- noinspection SqlDialectInspectionForFile
DROP TABLE IF EXISTS rc_flight;

CREATE TABLE rc_flight (
    flight_id varchar(200) NOT NULL,
    flight_name varchar(200) NULL,
    source_name varchar(200) NULL,
    target_name varchar(200) NULL,
    status varchar(200) NULL,
    type varchar(200) NULL,
    created_dt timestamp NULL,
    created_by varchar(200) NULL,
    modified_dt timestamp NULL,
    modified_by varchar(200) NULL,
    CONSTRAINT rc_flight_pkey PRIMARY KEY(flight_id)
);

-- noinspection SqlNoDataSourceInspectionForFile
-- noinspection SqlDialectInspectionForFile
DROP TABLE IF EXISTS rc_passenger;

CREATE TABLE rc_passenger (
    passenger_id varchar(200) NOT NULL,
    passenger_rrn varchar(200) NULL,
    name varchar(200) NULL,
    status varchar(200) NULL,
    type varchar(200) NULL,
    created_dt timestamp NULL,
    created_by varchar(200) NULL,
    modified_dt timestamp NULL,
    modified_by varchar(200) NULL,
    CONSTRAINT rc_passenger_pkey PRIMARY KEY(passenger_id)
);


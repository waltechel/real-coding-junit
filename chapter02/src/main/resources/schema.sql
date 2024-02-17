-- noinspection SqlNoDataSourceInspectionForFile
-- noinspection SqlDialectInspectionForFile
DROP TABLE IF EXISTS rc_flight;

CREATE TABLE rc_flight (
    flight_id varchar(200) NOT NULL,
    flight_name varchar(200) NOT NULL,
    source_name varchar(200) NOT NULL,
    target_name varchar(200) NOT NULL,
    status varchar(200) NOT NULL,
    created_dt timestamp NOT NULL,
    created_by varchar(200) NOT NULL,
    modified_dt timestamp NOT NULL,
    modified_by varchar(200) NOT NULL,
    CONSTRAINT rc_flight_pkey PRIMARY KEY(flight_id)
);
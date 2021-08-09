CREATE DATABASE IF NOT EXISTS demo CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS demo.patients
(
    patient_id    BIGINT       NOT NULL AUTO_INCREMENT,
    family        VARCHAR(20)  NOT NULL,
    given         VARCHAR(20)  NOT NULL,
    date_of_birth DATE         NOT NULL,
    sex           VARCHAR(1)   NOT NULL,
    address       VARCHAR(100) NOT NULL,
    phone         VARCHAR(15)  NOT NULL,
    PRIMARY KEY (patient_id)
) ENGINE = InnoDB;

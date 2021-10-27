CREATE TABLE Product
(
    product_id INTEGER NOT NULL AUTO_INCREMENT,
    product_type  VARCHAR(50) NOT NULL,
    price   VARCHAR(50) NOT NULL,
    startDate  VARCHAR(50) NULL,
    endDate   VARCHAR(50) NULL,
    product_event_id INTEGER NOT NULL,
    PRIMARY KEY (product_id)
);

CREATE TABLE ProductEvent
(
    event_id INTEGER NOT NULL AUTO_INCREMENT,
    event_type VARCHAR(50) NOT NULL,
    insured_id VARCHAR(50) NOT NULL,
    PRIMARY KEY (event_id)
);
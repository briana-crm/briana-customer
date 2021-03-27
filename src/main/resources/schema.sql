CREATE TABLE addresses (
    id       IDENTITY     NOT NULL PRIMARY KEY,
    country  VARCHAR(255) NOT NULL,
    city     VARCHAR(255) NOT NULL,
    postcode VARCHAR(255) NOT NULL
);

CREATE TABLE customers (
     id              IDENTITY NOT NULL PRIMARY KEY,
     name            VARCHAR(255) NOT NULL,
     phone           VARCHAR(255) NOT NULL,
     email           VARCHAR(255) NOT NULL,
     description     VARCHAR(255) NULL,
     address_id      INTEGER      NULL,
     organization_id INTEGER      NOT NULL,
     version         INTEGER      NOT NULL,
     FOREIGN KEY (address_id) REFERENCES addresses(id)
);

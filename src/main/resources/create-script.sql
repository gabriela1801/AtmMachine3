CREATE TABLE CLIENT_DETAILS (
  client_id INT NOT NULL ,
  card_number VARCHAR(20) NOT NULL ,
  first_name VARCHAR(20) NOT NULL ,
  last_name VARCHAR(20) NOT NULL ,
  existing_debit INTEGER NOT NULL,
  cont_type VARCHAR(20) NOT NULL,
  PRIMARY KEY (client_id, card_number)
);

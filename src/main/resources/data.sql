DROP TABLE IF EXISTS billionaires;

CREATE TABLE Trade (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  tradeId VARCHAR(250) NOT NULL,
  version int NOT NULL,
  counterPartyId VARCHAR(250) DEFAULT NULL,
  bookId VARCHAR(250) DEFAULT NULL,
  maturityDate Date not null,
  createdDate Date not null,
  expired VARCHAR(1) DEFAULT 'N',
);


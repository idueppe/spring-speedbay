DROP TABLE Application_Log;
DROP SEQUENCE LogSequence;
CREATE TABLE Application_Log (
  id        BIGINT PRIMARY KEY NOT NULL,
  message   CHARACTER (2048),
  createdBy CHARACTER (100),
  createdAt TIMESTAMP
);
CREATE SEQUENCE LogSequence;

DROP TABLE Application_Log;
DROP SEQUENCE LogSequence;
CREATE TABLE Application_Log (
  id        BIGINT PRIMARY KEY NOT NULL,
  message   CHARACTER VARYING(2048),
  createdBy CHARACTER VARYING(100),
  createdAt TIMESTAMP
);
CREATE SEQUENCE LogSequence;

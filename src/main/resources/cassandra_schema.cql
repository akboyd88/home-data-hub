DROP KEYSPACE IF EXISTS etltemplate;

CREATE KEYSPACE etltemplate WITH replication = {
  'class': 'SimpleStrategy',
  'replication_factor': 3
};

USE etltemplate;

CREATE TABLE test_table (
  key text PRIMARY KEY,
  value text
);
package db.migration;

import db.migration.helpers.SimpleExecutionMigration;

public class V0_04__CreateTestNumberTable extends SimpleExecutionMigration {
    private static final String UP_SQL = """
       CREATE TABLE IF NOT EXISTS test_numbers (
                   id uuid PRIMARY KEY default gen_random_uuid(),
                   value integer,
                   created timestamptz default now()
               );
               CREATE INDEX idx_value ON test_numbers(value);
               CREATE INDEX idx_created ON test_numbers(created);
    """;

    /**
     * Create test number table in database
     */
    public V0_04__CreateTestNumberTable(){
        super(false);
    }

    @Override
    public String getSql() {
        return UP_SQL;
    }
}

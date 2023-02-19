package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V4__insert_boardingPass extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("CREATE TABLE flightssearch.boarding_passes(\n" +
                        "id bigint primary key not null AUTO_INCREMENT,\n" +
                        "first_name varchar(255),\n" +
                        "last_name varchar(255)\n" +
                        ");");
    }

//"alter table flightssearch.boarding_passes add flight_id bigint null;\n" +
//        "alter table flightssearch.boarding_passes add foreign key (flight_id) references flightssearch.flight(id);");
}

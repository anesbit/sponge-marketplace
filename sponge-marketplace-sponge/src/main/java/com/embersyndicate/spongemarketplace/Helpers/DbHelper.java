package com.embersyndicate.spongemarketplace.Helpers;

import com.embersyndicate.spongemarketplace.Config.ConfigHandler;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;


public class DbHelper {
    private SqlService sql;

    public DataSource getDataSource(String jbcUrl) throws SQLException {
        if (sql == null) {
            sql = Sponge.getServiceManager().provide(SqlService.class).get();
        }
        return  sql.getDataSource(jbcUrl);
    }

    public void dbQuery() throws SQLException {

        Optional<String> dbType = Optional.ofNullable(ConfigHandler.dbType);
        private String dbT = "";
        if (dbType.isPresent()) {
                String dbT = dbType.get();

            } else {
                String dbT = "H2";
        }


        switch (dbT) {
            case "H2":
                String uri = "jbc:h2:data.db";

            case "MySQL":

            default: //H2

        }

    }
}

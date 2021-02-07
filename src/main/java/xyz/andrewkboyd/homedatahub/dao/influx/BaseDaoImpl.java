package xyz.andrewkboyd.etltemplate.dao.influx;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Qualifier;
import xyz.andrewkboyd.etltemplate.dao.interfaces.BaseDAO;

public abstract class BaseDaoImpl<T> implements BaseDAO<T> {

    private final InfluxDB db;

    protected BaseDaoImpl(@Qualifier("getInfluxDB") InfluxDB influxDB){
        db = influxDB;
    }

    public abstract Point getPointFromValue(T val);

    protected InfluxDB getDb() {
        return db;
    }

    public void save(T obj) {
        var point = getPointFromValue(obj);
        var dbInstance = getDb();
        dbInstance.write("etl", "etlDefault", point);
    }
}

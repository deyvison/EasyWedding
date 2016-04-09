package dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import entity.Casamento;
import entity.Convidado;

/**
 * Created by Deyvison on 08/04/2016.
 */
public class ConvidadoDAO extends BaseDaoImpl<Convidado,Integer> {

    public ConvidadoDAO(ConnectionSource connectionSource) throws SQLException {
        super(Convidado.class);
        setConnectionSource(connectionSource);
        initialize();
    }

    @Override
    public QueryBuilder<Convidado, Integer> queryBuilder() {
        return super.queryBuilder();
    }

    @Override
    public List<Convidado> query(PreparedQuery<Convidado> preparedQuery) throws SQLException {
        return super.query(preparedQuery);
    }

}

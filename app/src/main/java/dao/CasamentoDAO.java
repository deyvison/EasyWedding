package dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import entity.Casamento;

/**
 * Created by Deyvison on 08/04/2016.
 */
public class CasamentoDAO extends BaseDaoImpl<Casamento,Integer> {

    public CasamentoDAO(ConnectionSource connectionSource) throws SQLException {
        super(Casamento.class);
        setConnectionSource(connectionSource);
        initialize();
    }

    @Override
    public QueryBuilder<Casamento, Integer> queryBuilder() {
        return super.queryBuilder();
    }

    @Override
    public List<Casamento> query(PreparedQuery<Casamento> preparedQuery) throws SQLException {
        return super.query(preparedQuery);
    }

}

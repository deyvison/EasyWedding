package dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import entity.Convidado;
import entity.Usuario;

/**
 * Created by Deyvison on 08/04/2016.
 */
public class UsuarioDAO extends BaseDaoImpl<Usuario, Integer> {

    public UsuarioDAO(ConnectionSource connectionSource) throws SQLException {
        super(Usuario.class);
        setConnectionSource(connectionSource);
        initialize();
    }

    @Override
    public QueryBuilder<Usuario, Integer> queryBuilder() {
        return super.queryBuilder();
    }

    @Override
    public List<Usuario> query(PreparedQuery<Usuario> preparedQuery) throws SQLException {
        return super.query(preparedQuery);
    }
}

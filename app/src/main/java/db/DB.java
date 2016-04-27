package db;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ufpb.ayty.easywedding.LoginActivity;
import dao.CasamentoDAO;
import dao.ConvidadoDAO;
import dao.UsuarioDAO;
import entity.Casamento;
import entity.Convidado;
import entity.Usuario;

/**
 * Created by Deyvison on 08/04/2016.
 */
public class DB {

    private static DB singleton;

    private CasamentoDAO casamentoDAO;
    private ConvidadoDAO convidadoDAO;
    private UsuarioDAO usuarioDAO;

    private Helper helper;

    public static DB getInstance(Context context) {

        if(singleton == null){
            singleton = new DB(context);
        }
        return singleton;
    }

    private DB(Context context){
        helper = new Helper(context);
        try {
            casamentoDAO = new CasamentoDAO(helper.getConnectionSource());
            convidadoDAO = new ConvidadoDAO(helper.getConnectionSource());
            usuarioDAO = new UsuarioDAO(helper.getConnectionSource());
        } catch (SQLException e) {
            Log.i("ayty",e.getMessage());
        }
    }

    public void insertCasamento(Casamento casamento) throws SQLException {
        casamentoDAO.create(casamento);
    }
    public void updateCasamento(Casamento casamento) throws SQLException {
        casamentoDAO.update(casamento);
    }
    public void deleteCasamento(Casamento casamento) throws SQLException {
        casamentoDAO.delete(casamento);
    }
    public List<Casamento> selectCasamento() throws SQLException {
        List<Casamento> casamentos = new ArrayList<Casamento>();
        casamentos = casamentoDAO.queryForAll();
        return  casamentos;
    }
    public List<Casamento> selectCasamentoById(int id) throws SQLException {
        QueryBuilder<Casamento, Integer> builder = casamentoDAO.queryBuilder();
        builder.where().idEq(id);
        PreparedQuery<Casamento> preparedQuery = builder.prepare();
        List<Casamento> casamentos = casamentoDAO.query(preparedQuery);
        return casamentos;
    }


    public void insertConvidado(Convidado convidado) throws SQLException {

        convidadoDAO.create(convidado);
    }
    public void updateConvidado(Convidado convidado) throws SQLException {
        convidadoDAO.update(convidado);
    }
    public void deleteConvidado(Convidado convidado) throws SQLException {
        convidadoDAO.delete(convidado);
    }
    public List<Convidado> selectConvidado() throws SQLException {
        List<Convidado> convidados = new ArrayList<Convidado>();
        convidados = convidadoDAO.queryForAll();
        return convidados;
    }
    public List<Convidado> selectConvidadoById(int id) throws SQLException {
        QueryBuilder<Convidado, Integer> builder = convidadoDAO.queryBuilder();
        builder.where().idEq(id);
        PreparedQuery<Convidado> preparedQuery = builder.prepare();
        List<Convidado> convidados = convidadoDAO.query(preparedQuery);
        return convidados;
    }


    public void insertUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.create(usuario);
    }
    public void updateUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.update(usuario);
    }
    public void deleteUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.delete(usuario);
    }
    public List<Usuario> selectUsuario() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = usuarioDAO.queryForAll();
        return usuarios;
    }
    public List<Usuario> selectUsuarioById(int id) throws SQLException {
        QueryBuilder<Usuario, Integer> builder = usuarioDAO.queryBuilder();
        builder.where().idEq(id);
        PreparedQuery<Usuario> preparedQuery = builder.prepare();
        List<Usuario> usuarios = usuarioDAO.query(preparedQuery);
        return usuarios;
    }

    public List<Usuario> selectUsuarioByLogin(String login) throws SQLException {
        QueryBuilder<Usuario, Integer> builder = usuarioDAO.queryBuilder();
        builder.where().eq("login", login);
        PreparedQuery<Usuario> preparedQuery = builder.prepare();
        List<Usuario> user = usuarioDAO.query(preparedQuery);
        return user;
    }
}

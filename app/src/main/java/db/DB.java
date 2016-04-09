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
    private CasamentoDAO casamentoDAO;
    private ConvidadoDAO convidadoDAO;
    private UsuarioDAO usuarioDAO;

    private Helper helper;

    public DB(Context context){
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
        casamentoDAO.create(casamento);//INSERT
    }
    public void updateCasamento(Casamento casamento) throws SQLException {
        casamentoDAO.update(casamento);//UPDATE
    }
    public void deleteCasamento(Casamento casamento) throws SQLException {
        casamentoDAO.delete(casamento);//DELETE
    }
    public List<Casamento> selectCasamento() throws SQLException {
        List<Casamento> casamentos = new ArrayList<Casamento>();
        casamentos = casamentoDAO.queryForAll();//SELECT *
        return  casamentos;
    }
    public List<Casamento> selectCasamentoById(int id) throws SQLException {
        QueryBuilder<Casamento, Integer> builder = casamentoDAO.queryBuilder();//INSTANCIAÇÃO DO CONSTRUTOR DA QUERY
        builder.where().idEq(id);//CONSTRUÇÃO DO SQL COM A CLAUSULA WHERE
        PreparedQuery<Casamento> preparedQuery = builder.prepare();//
        List<Casamento> casamentos = casamentoDAO.query(preparedQuery);//SELECT ESPECÍFICO
        return casamentos;
    }


    public void insertConvidado(Convidado convidado) throws SQLException {

        convidadoDAO.create(convidado);//INSERT
    }
    public void updateConvidado(Convidado convidado) throws SQLException {
        convidadoDAO.update(convidado);//UPDATE
    }
    public void deleteConvidado(Convidado convidado) throws SQLException {
        convidadoDAO.delete(convidado);//DELETE
    }
    public List<Convidado> selectConvidado() throws SQLException {
        List<Convidado> convidados = new ArrayList<Convidado>();
        convidados = convidadoDAO.queryForAll();//SELECT *
        return convidados;
    }
    public List<Convidado> selectConvidadoById(int id) throws SQLException {
        QueryBuilder<Convidado, Integer> builder = convidadoDAO.queryBuilder();//INSTANCIAÇÃO DO CONSTRUTOR DA QUERY
        builder.where().idEq(id);//CONSTRUÇÃO DO SQL COM A CLAUSULA WHERE
        PreparedQuery<Convidado> preparedQuery = builder.prepare();//
        List<Convidado> convidados = convidadoDAO.query(preparedQuery);//SELECT ESPECÍFICO
        return convidados;
    }


    public void insertUsuario(Usuario usuario) throws SQLException {
        Log.i("ayty",(usuarioDAO==null)+ "? não pode dar true" );
        usuarioDAO.create(usuario);//INSERT
    }
    public void updateUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.update(usuario);//UPDATE
    }
    public void deleteUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.delete(usuario);//DELETE
    }
    public List<Usuario> selectUsuario() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = usuarioDAO.queryForAll();//SELECT *
        return usuarios;
    }
    public List<Usuario> selectUsuarioById(int id) throws SQLException {
        QueryBuilder<Usuario, Integer> builder = usuarioDAO.queryBuilder();//INSTANCIAÇÃO DO CONSTRUTOR DA QUERY
        builder.where().idEq(id);//CONSTRUÇÃO DO SQL COM A CLAUSULA WHERE
        PreparedQuery<Usuario> preparedQuery = builder.prepare();//
        List<Usuario> usuarios = usuarioDAO.query(preparedQuery);//SELECT ESPECÍFICO
        return usuarios;
    }
}

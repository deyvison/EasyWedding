package application;

import android.app.Application;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import adapter.IconMenu;
import br.com.ufpb.ayty.easywedding.R;
import db.DB;
import entity.Convidado;
import entity.Usuario;

/**
 * Created by Deyvison on 11/04/2016.
 */
public class CasamentoApplication extends Application {

    private List<IconMenu> icones;
    private List<Convidado> convidados;
    private Usuario user;

    private DB db;
    @Override
    public void onCreate() {
        super.onCreate();
        db = DB.getInstance(this);
        icones = new ArrayList<>();
        convidados = new ArrayList<>();

        criarIcones();
        //criarConvidados();

    }

    private void criarIcones() {
        icones.add(new IconMenu(R.drawable.testenoiva,"Noiva"));
        icones.add(new IconMenu(R.drawable.testenoivo,"Noivo"));
        icones.add(new IconMenu(R.drawable.testedata,"Data"));
        icones.add(new IconMenu(R.drawable.testeconvidados, "Convidados"));
        icones.add(new IconMenu(R.drawable.testecidade,"Cidade"));
        icones.add(new IconMenu(R.drawable.testelocal, "Local"));

        icones.add(new IconMenu(R.drawable.testeconvites, "Convites"));
        icones.add(new IconMenu(R.drawable.testalianca, "Alianças"));
        icones.add(new IconMenu(R.drawable.testebuffet, "Buffet"));
        icones.add(new IconMenu(R.drawable.testefotografia, "Fotos"));
        icones.add(new IconMenu(R.drawable.testebolo, "Bolo"));
        icones.add(new IconMenu(R.drawable.testefesta, "Festa"));
        icones.add(new IconMenu(R.drawable.testepresentes, "Presentes"));
        icones.add(new IconMenu(R.drawable.testevestido, "Vestido"));
    }

    public List<IconMenu> getIcons(){
        return icones;
    }

    public void criarConvidados() {
        if(user != null){
            for(Convidado c : user.getCasamento().getConvidados()){
                this.convidados.add(c);
            }
        }
//        convidados.add(new Convidado("Ana Paula"));
//        convidados.add(new Convidado("Rivanildo"));
//        convidados.add(new Convidado("Rháleff"));
//        convidados.add(new Convidado("Luana"));
//        convidados.add(new Convidado("Junior"));
//        convidados.add(new Convidado("Danila"));
//        convidados.add(new Convidado("Emerson"));
//        convidados.add(new Convidado("Romario"));
//        convidados.add(new Convidado("Bebeto"));
//        convidados.add(new Convidado("Ronaldo"));
//        convidados.add(new Convidado("Cafu"));
    }

//    private void criarConvidados(String login) {
//        try {
//            List<Usuario> users = db.selectUsuarioByLogin(login);
//            for(Convidado c : users.get(0).getCasamento().getConvidados()){
//                this.convidados.add(c);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Convidado> getConvidados() {
        return convidados;
    }

    public void setConvidados(List<Convidado> convidados) {
        this.convidados = convidados;
    }

    public void addConvidado(Convidado c){

        convidados.add(c);

        c.setCasamento(user.getCasamento());
        user.getCasamento().addConvidado(c);

        try {
            //db.insertConvidado(c);
            //db.updateCasamento(user.getCasamento());
           //
            db.updateUsuario(user);
           // this.convidados.clear();


            List<Usuario> retorno = db.selectUsuarioByLogin(user.getLogin());
//            for(Convidado c1 : retorno.get(0).getCasamento().getConvidados()){
  //              convidados.add(c1);
    //        }

            Log.i("ayty","tamanho de convidados no casamento aplication: "+this.convidados.size());
            Log.i("ayty","Adicionou? : "+retorno.get(0).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removerConvidado(int idx){ // bug está em remover

        Convidado remover = convidados.get(idx);

        convidados.remove(idx);
        user.getCasamento().getConvidados().remove(remover);

        try {
            db.deleteConvidado(remover);
            db.updateCasamento(user.getCasamento());
            db.updateUsuario(user);
            List<Usuario> retorno = db.selectUsuarioByLogin(user.getLogin());
            Log.i("ayty","tamanho de convidados no casamento aplication: "+this.convidados.size());
            Log.i("ayty", "Removeu?: " + retorno.get(0).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}

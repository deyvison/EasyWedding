package br.com.ufpb.ayty.easywedding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import db.DB;
import entity.Casamento;
import entity.Convidado;
import entity.Usuario;

public class LoginActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = (TextView) findViewById(R.id.textView);

        DB db = new DB(this);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),CadastroActivity.class);
                startActivity(i);

                //Toast.makeText(getApplicationContext(),"teste lol",Toast.LENGTH_SHORT).show();

            }
        });

        try {
            Casamento c = new Casamento("Jose","Leticia");

            Convidado convidado1= new Convidado("Joaquim");
            Convidado convidado2 = new Convidado("Joana");
            Convidado convidado3 = new Convidado("Josefa");

            Usuario user = new Usuario("jose","jalfdlaf",c);

            user.setCasamento(c);
            c.addUsuario(user);


            c.addConvidado(convidado1);
            c.addConvidado(convidado2);
            c.addConvidado(convidado3);

            convidado1.setCasamento(c);
            convidado2.setCasamento(c);
            convidado3.setCasamento(c);

            db.insertConvidado(convidado1);
            db.insertConvidado(convidado2);
            db.insertConvidado(convidado3);

            db.insertCasamento(c);
            db.insertUsuario(user);
        } catch (Exception e) {
            Log.e("ayty",e.getMessage());
        }

        List<Usuario> users = null;
        try {
            users = db.selectUsuarioByLogin("deyvison");
            for(Usuario us : users){
                Log.i("ayty","Resultado da pesquisa por login: "+us.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            List<Usuario> retorno = db.selectUsuario();
            for(Usuario u : retorno){
                Log.i("ayty",u.toString());
            }
        } catch (SQLException e) {
            Log.e("ayty", e.getMessage());
        }

        try {
            List<Convidado> retorno2 = db.selectConvidado();
            Log.i("ayty","tamanho dos convidados: "+retorno2.size());
            for(Convidado con : retorno2){
                Log.i("ayty",con.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

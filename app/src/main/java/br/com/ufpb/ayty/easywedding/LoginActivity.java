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

        DB db = DB.getInstance(this);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),CadastroActivity.class);
                startActivity(i);

                //Toast.makeText(getApplicationContext(),"teste lol",Toast.LENGTH_SHORT).show();

            }
        });
        /**
        try {
            //criação de casamento
            Casamento c = new Casamento("Jose","Leticia");
            db.insertCasamento(c);

            //criação dos convidados
            Convidado convidado1= new Convidado("Joaquim");
            Convidado convidado2 = new Convidado("Joana");
            Convidado convidado3 = new Convidado("Josefa");
            Convidado convidado4 = new Convidado("Rodrigo");
            convidado1.setCasamento(c);
            convidado2.setCasamento(c);
            convidado3.setCasamento(c);
            convidado4.setCasamento(c);
            db.insertConvidado(convidado1);
            db.insertConvidado(convidado2);
            db.insertConvidado(convidado3);
            db.insertConvidado(convidado4);

            //criação do usuário
            Usuario user = new Usuario("jose","jalfdlaf",c);
            user.setCasamento(c);
            db.insertUsuario(user);

            //adiciona usuário a uma coleção em casamento
            c.addUsuario(user);

            //adiciona convidado a uma coleção em casamento
            c.addConvidado(convidado1);
            c.addConvidado(convidado2);
            c.addConvidado(convidado3);
            c.addConvidado(convidado4);


        } catch (Exception e) {
            Log.e("ayty",e.getMessage());
        }

        try {
            List<Usuario> retorno = db.selectUsuario();
            for(Usuario u : retorno){
                Log.i("ayty",u.toString());
            }
        } catch (SQLException e) {
            Log.e("ayty", e.getMessage());
        }*/
    }
}

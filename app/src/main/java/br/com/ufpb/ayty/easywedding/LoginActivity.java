package br.com.ufpb.ayty.easywedding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        try {
            Casamento c = new Casamento("Paula","Deyvison");
            db.insertCasamento(c);

            Usuario user = new Usuario("deyvison","123",c);
            db.insertUsuario(user);
        } catch (Exception e) {
            Log.e("ayty",e.getMessage());
        }

        try {
            List<Usuario> retorno = db.selectUsuario();
            for(Usuario u : retorno){
                Log.i("ayty",u.toString());
                Casamento c = u.getCasamento();
                List<Casamento> byId = db.selectCasamentoById(c.getId());
                for(Casamento cc : byId){
                    Log.i("ayty","Aquiii: "+cc.toString());
                }


            }
        } catch (SQLException e) {
            Log.e("ayty",e.getMessage());
        }

        try {
            List<Casamento> casamentos = db.selectCasamento();

            for(Casamento c : casamentos){
                Log.i("ayty",c.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"teste lol",Toast.LENGTH_SHORT).show();

            }
        });
    }
}

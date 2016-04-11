package br.com.ufpb.ayty.easywedding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import db.DB;
import entity.Casamento;
import entity.Convidado;
import entity.Usuario;

public class LoginActivity extends AppCompatActivity {

    DB db;
    TextView novoCadastro;
    Button botaoEntrar;
    EditText campoLogin;
    EditText campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        novoCadastro = (TextView) findViewById(R.id.novoCadastro);
        botaoEntrar = (Button) findViewById(R.id.botaoEntrar);
        campoLogin = (EditText) findViewById(R.id.campoLoginEntrar);
        campoSenha = (EditText) findViewById(R.id.campoSenhaEntrar);

        db = DB.getInstance(this);

        novoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),CadastroActivity.class);
                startActivity(i);

            }
        });

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCampos(campoLogin, campoSenha)){
                    try {
                        List<Usuario> users = db.selectUsuarioByLogin(campoLogin.getText().toString());
                        if(users.size() == 0){
                            Toast.makeText(v.getContext(),"Usuário não cadastrado!",Toast.LENGTH_SHORT).show();;
                        }else{
                            boolean logado = false;

                            for(Usuario user : users){
                                if(user.getLogin().equals(campoLogin.getText().toString()) && user.getSenha().equals(campoSenha.getText().toString())){
                                    Toast.makeText(v.getContext(),"Usuário logado com sucesso!", Toast.LENGTH_SHORT).show();
                                    // criar a intent para a main activity
                                }else{
                                    Toast.makeText(v.getContext(),"Usuário ou senha inválidos!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
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
        }*/
        try {
            List<Usuario> retorno = db.selectUsuario();
            for(Usuario u : retorno){
                Log.i("ayty",u.toString());
            }
        } catch (SQLException e) {
            Log.e("ayty", e.getMessage());
        }
    }

    private boolean validarCampos(EditText campoLogin, EditText campoSenha) {
        View focus = null;
        boolean valido = true;

        if(TextUtils.isEmpty(campoLogin.getText().toString())){
            campoLogin.setError("Campo vazio!");
            focus = campoLogin;
            valido = false;
            focus.requestFocus();
        }

        if(TextUtils.isEmpty(campoSenha.getText().toString())){
            campoSenha.setError("Campo vazio!");
            focus = campoSenha;
            valido = false;
            focus.requestFocus();
        }
        if(!TextUtils.isEmpty(campoSenha.getText().toString()) && campoSenha.getText().toString().length() < 5){
            campoSenha.setError("A senha deve conter no mínimo 5 dígitos!");
            focus = campoSenha;
            valido = false;
            focus.requestFocus();
        }


        return valido;
    }
}

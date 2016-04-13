package br.com.ufpb.ayty.easywedding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import db.DB;
import entity.Casamento;
import entity.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private Button botaoCadastrar;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        botaoCadastrar = (Button)findViewById(R.id.botaoCadastro);

        db = DB.getInstance(this);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText campoLogin = (EditText) findViewById(R.id.campoLogin);
                EditText campoSenha = (EditText) findViewById(R.id.campoSenha);
                EditText campoNoiva = (EditText) findViewById(R.id.campoNoiva);
                EditText campoNoivo = (EditText) findViewById(R.id.campoNoivo);

                cadastrar(campoLogin, campoSenha, campoNoiva, campoNoivo);
            }
        });
    }

    public void cadastrar(EditText campoLogin, EditText campoSenha, EditText campoNoiva, EditText campoNoivo){

        String valorLogin, valorSenha, valorNoiva, valorNoivo;
        valorLogin = campoLogin.getText().toString();
        valorSenha = campoSenha.getText().toString();
        valorNoiva = campoNoiva.getText().toString();
        valorNoivo = campoNoivo.getText().toString();

        try {

            if (validarCampos(campoLogin,campoSenha,campoNoiva,campoNoivo)) {

                if (validarUsuario(valorLogin)) {

                    Casamento c = new Casamento(valorNoiva, valorNoivo);
                    db.insertCasamento(c);

                    Usuario user = new Usuario(valorLogin, valorSenha, c);
                    user.setCasamento(c);
                    db.insertUsuario(user);
                    Toast.makeText(this, "Usuário Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    // criar a intent para o main activity
                    Intent i = new Intent(this, MainActivity.class);
                    Bundle b = new Bundle();

                    b.putString("login",user.getLogin());
                    i.putExtras(b);
                    startActivity(i);

                    finish();
                } else {
                    Toast.makeText(this,"Usuário já existe!",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validarUsuario(String login) throws SQLException {
        return db.selectUsuarioByLogin(login).size() == 0;
    }

    public boolean validarCampos(EditText login, EditText senha, EditText noiva, EditText noivo){
        View focus = null;

        boolean valido = true;
        if(TextUtils.isEmpty(login.getText().toString())) {
            login.setError("Campo vazio!");
            focus = login;
            valido = false;
            focus.requestFocus();
        }

        if(TextUtils.isEmpty(senha.getText().toString())) {
            senha.setError("Campo vazio!");
            focus = senha;
            valido = false;
            focus.requestFocus();
        }
        if(!TextUtils.isEmpty(senha.getText().toString()) && senha.getText().toString().length() < 5) {
            senha.setError("A senha deve conter no mínimo 5 dígitos!");
            focus = senha;
            valido = false;
            focus.requestFocus();
        }
        if(TextUtils.isEmpty(noiva.getText().toString())) {
            noiva.setError("Campo vazio!");
            focus = noiva;
            valido = false;
            focus.requestFocus();
        }
        if(TextUtils.isEmpty(noivo.getText().toString())) {
            noivo.setError("Campo vazio!");
            focus = noivo;
            valido = false;
            focus.requestFocus();
        }

        return valido;
    }
}

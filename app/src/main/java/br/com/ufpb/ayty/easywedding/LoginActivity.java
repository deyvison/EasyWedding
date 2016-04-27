package br.com.ufpb.ayty.easywedding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import db.DB;
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
                                    Intent i = new Intent(v.getContext(),MainActivity.class);
                                    Bundle b = new Bundle();
                                    b.putString("login",user.getLogin());
                                    i.putExtras(b);
                                    startActivity(i);
                                    finish();
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

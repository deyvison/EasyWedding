package br.com.ufpb.ayty.easywedding;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import adapter.CasamentoAdapter;
import adapter.IconMenu;
import application.CasamentoApplication;
import db.DB;
import entity.Usuario;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CasamentoAdapter adapter;
    private CasamentoApplication application;
    private DB db;
    private Bundle bundle;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bundle = getIntent().getExtras();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = DB.getInstance(this);
        application = (CasamentoApplication) getApplicationContext();
        adapter = new CasamentoAdapter(this);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                IconMenu retorno = adapter.getItem(position);
                adapter.notifyDataSetChanged();
                // showAlertDialog(retorno, view);

                String item = retorno.getNome();
                try {
                    List<Usuario> users = db.selectUsuarioByLogin(bundle.getString("login"));
                    user = users.get(0);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(item.equals("Noiva")){
                    Toast.makeText(view.getContext(), "Entrou na noiva!", Toast.LENGTH_SHORT).show();

                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Nome da noiva")
                            .setMessage(user.getCasamento().getNoiva())
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // faz nada
                                }
                            })
                            .setNeutralButton("Editar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // inflar o input_text.xml
                                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                    builder.setTitle("Noiva");
                                    View viewInflated = LayoutInflater.from(view.getContext()).inflate(R.layout.text_input, (ViewGroup) view, false);
                                    final EditText input = (EditText) viewInflated.findViewById(R.id.input);
                                    input.setHint("Editar nome da noiva");
                                    input.setText(user.getCasamento().getNoiva());
                                    builder.setView(viewInflated);

                                    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            String novoNome = input.getText().toString();
                                            Toast.makeText(view.getContext(), novoNome, Toast.LENGTH_SHORT).show();
                                            // dar update no banco

                                            user.getCasamento().setNoiva(novoNome);
                                            try {
                                                db.updateCasamento(user.getCasamento());
                                                db.updateUsuario(user);
                                                List<Usuario> users = db.selectUsuarioByLogin(bundle.getString("login"));
                                                user = users.get(0);
                                                Log.i("ayty","nome da noiva deve estar mudado: "+user.getCasamento().getNoiva());
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });

                                    builder.show();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();

                }else if(item.equals("Noivo")){
                    Toast.makeText(view.getContext(),"Entrou no noivo!",Toast.LENGTH_SHORT).show();
                }else if(item.equals("Data")){
                    Toast.makeText(view.getContext(),"Entrou na data!",Toast.LENGTH_SHORT).show();
                }else if(item.equals("Convidados")){
                    Toast.makeText(view.getContext(),"Entrou em convidados!",Toast.LENGTH_SHORT).show();
                }else if(item.equals("Cidade")){
                    Toast.makeText(view.getContext(),"Entrou em cidades!",Toast.LENGTH_SHORT).show();
                }else if(item.equals("Local")){
                    Toast.makeText(view.getContext(),"Entrou em local!",Toast.LENGTH_SHORT).show();
                }
                //alert dialog com os dados e opcoes





            }
        });

      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Menu Principal");
            //actionBar.setDisplayHomeAsUpEnabled(true);
        }



       /** fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //if(id == android.R.id.home){
        //    Toast.makeText(MainActivity.this, "Voltei", Toast.LENGTH_SHORT).show();
        //}
        if(id == R.id.action_sair){
            Toast.makeText(MainActivity.this, "Saindo da conta", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    void showAlertDialog(IconMenu selecionado, View view){


    }
}

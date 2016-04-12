package br.com.ufpb.ayty.easywedding;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import adapter.CasamentoAdapter;
import adapter.IconMenu;
import application.CasamentoApplication;
import db.DB;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CasamentoAdapter adapter;
    private CasamentoApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        application = (CasamentoApplication) getApplicationContext();
        adapter = new CasamentoAdapter(this);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                IconMenu retorno = adapter.getItem(position);
                Toast.makeText(view.getContext(),"Item selecionado: "+retorno.getNome(),Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();

                //
                //alert dialog com os dados e opcoes
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Nome da Noiva")
                        .setMessage("Ana Paula Almeida")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // faz nada
                            }
                        })
                        .setNeutralButton("Editar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // inflar a view para editar


                                // inflanndo o text_input.xml
                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setTitle("Title");
                                View viewInflated = LayoutInflater.from(view.getContext()).inflate(R.layout.text_input, (ViewGroup) view, false);
                                final EditText input = (EditText) viewInflated.findViewById(R.id.input);
                                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                                builder.setView(viewInflated);

                                // Set up the buttons
                                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        String m_Text = input.getText().toString();
                                        Toast.makeText(view.getContext(),m_Text,Toast.LENGTH_SHORT).show();
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




            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Menu Principal");
            //actionBar.setDisplayHomeAsUpEnabled(true);
        }



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
}

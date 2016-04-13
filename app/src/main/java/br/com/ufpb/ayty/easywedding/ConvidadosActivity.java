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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import adapter.ConvidadosAdapter;
import application.CasamentoApplication;
import db.DB;
import entity.Convidado;
import entity.Usuario;

public class ConvidadosActivity extends AppCompatActivity {

    private ListView listView;
    private ConvidadosAdapter adapter;
    private CasamentoApplication application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convidados);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Lista de convidados");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        application = (CasamentoApplication) getApplicationContext();
        adapter = new ConvidadosAdapter(this);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                application.removerConvidado(position);
                adapter.notifyDataSetChanged();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {

                    LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
                    View promptView = layoutInflater.inflate(R.layout.text_input, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                    alertDialogBuilder.setView(promptView);

                    final EditText editText = (EditText) promptView.findViewById(R.id.input);
                    // setup a dialog window
                    alertDialogBuilder.setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    String valor = editText.getText().toString();
                                    Toast.makeText(view.getContext(), valor, Toast.LENGTH_SHORT).show();
                                    application.addConvidado(new Convidado(valor));
                                    adapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("Cancelar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create an alert dialog
                    AlertDialog alert = alertDialogBuilder.create();
                    alert.setTitle("Nome do convidado");
                    alert.show();





                }
            });
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){

            Toast.makeText(this,"Voltei", Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

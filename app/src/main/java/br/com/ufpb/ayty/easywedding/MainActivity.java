package br.com.ufpb.ayty.easywedding;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IconMenu retorno = adapter.getItem(position);
                Toast.makeText(view.getContext(),"Item selecionado: "+retorno.getNome(),Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
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
            Toast.makeText(MainActivity.this, "Saindo conta", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

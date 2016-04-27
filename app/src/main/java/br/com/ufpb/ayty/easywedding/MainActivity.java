package br.com.ufpb.ayty.easywedding;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Calendar;
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

    static final int DATE_DIALOG_ID = 0;

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

                String item = retorno.getNome();
                try {
                    List<Usuario> users = db.selectUsuarioByLogin(bundle.getString("login"));
                    user = users.get(0);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(item.equals("Noiva")){

                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Nome da noiva")
                            .setMessage(user.getCasamento().getNoiva())
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setNeutralButton("Editar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
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
                                            Toast.makeText(view.getContext(), "Nome da noiva alterado!", Toast.LENGTH_SHORT).show();

                                            user.getCasamento().setNoiva(novoNome);
                                            try {
                                                db.updateCasamento(user.getCasamento());
                                                db.updateUsuario(user);
                                                List<Usuario> users = db.selectUsuarioByLogin(bundle.getString("login"));
                                                user = users.get(0);
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
                            .setIcon(android.R.drawable.ic_menu_edit).show();

                }else if(item.equals("Noivo")){

                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Nome do noivo")
                            .setMessage(user.getCasamento().getNoivo())
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setNeutralButton("Editar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                    builder.setTitle("Noivo");
                                    View viewInflated = LayoutInflater.from(view.getContext()).inflate(R.layout.text_input, (ViewGroup) view, false);
                                    final EditText input = (EditText) viewInflated.findViewById(R.id.input);
                                    input.setHint("Editar nome do noivo");
                                    input.setText(user.getCasamento().getNoivo());
                                    builder.setView(viewInflated);

                                    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            String novoNome = input.getText().toString();
                                            Toast.makeText(view.getContext(), "Nome do noivo alterado!", Toast.LENGTH_SHORT).show();

                                            user.getCasamento().setNoivo(novoNome);
                                            try {
                                                db.updateCasamento(user.getCasamento());
                                                db.updateUsuario(user);
                                                List<Usuario> users = db.selectUsuarioByLogin(bundle.getString("login"));
                                                user = users.get(0);
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
                            .setIcon(android.R.drawable.ic_menu_edit).show();
                }else if(item.equals("Data")){
                    showDialog(DATE_DIALOG_ID);

                }else if(item.equals("Convidados")){
                    Intent i = new Intent(view.getContext(),ConvidadosActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }else if(item.equals("Cidade")){

                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Cidade do casamento")
                            .setMessage(user.getCasamento().getCidade())
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setNeutralButton("Editar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                    builder.setTitle("Cidade do casamento");
                                    View viewInflated = LayoutInflater.from(view.getContext()).inflate(R.layout.text_input, (ViewGroup) view, false);
                                    final EditText input = (EditText) viewInflated.findViewById(R.id.input);
                                    input.setHint("Editar cidade do casamento");
                                    input.setText(user.getCasamento().getCidade());
                                    builder.setView(viewInflated);

                                    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            String novoNome = input.getText().toString();
                                            Toast.makeText(view.getContext(), "Cidade alterada!", Toast.LENGTH_SHORT).show();

                                            user.getCasamento().setCidade(novoNome);
                                            try {
                                                db.updateCasamento(user.getCasamento());
                                                db.updateUsuario(user);
                                                List<Usuario> users = db.selectUsuarioByLogin(bundle.getString("login"));
                                                user = users.get(0);
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
                            .setIcon(android.R.drawable.ic_menu_edit).show();

                }else if(item.equals("Local")){

                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Local do casamento")
                            .setMessage(user.getCasamento().getLocal())
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setNeutralButton("Editar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                    builder.setTitle("Local do casamento");
                                    View viewInflated = LayoutInflater.from(view.getContext()).inflate(R.layout.text_input, (ViewGroup) view, false);
                                    final EditText input = (EditText) viewInflated.findViewById(R.id.input);
                                    input.setHint("Editar local do casamento");
                                    input.setText(user.getCasamento().getLocal());
                                    builder.setView(viewInflated);

                                    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            String novoNome = input.getText().toString();
                                            Toast.makeText(view.getContext(), "Local alterado!", Toast.LENGTH_SHORT).show();

                                            user.getCasamento().setLocal(novoNome);
                                            try {
                                                db.updateCasamento(user.getCasamento());
                                                db.updateUsuario(user);
                                                List<Usuario> users = db.selectUsuarioByLogin(bundle.getString("login"));
                                                user = users.get(0);
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
                            .setIcon(android.R.drawable.ic_menu_edit).show();
                }
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Menu Principal");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_sair){
            Toast.makeText(MainActivity.this, "Saindo da conta", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar calendario = Calendar.getInstance();
        int ano, mes, dia;

        int diau = user.getCasamento().getDia();
        int mesu = user.getCasamento().getMes();
        int anou = user.getCasamento().getAno();

        if(diau == 0 && mesu == 0 && anou == 0){
            ano = calendario.get(Calendar.YEAR);
            mes = calendario.get(Calendar.MONTH);
            dia = calendario.get(Calendar.DAY_OF_MONTH);
        }else{
            ano = anou;
            mes = mesu-1;
            dia = diau;
        }

        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, ano, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            int dia = dayOfMonth;
            int mes = monthOfYear;
            int ano = year;

            user.getCasamento().setDia(dia);
            user.getCasamento().setMes(mes+1);
            user.getCasamento().setAno(ano);

            try {
                db.updateCasamento(user.getCasamento());
                db.updateUsuario(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String data = String.valueOf(dayOfMonth) + " /"
                    + String.valueOf(monthOfYear+1) + " /" + String.valueOf(year);
            Toast.makeText(MainActivity.this,
                    "DATA = " + data, Toast.LENGTH_SHORT)
                    .show();
        }
    };
}

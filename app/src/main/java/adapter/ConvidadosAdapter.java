package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import application.CasamentoApplication;
import br.com.ufpb.ayty.easywedding.R;
import entity.Convidado;

/**
 * Created by Deyvison on 13/04/2016.
 */
public class ConvidadosAdapter extends BaseAdapter {

    private Context context;
    List<Convidado> convidados;
    private CasamentoApplication application;


    public ConvidadosAdapter(Context context){
        this.context = context;
        this.application = (CasamentoApplication) context.getApplicationContext();
        this.convidados = application.getConvidados();
    }

    public List<Convidado> getConvidados() {
        return convidados;
    }

    public void setConvidados(List<Convidado> convidados) {
        this.convidados = convidados;
    }

    @Override
    public int getCount() {
        return convidados.size();
    }

    @Override
    public Convidado getItem(int position) {

        return convidados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        view = LayoutInflater.from(context).inflate(R.layout.convidado_item_list, parent, false);

        Convidado c = convidados.get(position);

        TextView textView = (TextView) view.findViewById(R.id.convidadoCampo);

        textView.setText(c.getNome());

        return view;
    }
}

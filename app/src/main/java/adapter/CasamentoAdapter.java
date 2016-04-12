package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import application.CasamentoApplication;
import br.com.ufpb.ayty.easywedding.R;

/**
 * Created by Deyvison on 11/04/2016.
 */
public class CasamentoAdapter extends BaseAdapter{

    private Context context;
    List<IconMenu> icones;
    private CasamentoApplication application;

    public CasamentoAdapter(Context context){
        this.context = context;
        this.application = (CasamentoApplication) context.getApplicationContext();
        this.icones = application.getIcons();
    }

    @Override
    public int getCount() {
        return icones.size();
    }

    @Override
    public IconMenu getItem(int position) {
        return icones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        view = LayoutInflater.from(context).inflate(R.layout.layout_item_list, parent, false);

        IconMenu icon = icones.get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);

        imageView.setImageResource(icon.getIdImage());
        textView.setText(icon.getNome());

        return view;
    }
}

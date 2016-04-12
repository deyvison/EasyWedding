package application;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import adapter.IconMenu;
import br.com.ufpb.ayty.easywedding.R;

/**
 * Created by Deyvison on 11/04/2016.
 */
public class CasamentoApplication extends Application {

    private List<IconMenu> icones;

    @Override
    public void onCreate() {
        super.onCreate();
        icones = new ArrayList<>();
        criarIcones();
    }

    private void criarIcones() {
        icones.add(new IconMenu(R.drawable.testenoiva," Noiva"));
        icones.add(new IconMenu(R.drawable.testenoivo," Noivo"));
        icones.add(new IconMenu(R.drawable.testedata," Data"));
        icones.add(new IconMenu(R.drawable.testeconvidados, " Convidados"));
        icones.add(new IconMenu(R.drawable.testecidade," Cidade"));
        icones.add(new IconMenu(R.drawable.testelocal, " Local"));

        icones.add(new IconMenu(R.drawable.testeconvites, " Convites"));
        icones.add(new IconMenu(R.drawable.testalianca, " Alianças"));
        icones.add(new IconMenu(R.drawable.testebuffet, " Buffet"));
        icones.add(new IconMenu(R.drawable.testefotografia, " Fotos"));
        icones.add(new IconMenu(R.drawable.testebolo, " Bolo"));
        icones.add(new IconMenu(R.drawable.testefesta, " Festa"));
        icones.add(new IconMenu(R.drawable.testepresentes, " Presentes"));
        icones.add(new IconMenu(R.drawable.testevestido, " Vestido"));
    }

    public List<IconMenu> getIcons(){
        return icones;
    }
}

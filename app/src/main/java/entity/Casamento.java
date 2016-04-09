package entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.List;

/**
 * Created by Deyvison on 08/04/2016.
 */
@DatabaseTable(tableName = "casamento")
public class Casamento {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String noiva;

    @DatabaseField
    private String noivo;

    @DatabaseField
    private int dia;

    @DatabaseField
    private int mes;

    @DatabaseField
    private int ano;

    @DatabaseField
    private String cidade;

    @DatabaseField
    private String local;

    @ForeignCollectionField
    private Collection<Usuario> usuarios;

    //@ForeignCollectionField
    //private Collection<Convidado> convidados;



    public Casamento(){}

    public Casamento(String noiva, String noivo) {
        this.noiva = noiva;
        this.noivo = noivo;
    }

    public String getNoiva() {
        return noiva;
    }

    public void setNoiva(String noiva) {
        this.noiva = noiva;
    }

    public String getNoivo() {
        return noivo;
    }

    public void setNoivo(String noivo) {
        this.noivo = noivo;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

  //  public Collection<Convidado> getConvidados() {
      //  return convidados;
    //}

    //public void setConvidados(List<Convidado> convidados) {
      //  this.convidados = convidados;
    //}

    @Override
    public String toString() {
        return "Casamento{" +
                "noiva='" + noiva + '\'' +
                ", noivo='" + noivo + '\'' +
                ", dia=" + dia +
                ", mes=" + mes +
                ", ano=" + ano +
                ", cidade='" + cidade + '\'' +
                ", local='" + local + '\'' +
                //", convidados=" + convidados +
                '}';
    }
}

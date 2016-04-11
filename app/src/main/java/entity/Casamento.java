package entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
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
    private Collection<Usuario> usuario;

    @ForeignCollectionField
    private Collection<Convidado> convidados;

    public Casamento(){}

    public Casamento(String noiva, String noivo) {
        this.noiva = noiva;
        this.noivo = noivo;
        this.convidados = new ArrayList<Convidado>();
        this.usuario = new ArrayList<Usuario>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void addConvidado(Convidado c){
        this.convidados.add(c);
    }

    public Collection<Convidado> getConvidados() {
        return convidados;
    }

    public void setConvidados(Collection<Convidado> convidados) {
        this.convidados = convidados;
    }

    @Override
    public String toString() {
        String con = "";

        for(Convidado c : this.convidados){
            con += c.toString() +", ";
        }
        return "Casamento{" +
                "id=" + id +
                ", noiva='" + noiva + '\'' +
                ", noivo='" + noivo + '\'' +
                ", dia=" + dia +
                ", mes=" + mes +
                ", ano=" + ano +
                ", cidade='" + cidade + '\'' +
                ", local='" + local + '\'' +
                ", convidados=" + con +
                '}';
    }

    public Collection<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(Collection<Usuario> usuario) {
        this.usuario = usuario;
    }

    public void addUsuario(Usuario user) {
        this.usuario.add(user);
    }
}

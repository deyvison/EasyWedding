package entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Deyvison on 08/04/2016.
 */
@DatabaseTable(tableName = "convidado")
public class Convidado {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nome;


    @DatabaseField(foreign = true, foreignAutoRefresh=true)
    private Casamento casamento;

    public Convidado(){}

    public Convidado(String nome){
        this.nome = nome;
        casamento = new Casamento();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Convidado{" +
                "nome='" + nome + '\'' +
                '}';
    }

    public Casamento getCasamento() {
        return casamento;
    }

    public void setCasamento(Casamento casamento) {
        this.casamento = casamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   // public void addCasamento(Casamento c){
   //     this.casamento.add(c);
   // }

}

package entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Deyvison on 08/04/2016.
 */
@DatabaseTable(tableName = "convidado")
public class Convidado {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nome;

    public Convidado(){}

    public Convidado(String nome){
        this.nome = nome;
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
}

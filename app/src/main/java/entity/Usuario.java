package entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Deyvison on 08/04/2016.
 */
@DatabaseTable(tableName = "usuario")
public class Usuario {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String login;

    @DatabaseField
    private String senha;

    @DatabaseField(foreign = true)
    private Casamento casamento;

    public Usuario(){}

    public Usuario(String login, String senha, Casamento casamento) {
        this.login = login;
        this.senha = senha;
        this.casamento = casamento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Casamento getCasamento() {
        return casamento;
    }

    public void setCasamento(Casamento casamento) {
        this.casamento = casamento;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", casamento=" + casamento +
                '}';
    }
}

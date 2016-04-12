package adapter;

/**
 * Created by Deyvison on 11/04/2016.
 */
public class IconMenu {

    private int idImage;
    private String nome;

    public IconMenu(int idImage, String nome){
        this.idImage = idImage;
        this.nome = nome;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

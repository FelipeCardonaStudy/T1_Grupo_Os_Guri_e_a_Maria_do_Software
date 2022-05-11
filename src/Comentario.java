import java.util.Date;

public class Comentario{

    private String comentario;
    private Usuario usuario;
    private Date data;

    public Comentario(String comentario, Usuario usuario, Date data){
        this.comentario = comentario;
        this.usuario = usuario;
        this.data = data;
    }

    public boolean comentarioPermitido() {
        if (comentario.length() <= 100) {
           return true;
        }
        return false;
    }
}

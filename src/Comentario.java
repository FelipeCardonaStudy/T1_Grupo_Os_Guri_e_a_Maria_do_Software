import java.util.Date;

public class Comentario{

    private String comentario;
    private Usuario usuario;
    private Date data;
    private int count = 0; //variavel para controlar a quantidade de comentarios existentes para exibir no painel (opcao 7 do menu)

    public Comentario(String comentario, Usuario usuario, Date data){
        this.comentario = comentario;
        this.usuario = usuario;
        this.data = data;
        count++;
        usuario.incrementaQntdComentarios();
    }

    public Comentario(){

    }

    public boolean comentarioPermitido() {
        if (comentario.length() <= 100) {
           return true;
        }
        return false;
    }

    public int getQntComentarios(){
        return count;
    }
}

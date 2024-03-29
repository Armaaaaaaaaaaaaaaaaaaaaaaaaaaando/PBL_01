package prog.pbl.LibraryException.usersexcepitions;
import prog.pbl.model.usuarios.Leitor;
public class LeitorException extends Exception{

    private Leitor leitor;

    /**
     * construtor das exceções para leitor
     * @param excecao
     * @param leitor
     */
    public LeitorException(String excecao, Leitor leitor) {
        super(excecao);
        this.leitor = leitor;
    }
}

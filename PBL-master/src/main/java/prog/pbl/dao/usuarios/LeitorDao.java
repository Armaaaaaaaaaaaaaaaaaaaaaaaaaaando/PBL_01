package prog.pbl.dao.usuarios;

import prog.pbl.LibraryException.usersexcepitions.LeitorException;
import prog.pbl.dao.Dao;
import prog.pbl.model.usuarios.Leitor;

/**<p>Interface CRUD do leitor</p>*/
public interface LeitorDao extends Dao<Leitor, LeitorException> {

    /**
     * <p>Metodo responsavel por <b>retornar um usuario pelo login</b></p>
     * @param id <b>String</b>
     * @param senha <b>String</b>
     * @return <b>Leitor</b> - <i>O leitor a qual aquela senha e aquele id pertencem</i>
     * @throws LeitorException <i>caso não seja encontrado nenhum leitor</i>
     */
    public Leitor findLogin(String id, String senha) throws LeitorException;

}
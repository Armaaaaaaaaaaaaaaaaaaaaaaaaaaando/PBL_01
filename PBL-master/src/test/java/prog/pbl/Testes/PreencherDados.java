package prog.pbl.Testes;

import prog.pbl.dao.FileManeger;
import prog.pbl.dao.MasterDao;
import prog.pbl.model.emprestimo.Emprestimo;
import prog.pbl.model.emprestimo.FilaDeReserva;
import prog.pbl.model.estoque.Livro;
import prog.pbl.model.usuarios.Administrador;
import prog.pbl.model.usuarios.Bibliotecario;
import prog.pbl.model.usuarios.Leitor;

public class PreencherDados {

    static public void main(String[] args) {
        try {
            FileManeger.generateCache();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {

            MasterDao.getLeitorDao().clearAll();
            MasterDao.getAdministradorDao().clearAll();
            MasterDao.getBibliotecarioDao().clearAll();
            MasterDao.getLivroDao().clearAll();
            MasterDao.getEmprestimoDao().clearAll();
            MasterDao.getFiladeReservaDao().clearAll();
        } catch (Exception ignored) {

        }
        try {
            Leitor leitor = new Leitor("Maike", "111", "88052169500", "uefs", "(75) 9 92515720");
            Livro livro = new Livro("12","pedrin","terror","Uefs","fonte seca", 2020, "pedrin: o matador",5);
            FilaDeReserva filaDeReserva = new FilaDeReserva(livro.getIsbn());
            filaDeReserva.addOnReservas(leitor);
            MasterDao.getAdministradorDao().save(new Administrador("111", "Paulo", "Administrador", "88052169500"));
            MasterDao.getLeitorDao().save(leitor);
            MasterDao.getBibliotecarioDao().save(new Bibliotecario("Maike", "111", "88052169500", "Bibliotecario"));
            MasterDao.getLivroDao().save(livro);
            MasterDao.getEmprestimoDao().save(new Emprestimo(leitor, livro));
            MasterDao.getFiladeReservaDao().save(filaDeReserva);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package prog.pbl;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import prog.pbl.LibraryException.emprestimoException.EmprestimoException;
import prog.pbl.model.emprestimo.Emprestimo;
import prog.pbl.model.usuarios.Leitor;

import static prog.pbl.AnaliseLivro.emprestimoDao;

public class InforEmprestimos {
    private Stage stage;
    private Leitor leitor;
    @FXML
    private TableView<?> tabela;
    private ObservableList<Emprestimo> emprestimos;


    @FXML
    void initialize() {
        this.emprestimos = FXCollections.observableArrayList();


        System.out.println(emprestimoDao.findAll().size());


        if(emprestimoDao.findAll().size() > 0){
            this.emprestimos.addAll(emprestimoDao.findAll());
        }
        String nome = emprestimos.get(0).getLivro().getNome();

        System.out.println(nome);

        TableColumn livro = new TableColumn("Livro");
        TableColumn dataEmp = new TableColumn("Data emprestimo");
        TableColumn prazoFinal = new TableColumn("Prazo de entrega");
        TableColumn id = new TableColumn("ID");
        TableColumn renovacao = new TableColumn("Renovacoes");

        livro.setCellValueFactory(new PropertyValueFactory<Emprestimo,String>(nome));



    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setLeitor(Leitor leitor) throws EmprestimoException {
        this.leitor = leitor;
        System.out.println(leitor.getNome());
        System.out.println(emprestimoDao.findAll());
        System.out.println(emprestimoDao.findEmprestimosAtivosPorUsuario(leitor.getId()));

        //System.out.println(emprestimoDao.findEmprestimosAtivosPorUsuario(leitor.getId()));
    }
}

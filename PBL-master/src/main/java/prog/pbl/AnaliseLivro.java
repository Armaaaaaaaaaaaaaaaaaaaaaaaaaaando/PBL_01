package prog.pbl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import prog.pbl.LibraryException.emprestimoException.EmprestimoException;
import prog.pbl.dao.emprestimo.ImDiskEmprestimoDao;
import prog.pbl.model.emprestimo.Emprestimo;
import prog.pbl.model.estoque.Livro;
import prog.pbl.model.usuarios.Leitor;

public class AnaliseLivro {
    @FXML
    private Label quantidadeemprestimo;

    @FXML
    private Label ano;

    @FXML
    private Label autor;

    @FXML
    private Label categoria;

    @FXML
    private Label editora;

    @FXML
    private Label isbn;

    @FXML
    private Label nome;

    @FXML
    private Label quantidade;

    @FXML
    private Label quantidadepesquisa;

    @FXML
    private Button voltarButton;

    @FXML
    private Button pegarEmprestado;

    private Stage stage;
    private Leitor leitor;
    private Livro livro;
    @FXML
    private Label emprestado;
    public static ImDiskEmprestimoDao emprestimoDao = new ImDiskEmprestimoDao();

    @FXML
    void voltarButtonAction(ActionEvent event) {
        this.stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLivro(Livro livro,Leitor leitor) {
        this.ano.setText(Integer.toString(livro.getAnoDePublicacao()));
        this.autor.setText(livro.getAutor());
        this.isbn.setText(livro.getIsbn());
        this.nome.setText(livro.getNome());
        this.categoria.setText(livro.getCategoria());
        this.quantidade.setText(Integer.toString(livro.getQuantidade()));
        this.quantidadepesquisa.setText(Integer.toString(livro.getQntDeBuscas()));
        this.quantidadeemprestimo.setText(Integer.toString(livro.getQntDeEmprestimos()));
        this.editora.setText(this.editora.getText());
        this.leitor = leitor;
        this.livro = livro;
    }
    @FXML
    void pegarEmprestadoButton(ActionEvent event) throws EmprestimoException {
        try{
            Emprestimo emprestimo = new Emprestimo(leitor,livro);
            emprestimoDao.save(emprestimo);
            System.out.println(emprestimoDao.findAll());
            this.emprestado.setText("Emprestimo concedido!");

        }catch (Exception e){
            this.emprestado.setText("Erro ao emprestar!");
        }

    }

}

package prog.pbl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import prog.pbl.LibraryException.emprestimoException.EmprestimoException;
import prog.pbl.LibraryException.emprestimoException.ReservarException;
import prog.pbl.dao.emprestimo.ImDiskEmprestimoDao;
import prog.pbl.dao.emprestimo.ImDiskFilaDeReservaDao;
import prog.pbl.model.emprestimo.Emprestimo;
import prog.pbl.model.emprestimo.FilaDeReserva;
import prog.pbl.model.estoque.Livro;
import prog.pbl.model.usuarios.Leitor;

import java.util.HashMap;
import java.util.List;

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
    @FXML
    private Label ReservaMessage;

    @FXML
    private Label taemprestado;
    @FXML
    private Button reservaButton;


    private Stage stage;
    private Leitor leitor;
    private Livro livro;
    @FXML
    private Label emprestado;
    public static ImDiskEmprestimoDao emprestimoDao = new ImDiskEmprestimoDao();
    public static ImDiskFilaDeReservaDao reservaDao = new ImDiskFilaDeReservaDao();

    private List<FilaDeReserva> Todas_as_filas;
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
        this.editora.setText(livro.getEditora());
        this.leitor = leitor;
        this.livro = livro;
        if(livro.getQntDeEmprestimos()>0){
            this.taemprestado.setText("Sim");
        }
        else{
            this.taemprestado.setText("Nao");
        }

        if(leitor.getEmprestimos() == null){
            leitor.setNumEmprestimos(2);
        }

    }
    @FXML
    void pegarEmprestadoButton(ActionEvent event) throws EmprestimoException {
        try{
            Emprestimo emprestimo = new Emprestimo(leitor,livro);
            emprestimoDao.save(emprestimo);
            this.emprestado.setText("Emprestimo concedido!");
            System.out.println("Numero de emprestimos: "+leitor.getNumEmprestimos());

        }catch (Exception e){
            this.emprestado.setText("Impossivel emprestar!");
        }

    }

    @FXML
    void reservaButtonAction(ActionEvent event) throws ReservarException {
        System.out.println(reservaDao.findAll());
        try{
            try{
                FilaDeReserva fila;
                fila = reservaDao.findById(livro.getIsbn());
                if(fila.getReservas().isEmpty()){
                    fila.addOnReservas(leitor);
                    reservaDao.Update(fila,reservaDao.findById(livro.getIsbn()));

                }
            }catch (Exception j){
                FilaDeReserva reserva = new FilaDeReserva(livro.getIsbn());
                reserva.addOnReservas(leitor);
                reservaDao.save(reserva);
            }
            this.ReservaMessage.setText("Reservado com sucesso!");
            this.setLivro(livro,leitor);
        }
        catch (Exception e){
            this.ReservaMessage.setText("Impossivel reservar!");
        }


    }

}

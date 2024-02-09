package prog.pbl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import prog.pbl.model.emprestimo.Emprestimo;

public class AnaliseEmprestimo {
    private Stage stage;
    private Emprestimo emprestimo;

    @FXML
    private Label IDdoemprestimo;

    @FXML
    private Label Renovacoes;

    @FXML
    private Label dataDevolucao;

    @FXML
    private Label dataEmprestimo;

    @FXML
    private Label foiDevolvido;

    @FXML
    private Label leitorenvolvido;

    @FXML
    private Label livroEmprestado;

    @FXML
    private Label prazoFinal;

    @FXML
    private Button voltarButton;

    @FXML
    void voltarButtonAction(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void initialize() {

    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;

        this.leitorenvolvido.setText(emprestimo.getLeitor().getNome());
        this.livroEmprestado.setText(emprestimo.getLivro().getNome());
        this.dataEmprestimo.setText(emprestimo.getDataEmprestimo().toString());
        this.dataDevolucao.setText(emprestimo.getDataDevolucao().toString());
        this.IDdoemprestimo.setText(emprestimo.getId());
        if(emprestimo.isDevolvido() == true){
            this.foiDevolvido.setText("Sim");
        }
        else{
            this.foiDevolvido.setText("Nao");
        }
        this.prazoFinal.setText(emprestimo.getPrazoFinal().toString());
        this.Renovacoes.setText(emprestimo.getRenovacoes().toString());

    }
}

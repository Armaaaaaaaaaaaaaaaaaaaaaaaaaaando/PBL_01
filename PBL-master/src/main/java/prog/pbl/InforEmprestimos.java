package prog.pbl;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prog.pbl.LibraryException.emprestimoException.EmprestimoException;
import prog.pbl.model.emprestimo.Emprestimo;
import prog.pbl.model.estoque.Livro;
import prog.pbl.model.usuarios.Leitor;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static prog.pbl.AnaliseLivro.emprestimoDao;

public class InforEmprestimos {
    private Stage stage;
    private Leitor leitor;
    @FXML
    private TableView<Livro> tabela;

    private ObservableList<Livro> emprestimos;

    private List<Emprestimo> lista;

    @FXML
    private Button analiseButton;

    @FXML
    private Button renovarButton;

    @FXML
    private Button voltarButton;

    @FXML
    void initialize() throws EmprestimoException {
        emprestimos = FXCollections.observableArrayList();
        lista = emprestimoDao.findAll();
        for(int i = 0; i < lista.size();i++){
            emprestimos.add(lista.get(i).getLivro());
        }



        TableColumn nome = new TableColumn("Nome");

        nome.setCellValueFactory(new PropertyValueFactory<Livro,String>("nome"));

        this.tabela.getColumns().addAll(nome);
        this.tabela.setItems(emprestimos);
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

    @FXML
    void analiseButtonAction(ActionEvent event) {
        int i = this.tabela.getSelectionModel().getSelectedIndex();
        if(i >= 0){
            for(int j = 0;j <= lista.size();j++){
                if(lista.get(j).getLivro().getNome().equals(emprestimos.get(i).getNome())){
                    this.OpenAnalise(lista.get(j));
                }
            }
        }
    }

    @FXML
    void renovarButtonAction(ActionEvent event) {

    }

    @FXML
    void voltarButtonAcrion(ActionEvent event) {
        this.stage.close();
    }

    private void OpenAnalise(Emprestimo emprestimo){
        try{
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource("AnaliseEmprestimo.fxml");
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setTitle("Analise emprestimo");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL); // Define a modalidade da janela

            AnaliseEmprestimo controller = loader.getController();
            controller.setStage(stage);
            controller.setEmprestimo(emprestimo);




            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

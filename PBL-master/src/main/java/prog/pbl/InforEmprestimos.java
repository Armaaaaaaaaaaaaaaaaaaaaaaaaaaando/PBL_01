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
import prog.pbl.LibraryException.usersexcepitions.LeitorException;
import prog.pbl.model.emprestimo.Emprestimo;
import prog.pbl.model.estoque.Livro;
import prog.pbl.model.usuarios.Leitor;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static prog.pbl.AnaliseLivro.emprestimoDao;
import static prog.pbl.AnaliseUsuario.leitorDao;

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
    private Button devolverButton;

    @FXML
    void initialize1() throws EmprestimoException {
        emprestimos = FXCollections.observableArrayList();
        lista = emprestimoDao.findEmprestimosAtivosPorUsuario(leitor.getId());
        for(int i = 0; i < lista.size();i++){
            emprestimos.add(lista.get(i).getLivro());
        }



        TableColumn nome = new TableColumn("Titulo");

        nome.setCellValueFactory(new PropertyValueFactory<Livro,String>("nome"));

        this.tabela.getColumns().addAll(nome);
        this.tabela.setItems(emprestimos);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setLeitor(Leitor leitor) throws EmprestimoException {
        this.leitor = leitor;
        this.initialize1();
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
    void renovarButtonAction(ActionEvent event) throws EmprestimoException {
        int i = this.tabela.getSelectionModel().getSelectedIndex();
        if(i >= 0){
            for(int j = 0;j <= lista.size();j++){
                if(lista.get(j).getLivro().getNome().equals(emprestimos.get(i).getNome())){

                    lista.get(j).renovacaoEmprestimo(lista.get(j).getLivro().getIsbn(),lista.get(j).getLeitor().getId());
                    System.out.println(lista.get(j).getPrazoFinal());

                }
            }
        }
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

    @FXML
    void devolverButtonAction(ActionEvent event) throws EmprestimoException, LeitorException {
        int i = this.tabela.getSelectionModel().getSelectedIndex();
        if(i >= 0){
            for(int j = 0; j < lista.size();j++){
                if(lista.get(j).getLivro().equals(emprestimos.get(i))){
                    lista.get(j).setDevolvido(true);
                    emprestimos.remove(j);
                    emprestimoDao.delete(lista.get(i));
                }
            }


        }
    }
}

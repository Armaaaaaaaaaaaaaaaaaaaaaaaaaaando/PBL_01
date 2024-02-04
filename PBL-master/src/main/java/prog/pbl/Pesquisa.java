package prog.pbl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import prog.pbl.LibraryException.estoqueExceptions.LivroException;
import prog.pbl.model.estoque.Livro;
import prog.pbl.model.usuarios.Leitor;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import static prog.pbl.RegistroLivro.livroDao;

public class Pesquisa {

    @FXML
    private Button AutorButton;

    @FXML
    private Button CategoriaButton;

    @FXML
    private Button IsbnButton;

    @FXML
    private Button TituloButton;

    @FXML
    private Button VoltarButton;

    @FXML
    private Button analiseButton;

    @FXML
    private Label encontrado;

    @FXML
    private Label naoencontrado;

    @FXML
    private TextField nomeText;
    private Stage stage;
    private List<Livro> lista;
    private List<Livro> Analisador = new LinkedList<>();
    @FXML
    private TableView<Livro> tabela;

    private ObservableList<Livro> tabelaLivros;
    private Leitor leitor;

    @FXML
    void initialize1() {
        lista = new LinkedList<>();
    }

    @FXML
    void initialize() {
        lista = new LinkedList<>();
        this.tabelaLivros = FXCollections.observableArrayList();



        TableColumn nomeC1 = new TableColumn("Autor");
        TableColumn nomeC2 = new TableColumn("Categoria");
        TableColumn nomeC3 = new TableColumn("Titulo");
        TableColumn nomeC4 = new TableColumn("ISBN");




        nomeC1.setCellValueFactory(new PropertyValueFactory<Livro,String>("autor"));
        nomeC2.setCellValueFactory(new PropertyValueFactory<Livro,String>("categoria"));
        nomeC3.setCellValueFactory(new PropertyValueFactory<Livro,String>("nome"));
        nomeC4.setCellValueFactory(new PropertyValueFactory<Livro,String>("isbn"));




        this.tabela.getColumns().addAll(nomeC1,nomeC2,nomeC3,nomeC4);
        this.tabela.setItems(tabelaLivros);
    }

    @FXML
    void AutorButtonAction(ActionEvent event) throws LivroException {
        this.tabelaLivros.clear();
        try{
            lista.addAll(livroDao.findByAutor(this.nomeText.getText()));
            this.tabelaLivros.addAll(livroDao.findByAutor(this.nomeText.getText()));

            this.encontrado();
        }
        catch (Exception e){
            this.naoencontrado();
        }


    }

    @FXML
    void CategoriaButtonAction(ActionEvent event) {
        this.tabelaLivros.clear();
        try{
            lista.addAll(livroDao.findByCategoria(this.nomeText.getText()));
            this.tabelaLivros.addAll(livroDao.findByCategoria(this.nomeText.getText()));
            this.encontrado();
        }
        catch (Exception e){
            this.naoencontrado();
        }
    }

    @FXML
    void IsbnButtonAction(ActionEvent event) {
        this.tabelaLivros.clear();
        try{

            this.encontrado();
        }catch (Exception e){
            this.naoencontrado();
        }
    }

    @FXML
    void TituloButtonAction(ActionEvent event) {
        this.tabelaLivros.clear();
        try{
            lista.addAll(livroDao.findByNome(this.nomeText.getText()));
            this.tabelaLivros.addAll(livroDao.findByNome(this.nomeText.getText()));
            this.encontrado();
        }catch (Exception e){
            this.naoencontrado();
        }
    }

    @FXML
    void VoltarButtonAction(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void analiseButtonAction(ActionEvent event) {
        int i = this.tabela.getSelectionModel().getSelectedIndex();
        if(i >= 0){
            try{
                OpenAnaliseLivro(this.Analisador.get(i),leitor);
            }catch (Exception e){
                this.naoencontrado();
            }
        }
        else{
            this.naoencontrado();
        }

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void encontrado(){
        this.naoencontrado.setText("");
        this.encontrado.setText("Livro encontrado!");
        this.Analisador.addAll(lista);
        this.initialize1();
    }

    public void naoencontrado(){
        this.encontrado.setText("");
        this.naoencontrado.setText("Livro nao encontrado!");
        this.Analisador.clear();
        this.initialize1();
    }

    public void OpenAnaliseLivro(Livro livro,Leitor leitor){
        try{
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource("AnaliseLivro.fxml");
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setTitle("Analise livro");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL); // Define a modalidade da janela

            AnaliseLivro controller = loader.getController();
            controller.setStage(stage);
            controller.setLivro(livro,leitor);


            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }
}

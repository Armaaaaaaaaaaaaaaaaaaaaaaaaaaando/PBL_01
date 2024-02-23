package prog.pbl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import prog.pbl.LibraryException.estoqueExceptions.LivroException;
import prog.pbl.dao.estoque.ImDiskLivroDao;
import prog.pbl.model.estoque.Livro;

import java.util.LinkedList;
import java.util.List;

public class RegistroLivro {
/*
    @FXML
    private TextField anoText;

    @FXML
    private TextField autorText;

    @FXML
    private TextField categoriaText;

    @FXML
    private Button deletarButton;

    @FXML
    private Button editarButton;

    @FXML
    private TextField editoraText;

    @FXML
    private TextField enderecoText;

    @FXML
    private Label error;

    @FXML
    private TextField isbnText;

    @FXML
    private TextField nomeText;

    @FXML
    private Button salvarButton;

    @FXML
    private TableView<Livro> tebela;

    @FXML
    private Button voltarButton;
    private Stage stage;
    private ObservableList<Livro> livros;
    public static ImDiskLivroDao livroDao = new ImDiskLivroDao();
    private List<Livro> lista = new LinkedList<>();


    @FXML
    void initialize() {
        this.livros = FXCollections.observableArrayList();

        if (livroDao.findAll().size()>0){
            this.lista.addAll(livroDao.findAll());
            for (int i = 0;i<lista.size();i++){
                this.livros.addAll(this.lista.get(i));
            }
        }

        TableColumn nomeC1 = new TableColumn("Nome");
        TableColumn autorC2 = new TableColumn("Autor");
        TableColumn CategoriaC3 = new TableColumn("Categoria");

        nomeC1.setCellValueFactory(new PropertyValueFactory<Livro,String>("nome"));
        autorC2.setCellValueFactory(new PropertyValueFactory<Livro,String>("autor"));
        CategoriaC3.setCellValueFactory(new PropertyValueFactory<Livro,String>("categoria"));

        this.tebela.getColumns().addAll(nomeC1,autorC2,CategoriaC3);
        this.tebela.setItems(livros);




    }

    @FXML
    void deletarButtonAction(ActionEvent event) throws LivroException {
        int i = this.tebela.getSelectionModel().getSelectedIndex();
        if(i>=0){
            this.livroDao.delete(livros.get(i));
            this.livros.remove(i);
        }
    }

    @FXML
    void editarButtonAction(ActionEvent event) {
        int i = this.tebela.getSelectionModel().getSelectedIndex();
        if(i>=0){
            try{
                Livro li = new Livro(this.isbnText.getText(),this.autorText.getText(),this.categoriaText.getText(),this.enderecoText.getText(),
                        //this.editoraText.getText(),Integer.parseInt(this.anoText.getText()),this.nomeText.getText(), );
                //his.livroDao.Update(li,livros.get(i));
                //this.livros.set(i,li);
                this.cleanAll();
            }
            catch (Exception e){
                this.error.setText("Preencha os campos adequadamente!");
            }
        }

    }

    @FXML
    void salvarButtonAction(ActionEvent event) {
        try{
            Livro l = new Livro(this.isbnText.getText(),this.autorText.getText(),this.categoriaText.getText(),this.enderecoText.getText(),
                    this.editoraText.getText(),Integer.parseInt(this.anoText.getText()),this.nomeText.getText(), );
            livroDao.save(l);
            this.livros.addAll(l);
            this.cleanAll();
        }catch (Exception e ){
            this.error.setText("Preencha os campos adequadamente!");
        }

    }

    @FXML
    void voltarButtonAction(ActionEvent event) {
        this.stage.close();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void cleanAll(){
        this.error.setText("");
        this.anoText.clear();
        this.autorText.clear();
        this.categoriaText.clear();
        this.editoraText.clear();
        this.enderecoText.clear();
        this.isbnText.clear();
        this.nomeText.clear();
    }*/
}

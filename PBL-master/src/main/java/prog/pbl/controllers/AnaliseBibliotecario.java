package prog.pbl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import prog.pbl.LibraryException.usersexcepitions.BibliotecarioException;
import prog.pbl.dao.usuarios.ImDiskBibliotecarioDao;
import prog.pbl.model.usuarios.Bibliotecario;

import java.util.LinkedList;
import java.util.List;

public class AnaliseBibliotecario {

    @FXML
    private TextField CargoText;

    @FXML
    private Button EditarButton;

    @FXML
    private Button ExcluirButton;

    @FXML
    private TextField LoginText;

    @FXML
    private TextField NomeText;

    @FXML
    private Button SairBUtton;

    @FXML
    private Button SalvarButton;

    @FXML
    private Label error;

    @FXML
    private TextField senhaText;

    @FXML
    private TableView<Bibliotecario> tabela;
    private Stage stage;

    private ObservableList<Bibliotecario>bibliotecarios;

    private ImDiskBibliotecarioDao bibliotecarioDao = new ImDiskBibliotecarioDao();

    private List<Bibliotecario> lista = new LinkedList<>();
    @FXML
    void initialize() {
        this.bibliotecarios = FXCollections.observableArrayList();


        System.out.println(bibliotecarioDao.findAll().size());
        if(bibliotecarioDao.findAll().size() > 0){
            this.lista.addAll(bibliotecarioDao.findAll());
            for(int i = 0; i<lista.size();i++){
                this.bibliotecarios.addAll(this.lista.get(i));
            }

        }


        TableColumn nomeC1 = new TableColumn("Nome");
        TableColumn loginC2 = new TableColumn("Login");
        TableColumn cargoC3 = new TableColumn("Cargo");

        nomeC1.setCellValueFactory(new PropertyValueFactory<Bibliotecario,String>("nome"));
        loginC2.setCellValueFactory(new PropertyValueFactory<Bibliotecario,String>("id"));
        cargoC3.setCellValueFactory(new PropertyValueFactory<Bibliotecario,String>("cargo"));

        this.tabela.getColumns().addAll(nomeC1,loginC2,cargoC3);
        this.tabela.setItems(bibliotecarios);


    }


    @FXML
    void EditarButtonAction(ActionEvent event) {
        int i = this.tabela.getSelectionModel().getSelectedIndex();
        if(i >= 0){
            try {
                Bibliotecario bi = new Bibliotecario(this.NomeText.getText(),this.senhaText.getText(),this.LoginText.getText(),this.CargoText.getText());
                this.bibliotecarioDao.Update(bi,bibliotecarios.get(i));
                this.bibliotecarios.set(i,bi);
                this.cleanAll();
            }catch (Exception e){
                this.error.setText("Preencha os campos adequadamente!");
            }
        }

    }

    @FXML
    void ExcluirButtonAction(ActionEvent event) throws BibliotecarioException {
        int i = this.tabela.getSelectionModel().getSelectedIndex();
        if(i>=0){
            this.bibliotecarioDao.delete(bibliotecarios.get(i));
            this.bibliotecarios.remove(i);

        }

    }

    @FXML
    void SairBUttonAction(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void SalvarButtonAction(ActionEvent event) {
        try{
            Bibliotecario b = new Bibliotecario(this.NomeText.getText(),this.senhaText.getText(),this.LoginText.getText(),this.CargoText.getText());
            bibliotecarioDao.save(b);
            this.error.setText("Salvo!");
            this.bibliotecarios.addAll(b);
            this.cleanAll();
        }catch (Exception e){
            this.error.setText("Preencha os campos adequadamente!");
        }

    }

    private void cleanAll() {
        this.error.setText("");
        this.CargoText.clear();
        this.LoginText.clear();
        this.NomeText.clear();
        this.senhaText.clear();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

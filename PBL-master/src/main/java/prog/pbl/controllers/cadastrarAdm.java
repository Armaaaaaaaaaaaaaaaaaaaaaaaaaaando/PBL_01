package prog.pbl.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import prog.pbl.LibraryException.usersexcepitions.AdministradorException;
import prog.pbl.dao.usuarios.ImDiskAdministradorDao;
import prog.pbl.dao.usuarios.ImMemoryAdministradorDao;
import prog.pbl.model.usuarios.Administrador;

public class cadastrarAdm {

    @FXML
    private TextField CargoText;

    @FXML
    private Button EntrarButton;

    @FXML
    private TextField IdText;

    @FXML
    private TextField SenhaText;

    @FXML
    private Button VoltarButton;

    @FXML
    private Label error;

    @FXML
    private TextField nomeText;

    private Stage stage;
    public static ImDiskAdministradorDao dao = new ImDiskAdministradorDao();

    @FXML
    void EntrarButtonAction(ActionEvent event) throws AdministradorException {

        try{
            Administrador adm = new Administrador(this.SenhaText.getText(),this.nomeText.getText(),this.CargoText.getText(),this.IdText.getText());
            System.out.println("Armanzenado: "+adm.getNome());
            dao.save(adm);
            this.cleanAll();
            this.stage.close();
        }
        catch (Exception e){
            this.error.setText("Complete os campos");
        }

    }

    @FXML
    void VoltarButtonAction(ActionEvent event) {
        this.stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void cleanAll(){
        this.error.setText("");
        this.IdText.clear();
        this.CargoText.clear();
        this.nomeText.clear();
        this.SenhaText.clear();
    }
}

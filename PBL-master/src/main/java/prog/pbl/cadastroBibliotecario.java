package prog.pbl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import prog.pbl.dao.usuarios.ImDiskBibliotecarioDao;
import prog.pbl.dao.usuarios.ImMemoryBibliotecarioDao;
import prog.pbl.model.usuarios.Bibliotecario;

public class cadastroBibliotecario {

    @FXML
    private TextField CargoText;

    @FXML
    private TextField Idtext;

    @FXML
    private TextField SenhaText;

    @FXML
    private Label error;

    @FXML
    private TextField nomeText;

    @FXML
    private Button salvarButton;

    @FXML
    private Button voltarButton;
    private Stage stage;
    public static ImDiskBibliotecarioDao daoBb = new ImDiskBibliotecarioDao();

    @FXML
    void salvarButtonAction(ActionEvent event) {
        try{
            Bibliotecario bibio = new Bibliotecario(this.nomeText.getText(), this.SenhaText.getText(), this.Idtext.getText(),this.CargoText.getText());
            daoBb.save(bibio);
            System.out.println("Salvo: "+ bibio.getNome());
            this.cleanAll();
            this.stage.close();


        }catch (Exception e){
            this.error.setText("Preencha os campos!");
        }
    }

    @FXML
    void voltarButtonAction(ActionEvent event) {
        this.stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void cleanAll(){
        this.error.setText("");
        this.CargoText.clear();
        this.Idtext.clear();
        this.nomeText.clear();
        this.SenhaText.clear();
    }
}

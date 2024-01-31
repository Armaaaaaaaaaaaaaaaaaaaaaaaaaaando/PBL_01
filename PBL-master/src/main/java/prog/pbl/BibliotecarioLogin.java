package prog.pbl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static prog.pbl.cadastroBibliotecario.daoBb;

public class BibliotecarioLogin {

    @FXML
    private Button EntrarButton;

    @FXML
    private TextField LoginText;

    @FXML
    private TextField SenhaText;

    @FXML
    private Label error;

    @FXML
    void EntrarButtonAction(ActionEvent event) {
        try{
            daoBb.findLogin(this.LoginText.getText(),this.SenhaText.getText());
            System.out.println("Acesso Liberado!");
            this.OpenBibliotecario("BibliotecarioTela01.fxml");
            this.cleanAll();

        }catch (Exception e){
            this.error.setText("Preencha os campos");
        }
    }

    private void OpenBibliotecario(String url){
        try{
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource(url);
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setTitle("Bem vindo!");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL); // Define a modalidade da janela

            BibliotecarioTela01 controller = loader.getController();
            controller.setStage(stage);

            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void cleanAll(){
        this.LoginText.clear();
        this.SenhaText.clear();
    }

}

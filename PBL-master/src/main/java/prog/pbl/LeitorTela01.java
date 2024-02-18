package prog.pbl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prog.pbl.LibraryException.emprestimoException.EmprestimoException;
import prog.pbl.model.usuarios.Leitor;

import java.io.IOException;
import java.net.URL;

public class LeitorTela01 {

    @FXML
    private Button SairButton;

    @FXML
    private Button emprestimoButton;

    @FXML
    private Button verificaButton;
    @FXML
    private Button reservasButton;
    private Stage stage;
    private Leitor leitor;

    @FXML
    private Label mensagem;

    @FXML
    void SairButtonAction(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void emprestimoButtonAction(ActionEvent event) {
        this.inforEmprestimo("InforEmprestimos.fxml",leitor);
    }

    @FXML
    void verificaButtonAction(ActionEvent event) {
        this.verificar("Pesquisa.fxml",leitor);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void verificar(String url,Leitor leitor){
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

            Pesquisa controller = loader.getController();
            controller.setStage(stage);
            controller.setLeitor(leitor);
            this.mensagem.setText("");

            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    private void inforEmprestimo(String url,Leitor leitor){
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

            InforEmprestimos controller = loader.getController();
            controller.setStage(stage);
            controller.setLeitor(leitor);
            this.mensagem.setText("");

            stage.showAndWait();
        } catch (Exception e){
            this.mensagem.setText("Não encontramos empréstimos ou reservas associados à sua conta.");
        }
    }

    @FXML
    void reservasButtonAction(ActionEvent event) {
        this.Reserva("Reservas.fxml");
    }

    private void Reserva(String url){
        try{
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource(url);
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setTitle("Reservados");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL); // Define a modalidade da janela

            Reservas controller = loader.getController();
            controller.setStage(stage);
            //controller.setLeitor(leitor);
            this.mensagem.setText("");

            stage.showAndWait();
        } catch (IOException e) {
            this.mensagem.setText("Não encontramos empréstimos ou reservas associados à sua conta.");
        }
    }
}

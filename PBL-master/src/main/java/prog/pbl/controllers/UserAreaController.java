package prog.pbl.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prog.pbl.dao.MasterDao;
import prog.pbl.model.Sistema;
import prog.pbl.model.estoque.Livro;
import prog.pbl.model.usuarios.Administrador;
import prog.pbl.model.usuarios.Pessoa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static prog.pbl.controllers.AdmTela01.admTela01;
import static prog.pbl.controllers.BibliotecarioTela01.bibliotecarioTela01;
import static prog.pbl.controllers.GuestHomeController.guestHomeController;
import static prog.pbl.controllers.InfoUserController.infoUserController;
import static prog.pbl.controllers.LeitorTela01.leitorTela01;
import static prog.pbl.controllers.MainWindow.mainWindow;

public class UserAreaController implements Initializable {

    @FXML
    private Pane mainPane;

    @FXML
    Button reservaBtn;

    @FXML
    Button emprestimoBtn;

    @FXML
    Button relatorioBtn;

    @FXML
    Button gerenciamentoBtn;

    @FXML
    Button logOutBtn;

    @FXML
    ImageView profileBtn;

    static UserAreaController userAreaController;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        userAreaController = this;
        this.setScreen();
    }

    @FXML
    void setScreen(){
        if(Sistema.getSessaoAtualBibliotecario() != null || Sistema.getSessaoAtualLeitor() != null){
            relatorioBtn.setDisable(true);
            relatorioBtn.setVisible(false);
            gerenciamentoBtn.setDisable(true);
            gerenciamentoBtn.setVisible(false);
        }

        gerenciamentoBtn.setText("Home Page");
        profileBtn.setOnMouseClicked(mouseEvent -> showInformations());
        logOutBtn.setOnAction(actionEvent -> logOut());
        gerenciamentoBtn.setOnAction(actionEvent -> showGerenciamento());
        relatorioBtn.setOnAction(actionEvent -> showRelatorio());
        emprestimoBtn.setOnAction(actionEvent -> showEmprestimo());
        reservaBtn.setOnAction(actionEvent -> showReserva());
    }

    private void showInformations() {
        String url = "/prog/pbl/InfoUserPage.fxml";
        if(Sistema.getSessaoAtualBibliotecario() != null) {
            bibliotecarioTela01.callToShowInRight(url);
            infoUserController.setUser(Sistema.getSessaoAtualBibliotecario());
        } else if (Sistema.getSessaoAtualLeitor() != null) {
            leitorTela01.callToShowInRight(url);
            infoUserController.setUser(Sistema.getSessaoAtualLeitor());
        } else {
            admTela01.callToShowInRight(url);
            infoUserController.setUser(Sistema.getSessaoAtualAdministrador());
        }

    }
    public void logOut() {
        Sistema.setSessaoAtualLeitor(null);
        Sistema.setSessaoAtualAdministrador(null);
        Sistema.setSessaoAtualBibliotecario(null);
        mainWindow.callLoginScreen();
    }
    public void showGerenciamento(){
        switch (Sistema.currentLogMember()) {
            case 0 -> admTela01.refreshScreen();
            case 1 -> bibliotecarioTela01.refreshScreen();
            case 2 -> leitorTela01.refreshScreen();
            case 3 -> guestHomeController.refreshScreen();
        }

    }
    public void showRelatorio(){
        admTela01.callToShowInRight("/prog/pbl/Relatorio.fxml");
    }

    public void showEmprestimo() {
        String url = "/prog/pbl/ListEmprestimosPage.fxml";
        if(Sistema.getSessaoAtualBibliotecario() != null) {
            bibliotecarioTela01.callToShowInCenter(url);
        } else if (Sistema.getSessaoAtualLeitor() != null) {
            leitorTela01.callToShowInCenter(url);
        } else {
            admTela01.callToShowInCenter(url);
        }
    }

    public void showReserva() {
        String url = "/prog/pbl/ListReservasPage.fxml";
        if(Sistema.getSessaoAtualBibliotecario() != null) {
            bibliotecarioTela01.callToShowInCenter(url);
        } else if (Sistema.getSessaoAtualLeitor() != null) {
            leitorTela01.callToShowInCenter(url);
        } else {
            admTela01.callToShowInCenter(url);
        }
    }


}

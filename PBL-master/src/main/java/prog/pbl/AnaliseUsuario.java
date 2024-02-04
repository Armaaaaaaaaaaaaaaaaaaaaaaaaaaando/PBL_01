package prog.pbl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import prog.pbl.LibraryException.usersexcepitions.LeitorException;
import prog.pbl.dao.usuarios.ImDiskLeitorDao;
import prog.pbl.model.usuarios.Leitor;

import java.util.LinkedList;
import java.util.List;

public class AnaliseUsuario {

    @FXML
    private Button EditarButton;

    @FXML
    private TextField EndText;

    @FXML
    private Button ExcluirButton;

    @FXML
    private Button SalvarButton;

    @FXML
    private TextField TelText;

    @FXML
    private TextField loginText;
    @FXML
    private Label error;

    @FXML
    private Button sairButton;


    @FXML
    private TextField nomeText;

    @FXML
    private TextField senhaText;

    @FXML
    private TableView<Leitor> tabela;
    private ObservableList<Leitor> leitores;
    public static ImDiskLeitorDao leitorDao = new ImDiskLeitorDao();
    private List<Leitor> lista = new LinkedList<>();
    private Stage stage;



    @FXML
    void initialize() {
        this.leitores = FXCollections.observableArrayList();
        System.out.println(leitorDao.findAll().size());
        if(leitorDao.findAll().size() >0){
            lista.addAll(leitorDao.findAll());
            for(int i = 0; i < lista.size();i++){
                this.leitores.addAll(this.lista.get(i));
            }
        }



        TableColumn nomeC1 = new TableColumn("Nome");
        TableColumn loginC2 = new TableColumn("Login");

        nomeC1.setCellValueFactory(new PropertyValueFactory<Leitor,String>("nome"));
        loginC2.setCellValueFactory(new PropertyValueFactory<Leitor,String>("id"));

        this.tabela.getColumns().addAll(nomeC1,loginC2);

        this.tabela.setItems(leitores);
    }

    @FXML
    void EditarButtonAction(ActionEvent event) {
        int i = this.tabela.getSelectionModel().getSelectedIndex();
        if(i >= 0){
            try{
                Leitor le = new Leitor(this.nomeText.getText(),this.senhaText.getText(),this.loginText.getText(),this.EndText.getText(),this.TelText.getText());
                this.leitorDao.Update(le,leitores.get(i));
                this.leitores.set(i,le);
                this.cleanAll();
            }catch (Exception e){
                this.error.setText("Preencha os campos adequadamente!");
            }
        }


    }

    @FXML
    void ExcluirButtonAction(ActionEvent event) throws LeitorException {
        int i = this.tabela.getSelectionModel().getSelectedIndex();
        System.out.println(i);
        if(i >= 0){
            this.leitorDao.delete(leitores.get(i));
            this.leitores.remove(i);
        }

    }

    @FXML
    void SalvarButtonAction(ActionEvent event) {
        try{
            Leitor l = new Leitor(this.nomeText.getText(), this.senhaText.getText(),this.loginText.getText(),
                    this.EndText.getText(),this.TelText.getText());
            leitorDao.save(l);
            this.error.setText("Salvo!");
            this.leitores.addAll(l);
            this.cleanAll();
            System.out.println(leitorDao.findAll().size());
        }
        catch (Exception e){
            this.error.setText("Preencha os campos adequadamente!");
        }

    }
    public void cleanAll(){
        this.loginText.clear();
        this.nomeText.clear();
        this.EndText.clear();
        this.senhaText.clear();
        this.TelText.clear();

    }

    @FXML
    void sairButtonAction(ActionEvent event) {
        this.stage.close();
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

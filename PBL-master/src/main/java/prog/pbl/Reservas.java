package prog.pbl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import prog.pbl.LibraryException.emprestimoException.ReservarException;
import prog.pbl.dao.emprestimo.ImDiskFilaDeReservaDao;
import prog.pbl.model.emprestimo.FilaDeReserva;
import prog.pbl.model.estoque.Livro;

import java.util.List;

import static prog.pbl.RegistroLivro.livroDao;

public class Reservas {

    @FXML
    private Label exemplo;

    @FXML
    private Button retirarButton;

    @FXML
    private TableView<Livro> tabela;

    @FXML
    private Button voltarButton;

    private ObservableList<Livro> reservas;
    private List<FilaDeReserva> fila;
    public static ImDiskFilaDeReservaDao filaDao = new ImDiskFilaDeReservaDao();
    private Stage stage;

    @FXML
    void retirarButtonAction(ActionEvent event) throws ReservarException {
        int i = this.tabela.getSelectionModel().getSelectedIndex();
        if(i >= 0){
            for(int j = 0; j<fila.size();i++){
                if(fila.get(j).getIsbn().equals(reservas.get(i).getIsbn())){
                    FilaDeReserva oldFila = fila.get(j);
                    fila.get(j).removeOnReservas();
                    reservas.remove(i);
                    filaDao.Update(fila.get(j),oldFila);
                }
            }

        }
    }

    @FXML
    void voltarButtonAction(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void initialize() {
        reservas = FXCollections.observableArrayList();
        fila = filaDao.findAll();
        for(int i = 0;i<fila.size();i++){
            if(livroDao.findAll().get(i).getIsbn().equals(fila.get(i).getIsbn())&& !fila.get(i).getReservas().isEmpty()){
                reservas.add(livroDao.findAll().get(i));
            }
        }



        TableColumn nome = new TableColumn("Titulo");
        TableColumn autor = new TableColumn("Autor");
        nome.setCellValueFactory(new PropertyValueFactory<Livro,String>("nome"));
        autor.setCellValueFactory(new PropertyValueFactory<Livro,String>("autor"));

        this.tabela.getColumns().addAll(nome,autor);
        this.tabela.setItems(reservas);

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

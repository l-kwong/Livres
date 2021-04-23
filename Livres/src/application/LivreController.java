package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class LivreController {

    @FXML
    private Button btnEffacer;

    @FXML
    private TextField txtTitre;

    @FXML
    private TextField txtAuteur;

    @FXML
    private Button btnModifier;

    @FXML
    private TableColumn<Livre, String> auteurColumn;

    @FXML
    private TableColumn<Livre, String> titreColumn;

    @FXML
    private ComboBox<String> cboGenre;

    @FXML
    private TableColumn<Livre, Double> pagesColumn;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnClear;

    @FXML
    private TableColumn<Livre, String> genreColumn;

    @FXML
    private TextField txtPages;

    
    // liste pour les genres - cette liste permettera de donner les valeurs du comboBox
    public ObservableList<String> list=(ObservableList<String>) FXCollections.observableArrayList("fiction, non-fiction, science-fiction");
}

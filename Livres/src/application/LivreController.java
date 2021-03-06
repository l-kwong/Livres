package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LivreController implements Initializable{

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
    
    @FXML
    private TableView<Livre> livresTable;

    
    // liste pour les genres - cette liste permettera de donner les valeurs du comboBox
    public ObservableList<String> list=(ObservableList<String>) FXCollections.observableArrayList("fiction", "non-fiction", "science-fiction");
    
    // Placer les livres dans une observable list
 	public ObservableList<Livre> livreData=FXCollections.observableArrayList();
 	
 	// Cr??er une m??thode pour acc??der ?? la liste des livres
 	public ObservableList<Livre> getlivreData()
 	{
 		return livreData;
 	}
 	
 	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		cboGenre.setItems(list);
		// attribuer les valuers aux colonnes du tableView
		titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
		auteurColumn.setCellValueFactory(new PropertyValueFactory<>("auteur"));
		genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
		pagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
		livresTable.setItems(livreData);

		btnModifier.setDisable(true);
		btnEffacer.setDisable(true);
		btnClear.setDisable(true);

		showLivres(null);
		// Mettre ?? jour l'affiche d'un livre s??lectionn??
		livresTable.getSelectionModel().selectedItemProperty(). addListener((
				observable, oldValue, newValue)-> showLivres(newValue));

	}
 	
 	@FXML
	public void verfiNum()	// m??thode pour trouver des input non num??iques
	{
		txtPages.textProperty().addListener((observable,oldValue,newValue)->
		{
			if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
			{
				txtPages.setText(newValue.replaceAll("[^\\d*\\.]",""));	// si le input est non num??rique, ??a le remplace
			}
		});
	}
 	
 	// Ajouter livre
 	@FXML
 	void ajouter()
 	{
 		// V??rifier si un champ n'est pas vide
 		if(noEmptyInput())
 		{
 			Livre tmp=new Livre();
 			tmp=new Livre();
 			tmp.setTitre(txtTitre.getText());
 			tmp.setAuteur(txtAuteur.getText());
 			tmp.setPages(Double.parseDouble(txtPages.getText()));
 			tmp.setGenre(cboGenre.getValue());
 				livreData.add(tmp);
 				clearFields();
 		}
 	}
 	
 	// Effacer le contenu des champs
 	@FXML
 	void clearFields()
 	{
 		cboGenre.setValue(null);
 		txtTitre.setText("");
 		txtAuteur.setText("");
 		txtPages.setText("");
 	}
 	
 	// Afficher les livres
 	public void showLivres(Livre livre)
 	{
 		if(livre !=null)
 		{
 			cboGenre.setValue(livre.getGenre());
 			txtTitre.setText(livre.getTitre());
 			txtAuteur.setText(livre.getAuteur());
 			txtPages.setText(Double.toString(livre.getPages()));
 			btnModifier.setDisable(false);
 			btnEffacer.setDisable(false);
 			btnClear.setDisable(false);
 		}
 		else
 		{
 			clearFields();
 		}
 	}
 	
 	// Mise ?? jour d'un livre
	@FXML
	public void updateLivre()
	{
		Livre livre=livresTable.getSelectionModel().getSelectedItem();
		
		livre.setTitre(txtTitre.getText());
		livre.setAuteur(txtAuteur.getText());
		livre.setPages(Double.parseDouble(txtPages.getText()));
		livre.setGenre(cboGenre.getValue());
		livresTable.refresh();
	}
	
	// Effacer un livre
	@FXML
	public void deleteLivre()
	{
		int selectedIndex = livresTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >=0)
			{
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Effecer");
				alert.setContentText("confirmer la suppression!");
				Optional<ButtonType> result=alert.showAndWait();	
				if(result.get()==ButtonType.OK)
						livresTable.getItems().remove(selectedIndex);
			}
	}
	
	// v??rifier champs vides
	private boolean noEmptyInput()
	{
		String errorMessage="";
		if(txtTitre.getText()==null||txtTitre.getText().length()==0)
			{
				errorMessage+="Le champ titre ne doit pas ??tre vide! \n";
			}
		if(txtAuteur.getText()==null||txtAuteur.getText().length()==0)
			{
				errorMessage+="Le champ auteur ne doit pas ??tre vide! \n";
			}
		if(txtPages.getText()==null||txtPages.getText().length()==0)
			{
				errorMessage+="Le champ pages ne doit pas ??tre vide! \n";
			}
		if(cboGenre.getValue()==null)
			{
				errorMessage+="Le champ genre ne doit pas ??tre vide! \n";
			}
		if(errorMessage.length()==0)
			{
				return true;
			}
		else 
			{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Champs manquants");
			alert.setContentText("Compl??ter les champs manquants");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
			}
	}
	
	// sauvegarde de donn??es
	// Recup??rer le chemin (path) des fichiers si ??a existe
	public File getLivreFilePath()
	{
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);
		
		if(filePath != null)
		{
			return new File(filePath);
		}
			else
		{
			return null;
		}
	}
	
	// Attribuer un chemin de fichier
	public void setLivreFilePath(File file)
	{
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if(file != null)
		{
			prefs.put("filePath", file.getPath());
		}
			else
		{
			prefs.remove("filePath");
		}
	}
	
	// Prendre les donn??es de type XML et les convertir en donn??es de type javafx
	public void loadLivreDataFromFile(File file)
	{
		try {
				JAXBContext context = JAXBContext.newInstance(LivreListWrapper.class);
				Unmarshaller um = context.createUnmarshaller();
			
				LivreListWrapper wrapper = (LivreListWrapper) um.unmarshal(file);
				livreData.clear();
				livreData.addAll(wrapper.getLivres());
				setLivreFilePath(file);
				// Donner le titre du fichier sauvegard??
				Stage pStage=(Stage) livresTable.getScene().getWindow();
				pStage.setTitle(file.getName());
				
		} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");				
				alert.setHeaderText("les donn??es n'ont pas ??t?? trouv??es");
				alert.setContentText("Les donn??es ne pouvaient pas ??tre trouv??es dans le fichier : \n" +file.getPath());
				alert.showAndWait();
		}
	}
	
	// Prendre les donn??es de type JavaFx et les convertir en type XML
	public void saveLivreDataToFile(File file)
	{
		try {
				JAXBContext context = JAXBContext.newInstance(LivreListWrapper.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				LivreListWrapper wrapper = new LivreListWrapper();
				wrapper.setLivres(livreData);
				
				m.marshal(wrapper, file);
				
				// Sauvegarder dans le registre
				setLivreFilePath(file);
				// Donner le titre du fichier sauvegard??
				Stage pStage=(Stage) livresTable.getScene().getWindow();
				pStage.setTitle(file.getName());
		
		} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText("Donn??es non sauvegard??es");
				alert.setContentText("Les donn??es ne pouvaient pas ??tre sauvegard??es dans le fichier : \n" +file.getPath());
				alert.showAndWait();
		}
	}
	
	// Commencer un nouveau
	@FXML
	private void handleNew()
	{
		getlivreData().clear();
		setLivreFilePath(null);
	}
	
	// Le FileChooser permet ?? l'usager de choisir le fichier ?? ouvrir
	@FXML
	private void handleOpen() 
	{
		FileChooser fileChooser = new FileChooser();
		
		// Permettre un filtre sur l'extension du fichier ?? chercher
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files(*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
			
		// montrer le dialogue
		File file = fileChooser.showOpenDialog(null);
			
		if(file != null) {
			loadLivreDataFromFile(file);
		}
					
	}
	
	// Sauvegarder le ficher corrrespondant ?? l'??tudiant actif
	// S'il n'y a pas de fichier, le menu sauvegarder sous va s'afficher
	@FXML
	private void handleSave()
	{
		File livreFile = getLivreFilePath();
		if (livreFile != null) {
			saveLivreDataToFile(livreFile);
		} else {
			handleSaveAs();
		}
	}
	
	// Ouvrir le FileChooser pour trouver le chemin
	@FXML
	private void handleSaveAs() 
	{
		FileChooser fileChooser = new FileChooser();
			
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files(*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
			
		// Sauvegarde
		File file = fileChooser.showSaveDialog(null);
			
		if (file != null) {
			// V??rification de l'extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			saveLivreDataToFile(file);
		} 
			
	}
	
	// Afficher les statistiques
	@FXML
	void handleStats()
	{
		try {
				FXMLLoader loader=new FXMLLoader();
				loader.setLocation(Main.class.getResource("PagesStat.fxml"));
				AnchorPane pane=loader.load();
				 Scene scene=new Scene(pane);
				 PagesStat pagesstat=loader.getController();
				 pagesstat.SetStats(livreData);
				 Stage stage=new Stage();
				 stage.setScene(scene);
				 stage.setTitle("Statistique");
			   	 stage.setResizable(false);
				 stage.show();
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
}

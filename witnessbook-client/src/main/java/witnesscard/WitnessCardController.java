package witnesscard;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import delegates.CategoryDelegate;
import delegates.WitnessCardDelagate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.StringConverter;
import tn.esprit.sigma.witnessbook.entities.Category;
import tn.esprit.sigma.witnessbook.entities.WitnessCard;
import tn.esprit.sigma.witnessbook.interfaces.ICategoryServiceRemote;
import tn.esprit.sigma.witnessbook.interfaces.IWitnessCardServiceRemote;



public class WitnessCardController {

	
	@FXML
	private javafx.scene.control.Button AddWC;
	
	
	@FXML
	private javafx.scene.control.TextField witnessCardName;
	
	@FXML
	private javafx.scene.control.TextArea witnessCardDescription;
	
	
	@FXML
	private javafx.scene.control.TextField  witnessCardAddress;
	
	
	@FXML
	private ComboBox<String> witnessCardCategory;
	
	@FXML
	private javafx.scene.control.Button UpploadImage;
	
	
	@FXML
	private javafx.scene.control.TextField witnessCardSearchName;
	
	
	@FXML
	private javafx.scene.control.Button	ShowAllWitnessCards;
	
	@FXML
	private ImageView wcimageadded;
	
	
	  @FXML
	  private TableView<WitnessCard> WCTable;
	  
	 
	  
	  @FXML
	    private TableColumn<WitnessCard, String> nomC;
	  @FXML
	    private TableColumn<WitnessCard, String> descriptionC;
	  
	  @FXML
	    private TableColumn<WitnessCard, String> adresseC;

	  @FXML
	    private TableColumn<WitnessCard, String> categorieC;
	
	  @FXML
	    private TableColumn<WitnessCard, String> imageC ;
	  
	@FXML
    void showcat(ActionEvent event) {

        // ComboBox<Category> witnessCardCategory = new ComboBox<Category>();
       //	witnessCardCategory.getItems().addAll(CategoryDelegate.findAll());
	 	
		 //combo();
		/* List<String> findAllcategory = new ArrayList<String>();
		 ObservableList<String> options = FXCollections.observableArrayList();
	 	List<Category> lis=new ArrayList<Category>();
	 	lis=CategoryDelegate.findAll();
		 
	 	for (Category category : lis) {
	 			
	 		findAllcategory.add(category.getName());
	 			options.addAll(findAllcategory);
			}
	 		
 		witnessCardCategory.setItems(options);*/
		
		/* List<String> findAllcategory = new ArrayList<String>();
		 ObservableList<String> options = FXCollections.observableArrayList();
		 List<Category> lis=new ArrayList<Category>();
		 	lis=CategoryDelegate.findname();
		 
		 	for (Category category : lis) {
		 			
		 		findAllcategory.add(category.getName());
		 			options.addAll(findAllcategory);
		 			
				}
		 		
		 	witnessCardCategory.setItems(options); */
		
		//ObservableList<String> maliste = FXCollections.observableArrayList(CategoryDelegate.findname().toString());
	 		
		//witnessCardCategory.setItems(maliste);
		
	  
	   
		
	}
	
	@FXML
    void uppimg(ActionEvent event){
		

		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open Resource File");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
		         new ExtensionFilter("All Files", "*.*"));
		 File file = fileChooser.showOpenDialog(null);
		 
		 try {
             BufferedImage bufferedImage = ImageIO.read(file);
             Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             wcimageadded.setImage(image);
         } catch (IOException ex) {
             Logger.getLogger(WitnessCardController.class.getName()).log(Level.SEVERE, null, ex);
         }

	}
	
	
	@FXML
    void AddWitnessCard(ActionEvent event) {
		
		
		
		WitnessCard wc = new WitnessCard();
		wc.setName(witnessCardName.getText());
		wc.setDescription(witnessCardDescription.getText());
		wc.setAddress(witnessCardAddress.getText());
	//	wc.setCategory(witnessCardCategory.getValue().toString());
		wc.setAttachement(wcimageadded.getImage().toString());
       	WitnessCardDelagate.create(wc); 

		
	}
	
	
	@FXML
	
	void searchWC(ActionEvent event){
		
		
    	String query = witnessCardSearchName.getText();
    	List<WitnessCard> resultat = WitnessCardDelagate.SearchWC(query);
    	 
    	
		 
		 WCTable.getItems().clear();
    	WCTable.setItems(FXCollections.observableArrayList(resultat));
	}
	
	@FXML
	 void initialize(){
	
		nomC.setCellValueFactory(new PropertyValueFactory("name"));
		nomC.setCellFactory(TextFieldTableCell.forTableColumn());
		
		descriptionC.setCellValueFactory(new PropertyValueFactory("description"));
		descriptionC.setCellFactory(TextFieldTableCell.forTableColumn());
		
		adresseC.setCellValueFactory(new PropertyValueFactory("address"));
		adresseC.setCellFactory(TextFieldTableCell.forTableColumn());
		
		
		categorieC.setCellValueFactory(new PropertyValueFactory("category"));
		categorieC.setCellFactory(TextFieldTableCell.forTableColumn());
		
		

		
		imageC.setCellValueFactory(new PropertyValueFactory("attachment"));
		imageC.setCellFactory(TextFieldTableCell.forTableColumn());
	
	}
	
@FXML
	void showallWC(ActionEvent event){
		
		
	ObservableList<WitnessCard> resultat =FXCollections.observableArrayList(WitnessCardDelagate.findAll());
   	WCTable.getItems().clear();
   	WCTable.setItems(FXCollections.observableArrayList(resultat));
		
	}
	
	
	
}

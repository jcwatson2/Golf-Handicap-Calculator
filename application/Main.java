/**
 * 
 */
package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Button;


/**
 * @author jameswatson
 *
 */
public class Main extends Application {
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 300;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		IndexDataBase db = new IndexDataBase();
		db.addNewIndex("j", "j");
		BorderPane root = new BorderPane();
		primaryStage.setTitle("Handicap Calculator");
		
		// Main hbox
		HBox top = new HBox();
		// prompts for username and pw
		Label enterName = new Label("Enter your username: ");
		Label enterPW = new Label("Enter your password: ");
		//add to vbox
		VBox namePW = new VBox();
		namePW.getChildren().add(enterName);
		namePW.getChildren().add(enterPW);
		top.getChildren().add(namePW);
		
		TextField name = new TextField();
		PasswordField pw = new PasswordField();
		VBox namePWField = new VBox();
		namePWField.getChildren().add(name);
		namePWField.getChildren().add(pw);

		
		top.getChildren().add(namePWField);
		
		Button enter1 = new Button("Enter");
		enter1.setOnAction(e -> {
			String userName = name.getText();
			String passWord = pw.getText();
			if(!db.checkForIndex(userName, passWord)) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("PASSWORD AND USERNAME NOT VALID");
				errorAlert.setContentText("Username and password do not match to an account");
				errorAlert.showAndWait();
			}
			else {
				try {
				Main m = new Main();
				Stage stage2 = new Stage();
				m.start2(stage2);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
				
			name.clear();
			pw.clear();
		});
		VBox buttons = new VBox();
		buttons.getChildren().add(enter1);
		
		top.getChildren().add(buttons);
		
		root.setTop(top);
		
		HBox bottom = new HBox();
		VBox subBottom = new VBox();
		Label prompt = new Label("CREATE A NEW ACCOUNT");
		prompt.setStyle("-fx-font-weight: bold");
		subBottom.getChildren().add(prompt);
		
		Label newUser = new Label("Enter a username");
		subBottom.getChildren().add(newUser);
		
		TextField userTf = new TextField();
		subBottom.getChildren().add(userTf);
		
		Label newPW = new Label("Enter a password");
		subBottom.getChildren().add(newPW);
		
		HBox inputUser = new HBox();
		PasswordField pwf = new PasswordField();
		Button enterAcc = new Button("Enter");
		inputUser.getChildren().add(pwf);
		inputUser.getChildren().add(enterAcc);
		
		enterAcc.setOnAction(e -> {
			String newName = userTf.getText();
			String setPW = pwf.getText();
			if(db.containsUsername(newName)) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Username Error");
				error.setContentText("The entered username already exists. Chose another username");
				error.showAndWait();
				return;
			}
			if(setPW.length() < 7) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Invalid Password");
				error.setContentText("Password must be at least 6 characters long");
				error.showAndWait();
				return;
			}
			db.addNewIndex(newName, setPW);
			userTf.clear();
			pwf.clear();
		});
		
		subBottom.getChildren().add(inputUser);
		bottom.getChildren().add(subBottom);
		
		root.setBottom(bottom);
		
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setScene(mainScene);
		
		primaryStage.show();
	

	}
	
	public void start2(Stage primaryStage) {
		BorderPane root = new BorderPane();
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}

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
import javafx.geometry.Pos;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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

	/**
	 * Initializes the login window with options to create a new account
	 * if one does not already exist
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		IndexDataBase db = new IndexDataBase();
		db.addNewIndex("j", "j");
		BorderPane root = new BorderPane();
		primaryStage.setTitle("Handicap Calculator");
		
		/**
		 * loging into an existing account
		 */
		// Main hbox
		HBox top = new HBox();
		// prompts for username and pw
		Label enterName = new Label("Enter your username: ");
		Label enterPW = new Label("Enter your password: ");
		
		//add to vbox that will hold the lables, and will be inserted into the main
		// horizontal box
		VBox namePW = new VBox();
		namePW.getChildren().add(enterName);
		namePW.getChildren().add(enterPW);
		// adds to the main hbox
		top.getChildren().add(namePW);
		
		// creates text fields for the user to input their username
		// and password
		TextField name = new TextField();
		PasswordField pw = new PasswordField();
		// creates a vbox to hold these fields
		VBox namePWField = new VBox();
		namePWField.getChildren().add(name);
		namePWField.getChildren().add(pw);
		// adds the vbox to the top hbox along with the vbox that contains the prompts
		top.getChildren().add(namePWField);
		
		// creates a button for the user to press
		Button enter1 = new Button("Enter");
		// envent handling for enter1 button
		enter1.setOnAction(e -> {
			String userName = name.getText();
			String passWord = pw.getText();
			// checks if there is a valid username
			if(!db.checkForIndex(userName, passWord)) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("PASSWORD AND USERNAME NOT VALID");
				errorAlert.setContentText("Username and password do not match to an account");
				errorAlert.showAndWait();
			}
			
			// TODO: check and see if the password matches the username
			
			// both the username and password, opens a new window that shows the user's account
			else {
				try {
				// sets a new stage 
				Main m = new Main();
				Stage stage2 = new Stage();
				m.start2(stage2);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			
			// clears the fields after button is pushed
			name.clear();
			pw.clear();
		});
	
		//creates and adds the buttons to a sub vbox
		VBox buttons = new VBox();
		buttons.getChildren().add(enter1);
		// adds the vbox containing the buttons to the main hbox
		top.getChildren().add(buttons);
		
		// sets the top section of the boarder pane to the hbox containing the sub vboxes
		root.setTop(top);
		
		
		/**
		 * creating a new account
		 */
		// initializes an hbox to serve as the main bottom box
		HBox bottom = new HBox();
		// sub vbox that will contain a label for the section, promps for the new
		// username and password, along with their text fields
		VBox subBottom = new VBox();
		Label prompt = new Label("CREATE A NEW ACCOUNT");
		prompt.setStyle("-fx-font-weight: bold");
		subBottom.getChildren().add(prompt);
		
		// prompt for the username
		Label newUser = new Label("Enter a username");
		subBottom.getChildren().add(newUser);
		
		// text field for the username
		TextField userTf = new TextField();
		subBottom.getChildren().add(userTf);
		
		// prompt for the password
		Label newPW = new Label("Enter a password");
		subBottom.getChildren().add(newPW);
		
		// new hbox that will hold the password field and the enter button
		HBox inputUser = new HBox();
		PasswordField pwf = new PasswordField();
		Button enterAcc = new Button("Enter");
		inputUser.getChildren().add(pwf);
		inputUser.getChildren().add(enterAcc);
		
		// event handling for the enter button
		enterAcc.setOnAction(e -> {
			String newName = userTf.getText();
			String setPW = pwf.getText();
			
			// checks if the username is avalible
			if(db.containsUsername(newName)) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Username Error");
				error.setContentText("The entered username already exists. Chose another username");
				error.showAndWait();
				return;
			}
			// checks if the password entered is at least 6 characters
			if(setPW.length() < 7) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Invalid Password");
				error.setContentText("Password must be at least 6 characters long");
				error.showAndWait();
				return;
			}
			// adds the account to the database
			db.addNewIndex(newName, setPW);
			userTf.clear();
			pwf.clear();
		});
		
		// sets the sub boxes to the main box
		subBottom.getChildren().add(inputUser);
		bottom.getChildren().add(subBottom);
		
		// sets the main box to the bottom section of the border pane
		root.setBottom(bottom);
		
		
		// initializes the scene
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setScene(mainScene);
		
		primaryStage.show();
	

	}
	
	/**
	 * Method that launches the secondary account window after
	 * the user inputs existing account credentials
	 * @param primaryStage
	 */
	public void start2(Stage primaryStage) {
		BorderPane root = new BorderPane();
		// sets the title fo the window
		primaryStage.setTitle("USERNAME");
		
		Label topLabel = new Label("USERNAME's Index Account");
		topLabel.setFont(Font.font(18));
		root.setTop(topLabel);
		
		// left side containing the recent scores
		VBox recScore = new VBox();
		Label scoreList = new Label("Recent Scores");
		scoreList.setFont(Font.font(15));
		recScore.getChildren().add(scoreList);
		ListView<Integer> recentScores = new ListView<Integer>();
		ObservableList<Integer> scores =FXCollections.observableArrayList(50, 43, 60, 10, 1, 1, 1, 1, 1, 1, 1, 1);
		recentScores.setItems(scores);
		recentScores.setPrefHeight(200);
		recentScores.setPrefWidth(100);
		recScore.getChildren().add(recentScores);
		root.setLeft(recScore);
		
		
		//Right side for entering scores
		VBox scoreEnter = new VBox();
		Label enterLabel = new Label("New Entry");
		enterLabel.setFont(Font.font(15));
		scoreEnter.getChildren().add(enterLabel);
		
		Label newScore = new Label("Score:");
		scoreEnter.getChildren().add(newScore);
		
		TextField scoreF = new TextField();
		scoreEnter.getChildren().add(scoreF);
		
		Label newCR = new Label("Course Rating:");
		scoreEnter.getChildren().add(newCR);
		
		TextField crF = new TextField();
		scoreEnter.getChildren().add(crF);

		Label newSlope = new Label("Slope:");
		scoreEnter.getChildren().add(newSlope);

		TextField slopeF = new TextField();
		scoreEnter.getChildren().add(slopeF);
		
		Button enter = new Button("Enter");
		scoreEnter.getChildren().add(enter);
		
		//enter.setOnAction(e -> enterScorer());
		
		root.setRight(scoreEnter);

		// Center for displaying index
		VBox center = new VBox();
		Label indexLabel = new Label("Your Hanicap Index");
		indexLabel.setFont(Font.font(15));
		center.getChildren().add(indexLabel);
		Label index = new Label("4.1");
		index.setFont(Font.font(50));
		center.getChildren().add(index);
		center.setAlignment(Pos.CENTER);
		root.setCenter(center);
		
		// bottom for comparing different players
		VBox bottom = new VBox();
		Label compare = new Label("Compare Players");
		compare.setFont(Font.font(15));
		bottom.getChildren().add(compare);
		HBox subBottom = new HBox();
		Label enterPlayer = new Label("Enter players username");
		TextField playerTF = new TextField();
		Button enter2 = new Button("Enter");
		subBottom.getChildren().addAll(enterPlayer, playerTF, enter2);
		bottom.getChildren().add(subBottom);
		root.setBottom(bottom);
		
		
		
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
	
	
	


	public static void main(String[] args) {
		launch(args);
	}

}

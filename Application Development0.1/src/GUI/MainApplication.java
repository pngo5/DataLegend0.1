package GUI;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.Mysql;
import Objects.BusSchedule;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is the user main application It will include the ability for the user to
 * book reservations, delete their reservations, and view reservations.
 *
 * .. . . private Date startTime; private String startLocation; private String
 * endLocation; private double ticketPrice;
 * 
 */
public class MainApplication extends Application {
	Stage window;
	ChoiceBox<String> startCity;
	ChoiceBox<String> endCity;
	Button backToMainMenu, logOut, removeBooking, addBooking;
	TableColumn<BusSchedule, String> busNumber, startTime, endTime, startLocation, endLocation, passengerCount;
	TableView table, userBookingTable;
	Label fromCityLabel, toCityLabel, title, view, myBookedFlights;
	TextField fromCityText, toCityText;
    TextField busIDInput;
   final ObservableList<BusSchedule> BusSystem1 = FXCollections.observableArrayList();
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Main Application (User)");
		//window.getIcons().add(new ("icon.png"));

		//Create BorderPane
		BorderPane border = new BorderPane();
		
		//Creating HBox
		HBox topMenu = addHBox();
		
		//Creating the Tables
		GridPane grid = createGridPane();
		
		//Creating the main menu button, logout button, and add table button
		VBox inputs = addInputs();
		
		border.setTop(topMenu);
		border.setCenter(grid);
		border.setRight(inputs);
		
		Scene scene = new Scene(border, 1000, 650);
		//scene.getStylesheets().add("Layout.css");
		scene.getStylesheets().add("Layout.css");
		window.getIcons().add(new Image("icon.png"));
		window.setScene(scene);
		window.show();

	}
	
	public GridPane createGridPane() {
		
		
		GridPane grid = new GridPane();
		
		VBox busScheduleTable = createTable();
		
		VBox userBookings = createUserBookingTable();
		
		
		grid.add(busScheduleTable, 1, 1);
		grid.add(userBookings, 2, 1);
		
		grid.setHgap(50);
		grid.setVgap(30);
		
		return grid;
		
	}

	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    ChoiceBox<String> from = new ChoiceBox<>();
	    from.getItems().addAll("ATL","DAL","NY");
	    from.setPrefSize(100, 20);

	    ChoiceBox<String> to = new ChoiceBox<>();
	    to.getItems().addAll("ATL","DAL","NY");
	    to.setPrefSize(100, 20);
	    
	    from.setPrefSize(100, 20);
	    to.setPrefSize(100, 20);
	    
	    Button searchButton = new Button("Search");
	 
	    
	    hbox.getChildren().addAll(from, to, searchButton);

	    return hbox;
	}
	
	/**
	 * Menu for Main Menu, Logout, and etc.
	 * @return
	 */
	public VBox addInputs() {
		VBox v = new VBox();
		v.setPadding(new Insets(10));
	    v.setSpacing(8);
	    
		 Text title = new Text("Controllers");
		 title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		 
		
		 logOut = new Button("Logout");
	        logOut.setMinWidth(105);
	        logOut.setOnAction(e -> {
	              Login mm = new Login();
	              try {
	                  mm.start(window);
	              } catch (Exception e1) {
	                  // TODO Auto-generated catch block
	                  e1.printStackTrace();
	              }
	          });

	        backToMainMenu = new Button("Main Menu");
	        backToMainMenu.setMinWidth(105);
	        backToMainMenu.setOnAction(e -> {
	              Login mm = new Login();
	              try {
	                  mm.start(window);
	              } catch (Exception e1) {
	                  // TODO Auto-generated catch block
	                  e1.printStackTrace();
	              }
	          });

	        v.getChildren().addAll(title, logOut,backToMainMenu);


	        return v;
		
	}
	

	/**
	 * Creates the view tables
	 * 
	 * @return
	 */
	public ObservableList<BusSchedule> getBusSystem(){
		
		ObservableList<BusSchedule> products = Mysql.getBusSchedule();
		
		
		return products;

	}
	public VBox createTable() {

		/**
		 * Creating view column for User and matching it to their respective instances.
		 * 
		 * 	private int busID;
	private String departCity;
	private String arrivalCity;
	private String departTime;
	private String arrivalTime;
	private int passengerCount;
		 */
		busNumber = new TableColumn<>("Bus ID");
		busNumber.setMinWidth(45);
		busNumber.setCellValueFactory(new PropertyValueFactory<>("busID"));

		startTime = new TableColumn<>("Start Time");
		startTime.setMinWidth(45);
		startTime.setCellValueFactory(new PropertyValueFactory<>("departCity"));

		endTime = new TableColumn<>("End Time");
		endTime.setMinWidth(45);
		endTime.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));

		startLocation = new TableColumn<>("Start Location");
		startLocation.setMinWidth(45);
		startLocation.setCellValueFactory(new PropertyValueFactory<>("departTime"));

		endLocation = new TableColumn<>("End Location");
		endLocation.setMinWidth(45);
		endLocation.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));

		passengerCount = new TableColumn<>("Passenger count");
		passengerCount.setMinWidth(30);
		passengerCount.setCellValueFactory(new PropertyValueFactory<>("passengerCount"));
		
		

		//Creating table
		table = new TableView<>();

		//Populating table system
		table.setItems(getBusSystem());
		table.getColumns().addAll(busNumber, startTime, endTime, startLocation, endLocation, passengerCount);
		
		//Search the Bus ID Input
        busIDInput = new TextField();
        busIDInput.setPromptText("Bus ID");
        busIDInput.setMinWidth(100);
       
        //Button
        Button addButton = new Button("Reserve");
       
        
        //Create
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #336699;");
        hBox.getChildren().addAll(busIDInput, addButton);

		//Adding the table to a VbOX
		VBox vBox = new VBox();
		vBox.getChildren().addAll(table,hBox);

		return vBox;

	}
	



	public VBox createUserBookingTable() {

		/**
		 * 
		 * Creating view column for User and matching it to their respective instances.
		 * 
		 */
		busNumber = new TableColumn<>("Bus ID");
        busNumber.setMinWidth(100);
        busNumber.setCellValueFactory(new PropertyValueFactory<>("busNumber"));

		startTime = new TableColumn<>("Start Time");
		startTime.setMinWidth(100);
		startTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));

		endTime = new TableColumn<>("End Time");
		endTime.setMinWidth(100);
		endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));

		// Creating table

		userBookingTable = new TableView<>();

		// Populating table system

		userBookingTable.setItems(getBusSystem());
		userBookingTable.getColumns().addAll(busNumber, startTime, endTime);
		
       
        //Button
        removeBooking = new Button("Remove");
       
        
        //Create
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #336699;");
        hBox.getChildren().addAll(removeBooking);

		// Adding the table to a VbOX
		VBox vBox = new VBox();
		vBox.getChildren().addAll(userBookingTable, hBox);

		return vBox;

	}
	
	
	
	
	
	
	/**
	 * Start the main application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
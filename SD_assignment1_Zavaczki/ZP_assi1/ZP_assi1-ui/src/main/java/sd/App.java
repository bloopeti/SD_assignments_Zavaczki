package sd;

import java.util.List;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import sd.commands.*;
import sd.model.Match;
import sd.model.Tournament;

public class App extends Application {

    Stage window;
    Scene currentScene;//, loginScene, createAccScene;
    String windowName = "";
	
	public static void main(String[] args)
	{
		launch(args);
	}

/*	@Override
	public void start(Stage primaryStage) throws Exception
	{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginView.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Title of primaryStage");
        primaryStage.setScene(scene);
        primaryStage.show();
	}*/
	
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        //Display scene 1 at first
        window.setScene(setupLoginScene());
        window.show();
    }
    
    private Scene setupLoginScene()
    {
    	window.setTitle("TableTennis Manager - Login");
    	
    	Scene loginScene;
        //login window
        TextField tf_login = new TextField();
        tf_login.setPromptText("Mail");
        PasswordField pf_login = new PasswordField();
        pf_login.setPromptText("Password");
        Button button_login = new Button("Login");
        button_login.setOnAction(e -> {
	        //login method
	        //System.out.println(tf_login.getText() + " " + pf_login.getText()));
	        LoginCommand login = new LoginCommand(new String(tf_login.getText()), new String(pf_login.getText()));
	        int option = login.execute();
	        if(option == 0 || option == 1)
	        	window.setScene(setupChoiceScene(option));
	        else
	        	System.out.println("Login error, code: " + option);
        });
        Button button_newAcc = new Button("Create new account");
        button_newAcc.setOnAction(e -> window.setScene(setupCreateAccScene()));

        VBox element_login = new VBox(10);
        element_login.setPadding(new Insets(10, 10, 10, 10));
        element_login.getChildren().addAll(tf_login, pf_login, button_login, button_newAcc);
        		
        StackPane layout_login = new StackPane();
        layout_login.getChildren().addAll(element_login);
        loginScene = new Scene(layout_login, 600, 400);
        
        return loginScene;
    }
	
    private Scene setupCreateAccScene()
    {
    	window.setTitle("TableTennis Manager - Create new account");
    	
    	Scene createAccScene;
    	//create acc window
        TextField tf_createAcc = new TextField();
        tf_createAcc.setPromptText("Mail");
        PasswordField pf_createAcc = new PasswordField();
        pf_createAcc.setPromptText("Password");
        PasswordField pf_createAccRe = new PasswordField();
        pf_createAccRe.setPromptText("Repeat password");
        Button button_createAcc = new Button("Create account");
        button_createAcc.setOnAction(e -> {
        	//add create user method
        	String mail = new String(tf_createAcc.getText());
        	String pw1 = new String(pf_createAcc.getText());
        	String pw2 = new String(pf_createAccRe.getText());
        	CreateAccCheckMail freshMailChecker = new CreateAccCheckMail(mail);
    		if(!pw1.equals(pw2))
    			System.out.println("The two passwords don't match!");
    		else if(!freshMailChecker.execute())
    			System.out.println("That email address is already used!");
    		else if(pw1.equals(pw2) && freshMailChecker.execute())
        	{
        		CreateUserCommand newUser = new CreateUserCommand(mail, pw1);
        		newUser.execute();
                window.setScene(setupLoginScene());
        	}
        	/*System.out.println(pw1);
    		System.out.println(pw2);
        	System.out.println(pw1.equals(pw2));
    		System.out.println(freshMailChecker.execute());
        	if(pw1.equals(pw2) && freshMailChecker.execute())
        		System.out.println("yes");
        	else
        		System.out.println("no");*/
        });
        Button button_createAccB = new Button("Back");
        button_createAccB.setOnAction(e -> window.setScene(setupLoginScene()));

        VBox element_createAcc = new VBox(10);
        element_createAcc.setPadding(new Insets(10, 10, 10, 10));
        element_createAcc.getChildren().addAll(tf_createAcc, pf_createAcc, pf_createAccRe, button_createAcc, button_createAccB);

        StackPane layout_createAcc = new StackPane();
        layout_createAcc.getChildren().addAll(element_createAcc);
        createAccScene = new Scene(layout_createAcc, 600, 400);
        
        return createAccScene;
    }

    private Scene setupChoiceScene(int isAdmin)
    {
    	if (isAdmin == 1)
    		window.setTitle("TableTennis Manager - Table access - admin");
    	else
    		window.setTitle("TableTennis Manager - Table access - regular user");    		
    
    	Scene choiceScene;

    	Label label_choice = new Label("Which table's data would you like to access?");
    	
        Button button_choiceTournament = new Button("Tournaments");
        button_choiceTournament.setOnAction(e -> window.setScene(setupTournamentScene(isAdmin)));
        Button button_choiceMatch = new Button("Matches");
        button_choiceMatch.setOnAction(e -> window.setScene(setupMatchScene(isAdmin)));
        Button button_choiceGame = new Button("Games");
        //button_choiceTournament.setOnAction(e -> window.setScene(setupGameActionScene()));
        Button button_choiceUser = new Button("Users");
        //button_choiceTournament.setOnAction(e -> window.setScene(setupUserActionScene()));
        Button button_choiceLogout = new Button("Logout");
        button_choiceLogout.setOnAction(e -> window.setScene(setupLoginScene()));

        HBox element_choiceHoriz1 = new HBox(10);
        //element_choiceHoriz1.setPadding(new Insets(10, 10, 10, 10));
        element_choiceHoriz1.getChildren().addAll(button_choiceTournament, button_choiceMatch);
        
        HBox element_choiceHoriz2 = new HBox(10);
        //element_choiceHoriz2.setPadding(new Insets(10, 10, 10, 10));
        if(isAdmin == 1)
        	element_choiceHoriz2.getChildren().add(button_choiceUser);
    	element_choiceHoriz2.getChildren().add(button_choiceGame);
        
        VBox element_choiceVert = new VBox(10);
        element_choiceVert.setPadding(new Insets(10, 10, 10, 10));
        element_choiceVert.getChildren().addAll(label_choice, element_choiceHoriz1, element_choiceHoriz2, button_choiceLogout);

        StackPane layout_choice = new StackPane();
        layout_choice.getChildren().addAll(element_choiceVert);
        choiceScene = new Scene(layout_choice, 600, 400);
    	
    	return choiceScene;
    }
    
    private Scene setupTournamentScene(int isAdmin)
    {        
    	if (isAdmin == 1)
    		window.setTitle("TableTennis Manager - Tournament access - admin");
    	else
    		window.setTitle("TableTennis Manager - Tournament access - regular user");    		
    
    	Scene tournamentScene;
    	
    	Label label_tournament = new Label("What would you like to do?");
    	
        TableView<Tournament> tournamentTable;
        //Name column
        TableColumn<Tournament, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Start Date column
        TableColumn<Tournament, String> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setMinWidth(100);
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("start_date"));

        tournamentTable = new TableView<>();
        tournamentTable.getColumns().addAll(nameColumn, startDateColumn);

        TextField tournamentName = new TextField();
        tournamentName.setPromptText("Tournament name");
        tournamentName.setMinWidth(100);
        TextField tournamentDate = new TextField();
        tournamentDate.setPromptText("Tournament date");
        tournamentDate.setMinWidth(100);
        HBox element_tournamentInputFields = new HBox(10);
        if(isAdmin == 1)
        	element_tournamentInputFields.getChildren().addAll(tournamentName, tournamentDate);
        
        Button button_tournamentFindAll = new Button("Show all rows");
        button_tournamentFindAll.setOnAction(e -> {
        	GetAllTournamentsCommand getTournaments = new GetAllTournamentsCommand();
        	ObservableList<Tournament> tournaments = getTournaments.execute();
        	tournamentTable.setItems(tournaments);
        });
        Button button_tournamentFind = new Button("Show a specific row");
        //button_tournamentTournament.setOnAction(e -> window.setScene(setupMatchActionScene()));
        Button button_tournamentInsert = new Button("Insert a row");
        button_tournamentInsert.setOnAction(e -> {
        	CreateTournamentCommand newTournament = new CreateTournamentCommand(tournamentName.getText(), tournamentDate.getText());
        	newTournament.execute();
        	
        	GetAllTournamentsCommand getTournaments = new GetAllTournamentsCommand();
        	ObservableList<Tournament> tournaments = getTournaments.execute();
        	tournamentTable.setItems(tournaments);
        });
        Button button_tournamentDelete = new Button("Delete a row");
        //button_tournamentTournament.setOnAction(e -> window.setScene(setupUserActionScene()));
        Button button_tournamentEdit = new Button("Edit a row");
        //button_tournamentTournament.setOnAction(e -> window.setScene(setupUserActionScene()));
        Button button_tournamentBack = new Button("Back");
        button_tournamentBack.setOnAction(e -> window.setScene(setupChoiceScene(isAdmin)));

        HBox element_tournamentHoriz1 = new HBox(10);
        //element_tournamentHoriz1.setPadding(new Insets(10, 10, 10, 10));
        element_tournamentHoriz1.getChildren().addAll(button_tournamentFindAll, button_tournamentFind);
        
        HBox element_tournamentHoriz2 = new HBox(10);
        //element_tournamentHoriz2.setPadding(new Insets(10, 10, 10, 10));
        if(isAdmin == 1)
        	element_tournamentHoriz2.getChildren().addAll(button_tournamentInsert, button_tournamentDelete, button_tournamentEdit);
        
        VBox element_tournamentVert = new VBox(10);
        element_tournamentVert.setPadding(new Insets(10, 10, 10, 10));
        element_tournamentVert.getChildren().addAll(label_tournament, element_tournamentHoriz1, element_tournamentHoriz2, 
        		element_tournamentInputFields, tournamentTable, button_tournamentBack);

        StackPane layout_tournament = new StackPane();
        layout_tournament.getChildren().addAll(element_tournamentVert);
        tournamentScene = new Scene(layout_tournament, 600, 400);
    	
    	return tournamentScene;
    }

    private Scene setupMatchScene(int isAdmin)
    {
    	if (isAdmin == 1)
    		window.setTitle("TableTennis Manager - Match access - admin");
    	else
    		window.setTitle("TableTennis Manager - Match access - regular user");    		
    
    	Scene matchScene;

    	Label label_match = new Label("What would you like to do?");
    	

        TableView<Match> matchTable;
        //ID column
        TableColumn<Match, Integer> IDColumn = new TableColumn<>("ID");
        IDColumn.setMaxWidth(25);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        //Level column
        TableColumn<Match, Integer> levelColumn = new TableColumn<>("Level");
        levelColumn.setMinWidth(10);
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        
        //Price column
        TableColumn<Match, String> player1Column = new TableColumn<>("Player 1");
        player1Column.setMinWidth(150);
        player1Column.setCellValueFactory(new PropertyValueFactory<>("player1"));

        //Price column
        TableColumn<Match, String> player2Column = new TableColumn<>("Player 2");
        player2Column.setMinWidth(150);
        player2Column.setCellValueFactory(new PropertyValueFactory<>("player2"));
        
        //tournament name column
        TableColumn<Match, String> tournamentColumn = new TableColumn<>("Tournament");
        tournamentColumn.setMinWidth(150);
        tournamentColumn.setCellValueFactory(new PropertyValueFactory<>("tournament_name"));

        matchTable = new TableView<>();
        matchTable.getColumns().addAll(IDColumn, levelColumn, player1Column, player2Column, tournamentColumn);
    	
        Button button_matchFindAll = new Button("Show all rows");
        button_matchFindAll.setOnAction(e -> {
        	GetAllMatchesCommand getMatches = new GetAllMatchesCommand();
        	List<Match> matches = getMatches.execute();
        	matchTable.setItems((ObservableList<Match>) matches);
        });
        Button button_matchFind = new Button("Show a specific row");
        //button_matchMatch.setOnAction(e -> window.setScene(setupMatchActionScene()));
        Button button_matchInsert = new Button("Insert a row");
        //button_matchMatch.setOnAction(e -> window.setScene(setupGameActionScene()));
        Button button_matchDelete = new Button("Delete a row");
        //button_matchMatch.setOnAction(e -> window.setScene(setupUserActionScene()));
        Button button_matchEdit = new Button("Edit a row");
        //button_matchMatch.setOnAction(e -> window.setScene(setupUserActionScene()));
        Button button_matchBack = new Button("Back");
        button_matchBack.setOnAction(e -> window.setScene(setupChoiceScene(isAdmin)));

        HBox element_matchHoriz1 = new HBox(10);
        //element_matchHoriz1.setPadding(new Insets(10, 10, 10, 10));
        element_matchHoriz1.getChildren().addAll(button_matchFindAll, button_matchFind);
        
        HBox element_matchHoriz2 = new HBox(10);
        //element_matchHoriz2.setPadding(new Insets(10, 10, 10, 10));
        if(isAdmin == 1)
        	element_matchHoriz2.getChildren().addAll(button_matchInsert, button_matchDelete, button_matchEdit);
        
        VBox element_matchVert = new VBox(10);
        element_matchVert.setPadding(new Insets(10, 10, 10, 10));
        element_matchVert.getChildren().addAll(label_match, element_matchHoriz1, element_matchHoriz2, matchTable, button_matchBack);

        StackPane layout_match = new StackPane();
        layout_match.getChildren().addAll(element_matchVert);
        matchScene = new Scene(layout_match, 600, 400);
    	
    	return matchScene;
    }
}

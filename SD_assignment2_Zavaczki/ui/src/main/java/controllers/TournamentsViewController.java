package controllers;

import Commands.tournament.DeleteTournamentCommand;
import Commands.tournament.SortTournamentsByStatusCommand;
import daos.TournamentDao;
import daos.factory.DaoFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Tournament;

import java.io.IOException;
import java.util.List;

public class TournamentsViewController {
    @FXML
    public TableView<Tournament> tournamentTable;

    @FXML
    public Button getAllTButton;

    @FXML
    public Button createTButton;

    @FXML
    public Button updateTButton;

    @FXML
    public Button deleteTButton;

    @FXML
    public Button backButton;

    @FXML
    public Button selectAllButton;

    @FXML
    public Button selectFinishedButton;

    @FXML
    public Button selectOngoingButton;

    @FXML
    public Button selectUpcomingButton;

    @FXML
    public TextField searchField;

    @FXML
    public void initialize() {
        getAllT();
    }

    @FXML
    public void getAllT() {
        TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        ObservableList<Tournament> tournaments = FXCollections.observableArrayList(tournamentDao.findAll());
        System.out.println(tournaments.toString());
        //tournamentTable.setItems(tournaments);

        sortTable(tournaments);
    }

    @FXML
    public void createT() {
        Stage stage = (Stage) createTButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        Tournament tournament = tournamentTable.getSelectionModel().getSelectedItem();

        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/tournamentCreateView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateT() {
        Stage stage = (Stage) updateTButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        Tournament tournament = tournamentTable.getSelectionModel().getSelectedItem();

        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/tournamentUpdateView.fxml"));
            root = fxmlLoader.load();

            TournamentUpdateViewController tournamentUpdateViewController = fxmlLoader.getController();
            tournamentUpdateViewController.setTournamentToChange(tournament);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteT() {
        Tournament tournament = tournamentTable.getSelectionModel().getSelectedItem();
        DeleteTournamentCommand dtc = new DeleteTournamentCommand(tournament);
        dtc.execute();

        getAllT();
    }

    @FXML
    public void goBackToChoice() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            //if(user.getIsAdmin == 1)
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/choiceAdminView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void selectAll() {
        TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        ObservableList<Tournament> tournaments = FXCollections.observableArrayList(tournamentDao.findAll());

        tournamentTable.setItems(tournaments);
    }

    @FXML
    public void selectFinished() {
        TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        List<Tournament> tournaments = tournamentDao.findAll();

        SortTournamentsByStatusCommand byStatusCommand = new SortTournamentsByStatusCommand(tournaments, "finished");
        ObservableList<Tournament> tournaments1 = FXCollections.observableArrayList(byStatusCommand.execute());

        tournamentTable.setItems(tournaments1);
    }

    @FXML
    public void selectOngoing() {
        TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        List<Tournament> tournaments = tournamentDao.findAll();

        SortTournamentsByStatusCommand byStatusCommand = new SortTournamentsByStatusCommand(tournaments, "ongoing");
        ObservableList<Tournament> tournaments1 = FXCollections.observableArrayList(byStatusCommand.execute());

        tournamentTable.setItems(tournaments1);
    }

    @FXML
    public void selectUpcoming() {
        TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        List<Tournament> tournaments = tournamentDao.findAll();

        SortTournamentsByStatusCommand byStatusCommand = new SortTournamentsByStatusCommand(tournaments, "upcoming");
        ObservableList<Tournament> tournaments1 = FXCollections.observableArrayList(byStatusCommand.execute());

        tournamentTable.setItems(tournaments1);
    }

    @FXML
    private void sortTable(ObservableList<Tournament> tournaments) {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Tournament> filteredData = new FilteredList<>(tournaments, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tournament -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (tournament.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Tournament> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tournamentTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tournamentTable.setItems(sortedData);
    }
}

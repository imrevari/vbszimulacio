package hu.itqs.szimulacio;

import hu.itqs.szimulacio.domain.Couple;
import hu.itqs.szimulacio.domain.Husband;
import hu.itqs.szimulacio.domain.Match;
import hu.itqs.szimulacio.domain.Wife;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends Application {


    private Simulator simulator = Simulator.getInstance();


    @Override
    public void start(Stage primaryStage) throws Exception {


        Button button = new Button();
        button.setText("Meccs");
        button.prefHeight(34.0);
        button.prefWidth(71.0);
        button.setLayoutY(789.0);
        button.setLayoutX(93.0);

        Label twoTeams = new Label();
        twoTeams.setLayoutX(565.0);
        twoTeams.setLayoutY(47.0);
        twoTeams.prefHeight(43.0);
        twoTeams.prefWidth(139.0);

        Label avaregeBear = new Label();
        avaregeBear.setText("Átlagosan 0.0 sör");
        avaregeBear.setLayoutX(539.0);
        avaregeBear.setLayoutY(768.0);
        avaregeBear.prefHeight(43.0);
        avaregeBear.prefWidth(200.0);

        Label totalSpareTime = new Label();
        totalSpareTime.setText("Összesen 0 perc.");
        totalSpareTime.setLayoutX(925.0);
        totalSpareTime.setLayoutY(768.0);
        totalSpareTime.prefHeight(43.0);
        totalSpareTime.prefWidth(200.0);

        ListView couplesList = new ListView();
        couplesList.setLayoutX(93.0);
        couplesList.setLayoutY(222.0);
        couplesList.prefHeight(474.0);
        couplesList.prefWidth(252.0);

        List<Couple> couples = simulator.listAllCouples();
        List<String> stringCouples = couples.stream().map(item -> (item.getHusband().getName() + " - " + item.getWife().getName())).collect(Collectors.toList());
        ObservableList<String> coupleObsList = FXCollections.observableArrayList (stringCouples);
        couplesList.setItems(coupleObsList);

        ListView husbandsListView = new ListView();
        husbandsListView.setLayoutX(497.0);
        husbandsListView.setLayoutY(222.0);
        husbandsListView.prefHeight(474.0);
        husbandsListView.prefWidth(252.0);

        husbandsListView.setItems(showHusbands());


        ListView wivesListView = new ListView();
        wivesListView.setLayoutX(880.0);
        wivesListView.setLayoutY(222.0);
        wivesListView.prefHeight(474.0);
        wivesListView.prefWidth(252.0);

        wivesListView.setItems(showWives());

        Label coupleLable = new Label();
        coupleLable.setLayoutX(144.0);
        coupleLable.setLayoutY(139.0);
        coupleLable.prefHeight(43.0);
        coupleLable.prefWidth(163.0);
        coupleLable.setText("Házaspárok");

        Label husbLable = new Label();
        husbLable.setLayoutX(539.0);
        husbLable.setLayoutY(139.0);
        husbLable.prefHeight(43.0);
        husbLable.prefWidth(163.0);
        husbLable.setText("Férjek");

        Label wifeLable = new Label();
        wifeLable.setLayoutX(950.0);
        wifeLable.setLayoutY(139.0);
        wifeLable.prefHeight(43.0);
        wifeLable.prefWidth(163.0);
        wifeLable.setText("Feleségek");


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Match match = simulator.createAndSaveRandomMatch();

                twoTeams.setText(match.getTeam1().getName() + " - " + match.getTeam2().getName());
                simulator.addMatchToRandomCouples(match);

                husbandsListView.setItems(showHusbands());
                wivesListView.setItems(showWives());

                avaregeBear.setText("Átlagosan " + new DecimalFormat("##.##").format(simulator.avarageBearConsumed())  + " sör.");
                totalSpareTime.setText("Összesen " + simulator.totalSpareTime() + " perc.");
            }
        });


        AnchorPane root = new AnchorPane();
        root.prefHeight(899.0);
        root.prefWidth(1281.0);
        root.getChildren().add(button);
        root.getChildren().add(couplesList);
        root.getChildren().add(husbandsListView);
        root.getChildren().add(wivesListView);
        root.getChildren().add(coupleLable);
        root.getChildren().add(husbLable);
        root.getChildren().add(wifeLable);

        root.getChildren().add(twoTeams);
        root.getChildren().add(avaregeBear);
        root.getChildren().add(totalSpareTime);

        primaryStage.setTitle("Foci VB");
        primaryStage.setScene(new Scene(root, 1281, 899));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private ObservableList<String> showHusbands(){
        List<Husband> husbands = simulator.listAllHusbands();
        List<String> stringHusbands = husbands.stream().map(item -> (item.getName() + " " + item.getConsumedBear() + " sört ívott.")).collect(Collectors.toList());
        ObservableList<String> husbandObsList = FXCollections.observableArrayList (stringHusbands);
        return husbandObsList;
    }

    private ObservableList<String> showWives(){
        List<Wife> wives = simulator.listAllWives();
        List<String> stringWives = wives.stream().map(item -> (item.getName() + "szabadideje: " + item.getSpareTime() + " perc.")).collect(Collectors.toList());
        ObservableList<String> wivesObsList = FXCollections.observableArrayList (stringWives);
        return wivesObsList;
    }
}

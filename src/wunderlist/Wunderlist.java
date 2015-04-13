/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wunderlist;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author yashar
 */
public class Wunderlist extends Application {

    AnchorPane mainFrame;
    AnchorPane informationBoard;
    VBox middleBox;
    ListView<Entry> items;
    ObservableList<Entry> listOfItems;
    boolean informationBoardOpened = false;

    @Override
    public void start(Stage primaryStage) throws IOException {

        listOfItems = FXCollections.observableArrayList();
        Parent root = FXMLLoader.load(getClass().getResource("frame.fxml"));
        mainFrame = (AnchorPane) root.lookup("#mainFrame");
        mainFrame.setStyle("-fx-background-image: url('wunderlist/Images/Backgrounds/06.jpg'); -fx-background-repeat: stretch; -fx-background-position: center center");
        FlowPane flowPane = (FlowPane) mainFrame.lookup("#flowPane");
        middleBox = (VBox) flowPane.lookup("#middleBox");
        informationBoard = (AnchorPane) root.lookup("#informationBoard");
        items = (ListView<Entry>) root.lookup("#items");
        items.setItems(listOfItems);
        items.setCellFactory(lv -> new ListCell<Entry>() {

            private final ImageView imageView = new ImageView();

            @Override
            public void updateItem(Entry item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {

                    setText(null);
                    setGraphic(null);

                } else {
                    String text = item.getTitle();
                    setText(null);
                    AnchorPane ap = new AnchorPane();
                    ap.setPrefHeight(40);
                    CheckBox cb = new CheckBox("");
                    cb.setLayoutY(10);
                    cb.setLayoutX(10);
                    Label label = new Label(text);
                    label.setLayoutX(cb.getLayoutX() + 30);
                    label.setLayoutY(10);
                    Image image = new Image(getClass().getResourceAsStream("Images/AccPic.png"));
                    imageView.setImage(image);
                    imageView.setLayoutX(470);
                    imageView.setLayoutY(8);
                    ap.getChildren().addAll(cb, label);
                    setGraphic(ap);
                }
            }
        });
        items.setOnMouseClicked((MouseEvent event) -> {
            if (items.getSelectionModel().getSelectedItem() != null) {
                if (event.getClickCount() == 2) {
                    final Timeline timeline1 = new Timeline();
                    timeline1.getKeyFrames().addAll(new KeyFrame(new Duration(200), new KeyValue(informationBoard.translateXProperty(), informationBoardOpened ? 0 : -380)));
                    timeline1.play();
                    //final Timeline timeline2 = new Timeline();
                    //timeline2.getKeyFrames().addAll(new KeyFrame(new Duration(200), new KeyValue(middleBox.translateXProperty(), informationBoardOpened ? 0 : -380)));
                    //timeline2.play();
                    final ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), middleBox);
                    scaleTransition.setByX(informationBoardOpened ? 0 : -380);
                    //scaleTransition.play();
                    informationBoardOpened = !informationBoardOpened;
                    System.out.println("clicked on " + items.getSelectionModel().getSelectedItem());
                }
            }

        });
        TextField addItemTextField = (TextField) middleBox.lookup("#addItemTextField");
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        addItemTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                mainFrame.requestFocus();
                firstTime.setValue(false);
            }
        });
        addItemTextField.setOnAction((ActionEvent event) -> {
            if (addItemTextField.getText().length() > 0) {
                Entry e = new Entry(addItemTextField.getText());
                listOfItems.add(0, e);
                addItemTextField.setText("");
            }

        });
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

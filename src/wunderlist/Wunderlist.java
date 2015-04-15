/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wunderlist;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author yashar
 */
public class Wunderlist extends Application {

    AnchorPane mainFrame;
    AnchorPane informationBoard;
    Text informationBoardText;
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
        informationBoardText = (Text) informationBoard.lookup("#informationBoardText");
        items = (ListView<Entry>) root.lookup("#items");
        items.setItems(listOfItems);
        items.setCellFactory(new Callback<ListView<Entry>, ListCell<Entry>>() {

            public ListCell<Entry> call(ListView<Entry> lv) {
                return new ListCell<Entry>() {

                    private final ImageView imageView = new ImageView();

                    @Override
                    public void updateItem(Entry item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setText(null);
                            setGraphic(null);

                        } else {
                            String text = item.title.get();
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
                };
            }
        });
        items.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Entry>() {

            @Override
            public void changed(ObservableValue<? extends Entry> observable, Entry oldValue, Entry newValue) {
                if (items.getSelectionModel().getSelectedIndex() != -1) {
                    if (oldValue != null) {
                        informationBoardText.textProperty().unbindBidirectional(oldValue.title);
                    }
                    informationBoardText.setText(newValue.title.get());
                    informationBoardText.textProperty().bindBidirectional(newValue.title);
                }

            }
        });
        items.setOnMouseClicked((MouseEvent event) -> {
            if (items.getSelectionModel().getSelectedItem() != null) {
                if (event.getClickCount() == 2) {
                    final Timeline timeline = new Timeline();
                    timeline.setCycleCount(200);
                    KeyFrame kf = new KeyFrame(Duration.millis(1), (ActionEvent event1) -> {
                        if (!informationBoardOpened) {
                            informationBoard.setLayoutX(informationBoard.getLayoutX() - 1.9);
                            middleBox.setPrefWidth(middleBox.getPrefWidth() - 1.9);
                        } else {
                            informationBoard.setLayoutX(informationBoard.getLayoutX() + 1.9);
                            middleBox.setPrefWidth(middleBox.getPrefWidth() + 1.9);
                        }
                    });
                    timeline.getKeyFrames().add(kf);
                    timeline.play();
                    timeline.setOnFinished((ActionEvent event1) -> {
                        informationBoardOpened = !informationBoardOpened;
                    });
                    System.out.println("clicked on " + items.getSelectionModel().getSelectedItem());
                }
            }

        });
        TextField addItemTextField = (TextField) middleBox.lookup("#addItemTextField");
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

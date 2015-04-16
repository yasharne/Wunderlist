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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    TextField informationBoardText;
    CheckBox informationBoardDone;
    TextArea informationBoardNote;
    ImageView informationBoardFavorite;
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
        informationBoardText = (TextField) informationBoard.lookup("#informationBoardText");
        informationBoardDone = (CheckBox) informationBoard.lookup("#informationBoardDone");
        informationBoardNote = (TextArea) informationBoard.lookup("#informationBoardNote");
        informationBoardFavorite = (ImageView) informationBoard.lookup("#informationBoardFavorite");
        items = (ListView<Entry>) root.lookup("#items");
        items.setItems(listOfItems);
        items.setCellFactory(new Callback<ListView<Entry>, ListCell<Entry>>() {

            @Override
            public ListCell<Entry> call(ListView<Entry> lv) {
                return new ListCell<Entry>() {

                    @Override
                    public void updateItem(Entry item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setText(null);
                            setGraphic(null);

                        } else {
                            ImageView iv = new ImageView(new Image(getClass().getResourceAsStream("Images/favorite" + (item.favorite.get() ? "1" : "0") + ".png")));
                            iv.setOnMouseClicked((MouseEvent event) -> {
                                items.getSelectionModel().selectedItemProperty().get().favorite.set(!items.getSelectionModel().selectedItemProperty().get().favorite.get());
                                iv.setImage(new Image(getClass().getResourceAsStream("Images/favorite" + (item.favorite.get() ? "1" : "0") + ".png")));
                                //informationBoardFavorite.setImage(iv.getImage());
                                //iv.imageProperty().bindBidirectional(new Image(getClass().getResourceAsStream("Images/favorite"+(item.favorite.get()?"1":"0")+".png")));
                                event.consume();
                            });
                            iv.setFitHeight(25);
                            iv.setFitWidth(25);
                            String text = item.title.get();
                            setText(null);
                            AnchorPane ap = new AnchorPane();
                            ap.setPrefHeight(40);
                            CheckBox cb = new CheckBox("");
                            cb.setLayoutY(10);
                            cb.setLayoutX(10);
                            cb.selectedProperty().bindBidirectional(item.done);
                            Label label = new Label(text);
                            label.setLayoutX(cb.getLayoutX() + 30);
                            label.setLayoutY(10);
                            label.textProperty().bindBidirectional(item.title);
                            ap.getChildren().addAll(cb, label);
                            BorderPane borderPane = new BorderPane(null, null, iv, null, ap);
                            setGraphic(borderPane);
                        }
                    }
                };
            }
        });
        items.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Entry> observable, Entry oldValue, Entry newValue) -> {
            if (items.getSelectionModel().getSelectedIndex() != -1) {
                if (oldValue != null) {
                    informationBoardText.textProperty().unbindBidirectional(oldValue.title);
                    informationBoardDone.selectedProperty().unbindBidirectional(oldValue.done);
                    informationBoardNote.textProperty().unbindBidirectional(oldValue.note);
                    informationBoardFavorite.imageProperty().unbindBidirectional(oldValue.favoriteImage);
                }
                informationBoardText.setText(newValue.title.get());
                informationBoardText.textProperty().bindBidirectional(newValue.title);
                informationBoardText.textProperty().addListener((ObservableValue<? extends String> observable1, String oldValue1, String newValue1) -> {
                    items.getSelectionModel().selectedItemProperty().get().title.set(newValue1);
                });
                informationBoardDone.setSelected(newValue.done.get());
                informationBoardDone.selectedProperty().bindBidirectional(newValue.done);
                informationBoardNote.setText(newValue.note.get());
                informationBoardNote.textProperty().bindBidirectional(newValue.note);
                informationBoardNote.textProperty().addListener((ObservableValue<? extends String> observable1, String oldValue1, String newValue1) -> {
                    items.getSelectionModel().selectedItemProperty().get().note.set(newValue1);
                });
                //----------------set suitable image on favorite image
                newValue.favoriteImage.set(new Image(getClass().getResourceAsStream("Images/favorite" + (newValue.favorite.get() ? "1" : "0") + ".png")));
                informationBoardFavorite.imageProperty().bindBidirectional(newValue.favoriteImage);
                //System.out.println(newValue.favoriteImage);
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

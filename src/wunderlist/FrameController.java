/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wunderlist;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author yashar
 */
public class FrameController implements Initializable {

    private Wunderlist wunderlist;
    @FXML
    private AnchorPane mainFrame;
    @FXML
    private FlowPane flowPane;
    @FXML
    private VBox middleBox;
    @FXML
    private TextField addItemTextField;
    @FXML
    private ListView<?> items;
    @FXML
    private AnchorPane informationBoard;
    @FXML
    private CheckBox informationBoardDone;
    @FXML
    private TextField informationBoardText;
    @FXML
    private ImageView informationBoardFavorite;
    @FXML
    private DatePicker informationBoardDueDate;
    @FXML
    private DatePicker informationBoardReminMe;
    @FXML
    private TextArea informationBoardNote;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setMainApp(Wunderlist w) {
        this.wunderlist = w;
    }

    @FXML
    private void handleNew() {
        wunderlist.listOfItems.clear();
        wunderlist.setEntryFilePath(null);
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(wunderlist.getPrimaryStage());

        if (file != null) {
            wunderlist.loadEntryFromFile(file);
        }
    }

    @FXML
    private void handleSave() {
        File personFile = wunderlist.getEntryFilePath();
        if (personFile != null) {
            wunderlist.saveEntryToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(wunderlist.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            wunderlist.saveEntryToFile(file);
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wunderlist;

import java.util.Calendar;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author yashar
 */
public class Entry {

    public StringProperty title;
    public StringProperty note;
    public BooleanProperty done;
    public BooleanProperty favorite;
    public ObjectProperty<javafx.scene.image.Image> favoriteImage;
    private Calendar createTime;
    private Calendar dueDate;

    public Entry(String title) {
        this.title = new SimpleStringProperty(title);
        this.note = new SimpleStringProperty("");
        createTime = Calendar.getInstance();
        this.done = new SimpleBooleanProperty(false);
        this.favorite = new SimpleBooleanProperty(false);
        //this.favoriteImage = new SimpleObjectProperty<>();
this.favoriteImage = new SimpleObjectProperty<>(new Image(getClass().getResourceAsStream("Images/favorite"+(this.favorite.get()?"1":"0")+".png")));
        //System.out.println(createTime.getTime());
        //System.out.println(title);
    }

    public BooleanProperty getDone() {
        return this.done;
    }

    public void setDone(BooleanProperty done) {
        this.done = done;
    }

    public String getDuDate() {

        if (dueDate != null) {
            return this.dueDate.getTime().toString();
        }
        return "";
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

}

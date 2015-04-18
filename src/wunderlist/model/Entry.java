/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wunderlist.model;

import java.time.LocalDate;
import java.util.Calendar;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import wunderlist.util.LocalDateAdapter;

/**
 *
 * @author yashar
 */
public class Entry {

    private StringProperty title;
    private StringProperty note;
    private BooleanProperty done;
    private BooleanProperty favorite;
    //private ObjectProperty<Image> favoriteImage;
    private ObjectProperty<LocalDate> createTime;
    private Calendar dueDate;

    public Entry(){
        this("");
    }
    
    public Entry(String title) {
        this.title = new SimpleStringProperty(title);
        this.note = new SimpleStringProperty("");
        this.createTime = new SimpleObjectProperty<>(LocalDate.now());
        //createTime = Calendar.getInstance();
        this.done = new SimpleBooleanProperty(false);
        this.favorite = new SimpleBooleanProperty(false);
        //this.favoriteImage = new SimpleObjectProperty<>();
        //this.favoriteImage = new SimpleObjectProperty<Image>(new Image(getClass().getResourceAsStream("Images/favorite" + (this.favorite.get() ? "1" : "0") + ".png")));
    }

    public String getTitle() {
        return this.title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty title() {
        return this.title;
    }

    //---------------------------------------------
    public String getNote() {
        return this.note.get();
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public StringProperty note() {
        return this.note;
    }

    //--------------------------------------------
    public boolean getDone() {
        return this.done.get();
    }

    public void setDone(boolean done) {
        this.done.set(done);
    }

    public BooleanProperty done() {
        return this.done;
    }

    //------------------------------------------
    public boolean getFavorite() {
        return this.favorite.get();
    }

    public void setFavorite(boolean favorite) {
        this.favorite.set(favorite);
    }

    public BooleanProperty favorite() {
        return this.favorite;
    }

    //--------------------------------------------
    /*   public Image getFavoriteImage() {
    return this.favoriteImage.get();
    }
    
    public void setFavoriteImage(Image favoriteImage) {
    this.favoriteImage.set(favoriteImage);
    }*/


    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getCreateTime() {
        return createTime.get();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wunderlist.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author yashar
 */
public class Category {

    private StringProperty title;
    private ObservableList<Entry> listOfItems;
    private List<Entry> convertedListOfItems;

    public Category() {

        this("");

    }

    public Category(String title) {
        this.title = new SimpleStringProperty(title);
        listOfItems = FXCollections.observableArrayList();

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

    public StringProperty getSize() {
        return new SimpleStringProperty(this.listOfItems.size() + "");
    }
    /*
     public ObjectProperty list(){
     convertedListOfItems = new SimpleObjectProperty<>();
     List<Entry> temp = new ArrayList<>();
     for (Entry listOfItem : listOfItems) {
     temp.add(listOfItem);
     }
     convertedListOfItems.set(temp);
     return convertedListOfItems;
     }
    
     //@XmlJavaTypeAdapter(EntryAdapter.class)*/

    public ObservableList<Entry> getList() {
        return this.listOfItems;
    }

    public List<Entry> get() {
        convertedListOfItems = new ArrayList<>();
        for (Entry listOfItem : listOfItems) {
            convertedListOfItems.add(listOfItem);
        }
        return convertedListOfItems;
    }

    public void set(List<Entry> entries) {
        this.listOfItems.clear();
        for (Entry entrie : entries) {
            this.listOfItems.add(entrie);
        }
    }

    public void add(Entry entry) {
        this.listOfItems.add(0, entry);
    }

}

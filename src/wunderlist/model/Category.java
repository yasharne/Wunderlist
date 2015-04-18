/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wunderlist.model;

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
    public ObservableList<Entry> listOfItems;
    
    public Category(){
        
        this("");
        
    }
    
    public Category(String title){
        this.title = new SimpleStringProperty(title);
        listOfItems = FXCollections.observableArrayList();
    }
    
    public String getTitle(){
        return this.title.get();
    }
    
    public void setTitle(String title){
        this.title.set(title);
    }
    
    public StringProperty title(){
        return this.title;
    }
    
    
    
}

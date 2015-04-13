/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wunderlist;

import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author yashar
 */
public class Entry {

    private String title;
    private Calendar createTime;
    private Calendar dueDate;

    public Entry(String title) {
        this.title = title;
        createTime = Calendar.getInstance();
        System.out.println(createTime.getTime());
        System.out.println(title);
    }

    public String getTitle() {
        return this.title;
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

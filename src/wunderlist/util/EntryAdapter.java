/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wunderlist.util;

import java.util.ArrayList;
import wunderlist.model.Entry;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author yashar
 */
public class EntryAdapter extends XmlAdapter<List<Entry>, ObservableList<Entry>> {

    @Override
    public ObservableList<Entry> unmarshal(List<Entry> v) throws Exception {
        return FXCollections.observableArrayList(v);
    }

    @Override
    public List<Entry> marshal(ObservableList<Entry> v) throws Exception {
        return new ArrayList<Entry>(v);
    }

}

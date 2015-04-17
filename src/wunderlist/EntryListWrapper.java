/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wunderlist;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yashar
 */
@XmlRootElement(name = "entries")
public class EntryListWrapper {
    
    private List<Entry> entries;
    
    @XmlElement(name = "entry")
    public List<Entry> getEntries(){
        return entries;
    }
    
    public void setEntries(List<Entry> entries){
        this.entries = entries;
    }
    
}

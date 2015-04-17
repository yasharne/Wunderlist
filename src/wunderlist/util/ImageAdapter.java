/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wunderlist.util;
import javafx.scene.image.Image;
import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 *
 * @author yashar
 */
public class ImageAdapter extends XmlAdapter<String, Image>{

    @Override
    public Image unmarshal(String v) throws Exception {
        return null;
    }

    @Override
    public String marshal(Image v) throws Exception {
        return v.toString();
    }
    
}

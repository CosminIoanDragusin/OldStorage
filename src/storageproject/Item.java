/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageproject;

import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user
 */
public class Item {
     private final StringProperty id;
    private final StringProperty name;
    private final StringProperty mobile;
    private final StringProperty  course;
    
    public Item()
    {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        mobile = new SimpleStringProperty(this, "mobile");
        course = new SimpleStringProperty(this, "course");
    }
 
    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }
 
    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String newName) { name.set(newName); }
 
    public StringProperty mobileProperty() { return mobile; }
    public String getMobile() { return mobile.get(); }
    public void setMobile(String newMobile) { mobile.set(newMobile); }
    
    public StringProperty courseProperty() { return course; }
    public String getCourse() { return course.get(); }
    public void setCourse(String newCourse) { course.set(newCourse); 
}

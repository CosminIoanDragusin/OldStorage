/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageproject;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {
    
    Connection connection;
    PreparedStatement pst;
    int myIndex;
    int id;
    
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> columnAction;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnMeasUnit;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnPriceUnit;

    @FXML
    private TableColumn<?, ?> columnQuantity;

    @FXML
    private Label label;

    @FXML
    private TextField labelId;

    @FXML
    private TextField labelMeasureUnit;

    @FXML
    private TextField labelName;

    @FXML
    private TextField labelPriceUnit;

    @FXML
    private TextField labelQuantity;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<?> tableStorage;
    
    @FXML
    private FontAwesomeIconView closeIcon;

    @FXML
    void add(ActionEvent event) {

      
            try
        {
            pst = connection.prepareStatement("insert into storagedb(id,name,unit,price_unit,quantity)values(?,?,?,?,?)");
            pst.setString(1,labelId.getText());
            pst.setString(2,labelName.getText());
            pst.setString(3,labelMeasureUnit.getText());
            pst.setString(4,labelPriceUnit.getText());
            pst.setString(5,labelQuantity.getText());
                 //pst.setString(6,textAction.getText());
            pst.executeUpdate();
          
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Items Added");
 
      alert.setHeaderText("Items Added");
      alert.setContentText("Record Addedddd!");
 
      alert.showAndWait();
 
      upDateDB();
      
       labelId.setText("");
       labelId.requestFocus();
       labelName.setText("");
       labelMeasureUnit.setText("");
       labelPriceUnit.setText("");
       labelQuantity.setText("");
       //labelAction.setText("");
            
            }
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void refresh(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }
    
    
    
    public void Connect()
    {
    try
    {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/storagedb","root","P4r0l4m34mysql");
    } catch (ClassNotFoundException ex)
    {
    
    }   catch (SQLException ex) 
    {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    
    
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
    }    

    private void upDateDB() {
        Connect();
          ObservableList<Item> students = FXCollections.observableArrayList();
       try
       {
           pst = connection.prepareStatement("select * from storagedb");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            Item itemFromStorage = new Item();
            itemFromStorage.setId(rs.getString("id"));
            itemFromStorage.setName(rs.getString("name"));
            itemFromStorage.setMobile(rs.getString("mobile"));
            itemFromStorage.setCourse(rs.getString("course"));
            students.add(itemFromStorage);
       }
    }
                tableStorage.setItems(students);
                labelId.setCellValueFactory(f -> f.getValue().idProperty());
                NameColmn.setCellValueFactory(f -> f.getValue().nameProperty());
                MobileColmn.setCellValueFactory(f -> f.getValue().mobileProperty());
                CourseColmn.setCellValueFactory(f -> f.getValue().courseProperty());
                
              
 
       }
      
       catch (SQLException ex)
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
 
                tableStorage.setRowFactory( tv -> {
     TableRow<Student> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
            myIndex =  table.getSelectionModel().getSelectedIndex();
           id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
           txtName.setText(table.getItems().get(myIndex).getName());
           txtMobile.setText(table.getItems().get(myIndex).getMobile());
                            txtCourse.setText(table.getItems().get(myIndex).getCourse());
                          
                        
                          
        }
     });
        return myRow;
                   });
    
    }
    
}

package view;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

// interface 

public class viewController implements Initializable{

    @FXML private TextField Title;
    @FXML private AnchorPane background;
    @FXML private Button btnADD;
    @FXML private Button btnDEL;
    @FXML private Button btnUPDATE;
    @FXML private TableColumn<Order,String> colAdd;
    @FXML private TableColumn<Order,String> colContact;
    @FXML private TableColumn<Order,Integer> colID;
    @FXML private TableColumn<Order,String> colName;
    @FXML private TableColumn<Order,String> colOrder;
    @FXML private TableColumn<Order,String> colRelease;
    @FXML private TableView<Order>table1;
    @FXML private TextField titleADD;
    @FXML private TextField titleCN;
    @FXML private TextField titleID;
    @FXML private TextField titleName;
    @FXML private TextField titleOrder;
    @FXML private TextField titleRelease;
    @FXML private VBox vb1;




    // ActionEvent for button Action

    @FXML void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnADD)InsertRecord();
        else if(event.getSource() == btnUPDATE)UpdateRecord();
        else if(event.getSource() == btnDEL)DeleteRecord();
    }





    




    // Get jdbc Connection


    public Connection getConnection(){
        Connection connection;
        try{
            System.out.println("----------------Connection Established-------------");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS","root","");
            return connection;
        }catch(Exception ex){
            System.out.println("Error :  "+ex.getMessage());
            return null;
        }
    }



    // Get order list by select query

    public ObservableList<Order>getOrderlist(){
        ObservableList<Order>orderlist = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM `Order`";
        java.sql.Statement st;
        ResultSet rs;

        try{
            st = connection.createStatement();
            rs = st.executeQuery(query); 
            Order order;
            while(rs.next()){
                order = new Order(rs.getInt("id"),rs.getString("name"), rs.getString("contact_no"),
                                    rs.getString("Address"),rs.getString("order_date"),rs.getString("release_date"));
                orderlist.add(order);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return orderlist;
    }



    // Presenting the order list in UI



    public void showOrder(){
        System.out.println("..............Data is showed ................");
        ObservableList<Order>list = getOrderlist();

        colID      .setCellValueFactory(new PropertyValueFactory<Order,Integer>           ("id"));
        colName    .setCellValueFactory(new PropertyValueFactory<Order, String>         ("name"));
        colAdd     .setCellValueFactory(new PropertyValueFactory<Order, String>      ("Address"));
        colContact .setCellValueFactory(new PropertyValueFactory<Order, String>   ("contact_no"));
        colOrder   .setCellValueFactory(new PropertyValueFactory<Order, String>   ("order_date"));
        colRelease .setCellValueFactory(new PropertyValueFactory<Order, String> ("release_date"));
        
        table1.setItems(list);
    }








    // Query executer in database

    private void executeQuery(String query){
        Connection connection = getConnection();
        java.sql.Statement st;
        try{
            System.out.println("Query is Executed");
            st = connection.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }








    // Insert records



    private void InsertRecord(){
        String query = "INSERT INTO `Order`(`id`, `name`, `contact_no`, `Address`, `order_date`, `release_date`) VALUES(" 
                        + titleID.getText() + ",'" + titleName.getText() + "','" 
                        + titleCN.getText() + "','" + titleADD.getText() + "','" 
                        + titleOrder.getText() + "','" + titleRelease.getText() + "')";
        executeQuery(query);
        showOrder();
    }






    // Update Records

 
    private void UpdateRecord(){
        String query = "UPDATE `Order` SET `name` = '" 
                        + titleName.getText() + "',`contact_no` = '" + titleCN.getText() + "',`Address` = '" + titleADD.getText() 
                        + "',`order_date` = '" + titleOrder.getText() + "',`release_date` = '" + titleRelease.getText() 
                        + "' WHERE id = " + titleID.getText();
        executeQuery(query);
        showOrder();
    }




    // Delete records



    private void DeleteRecord(){
        String query = "DELETE FROM `Order` WHERE `id` = " + titleID.getText();
        executeQuery(query);
        showOrder();
    }





    // Implementing abstract method



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        showOrder(); 
    }






}

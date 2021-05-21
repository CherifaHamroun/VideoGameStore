package com.company.gui;

import com.company.Client;
import com.company.QueryProcessor;
import com.company.StockItem;
import com.company.TransactionProcessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemController implements Initializable {

    QueryProcessor qp = new QueryProcessor();
    TransactionProcessor tp = new TransactionProcessor();

    @FXML
    private TextField param_found_input;

    @FXML
    private TextField title_input;

    @FXML
    private TextField price_input;

    @FXML
    private TextField type_input;

    @FXML
    private TextField param_input;

    @FXML
    private TableView<StockItem> Items;

    @FXML
    private TableColumn<StockItem, Integer> item_id;

    @FXML
    private TableColumn<StockItem, String> item_title;

    @FXML
    private TableColumn<StockItem, Float> item_price;

    @FXML
    private TableColumn<StockItem, String> item_type;

    @FXML
    private TableColumn<StockItem, String> item_parametre;

    @FXML
    private TableColumn<StockItem, String> is_out;

    @FXML
    private TextField customer_check_name;

    @FXML
    private TextField item_check_title;

    @FXML
    private DatePicker due_date;

    public void overDueItems() throws Exception{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("overdueItemsList.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.tp.AddStock("Titanic",1000,"Netflix",1);
        System.out.println("test");
        System.out.println(this.tp.getStockList().size());
        item_id.setCellValueFactory(new PropertyValueFactory<StockItem,Integer>("itemID"));
        item_title.setCellValueFactory(new PropertyValueFactory<StockItem,String>("title"));
        item_price.setCellValueFactory(new PropertyValueFactory<StockItem,Float>("rentalPrice"));
        List<StockItem> values = new ArrayList<StockItem>(tp.getStockList().values());
        ObservableList<StockItem> items_list = FXCollections.observableArrayList(values);
        Items.setItems(items_list);
    }
}

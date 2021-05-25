package com.company.gui;

import com.company.Film;
import com.company.Jeux;
import com.company.StockItem;
import com.company.pipeandfilter.FilterTransactionProcessor;
import com.company.pipeandfilter.Snippet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class films_gamesController implements Initializable,Gestionnaire {
        public class Data {
            public Integer ID;
            public String title;
            public Float price;
            public String Type;
            public String param;
            public String isCheckedOut;
            public String getIsCheckedOut() {
                return isCheckedOut;
            }

            public void setIsCheckedOut(String isCheckedOut) {
                this.isCheckedOut = isCheckedOut;
            }

            public Integer getID() {
                return ID;
            }

            public void setID(Integer ID) {
                this.ID = ID;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Float getPrice() {
                return price;
            }

            public void setPrice(Float price) {
                this.price = price;
            }

            public String getType() {
                return Type;
            }

            public void setType(String type) {
                Type = type;
            }

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }
        }
    @FXML
    private TableView<Data> Items;

    @FXML
    private TableColumn<Data, Integer> item_id;

    @FXML
    private TableColumn<Data, String > item_title;

    @FXML
    private TableColumn<Data, Float> item_price;

    @FXML
    private TableColumn<Data, String > item_type;

    @FXML
    private TableColumn<Data, String> item_param;

    @FXML
    private TableColumn<Data, String> is_out;
    public void OnActionGoBack(ActionEvent e) throws IOException {
        changeSceneButtonPushed(e,"Menu.fxml");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[] dataSent = Snippet.p3.dataOUT().split(" ");

        List<StockItem> values = new ArrayList<StockItem>();
        for (int i=1;i<dataSent.length;i++){
            StockItem stk = FilterTransactionProcessor.tran.getStockList().get(dataSent[i]);
            values.add(stk);
            System.out.println(dataSent[i]);
        }
        item_id.setCellValueFactory(new PropertyValueFactory<Data,Integer>("ID"));
        item_title.setCellValueFactory(new PropertyValueFactory<Data,String>("title"));
        item_price.setCellValueFactory(new PropertyValueFactory<Data,Float>("price"));
        item_type.setCellValueFactory(new PropertyValueFactory<Data,String>("Type"));
        item_param.setCellValueFactory(new PropertyValueFactory<Data,String>("param"));
        is_out.setCellValueFactory(new PropertyValueFactory<Data,String>("isCheckedOut"));
        List<Data> l = new ArrayList<>();
        for (int i=0;i<values.size();i++){
            Data data = new Data();
            int id = values.get(i).getItemID();
            data.setID(id);
            data.setTitle(values.get(i).getTitle());
            data.setPrice(values.get(i).getRentalPrice());
            String t = values.get(i).getClass().getName().replace("com.company.","");
            data.setType(t);
            if (data.getType().equals("Film")) {
                Film f = (Film)values.get(i);
                data.setParam(f.getActor());
            }
            else {
                if (data.getType().equals("Jeux")) {
                    Jeux j = (Jeux) values.get(i);
                    data.setParam(j.getPlatform());
                }
            }
            Snippet.p1.dataIN("IsCheckedOut"+" "+ data.getTitle());
            String isC = Snippet.p3.dataOUT();
            if (isC == "1") isC = "Yes";
            else isC = "No";
            data.setIsCheckedOut(isC);
            l.add(data);
        }
        ObservableList<Data> items_list = FXCollections.observableArrayList(l);
        Items.setItems(items_list);

    }

}

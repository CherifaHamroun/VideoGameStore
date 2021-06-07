package com.company.presentation;

import com.company.metier.*;
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

public class ActorController implements Initializable,Gestionnaire {
    public class Data {
        public Integer ID;
        public String title;
        public Double price;
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

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
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
    private TableColumn<Data, String> item_title;

    @FXML
    private TableColumn<Data, Float> item_price;

    @FXML
    private TableColumn<Data, String> item_type;

    @FXML
    private TableColumn<Data, String> item_param;

    @FXML
    private TableColumn<Data, String> is_out;

    public void OnActionGoBack(ActionEvent e) throws IOException {

        changeSceneButtonPushed(e, "/itemList.fxml");
    }
    IQueryMetier qm=new QueryMetier();
    public static String dataSent = "";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        item_id.setCellValueFactory(new PropertyValueFactory<Data, Integer>("ID"));
        item_title.setCellValueFactory(new PropertyValueFactory<Data, String>("title"));
        item_price.setCellValueFactory(new PropertyValueFactory<Data, Float>("price"));
        item_type.setCellValueFactory(new PropertyValueFactory<Data, String>("Type"));
        item_param.setCellValueFactory(new PropertyValueFactory<Data, String>("param"));
        is_out.setCellValueFactory(new PropertyValueFactory<Data, String>("isCheckedOut"));
        List<Integer> values = new ArrayList<Integer>(qm.findByActor(dataSent));
        List<Data> l = new ArrayList<>();
        for (int i=0;i<values.size();i++){
            Data data = new Data();
            StockItemEntity stk = qm.findItemById(values.get(i));
            data.setID(stk.getItemId());
            data.setTitle(stk.getTitle());
            data.setPrice(stk.getRentalPrice());
            String[] s= qm.getTypeParam(stk.getItemId()).split(";");
            data.setParam(s[1]);
            data.setType(s[0]);
            String isC = qm.isCheckedOut(stk.getItemId());
            data.setIsCheckedOut(isC);
            l.add(data);
        }
        ObservableList<Data> items_list = FXCollections.observableArrayList(l);
        Items.setItems(items_list);
    }
}

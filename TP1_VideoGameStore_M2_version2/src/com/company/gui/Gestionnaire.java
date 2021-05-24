package com.company.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface Gestionnaire {
    default void changeSceneButtonPushed(ActionEvent event, String lien) throws IOException {
        Parent tableViewParent= FXMLLoader.load(getClass().getResource(lien));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    }

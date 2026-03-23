package com.example.addressbook;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class HelloController {

    @FXML
    private CheckBox agreeCheckBox;

    @FXML
    private Button nextButton;

    @FXML
    private TextArea termsAndConditions;

    @FXML
    public void initialize(){
        termsAndConditions.setText("""
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut 
          labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
          exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit
          esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui 
         officia deserunt mollit anim id est laborum. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi.
         Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris.
                """);
    }

    @FXML
    protected void onAgreeCheckBoxClick(){
        boolean accepted = agreeCheckBox.isSelected();
        nextButton.setDisable(!accepted);
    }

    @FXML
    protected void onNextButtonClick() throws IOException {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onCancelButtonClick(){
        Stage stage = (Stage) nextButton.getScene().getWindow();
        stage.close();
    }

}

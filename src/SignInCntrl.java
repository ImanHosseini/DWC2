/**
 * Created by ImanH on 12/2/2017.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInCntrl {
    GUIHandler guih;

    @FXML
    private TextField Pass;

    @FXML
    private TextField UserName;

    @FXML
    private Button LogInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    protected void handleLogInButtonAction(ActionEvent event) throws Exception {
        Stage owner=(Stage)LogInButton.getScene().getWindow();
        Parent root;
        if(UserName.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your username");

        }else{
            System.out.println(guih);
         //  guih.ml.netQueue="SIGNIN"+UserName.getText()+" "+Pass.getText();
            boolean rez = guih.ml.neth.login(UserName.getText(),Pass.getText());
            if( !rez ){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Wrong credentials or bad network.");
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hub2.fxml"));
            root = fxmlLoader.load();


            HubCntrl controller = fxmlLoader.getController();
            controller.guih=guih;
            guih.ml.hubcntrl=controller;
            Scene scene = new Scene(root);
            owner.setScene(scene);
            owner.show();

            System.out.println(controller);

            guih.ml.neth.mapReq();


         //   System.out.println(guih.ml.netQueue);
        }
    }
    @FXML
    protected void handleSignUpButtonAction(ActionEvent event) {
        System.out.println(guih.ml.netQueue);
    }
}
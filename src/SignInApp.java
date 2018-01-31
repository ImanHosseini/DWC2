/**
 * Created by ImanH on 12/2/2017.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignInApp extends Application {

    public static GUIHandler guih=null;

//    public SignInApp(GUIHandler guih){
//        this.guih=guih;
//    }

    public void launcher(GUIHandler guih) {
        SignInApp.guih=guih;
       Application.launch(SignInApp.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignIn2.fxml"));
        Parent root = fxmlLoader.load();
        SignInCntrl controller = fxmlLoader.<SignInCntrl>getController();
        controller.guih=guih;
        primaryStage.setTitle("Registration Form FXML Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);


        primaryStage.show();
        System.out.println(guih.ml.netQueue);

    }
    public void close(){
        Platform.exit();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
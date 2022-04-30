package Userpage;

import javafx.application.Application;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
//import java


public class User extends Application{
    String userpage = "Studentpage.fxml";
    @Override
    public void start(Stage stage) throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("/Userpage/Studentpage.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Student page");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){

        Application.launch(args);
    }

}

package Userpage;
import javafx.scene.control.TextField;
import org.json.*;

import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.nio.file.Paths;
import java.util.jar.JarException;

public class controller {
    String filename;
    @FXML
    private Button save_file;
    @FXML
   private TextField filename_textfield;
    @FXML
    void filename_function(){
        filename = filename_textfield.getText();
    }
    @FXML
    private ListView projects_list;

    ArrayList<String> list_items = new ArrayList<>();
    String projects_name_selected;
    Path path = Paths.get(System.getProperty("user.dir"));
    String project_path_ =  path.getParent().toString() + "/projects/";

    @FXML
    void list_projects(){
        //process to list the projects available

        try {
            String[] commands = {"ls", project_path_};
            boolean isWindows = System.getProperty("os.name")
                    .toLowerCase().startsWith("windows");
            ProcessBuilder processbuilder = new ProcessBuilder(commands);
            if (isWindows) {
                processbuilder.command("cmd.exe", "/c", "dir");
            } else {
                processbuilder.command("sh", "-c", "ls");
            }
            processbuilder.directory(new File(project_path_));
            Process process = processbuilder.start();

            StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            process.waitFor();

            System.out.println("Line 39 in list view\t"+streamGobbler.project_names.size());
            list_items= streamGobbler.project_names;
            for(String file: list_items){
                projects_list.getItems().add(file);
            }




        }catch(Exception e){
            System.out.println("Error message for list view\t"+ e.getMessage());
        }


        //get the selected item in list view to get the project path
        projects_name_selected = projects_list.getSelectionModel().getSelectedItem().toString();

//        System.out.println("project controller \t"+ project_name_selected);

    }

    @FXML
    private Button save_file1;

    @FXML
    void save_function(){
        String path =  projects_name_selected;

        String cwd = System.getProperty("user.dir");


        try{
            File javafile = new File(cwd + "/projects/" + path);
            //create the directory of the project in th local machine
            if(!javafile.isDirectory()) {
                javafile.mkdir();
            }
            // System.getProperty("user.dir");
            File file = new File(cwd + "/projects/"+ path+"/" +filename);
            PrintWriter writer = new PrintWriter(file);

            writer.write(solution_area.getText());
            writer.flush();
            writer.close();

        }catch(IOException e){
            System.out.println("Error writing code file \t"+ e.getMessage());
        }
    }
    @FXML
    private TextArea project_description;
    @FXML
    void add_description(){
        try {


            FileReader reader = new FileReader(project_path_ + projects_name_selected + "/project_details.json");
            JSONTokener tokener = new JSONTokener(reader);

            JSONObject object = new JSONObject(tokener);



            String project_description1 = object.getString("Description");

            project_description.setText(project_description1);
            project_name.setText(projects_name_selected);

        }catch(IOException e){
            System.out.println("project details does not exist \t"+ e.getMessage());
        }catch(JSONException e){
            System.out.println("project details does not exist \t"+ e.getMessage());
        }



    }

    @FXML
    private TextArea project_info;

    @FXML
    private Button run_button;

    @FXML
    private TextArea solution_area;

    @FXML
    void createnewpage(ActionEvent event) {
        try{
            Parent root= FXMLLoader.load(getClass().getResource("Studentpage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Student page");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
    }

//    @FXML
//    void list

    @FXML
    void mouse_exited_get_code(MouseEvent event) {
        //System.out.println("line 47 \t"+solution_area.getText() );
        Path path = Paths.get(System.getProperty("user.dir"));
        String project_path_ =  path.getParent().toString() + "/projects/";

        //accessing the project details

        //Parameter to the function
        String project_name = "";

        String path_pdetails =  project_path_ + "/"+ project_name +"/project_details";
        //System.out.println("Accessing the projects path\t"+project_path_);
    }
    @FXML
    private TextArea runcode_output;
    @FXML
    void run_code(ActionEvent event) {
        String path =  projects_name_selected;
        String filename = filename_textfield.getText();
        String cwd = System.getProperty("user.dir");
        String project_path_ = System.getProperty("user.dir") +"/projects/";

        System.out.println("project user page \t" + project_path_);
        ArrayList<String> output = new ArrayList<String>();
        //code to write to a java `file
        try{
            String file_path = project_path_+"/"+projects_name_selected+"/"+filename;
            File javafile = new File(project_path_ +"/"+ projects_name_selected);
            //create the directory of the project in th local machine
            if(!javafile.isDirectory()){
               javafile.mkdir();
            }


            // System.getProperty("user.dir");
            File file = new File(file_path);
            PrintWriter writer = new PrintWriter(file);

            writer.write(solution_area.getText());
            writer.flush();
            writer.close();

            //compiling the code

            System.out.println("line 206 executing code \t"+project_path_+projects_name_selected+"/"+filename);
//
            executecode exec1 = new executecode();


           output = exec1.compile(project_path_+projects_name_selected,filename);

        }catch(IOException e){
            System.out.println("Error writing code file" + e.getMessage() +  System.getProperty("user.dir"));
        }


        //diplaying the output in the user page
        for(String line:output){
            runcode_output.setText(line);
        }

        String isWindows = System.getProperty("os.name").toLowerCase();



    }

    @FXML
    private Label project_name;
    @FXML
    void project_name_func(){

    }
    @FXML
    void close_function(){


    }





}

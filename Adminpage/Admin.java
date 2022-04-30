import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

import javafx.geometry.Insets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Admin extends Application {
    Button upload_project;
    TextArea project_description;
    @Override
    public void start(Stage stage){
        stage.setTitle("Admin Page");
        //stage.setFullScreen(true);
        
        Label label = new Label("Project Description :");

        Font font = Font.font("verdana",FontWeight.BOLD,FontPosture.REGULAR,12);
        label.setFont(font);

        TextArea project_name = new TextArea();
        project_name.setPromptText("Project name");

        Button upload_files = new Button("Upload files");

        TextArea project_Id = new TextArea();
        project_Id.setPromptText("Project ID");

        TextArea no_question = new TextArea();
        no_question.setPromptText("Number of questions");

        project_description = new TextArea();
        project_description.setPromptText("Project Description :");
        
        CheckBox autoevaluation = new CheckBox("Auto-Evaluation");


        //Menu Items for the tech stack


        MenuItem tech1 = new MenuItem("Java");
        MenuItem tech2 = new MenuItem("DOT Net");

        MenuButton techstack = new MenuButton("Techstack",null,tech1,tech2);



//        MenuBar mb = new MenuBar();
//        mb.getMenus().add(techstack);

        TextArea marks = new TextArea();
        marks.setPromptText("marks");

        TextArea Department = new TextArea();
        Department.setPromptText("Department");

        DatePicker deadline = new DatePicker();

        TextArea deadline_time = new TextArea();

        deadline_time.setPromptText("hour: minute: second");
        upload_project = new Button("upload project");
        upload_project.setId("upload project");
        upload_project.setAlignment(Pos.CENTER);
      
       

        VBox vbox = new VBox();
      
        HBox hbox = new HBox();

        Label uploaded = new Label("Not yet uploaded"); 

        Button clear = new Button("clear");

        Label upload_status = new Label("First Upload Project to upload files");

        hbox.getChildren().addAll(upload_project,uploaded,clear);
        hbox.setSpacing(60);

        hbox.setPadding(new Insets(20,20,20,20));
        vbox.getChildren().addAll(
                    label,
                    project_name,
                    project_Id,
                    project_description,
                    no_question,
                    autoevaluation,
                    techstack,
                    Department,
                    marks,
                    deadline,
                    deadline_time,
                    hbox,
                    upload_status,
                    upload_files
                    );
        vbox.setSpacing(25);

        vbox.setPadding(new Insets(20,20,20,20));

        ScrollPane pane = new ScrollPane();
        pane.setContent(vbox);
        Scene scene = new Scene(pane,700,700);
       // scene.add(textarea);
        
        stage.setScene(scene);
        stage.show();


        //up_files u_files = new up_files();
        Label value_ = new Label();
        ;


        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
                String techstack_;
                public void handle(ActionEvent event) {
                    if (event.getSource() == upload_project) {
                        System.out.println("Hello uploaded has been clicked " + event.getSource());


                        System.out.println("line 133 inside if ");
                        String projectname = project_name.getText().replaceAll("\n", System.getProperty("line.separator"));
                        String path =  projectname;
                        String project_id = project_Id.getText().replaceAll("\n", System.getProperty("line.separator"));
                        String description = project_description.getText().replaceAll("\n", System.getProperty("line.separator"));
                        String no_questions =  no_question.getText().replaceAll("\n", System.getProperty("line.separator"));
                        String deadline_t = deadline_time.getText().replaceAll("\n", System.getProperty("line.separator"));
                        String Techstack = techstack_;

                        System.out.println("VALUE OF TECHSTACK 1\t"+ value_.getText());
                       // System.out.println("VALUE OF TECHSTACK 2\t"+tech2.isVisible());
                        Boolean auto_evaluation = autoevaluation.isSelected();


                        details det = new details(projectname,project_id,path,description,deadline_t,Techstack,no_questions,auto_evaluation);

                        project_details _details = new project_details(det);
    //
                        uploaded.setText("Project uploaded \t" + project_description.getText());
                    }
                    else if (event.getSource()== upload_files) {
                        Path path = Paths.get(System.getProperty("user.dir")).getParent();
                        String filepath =  path.toString();
                        System.out.println("This is Admin get parent of user.dir\t" + filepath);

                        //choosing the file to upload
                        FileChooser fileChooser = new FileChooser();
                        File selectedFile = fileChooser.showOpenDialog(stage);
                        System.out.println("File choose \t" + selectedFile.getName());
                        try {
                            if(selectedFile != null){
                                if(project_name.getText() !=""){
                                    System.out.println("Line 178 get project_name\t"+ project_name.getText());
                                    Path path_ = Paths.get(filepath+"/projects/"+project_name.getText()+"/"+selectedFile.getName());
                                    System.out.println("line 178 File path to upload\t" + path_.toAbsolutePath());
                                    Files.copy(selectedFile.toPath(),path_);
                                }else{
                                    upload_status.setText("First upload project");
                                }


                            }
                           // File project_f = new File( path.getParent() );
                        }catch(IOException e){
                            System.out.println("Error in writing selected file \t" +e.getMessage());
                        }
                    }else if(event.getSource() == clear){
                        project_Id.setText("");
                        project_name.setText("");
                        project_description.setText("");
                        no_question.setText("");
                        marks.setText("");
                        uploaded.setText("Not yet uploaded");
                        deadline_time.setText("");


                    }
                    else if(event.getSource() == tech1){
                            System.out.println("This is tech1 \t" + tech1.getText());
                            techstack_= tech1.getText();
                    }
                    else if(event.getSource() == tech2){
                        System.out.println("This is tech2 \t"+tech2.getText());
                            techstack_=tech2.getText();
                    }

                }
            };
            upload_project.setOnAction(event);
            clear.setOnAction(event);
            upload_files.setOnAction(event);
            tech1.setOnAction(event);
            tech2.setOnAction(event);
    }
    public static void main(String []args){
        Application.launch(args);
    }
}
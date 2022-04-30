import java.io.*;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class project_details {

        public project_details(details details){

            JSONObject p_details = new JSONObject();

            //creating a json file that will be stored in a local file
            //The JSON file will hold the metadata of the project(project details)
            try {
                p_details.put("Project_name", details.project_name);
                p_details.put("Project", details.path);
                p_details.put("Description", details.description);
                p_details.put("Deadline time ", details.deadline_t);
                p_details.put("TechStack", details.Techstack);
                p_details.put("Number of questions", details.no_questions);
                p_details.put("Auto evaluation", details.auto_evaluation);
            }catch(Exception e){
                System.out.println("Exception while adding details to JSON" + e.getMessage());
            }
            try{

                //add the project details file to project sub folder
                Path path = Paths.get(System.getProperty("user.dir"));
                String project_path =  path.getParent().toString() +"/projects/";
                System.out.println("Project  path line 27 \t"+  project_path);


                System.out.println("line 28 inside project_details " + details.path + "/project_details");
                File project_dir = new File(project_path + details.path);
                project_dir.mkdir();
                File file = new File(project_path  + details.path+"/project_details.json");
                FileWriter writer = new FileWriter(file);
                
               
                try{

                    writer.write(p_details.toString());
                    writer.flush();
                    writer.close();
                }
                catch(Exception e){
                    System.out.println("Error in Project_details trying to write\t"+e.getMessage());
                }

            }catch(IOException e){
                System.out.println("Error when writing to file "+e.getMessage());
                e.printStackTrace();
            }
    }
}



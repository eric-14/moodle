package Userpage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Executors;

public class executecode {
    public ArrayList<String> compile (String project_name, String question){
        //Enter the path of projects and questions

      //first command is to change to the current working directory

        //printing files in the current working directory

        //now i need to access the question at hand and compile it then reun it and return the user the necessary output
        ArrayList<String> list_items2 = new ArrayList<>();

        try {
            System.out.println("line 17 inside execute code  \t" + project_name +"/"+ question);

            //compile the code
            String[] commands = {"javac", project_name +"/" + question};



            //process to run javac
            ProcessBuilder processbuilder = new ProcessBuilder(commands);
            Process process = processbuilder.start();

            StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            process.waitFor();

            //process to run java
            question.replace(".java",".class");
            String[] commands1 = {"java", project_name +"/" + question};
            ProcessBuilder processbuilder1 = new ProcessBuilder(commands1);
            Process process1 = processbuilder1.start();

            StreamGobbler streamGobbler1 = new StreamGobbler(process1.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler1);
            process1.waitFor();

            ArrayList<String> list_items = new ArrayList<>();
            ArrayList<String> list_items1 = new ArrayList<>();


            System.out.println("Line 30 in execute code 1 \t"+streamGobbler.project_names.size());
            System.out.println("Line 30 in execute code 2\t"+streamGobbler1.project_names.size());

            list_items= streamGobbler.project_names;
            list_items2= streamGobbler1.project_names;

            for(String file: list_items2){
                list_items2.add(file);
                System.out.println("line execute code list items hello world\t"+ file);
            }


            System.out.println("Line 25 inside execute code");

            System.out.println("line 30 confirming the while loop is running");

            int exitCode = process.waitFor();
            System.out.println("Process exit code\t" + exitCode);




        }catch(Exception e) {
            System.out.println("Error while Compiling the code "+e.getMessage());

        }

        return list_items2;
    }
}

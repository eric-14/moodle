package Userpage;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StreamGobbler implements Runnable {
    private InputStream inputStream;
    private Consumer<String> consumer;

    public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
        this.inputStream = inputStream;
        this.consumer = consumer;
    }

    public ArrayList<String> project_names= new ArrayList<>();

    @Override
    public void run()  {
       BufferedReader reader =  new BufferedReader(new InputStreamReader(inputStream));
                String i;
                try {
                    while ((i = reader.readLine()) != null) {
                        this.project_names.add(i);
                        System.out.println("adding to the project _names array");
                    }
                }catch(IOException e){
                    System.out.println("Error while reading from process\t "+ e.getMessage());
                }
    }
}
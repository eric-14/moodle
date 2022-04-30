public class details {
    String project_name;
    String project_Id;
    String no_questions;
    //path name of the folder
    String path;
    //project description
    String description;
    //time of deadline
    String deadline_t;
    String Techstack;
    Boolean auto_evaluation;

    //object time
    public details(String project_name,
                   String project_Id,
                   String path,
                   String description,
                   String deadline_t,
                   String Techstack,
                   String no_questions,
                   Boolean auto_evaluation){
        this.project_Id = project_Id;
        this.project_name = project_name;      
        this.path = path;
        this.description = description;
        this.deadline_t = deadline_t;
        this.Techstack = Techstack;
        this.no_questions =no_questions;
        this.auto_evaluation = auto_evaluation;
    }

}

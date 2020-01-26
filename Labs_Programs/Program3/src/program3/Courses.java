package program3;

public class Courses {


    public String prof;
    public String id;


    public Courses(String[] s){

        this.prof = s[7].substring(0, s[7].length()-1) + " " + s[6].substring(1, s[6].length());
        this.id = s[0].substring(0, s[0].length()-4);

    }


    public String getProf(){
        return this.prof;
    }

    public String getId(){
        return this.id;
    }




}
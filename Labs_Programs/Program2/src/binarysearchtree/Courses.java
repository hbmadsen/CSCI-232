package binarysearchtree;

public class Courses {


    public int sect = 1;
    public int seats;
    public String title;
    public String id;
    public String key;

    public Courses(String[] s){
        this.seats = Integer.parseInt(s[3]);
        this.title = s[1];
        this.id = s[0].substring(0, s[0].length()-4);
        this.key = s[14] + "-" + s[15];
        this.key = this.key.replaceAll("\\s","-");
    }

    public boolean isLecture(String[] s){

        boolean isLecture = false;

        if(!s[6].equals("Staff")){
            isLecture = true;
        }
        return isLecture;
    }

    public String getKey(){
        return this.key;
    }

    public int getSect(){
        return this.sect;
    }

    public int getSeats(){
        return this.seats;
    }

    public String getTitle(){
        return this.title;
    }

    public String getId(){
        return this.id;
    }

    public void incrementSect(){
        sect += 1;
    }

    public void incrementSeats(String[] s){
        this.seats += Integer.parseInt(s[3]);
    }




}

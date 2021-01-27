
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Student implements Comparable<Student>{
    private String ID;
    private String name;
    //Save season and year separately to create complete semester ("Spring 2021", "Summer 2021"...)
    private String season;
    private int year;
    private String semester;
    private String course;
    
    //Each student can have multiple course (no duplicate) in one semester
    Map<String, Set<String>> m = new HashMap<>();
    
    String semester() {
        semester = season + String.valueOf(year);
        return semester;
    }

    public Student(String ID, String name, String season, int year, String course) {
        this.ID = ID;
        this.name = name;
        this.season = season;
        this.year = year;
        this.semester = semester();
        this.course = course;
    }

    public Student() {
        this.ID = "No ID";
        this.name = "No name";
        this.season = "No season";
        this.year = 0;
        this.semester = semester();
        this.course = "No course";
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    
    public void print(){
        System.out.printf("%-15s%-15s%-15s\n", name, semester, course);
    }

    @Override
    public int compareTo(Student t) {
        return t.name.compareTo(this.name);
    }
}


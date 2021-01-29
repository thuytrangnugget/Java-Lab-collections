
import java.util.InputMismatchException;

public class Student implements Comparable<Student>{
    private String ID;
    private String name;
    private String semester;
    private String course;
        
    public Student(String ID, String name, String semester, String course) {
        this.ID = ID;
        this.name = name;
        this.semester = semester;
        this.course = course;
    }

    public Student() {
        this.ID = "No ID";
        this.name = "No name";
        this.semester = "No semester";
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
        System.out.printf("%-15s %-15s%-15s\n", name, semester, course);
    }

    @Override
    public int compareTo(Student t) {
        return t.name.compareTo(this.name);
    }
}

class Report {

    private String studentName;
    private String courseName;
    private int totalCourse;

    public Report() {
    }

    public Report(String studentName, String courseName, int totalCourse) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.totalCourse = totalCourse;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTotalCourse() {
        return totalCourse;
    }
    
}
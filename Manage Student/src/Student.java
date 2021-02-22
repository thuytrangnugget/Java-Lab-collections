
import java.util.StringTokenizer;


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
    public int compareTo(Student o) {
        String[] words1 = new String[5];
        String[] words2 = new String[5];
        int num1 = 0;
        int num2 = 0;
        StringTokenizer tk1 = new StringTokenizer(this.getName());
        StringTokenizer tk2 = new StringTokenizer(o.getName());
        
        while (tk1.hasMoreTokens()) {
            words1[num1] = tk1.nextToken();
            num1++;
        }
        while (tk2.hasMoreTokens()) {
            words2[num2] = tk2.nextToken();
            num2++;
        }
        while (true) {
            int temp = words1[--num1].compareTo(words2[--num2]);
            if (temp != 0) {
                return temp;
            }
            if (num1 == 0 || num2 == 0) {
                break;
            }
        }
        return this.getName().compareTo(o.getName());
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
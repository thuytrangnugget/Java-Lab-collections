
import java.util.ArrayList;
import java.util.Scanner;

public class Validation {
    static Scanner in = new Scanner(System.in);
    
    public static int checkInputIntLimit(int min, int max) {
        //loop until user input correct
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }
    
    public static String checkString() {
        String result;
        while (true) {
            result = in.nextLine().trim();
            if (result.equals("")) {
                return "";
            } else {
                return result;
            }
        }
    }
    
    public static boolean checkInputYN() {
        String result;
        while (true) {
            result = checkString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N");
            System.out.println("Enter again: ");
        }
    }
    
    public static boolean checkInputUD() {
        String result;
        while (true) {
            result = checkString();
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
            if (result.equalsIgnoreCase("D")) {
                return false;
            }
            System.err.println("Please input u/U or d/D");
            System.out.println("Enter again: ");
        }
    }
    
    public static String InputCourse() {
        String result; 
        while (true) {
            result = checkString();
            if (result.equalsIgnoreCase("java")
                    || result.equalsIgnoreCase(".net")
                    || result.equalsIgnoreCase("c/c++")) {
                return result;
            }
            System.err.println("There are only 3 courses: Java, .Net, C/C++");
            System.out.println("Enter again: ");
        }
    }
    
    
    public static boolean checkReportExist(ArrayList<Report> listReport, String name, String course, int total) {
        for(Report report : listReport) {
            if(name.equalsIgnoreCase(report.getCourseName())
                    && course.equalsIgnoreCase(report.getCourseName())
                    && total == report.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkIdExist(ArrayList<Student> ls, String id, String name) {
        for (Student student: ls) {
            if (id.equalsIgnoreCase(student.getID())
                    && !name.equalsIgnoreCase(student.getName())) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkStudentExist(ArrayList<Student> ls, String id, String studentName, String semester, String courseName) {
        int size = ls.size();
        for (Student student: ls) {
            if (id.equalsIgnoreCase(student.getID())
                    && studentName.equalsIgnoreCase(student.getName())
                    && semester.equalsIgnoreCase(student.getSemester())
                    && courseName.equalsIgnoreCase(student.getCourse())) {
                return true;
            }
        }
        return false;
    }
}


import java.util.ArrayList;
import java.util.Collections;

public class Manager {
    
    public static void menu() {
        System.out.println(" 1.	Create");
        System.out.println(" 2.	Find and Sort");
        System.out.println(" 3.	Update/Delete");
        System.out.println(" 4.	Report");
        System.out.println(" 5.	Exit");
        System.out.print(" Enter your choice: ");
    }
    
    public static void createStudent(ArrayList<Student> ls) {
        String id;
        String name;
        String semester;
        String courseName;
        Student x;
        int count = ls.size();
        if (count > 10) {
            System.out.println("Do you want to continue? (Y/N");
            if (!Validation.checkInputYN()) {
                return;
            }
        }
        while(true) {
            System.out.println("Enter id: ");
            id = Validation.checkString();
            System.out.println("Enter name student");
            name = Validation.checkString();
            if (!Validation.checkIdExist(ls, id, name)) {
                System.out.println("ID existed! Please input again!");
                continue;
            }
            System.out.println("Enter semester: ");
            semester = Validation.checkString();
            System.out.println("Enter course name: ");
            courseName = Validation.checkString();
            if (!Validation.checkStudentExist(ls, id, name, semester, courseName)) {
                x = new Student(id, name, semester, courseName);
                ls.add(x);
                count++;
                System.out.println("Add new student successfully");
                x.print();
                return;
            }
            System.err.println("Student existed! Enter another student or back to main screen");
        }
    }
    
    //list student found by name
    public static ArrayList<Student> listStudentFindByName(ArrayList<Student> ls) {
        ArrayList<Student> listStudentFindByName = new ArrayList<>();
        System.out.println("Enter name to search: ");
        String name = Validation.checkString();
        for (Student student : ls) {
            if (student.getName().contains(name)) {
                listStudentFindByName.add(student);
            }
        }
        return listStudentFindByName;
    }
    
    
    public static void findAndSort(ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty! You have to add student first");
            return;
        }
        ArrayList<Student> listFindByName = listStudentFindByName(ls);
        if (listFindByName.isEmpty()) {
            System.err.println("Not exist");
        } else {
            Collections.sort(listFindByName);
            System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");
            for (Student student : listFindByName) {
                student.print();
            }
        }
        
        
    }
    
    public static void updateOrDelete(ArrayList<Student> ls) {
    
    }
    
}

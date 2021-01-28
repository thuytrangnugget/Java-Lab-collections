
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;

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
    
    //Sau nay kiem tra student unavailable cho de
    public static ArrayList<Student> getListStudentById(ArrayList<Student> ls, String id) {
        ArrayList<Student> getListStudentById = new ArrayList<>();
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getID())) {
                getListStudentById.add(student);
            }
        }
        return getListStudentById;
    }
    
    
    public static Student getStudentByListFound(ArrayList<Student> listStudentFindByName) {
        System.out.println("List student found: ");
        System.out.println("List student found: ");
        int count = 1;
        System.out.printf("%-10s%-15s%-15s%-15s\n", "Number", "Student name",
                "semester", "Course Name");
        //display list student found
        for (Student student : listStudentFindByName) {
            System.out.printf("%-10d%-15s%-15s%-15s\n", count,
                    student.getName(), student.getSemester(),
                    student.getCourse());
            count++;
        }
        System.out.print("Enter student: ");
        int choice = Validation.checkInputIntLimit(1, listStudentFindByName.size());
        return listStudentFindByName.get(choice - 1);
    }
    
    public static void updateOrDelete(ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty!");
            return;
        }
        
        boolean loopAskID = true; 
        boolean continueAfterWrongID = true;
        String id;
        ArrayList<Student> list =  new ArrayList<>();
        //Nhap sai ID thi nhap lai dua khac
        while (loopAskID) {
            System.out.println("Enter id: ");
            try {
            id = Validation.checkString();
            list = getListStudentById(ls, id);
            if (list.isEmpty()) {
                throw new EmptyStackException();
                }    
            } catch (EmptyStackException e) {
                System.out.println("Student unavailable. Do you want to continue? (Y/N)");
                if (!Validation.checkInputYN()) {
                    loopAskID = false;
                    continueAfterWrongID = false;
                }
            }
        }
        if(!continueAfterWrongID) return;
        Student toFunction = getStudentByListFound(list);
        System.out.println("Student's information: ");
        toFunction.print();
        
        //Thao tac chinh
        boolean loop = true;
        System.out.print("Do you want to update (U) or delete (D) student: ");
        int updateChoice;
        while (loop) {
            //Validation.checkInputUD() --> update
            if (Validation.checkInputUD()) {
                System.out.println("1. Update name");
                System.out.println("2. Updte semester");
                System.out.println("3. Update courseName");
                System.out.print("Select your option (1/2/3)");
                updateChoice = Validation.checkInputIntLimit(1, 3);
                switch (updateChoice) {
                    case 1:
                        System.out.print("Enter new name, press Enter to skip: ");
                        String name = Validation.checkString();
                        if (name.equals('')) {
                            break;
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        }
    }
    
    public static void sort() {
    
    }
}


import java.util.ArrayList;
import java.util.Collections;

public class Manager {
    
    public static void menu() {
        System.out.println("======== STUDENT MANAGEMENT SYSTEM ========");
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
        //Student list > 10 student thi bat nhap lai
        if (count > 10) {
            System.out.println("Do you want to continue? (Y/N");
            if (!Validation.checkInputYN()) {
                return;
            }
        }
        int countExistedStudent = 0;
        while(true) {
            System.out.println("Enter id: ");
            id = Validation.checkString();
            for (Student s: ls) {
                if (id.equals(s.getID())) {
                    countExistedStudent++;
                }
            }
            //ton tai student voi ID thi khong bat nhap lai nua
            if (countExistedStudent > 0) {
                System.out.println("Found " + countExistedStudent + " records with ID " + id);
                for (Student s : ls) {
                    if (id.equals(s.getID())) {
                        s.print();
                    }
                }
                boolean loop = true;
                boolean createNewStudent = false;
                while (loop) {
                    if (createNewStudent) {
                        loop = false;
                    } else {
                        System.out.println("Do you want to create another record for this student? (Y/N)");
                        if (Validation.checkInputYN()) {
                            //everyone will have the same name so we take 1
                            name = ls.get(0).getName();
                            newRecordForExsistedStudent(ls, id, name);
                        } else {
                            System.out.println("Do you want to create another student? (Y/N)");
                            if (!Validation.checkInputYN()) {
                                loop = false;
                                return;
                            } else {
                                createNewStudent = true;
                                countExistedStudent = 0;
                            }
                        }
                    }
                }
                continue;
            }
            System.out.println("Creating student with ID: " + id);
            System.out.print("Enter student's name: ");
            name = Validation.checkString();
            System.out.print("Enter semester: ");
            semester = Validation.checkString();
            System.out.print("Enter course name: ");
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
    
    
    public static void newRecordForExsistedStudent(ArrayList<Student> ls, String id, String name) {
        String semester, courseName;
        Student x;
        System.out.println("Enter semester: ");
        semester = Validation.checkString();
        System.out.println("Enter course name: ");
        courseName = Validation.checkString();
        if (!Validation.checkStudentExist(ls, id, name, semester, courseName)) {
                x = new Student(id, name, semester, courseName);
                ls.add(x);
                System.out.println("Add new student successfully");
                x.print();
                return;
            }
            System.err.println("Student existed! Enter another student or back to main screen");
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
        //ArrayList<Student> list =  new ArrayList<>();
        //Nhap sai ID thi nhap lai dua khac
        Student toFunction = new Student();
        while (loopAskID) {
            System.out.println("Enter id: ");
            id = Validation.checkString();
            for (Student x : ls) {
                if (id.equalsIgnoreCase(x.getID())) {
                    toFunction = x;
                } else {
                    loopAskID = false;
                }
            }
//            try {
//            id = Validation.checkString();
//            list = getListStudentById(ls, id);
//            if (list.isEmpty()) {
//                throw new EmptyStackException();
//                }    
//            } catch (EmptyStackException e) {
//                System.out.println("Student unavailable. Do you want to continue? (Y/N)");
//                if (!Validation.checkInputYN()) {
//                    loopAskID = false;
//                    continueAfterWrongID = false;
//                    }
//                }
        }
        if(!continueAfterWrongID) return;
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
                        if (name == "") {
                            continue;
                        } else {}
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        }
    }
    
    public static void report(ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Report> lr = new ArrayList<>();
        int size = ls.size();
        for (int i = 0; i < size; i++) {
            int total = 0;
            for (Student student1 : ls) {
                for (Student student2 : ls) {
                    if (student1.getID().equalsIgnoreCase(student2.getID())
                            && student1.getCourse().equalsIgnoreCase(student2.getCourse())) {
                        total++;
                    }
                }
                if (Validation.checkReportExist(lr, student1.getName(),
                        student1.getCourse(), total)) {
                    lr.add(new Report(student1.getName(),
                            student1.getName(), total));
                }
            }
        }
        //print report
        for (int i = 0; i < lr.size(); i++) {
            System.out.printf("%-15s|%-10s|%-5d\n", lr.get(i).getStudentName(),
                    lr.get(i).getCourseName(), lr.get(i).getTotalCourse());
        }
    }
}


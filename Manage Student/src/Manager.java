
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
            courseName = Validation.InputCourse();
            
            if (!Validation.checkStudentExist(ls, id, name, semester, courseName)) {
                x = new Student(id, name, semester, courseName);
                ls.add(x);
                count++;
                System.out.println("Add new student successfully");
                x.print();
                return;
            } else {
                System.err.println("Record existed! Enter another student or back to main screen");
            }
                    System.out.println("");
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
        System.err.println("Record existed! Enter another student or back to main screen");
    }
    
    //list student found by name
    public static ArrayList<Student> listStudentFindByName(ArrayList<Student> ls) {
        ArrayList<Student> listStudentFindByName = new ArrayList<>();
        System.out.print("Enter name to search or press Enter to display every student: ");
        String name = Validation.checkString();
        if (name.equals("")) {
            return ls;
        } else {
            for (Student student : ls) {
                if (student.getName().contains(name)) {
                listStudentFindByName.add(student);
                }
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
        System.out.println("");
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
    
    //get student user want to update/delete in list found
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
    
    public static void updateOrDelete(int count, ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty");
            return;
        }
        System.out.print("Enter id: ");
        String id = Validation.checkString(); 
        ArrayList<Student> listStudentFindByName = getListStudentById(ls, id);
        if (listStudentFindByName.isEmpty()) {
            System.err.println("Student not found!");
            return;
        } else {
            Student student = getStudentByListFound(listStudentFindByName);
            System.out.print("Do you want to update (U) or delete (D) this student? ");
            if (Validation.checkInputUD()) {
                Scanner sc = new Scanner (System.in);
                while (true) {
                    System.out.println("Updating student");
                    System.out.print("Type new name or press Enter to skip: ");
                    String name = sc.nextLine();
                    if (name.compareTo("") != 0) {
                        student.setName(name);
                    }
                    System.out.print("Type new semester or press Enter to skip: ");
                    String semester = sc.nextLine();
                    if (semester.compareTo("") != 0) {
                        student.setSemester(semester);
                    }
                    System.out.print("Type new course or press Enter to skip: ");
                    String course = sc.nextLine();
                    if (course.compareTo("") != 0) {
                        student.setCourse(course);
                    }
                    if (Validation.checkStudentExist(ls, id, name, semester, course)) {
                        System.out.println("Update sucessully");
                        return;
                    }
                    return;
                }
            } else {
                ls.remove(student);
                count--;
                System.out.println("Delete student with ID " + id + " successfully");
            }   
                System.out.println("");
                return;
        }
    }
    
    public static void report(ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty");
            return;
        }
        ArrayList<Report> reportList = new ArrayList<>();
        for (int i = 0; i < ls.size(); i++) {
            int total = 0;
            String id = ls.get(i).getID();
            String courseName = ls.get(i).getCourse();
            String studentName = ls.get(i).getName();
            for (Student studentCountTotal : ls) {
                if (id.equalsIgnoreCase(studentCountTotal.getID())) {
                    total++;
                }
            }
            if (Validation.checkReportExist(reportList, studentName, courseName, total)) {
                reportList.add(new Report(ls.get(i).getName(), courseName, total));
            }

        }
        //print report
        for (int i = 0; i < reportList.size(); i++) {
            System.out.printf("%-15s|%-10s|%-5d\n", reportList.get(i).getStudentName(),
                    reportList.get(i).getCourseName(), reportList.get(i).getTotalCourse());
        }
    }

}


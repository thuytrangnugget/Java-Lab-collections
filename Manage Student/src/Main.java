
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        int choice;
        Validation validation = new Validation();
        list.add(new Student("HE150573", "Duong Thuy Trang","Spring 2021", "java"));
        list.add(new Student("HE150573", "Duong Thuy Trang","Fall 2020", "c/c++"));
        list.add(new Student("HE150125", "Nguyen Hoang Minh","Spring 2021", "java"));
        list.add(new Student("HE130594", "Hoang Duc Nam","Spring 2021", ".net"));
        list.add(new Student("HE140595", "Pham Bac Nguyen","Spring 2021", ".net"));
        list.add(new Student("HE140595", "Pham Bac Nguyen","Fall 202", "c/c++"));
        while (true) {
            Manager.menu();
            choice = validation.checkInputIntLimit(1, 5);
            switch(choice) {
                case 1:
                    Manager.createStudent(list);
                    break;
                case 2:
                    Manager.findAndSort(list);
                    break;
                case 3:
                    Manager.updateOrDelete(choice, list);
                    break;
                case 4:
                    Manager.report(list);
                    break;
                case 5:
                    return;
            }
            
        }
    }
    
}

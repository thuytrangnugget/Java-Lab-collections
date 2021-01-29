
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
        
       public static String checkString() {
        String result;
        while (true) {
            result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("String can't be empty!");
                System.out.println("Enter again: ");
            } else {
                return result;
            }
        }
    }

}

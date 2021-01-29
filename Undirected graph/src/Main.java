
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Graph myGraph = new Graph(5);
        myGraph.addEdge(1, 4);
        myGraph.addEdge(2, 4);
        myGraph.addEdge(2, 5);
        myGraph.addEdge(3, 5);
        myGraph.addEdge(4, 5);
       
        int start, end;
        while (true) {
            System.out.println("Enter the start point: ");
            start = Validation.checkInputIntLimit(1, myGraph.vertexCount);
            System.out.println("Enter the end point: ");
            end = Validation.checkInputIntLimit(1, myGraph.vertexCount);
            myGraph.isEdge(start, end);
            System.out.println("Do you want to continue?");
            if(!Validation.checkInputYN()) {
                break;
            }
        }
        
        
    }
}
    

public class Graph {
    boolean adjacencyMatrix[][];
    int vertexCount; 

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        adjacencyMatrix = new boolean[vertexCount][vertexCount];
    }
    
    public void addEdge(int i, int j) {
        if (i>= 1 && i <= vertexCount && j >= 0 && j <= vertexCount) {
            adjacencyMatrix[i-1][j-1] = true;
            adjacencyMatrix[j-1][i-1] = true;
        }
    }
    
    public void removeEdge(int i, int j) {
        if (i >= 1 && i < vertexCount && j >= 0 && j <= vertexCount) {
            adjacencyMatrix[i-1][j-1] = false;
            adjacencyMatrix[j-1][i-1] = false;
        }
    }
    
    public boolean edgeCheck(int i, int j) {
        if (i >= 1 && i < vertexCount && j >= 0 && j <= vertexCount) {
            return adjacencyMatrix[j-1][i-1] || adjacencyMatrix[i-1][j-1];
        } else {
            return false;
        }
    }
    
    public void isEdge(int i, int j) {
        if(edgeCheck(i, j)) {
            System.out.println("This is an edge");
            return;
        } else {
            System.out.println("This is not an edge");
        }
    }
}

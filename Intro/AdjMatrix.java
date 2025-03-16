public class AdjMatrix{
    int v;
    int[][] adjMatrix;
    AdjMatrix(int v)
    {
        this.v = v;
        adjMatrix = new int[v][v];
    }
    void addEdge(int u,int v)
    {
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;
    }

    void removeEdge(int u,int v)
    {
        adjMatrix[u][v] = 0;
        adjMatrix[v][u] = 0;
    }

    void print()
    {
        for (int i = 0; i < v; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < v; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        AdjMatrix graph = new AdjMatrix(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.print();
    }
}
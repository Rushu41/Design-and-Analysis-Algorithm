import java.util.*;

public class AdjList {
    int v;
    List<List<Integer>> adjList;

    AdjList(int v) {
        this.v = v;
        adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adjList.add(new LinkedList<>());
        }
    }

    void addEdge(int u, int v) 
    {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    void removeEdge(int u, int v) {
        adjList.get(u).remove((Integer) v);
        adjList.get(v).remove((Integer) u);
    }

    void print() {
        for (int i = 0; i < v; i++) {
            System.out.print(i + " -> ");
            for (int j = 0; j < adjList.get(i).size(); j++) 
            {
                System.out.print(adjList.get(i).get(j) + " "); 
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int v = 5;
        AdjList graph = new AdjList(v);

        // Adding edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        System.out.println("Graph before removing edge:");
        graph.print();

        // Removing an edge
        graph.removeEdge(3, 4);

        System.out.println("\nGraph after removing edge (3,4):");
        graph.print();
    }
}

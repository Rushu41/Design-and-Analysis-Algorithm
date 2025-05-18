import java.util.*;

class Edge {
    int u, v, w;
    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

class Graph {
    int v;
    List<List<Edge>> adj;

    Graph(int v) {
        this.v = v;
        adj = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v, int w) {
        adj.get(u).add(new Edge(u, v, w));
        adj.get(v).add(new Edge(v, u, w)); // Undirected graph
    }

    void floydWarshall() {
        int[][] dist = new int[v + 1][v + 1];

        // Initialize distance matrix
        for (int i = 1; i <= v; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        // Fill initial distances from adjacency list
        for (int u = 1; u <= v; u++) {
            for (Edge edge : adj.get(u)) {
                dist[edge.u][edge.v] = edge.w;
            }
        }

        // Floyd-Warshall algorithm
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }

        // Print shortest path matrix
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}

public class FloydWarshall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt(); // number of vertices
        int r = sc.nextInt(); // number of edges

        Graph g = new Graph(c);

        for (int i = 0; i < r; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            g.addEdge(u, v, w);
        }

        g.floydWarshall();
    }
}

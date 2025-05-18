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
    List<Edge> edgeList; // Bellman-Ford needs a flat edge list

    Graph(int v) {
        this.v = v;
        edgeList = new ArrayList<>();
    }

    void addEdge(int u, int v, int w) {
        edgeList.add(new Edge(u, v, w));
        // For undirected graph, add both directions
        edgeList.add(new Edge(v, u, w));
    }

    void bellmanFord(int src) {
        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i < v; i++) {
            for (Edge edge : edgeList) {
                if (dist[edge.u] != Integer.MAX_VALUE &&
                        dist[edge.u] + edge.w < dist[edge.v]) {
                    dist[edge.v] = dist[edge.u] + edge.w;
                }
            }
        }

        // Check for negative weight cycle
        for (Edge edge : edgeList) {
            if (dist[edge.u] != Integer.MAX_VALUE &&
                    dist[edge.u] + edge.w < dist[edge.v]) {
                System.out.println("Graph contains a negative weight cycle");
                return;
            }
        }

        // Print result
        for (int i = 1; i <= v; i++) {
            System.out.println("Distance from " + src + " to " + i + ": " +
                    (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }
}

public class BellmanFord {
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

        int src = sc.nextInt(); // Source node
        g.bellmanFord(src);
    }
}

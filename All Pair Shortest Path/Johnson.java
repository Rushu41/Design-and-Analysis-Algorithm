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
    List<Edge> edges; // for Bellman-Ford
    List<List<Edge>> adj; // for Dijkstra

    Graph(int v) {
        this.v = v;
        edges = new ArrayList<>();
        adj = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v, int w) {
        edges.add(new Edge(u, v, w));
        adj.get(u).add(new Edge(u, v, w)); // for Dijkstra
        // Note: Don't add reverse edge for directed Johnson's
    }

    boolean bellmanFord(int[] h) {
        Arrays.fill(h, Integer.MAX_VALUE);
        h[0] = 0;

        for (int i = 0; i < v; i++) {
            for (Edge edge : edges) {
                if (h[edge.u] != Integer.MAX_VALUE && h[edge.u] + edge.w < h[edge.v]) {
                    h[edge.v] = h[edge.u] + edge.w;
                }
            }
        }

        for (Edge edge : edges) {
            if (h[edge.u] != Integer.MAX_VALUE && h[edge.u] + edge.w < h[edge.v]) {
                return false; // Negative cycle
            }
        }
        return true;
    }

    int[] dijkstra(int src) {
        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            int d = cur[1];
            if (d > dist[u]) continue;

            for (Edge edge : adj.get(u)) {
                int newDist = dist[u] + edge.w;
                if (newDist < dist[edge.v]) {
                    dist[edge.v] = newDist;
                    pq.offer(new int[]{edge.v, newDist});
                }
            }
        }
        return dist;
    }

    void johnsonsAlgorithm() {
        // Step 1: Add a dummy node 0 and connect to all nodes with weight 0
        for (int i = 1; i <= v; i++) {
            edges.add(new Edge(0, i, 0));
        }

        // Step 2: Run Bellman-Ford from dummy node
        int[] h = new int[v + 1];
        if (!bellmanFord(h)) {
            System.out.println("Graph contains a negative weight cycle");
            return;
        }

        // Step 3: Reweight all edges: w'(u,v) = w(u,v) + h[u] - h[v]
        List<List<Edge>> newAdj = new ArrayList<>();
        for (int i = 0; i <= v; i++) newAdj.add(new ArrayList<>());

        for (Edge edge : edges) {
            if (edge.u != 0) { // Skip dummy edges
                int newW = edge.w + h[edge.u] - h[edge.v];
                newAdj.get(edge.u).add(new Edge(edge.u, edge.v, newW));
            }
        }

        this.adj = newAdj;

        // Step 4: Run Dijkstra from every node and adjust result
        for (int u = 1; u <= v; u++) {
            int[] dist = dijkstra(u);
            System.out.println("Shortest distances from node " + u + ":");
            for (int v = 1; v <= this.v; v++) {
                if (dist[v] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    int actualDist = dist[v] + h[v] - h[u];
                    System.out.print(actualDist + " ");
                }
            }
            System.out.println();
        }
    }
}

public class Johnson {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt(); // number of vertices
        int r = sc.nextInt(); // number of edges

        Graph g = new Graph(c);

        for (int i = 0; i < r; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            g.addEdge(u, v, w); // Directed graph
        }

        g.johnsonsAlgorithm();
    }
}

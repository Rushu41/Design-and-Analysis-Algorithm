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
    int[] nodeCost;

    Graph(int v) {
        this.v = v;
        adj = new ArrayList<>();
        nodeCost = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v, int w) {
        adj.get(u).add(new Edge(u, v, w));
        adj.get(v).add(new Edge(v, u, w)); // remove if directed
    }

    void setNodeCost(int[] costs) {
        for (int i = 1; i <= v; i++) {
            nodeCost[i] = costs[i];
        }
    }

    void dijkstra(int src) {
        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = nodeCost[src]; // Start with node cost of source

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, dist[src]});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            int d = cur[1];

            if (d > dist[u]) continue;

            for (Edge edge : adj.get(u)) {
                int v = edge.v;
                int weight = edge.w;

                int newDist = dist[u] + weight + nodeCost[v];
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.offer(new int[]{v, newDist});
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            System.out.println("Cost from " + src + " to " + i + ": " +
                (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }
}

public class DijkstraWithNodeCost {
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

        int[] costs = new int[c + 1];
        for (int i = 1; i <= c; i++) {
            costs[i] = sc.nextInt(); // input node cost for each node
        }
        g.setNodeCost(costs);

        int src = sc.nextInt(); // source node
        g.dijkstra(src);
    }
}

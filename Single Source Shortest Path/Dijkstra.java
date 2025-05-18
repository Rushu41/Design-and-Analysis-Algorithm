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
        adj.get(v).add(new Edge(v, u, w)); 
    }

    void dijkstra(int src) {
        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int d = cur[1];

            if (d > dist[node]) continue;

            for (Edge edge : adj.get(node)) {
                int next = edge.v;
                int weight = edge.w;

                if (dist[node] + weight < dist[next]) {
                    dist[next] = dist[node] + weight;
                    pq.offer(new int[]{next, dist[next]});
                }
            }
        }

      
        for (int i = 1; i <= v; i++) {
            System.out.println("Distance from " + src + " to " + i + ": " +
                    (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }
}

public class Dijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int c = sc.nextInt(); 
        int r = sc.nextInt(); 

        Graph g = new Graph(c);

        for (int i = 0; i < r; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            g.addEdge(u, v, w);
        }

        int src = sc.nextInt(); // source node
        g.dijkstra(src);
    }
}

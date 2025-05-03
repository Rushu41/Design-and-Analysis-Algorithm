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
    List<Edge> edges;

    Graph(int v) {
        this.v = v;
        edges = new ArrayList<>();
    }

    void addEdge(int u, int v, int w) {
        edges.add(new Edge(u, v, w));
    }

    int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    void union(int[] parent, int[] rank, int x, int y) {
        int px = find(parent, x);
        int py = find(parent, y);

        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[py] = px;
            rank[px]++;
        }
    }

    void kruskalMST() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        pq.addAll(edges);

        int[] parent = new int[v + 1];
        int[] rank = new int[v + 1];

        for (int i = 0; i <= v; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        while (!pq.isEmpty() && mst.size() < v - 1) {
            Edge e = pq.poll();
            int pu = find(parent, e.u);
            int pv = find(parent, e.v);

            if (pu != pv) {
                mst.add(e);
                totalCost += e.w;
                union(parent, rank, pu, pv);
            }
        }

        System.out.println("Edges in MST:");
        for (Edge e : mst) {
            System.out.println(e.u + " - " + e.v + " : " + e.w);
        }
        System.out.println("Total cost of MST: " + totalCost);
    }
}

public class KruskalMST {
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

        g.kruskalMST();
    }
}

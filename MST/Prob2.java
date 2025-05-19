// You are given a connected graph with n cities and m bidirectional roads. Each
// road has a maintenance cost. You need to remove as many roads as possible so
// that:

// All cities are still connected (i.e., the graph stays connected).

// The total maintenance cost is minimized.

// Output the maximum cost saved by removing unnecessary roads.

// 📌 Sample Input:

// 4 5
// 1 2 4
// 1 3 3
// 2 3 2
// 3 4 6
// 2 4 5
// 📌 Sample Output:

// Saved cost: 11
// 💡 Explanation:
// Total cost of all roads = 4 + 3 + 2 + 6 + 5 = 20
// MST cost = 2 + 3 + 5 = 10 (Kruskal chooses min-cost edges)
// Saved cost = 20 - 9 = 11


import java.util.*;

class Edge {
    int u, v, w;
    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

public class Prob2{
    static int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    static void union(int[] parent, int[] rank, int x, int y) {
        int px = find(parent, x);
        int py = find(parent, y);

        if (px != py) {
            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); 
        int m = sc.nextInt(); 
        List<Edge> edges = new ArrayList<>();
        int totalCost = 0;

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new Edge(u, v, w));
            totalCost += w;
        }

        edges.sort(Comparator.comparingInt(e -> e.w));
        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int mstCost = 0;
        int edgesUsed = 0;

        for (Edge e : edges) {
            int pu = find(parent, e.u);
            int pv = find(parent, e.v);
            if (pu != pv) {
                union(parent, rank, pu, pv);
                mstCost += e.w;
                edgesUsed++;
            }
        }

        if (edgesUsed == n - 1) {
            System.out.println("Saved cost: " + (totalCost - mstCost));
        } else {
            System.out.println("Impossible to connect all cities.");
        }
    }
}

// A country has n cities and m possible roads that can be built to connect
// them. Each road connects two cities and has a certain cost. Your task is to
// determine the minimum total cost to connect all the cities such that every
// city is reachable from any other city (i.e., the graph is connected). If it
// is not possible to connect all the cities, print "Impossible".

// 🧠 Input Format:
// python-repl
// Copy
// Edit
// n m
// u1 v1 w1
// u2 v2 w2
// ...
// um vm wm
// n: number of cities (1-based indexing)

// m: number of possible roads

// Each of the next m lines contains 3 integers:

// u, v: cities connected by this road

// w: cost to build the road

// 📤 Output Format:
// If all cities can be connected: print the minimum total cost.

// Else: print "Impossible"

// 📌 Sample Input:
// 4 3
// 1 2 3
// 2 3 4
// 3 4 5
// 📌 Sample Output:
// 12
// 📌 Sample Input 2 (Disconnected Graph):
// 4 2
// 1 2 3
// 3 4 4
// 📌 Sample Output 2:
// Impossible


import java.util.*;

class Edge {
    int u, v, w;
    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

public class Prob1 {
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

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new Edge(u, v, w));
        }

        edges.sort(Comparator.comparingInt(e -> e.w));

        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int totalCost = 0;
        int edgesUsed = 0;

        for (Edge e : edges) {
            int pu = find(parent, e.u);
            int pv = find(parent, e.v);
            if (pu != pv) {
                union(parent, rank, pu, pv);
                totalCost += e.w;
                edgesUsed++;
            }
        }

        if (edgesUsed == n - 1)
            System.out.println(totalCost);
        else
            System.out.println("Impossible");
    }
}

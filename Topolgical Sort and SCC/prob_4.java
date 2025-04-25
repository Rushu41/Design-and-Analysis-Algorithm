// There are m teleporters connecting the n planets in a game. When there is a path from a to
// b and from b to a, two planets, a and b, are in the same kingdom. You have to identify the
// kingdom of each planet.
// Input
// The number of planets and teleporters is indicated by the two integers n and m on the first
// input line. Numbers 1,2,…,n are the planets' numbers.
// The teleporters are then described in m lines. There are two integers on each line, a and b.
// You can use a teleporter to go from planet a to planet b.
// Output
// The number of kingdoms, k, should be printed first. Next, print a kingdom label between 1
// and k for every planet. Any valid answer can be printed.
// Constraints
// ● 1≤n≤10^5
// ● 1≤m≤2*10^5

// Sample Input 
// 5 6
// 1 2
// 2 3
// 3 1
// 3 4
// 4 5
// 5 4
// Sample Output
// 2 
// 1 1 1 2 2


import java.util.*;

class Graph {
    int v;
    List<List<Integer>> Adj;
    Stack<Integer> st;

    Graph(int v) {
        this.v = v;
        Adj = new ArrayList<>(v + 1);
        for (int i = 0; i <= v; i++) {
            Adj.add(new LinkedList<>());
        }
        st = new Stack<>();
    }

    void addEdge(int u, int v) {
        Adj.get(u).add(v);
    }

    void sortDFS(int src, boolean[] vis) {
        vis[src] = true;
        for (int i : Adj.get(src)) {
            if (!vis[i]) {
                sortDFS(i, vis);
            }
        }
        st.push(src);
    }

    void DFS(int src, boolean[] vis, List<Integer> comp) {
        vis[src] = true;
        comp.add(src);
        for (int i : Adj.get(src)) {
            if (!vis[i]) {
                DFS(i, vis, comp);
            }
        }
    }

    void topoSort() {
        boolean[] vis = new boolean[v + 1];
        for (int i = 1; i <= v; i++) {
            if (!vis[i]) {
                sortDFS(i, vis);
            }
        }
    }

    Graph reverse() {
        Graph g = new Graph(v);
        for (int i = 1; i <= v; i++) {
            for (int j : Adj.get(i)) {
                g.Adj.get(j).add(i);
            }
        }
        return g;
    }

    List<List<Integer>> pathSCC() {
        topoSort();
        Graph g = reverse();
        boolean[] vis = new boolean[v + 1];
        Arrays.fill(vis, false);
        List<List<Integer>> scc = new ArrayList<>();

        while (!st.isEmpty()) {
            int node = st.pop();
            if (!vis[node]) {
                List<Integer> comp = new ArrayList<>();
                g.DFS(node, vis, comp);
                scc.add(comp);
            }
        }
        return scc;
    }
}

public class prob_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        Graph g = new Graph(v);

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt(); 
            int b = sc.nextInt(); 
            g.addEdge(a, b);
        }

        List<List<Integer>> sccs = g.pathSCC();
        int[] path = new int[v + 1];

        for (int i = 0; i < sccs.size(); i++) {
            for (int cp : sccs.get(i)) {
                path[cp] = i + 1;
            }
        }

        System.out.println(sccs.size());
        for (int i = 1; i <= v; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println();
    }
}

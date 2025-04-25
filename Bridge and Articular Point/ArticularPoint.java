import java.util.*;

class Graph {
    int v;
    List<List<Integer>> Adj;

    Graph(int v) {
        this.v = v;
        Adj = new ArrayList<>(v + 1);
        for (int i = 0; i <= v; i++) {
            Adj.add(new LinkedList<>());
        }
    }

    void addEdge(int u, int v) {
        Adj.get(u).add(v);
        Adj.get(v).add(u);
    }

    int time = 0;

    void DFS(int u, boolean[] visited, int[] disc, int[] low, int[] parent, boolean[] isArticulation) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : Adj.get(u)) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                DFS(v, visited, disc, low, parent, isArticulation);

                low[u] = Math.min(low[u], low[v]);

                if (parent[u] == -1 && children > 1) {
                    isArticulation[u] = true;
                }

                if (parent[u] != -1 && low[v] >= disc[u]) {
                    isArticulation[u] = true;
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}

public class ArticularPoint {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        int r = sc.nextInt();

        Graph g = new Graph(c);

        for (int i = 0; i < r; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.addEdge(u, v);
        }

        boolean[] visited = new boolean[c + 1];
        int[] disc = new int[c + 1];
        int[] low = new int[c + 1];
        int[] parent = new int[c + 1];
        boolean[] isArticulation = new boolean[c + 1];
        Arrays.fill(parent, -1);

        for (int i = 1; i <= c; i++) {
            if (!visited[i]) {
                g.DFS(i, visited, disc, low, parent, isArticulation);
            }
        }

        System.out.println("Articulation Points:");
        boolean found = false;
        for (int i = 1; i <= c; i++) {
            if (isArticulation[i]) {
                System.out.println(i);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }
}

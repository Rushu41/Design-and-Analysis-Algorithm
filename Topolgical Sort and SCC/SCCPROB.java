import java.util.*;

class Graph {
    int v;
    List<List<Integer>> Adj;
    Stack<Integer> st;

    Graph(int v) {
        this.v = v;
        Adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            Adj.add(new ArrayList<>());
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
        boolean[] vis = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                sortDFS(i, vis);
            }
        }
    }

    Graph reverse() {
        Graph g = new Graph(v);
        for (int i = 0; i < v; i++) {
            for (int j : Adj.get(i)) {
                g.Adj.get(j).add(i);
            }
        }
        return g;
    }

    List<List<Integer>> pathSCC() {
        topoSort();
        Graph gr = reverse();
        boolean[] vis = new boolean[v];
        Arrays.fill(vis, false);
        List<List<Integer>> scc = new ArrayList<>();

        while (!st.isEmpty()) {
            int node = st.pop();
            if (!vis[node]) {
                List<Integer> comp = new ArrayList<>();
                gr.DFS(node, vis, comp);
                scc.add(comp);
            }
        }
        return scc;
    }
}

public class SCCPROB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt(); // Number of vertices
        int e = sc.nextInt(); // Number of edges

        Graph g = new Graph(v);

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            g.addEdge(a, b);
        }

        List<List<Integer>> sccs = g.pathSCC();

        Map<Integer, Integer> nodeToComp = new HashMap<>();
        for (int i = 0; i < sccs.size(); i++) {
            for (int node : sccs.get(i)) {
                nodeToComp.put(node, i);
            }
        }

        Set<String> edges = new HashSet<>();
        for (int u = 0; u < v; u++) {
            for (int w : g.Adj.get(u)) {
                int cu = nodeToComp.get(u);
                int cv = nodeToComp.get(w);
                if (cu != cv) {
                    edges.add(cu + " " + cv);
                }
            }
        }

        System.out.println(sccs.size());
        for (String edge : edges) {
            System.out.println(edge);
        }
    }
}

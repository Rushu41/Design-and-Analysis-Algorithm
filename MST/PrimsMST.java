import java.util.*;
class Edge{
    int u,v,w;
    Edge(int u,int v,int w)
    {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}
class Graph{
    int v;
    List<List<Edge>> adj;
    Graph(int v)
    {
        this.v = v;
        adj = new ArrayList<>();
        for(int i=0;i<=v;i++)
        {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int u,int v,int w)
    {
       adj.get(u).add(new Edge(u, v, w));
       adj.get(v).add(new Edge(v, u, w));
    }

    void prim()
    {
        boolean[] vis = new boolean[v+1];
        PriorityQueue <Edge> pq = new PriorityQueue<>((a,b)->a.w-b.w);
        vis[1] = true;
        pq.addAll(adj.get(1));
        List<Edge> mst = new ArrayList<>();
        int cnt = 0;
        while(!pq.isEmpty() && mst.size()<v-1)
        {
            Edge e = pq.poll();
            if(vis[e.v]) continue;

            vis[e.v] = true;
            mst.add(e);
            cnt += e.w;

            for(Edge next : adj.get(e.v))
            {
                if(!vis[next.v])
                {
                    pq.offer(next);
                }
            }
        }
        System.out.println("Edges in MST:");
        for (Edge e : mst) {
            System.out.println(e.u + " - " + e.v + " : " + e.w);
        }
        System.out.println("Total cost of MST: " + cnt);
    }
}
public class PrimsMST {
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

        g.prim();
    }
}

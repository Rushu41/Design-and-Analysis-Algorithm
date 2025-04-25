import java.util.*;
class Graph{
    int v;
    List<List<Integer>>Adj;
    Graph(int v)
    {
        this.v = v;
        Adj = new ArrayList<>(v);
        for(int i=0;i<=v;i++)
        {
            Adj.add(new LinkedList<>());
        }
    }

    void addEdge(int u,int v)
    {
        Adj.get(u).add(v);
        Adj.get(v).add(u);
    }
    int time = 0;
    void DFS(int src, int[] d,int[] low,int[] parent, boolean[] vis,List<int[]> bridge)
    {
       vis[src] = true;
       d[src] = low[src] = ++time;

       for(int w: Adj.get(src))
       {
        if(!vis[w])
        {
            parent[w] = src;
            DFS(w,d,low,parent,vis,bridge);
            
            if(low[w] > d[src])
            {
               bridge.add(new int[]{src,w});
            }
             low[src] = Math.min(low[w],low[src]);
        }
        else if(w != parent[src])
        {
           low[src] = Math.min(low[src],d[w]);
        }
       }
    }
}
public class bridge{
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

        int[] d = new int[c + 1];     
        int[] low = new int[c + 1];    
        int[] parent = new int[c + 1];
        boolean[] vis = new boolean[c + 1]; 
        Arrays.fill(parent, -1);

        List<int[]> bridges = new ArrayList<>();

        for (int i = 1; i <= c; i++) {  
            if (!vis[i]) {
                g.DFS(i, d, low, parent, vis, bridges);
            }
        }

        System.out.println("Total Bridges: " + bridges.size());
        for (int[] edge : bridges) {
            System.out.println("Bridge found between: " + edge[0] + " - " + edge[1]);
        }
    
    }
}
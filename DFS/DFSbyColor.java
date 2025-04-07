import java.util.*;
class Graph{
    int V;
    List<List<Integer>> Adj;
    String[] color;
    int[] prev, d, f;
    int time;
    Graph(int v){
        V = v;
        Adj = new ArrayList<>();
        for(int i=0; i<v; i++){
           Adj.add( new LinkedList<>() );
        }
        color = new String[v];
        prev = new int[v];
        d = new int[v];
        f = new int[v];
        time = 0;
    }
    void addEdge(int u, int v){
        Adj.get(u).add(v);
    }
    void DFS(){
        for(int i=0;i<V;i++)
        {
            color[i] = "white";
            prev[i] = -1;
            d[i] = -1;
            f[i] = -1;
        }
        time = 0;
        for(int i=0;i<V;i++)
        {
            if(color[i].equals("white"))
            {
                DFSVisit(i);
            }
        }
    }
    void DFSVisit(int u)
    {
        color[u] = "grey";
        time++;
        d[u] = time;
    for(int i : Adj.get(u))
    {
        if(color[i].equals("white"))
        {
            prev[i] = u;
            DFSVisit(i);
        }
    } 
    time++;
    f[u] = time;
    color[u] = "black"; 
 }

 void print()
 {
      for (int i = 0; i < V; i++) {
                System.out.println("Vertex " + i + ": discovery time = " + d[i] + ", finish time = " + f[i] + ", predecessor = " + prev[i]);
            }
 }
}
public class DFSbyColor{
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 4);
        g.DFS();
        g.print();
        
    }
}
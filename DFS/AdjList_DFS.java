import java.util.*;
public class AdjList_DFS{
    int v;
    List<List<Integer>> adjList;
    AdjList_DFS(int v)
    {
        this.v =v;
        adjList = new ArrayList<>(v);
        for(int i=0;i<v;i++)
        {
            adjList.add( new LinkedList<>());
        }
    }
    void addEdge(int u,int v)
    {
        adjList.get(u).add(v);
       
    }
    void DFS(int src, boolean vis[]){
        vis[src] = true;
        System.out.print(src+" ");
        for(int i=0;i<adjList.get(src).size();i++)
        {
            int child = adjList.get(src).get(i);
            if(!vis[child])
            {
                DFS(child, vis);
            }
        }
    }
    public static void main(String[] args) {
        int v = 5;
        boolean vis[] = new boolean[v];
        AdjList_DFS g = new AdjList_DFS(v);

        // Adding edges
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        g.DFS(0, vis);


    }
}
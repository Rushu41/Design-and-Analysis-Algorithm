import java.util.*;
public class BFS_with_List{
    int v;
    List<List<Integer>> adjList;

     BFS_with_List(int v) 
    {
        this.v = v;
        adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++)
     {
            adjList.add(new LinkedList<>());
        }
    }
    void addEdge(int u,int v)
    {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }
    void removeEdge(int u,int v)
    {
        adjList.get(u).remove((Integer)v);
        adjList.get(v).remove((Integer)u);
    }
    void bfs(int s)
    {
        boolean vis[] = new boolean[v];
        Queue<Integer>q = new LinkedList<>();
        q.add(s);
        vis[s] = true;
        while(!q.isEmpty())
        {
            int node = q.poll();
            System.out.print(node+" ");
            for(int i=0;i<adjList.get(node).size();i++)
            {
                int src = adjList.get(node).get(i);
                if(!vis[src])
                {
                    vis[src] = true;
                    q.add(src);
                }
            }
        }
    }
    public static void main(String[] args) {
        int v = 5;
        BFS_with_List graph = new BFS_with_List(v);

        // Adding edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.bfs(0);

    }
}
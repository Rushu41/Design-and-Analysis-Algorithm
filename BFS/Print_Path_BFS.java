import java.util.*;
public class Print_Path_BFS{
     int v;
    int[][] adjMatrix;
   Print_Path_BFS(int v)
    {
        this.v = v;
        adjMatrix = new int[v][v];
    }
    void addEdge(int u,int v)
    {
        adjMatrix[u][v] = 1;
        // adjMatrix[v][u] = 1;
    }

    void removeEdge(int u,int v)
    {
        adjMatrix[u][v] = 0;
        // adjMatrix[v][u] = 0;
    }
    void bfs(int s,int path[])
    {
        boolean vis[] = new boolean[v];
        Queue<Integer>q = new LinkedList<>();
        q.add(s);
        vis[s] = true;
       
        for(int i=0;i<v;i++)
        {
            path[i] = -1;
        }
       
        while(!q.isEmpty())
        {
            int node = q.poll();
            // System.out.print(node+" ");
            for(int i=0;i<v;i++)
            {
                if(adjMatrix[node][i] == 1 && !vis[i])
                {
                    vis[i] = true;
                    q.add(i);
                   
                    path[i] = node;
                }
            }
        }
       
    }

    void printPath(int s, int v, int prev[]) 
    {
        if (v == s) {
            System.out.print(s + " ");
        } else if (prev[v] == -1) {
            System.out.println("No path");
        } else {
            printPath(s, prev[v], prev);
            System.out.print(v + " ");
        }
    }

   
    public static void main(String[] args) {
        int v = 5;
        Print_Path_BFS graph = new Print_Path_BFS(v);

        // Adding edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        int path[] = new int[v];
        graph.bfs(0,path);
        graph.printPath(0, 3, path);
       
        
    }
}
import java.util.*;
public class BFS_with_Matrix{
    int v;
    int[][] adjMatrix;
    BFS_with_Matrix(int v)
    {
        this.v = v;
        adjMatrix = new int[v][v];
    }
    void addEdge(int u,int v)
    {
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;
    }

    void removeEdge(int u,int v)
    {
        adjMatrix[u][v] = 0;
        adjMatrix[v][u] = 0;
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
            for(int i=0;i<v;i++)
            {
                if(adjMatrix[node][i] == 1 && !vis[i])
                {
                    vis[i] = true;
                    q.add(i);
                }
            }
        }
    }
    public static void main(String[] args) {
         int v = 5;
        BFS_with_Matrix graph = new BFS_with_Matrix(v);

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
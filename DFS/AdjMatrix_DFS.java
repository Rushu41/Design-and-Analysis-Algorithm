public class AdjMatrix_DFS{
    int v;
    int[][] adjMatrix = new int[v][v];
    AdjMatrix_DFS(int v)
    {
        this.v =v;
        adjMatrix = new int[v][v];
    }
    void addEdge(int u,int v)
    {
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;  
    }
    void DFS(int src, boolean vis[]){
        vis[src] = true;
        System.out.print(src+" ");
        for(int i=0;i<v;i++)
        {
            if(adjMatrix[src][i] == 1 && !vis[i])
            {
                DFS(i, vis);
            }
        }
    }
    public static void main(String[] args) {
        int v = 5;
        boolean vis[] = new boolean[v];
        AdjMatrix_DFS g = new AdjMatrix_DFS(v);

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
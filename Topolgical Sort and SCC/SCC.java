import java.util.*;
class Graph{
    int v;
    List<List<Integer>>Adj;
    boolean[] vis;
    Stack<Integer> st;
    Graph(int v)
    {
        this.v = v;
        Adj =new ArrayList<>(v);
        vis = new boolean[v+1];
        for(int i=0;i<=v;i++)
        {
            Adj.add(new LinkedList<>());
        }
        st = new Stack<>();
    }

    void addEgde(int u,int v)
    {
        Adj.get(u).add(v);
    }

    void sortDFS(int src)
    {
      vis[src] = true;
    //   System.out.print(src+" ");
      for(int i: Adj.get(src)){
        if(!vis[i])
        {
           sortDFS(i);
        }
      }
       st.push(src);
       
    }

    void DFS(int src,boolean[] vis,List<Integer>comp)
    {
        vis[src] =true;
        comp.add(src);
        for(int i: Adj.get(src))
        {
            if(!vis[i])
            {
                DFS(i,vis,comp);
            }
        }
    }

    void toposort()
    {
        for(int i=0;i<=v;i++)
        {
           if(!vis[i])
           {
             sortDFS(i);
           }
        }

        // while(!st.empty())
        // {
        //     int n = st.pop();
        //     System.out.print(n+ " ");
        // }
    }

     Graph reverse()
    {
        Graph g = new Graph(v);
        for(int i=1;i<=v;i++)
        {
            for(int j : Adj.get(i))
            {
                g.Adj.get(j).add(i);
            }
        }
        return g;
    }
   

    List<List<Integer>> SCC(){
        toposort();
        Graph g = reverse();
        boolean[] vis = new boolean[v+1];
        Arrays.fill(vis,false);
        List<List<Integer>> scc = new ArrayList<>();
        while(!st.empty())
        {
            int node = st.pop();
            
            if(!vis[node])
            {
                List<Integer> comp= new ArrayList<>();
                g.DFS(node,vis,comp);
                scc.add(comp);
            }
        }
        return scc;
    }
}
public class SCC{
    public static void main(String[] args)
    {
       Scanner sc = new Scanner(System.in);
       int v = sc.nextInt();
       Graph g = new Graph(v);

       g.addEgde(0, 1);
       g.addEgde(1, 0);
       g.addEgde(2, 3);
       g.addEgde(3, 4);
       g.addEgde(4, 2);    
       g.addEgde(5, 6);
       g.addEgde(6, 5);
       List<List<Integer>> scc = g.SCC();
       for( List<Integer> lst : scc)
       {
         System.out.println(lst);
       }
    }
}
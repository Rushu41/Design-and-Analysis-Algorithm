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

    void toposort()
    {
        for(int i=0;i<=v;i++)
        {
           if(!vis[i])
           {
             sortDFS(i);
           }
        }

        while(!st.empty())
        {
            int n = st.pop();
            System.out.print(n+ " ");
        }
    }
}
public class TopoSort{
    public static void main(String[] args)
    {
       Scanner sc = new Scanner(System.in);
       int v = sc.nextInt();
       Graph g = new Graph(v);

       g.addEgde(0,1);
       g.addEgde(0,2);
       g.addEgde(1,2);
       g.addEgde(1,4);
       g.addEgde(1,7);
       g.addEgde(2,3);
       g.addEgde(2,4);
       g.addEgde(3,5);
       g.addEgde(4,5);
       g.addEgde(4,6);
       g.addEgde(4,7);
       g.addEgde(5,7);
       g.addEgde(6,7);
       g.addEgde(3,7);

       g.toposort();
    }
}
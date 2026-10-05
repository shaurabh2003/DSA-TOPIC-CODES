import java.util.ArrayList;

public class Dfs {
    static class Edge{
        int scr;
        int dest;
        int weight;

        Edge(int s,int d,int w){
            scr=s;
            dest=d;
            weight=w;
        }
    }
    
    static void createGraph(ArrayList<Edge>[]graph){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }

         //0 - vertex;
        graph[0].add(new Edge(0, 1, 1));

        //1 - vertex;
        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 2, 1));
        graph[1].add(new Edge(1, 3, 1));

        //2 - vertex;
        graph[2].add(new Edge(2, 1, 1));
        graph[2].add(new Edge(2, 3, 1));
        graph[2].add(new Edge(2, 4, 1));

        //3 - vertex;
        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 2, 1));

        //4 - vertex;
        graph[4].add(new Edge(4, 2, 1));
    }

    public static void dfs(ArrayList<Edge>[]graph,int curr,boolean visit[]){
        //visit:
        System.out.print(curr+" ");
        visit[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            if(!visit[e.dest]){
                dfs(graph, e.dest, visit);
            }
        }
    }
    public static void main(String[] args) {
        int v=5;
        ArrayList<Edge> []graph=new ArrayList[v];
        createGraph(graph);
        dfs(graph, 0, new boolean[v]);
    }
}

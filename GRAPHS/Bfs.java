import java.util.*;

public class Bfs {
    static class Edge{
        int src;
        int dest;
        int weight;
        public Edge(int s,int d,int w){
            src=s;
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

    public static void bfs(ArrayList<Edge>[]graph){
        Queue<Integer> q=new LinkedList<>();
        boolean visit[]=new boolean[graph.length];
        q.add(0);

        while (!q.isEmpty()) {
            int curr=q.remove();
            if(!visit[curr]){
                System.out.print(curr+" ");
                visit[curr]=true;
                for(int i=0;i<graph[curr].size();i++){
                    Edge e=graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
    }
    public static void main(String[] args) {
        int v=5;
        ArrayList<Edge> []graph=new ArrayList[v];
        createGraph(graph);
        bfs(graph);
    }
}

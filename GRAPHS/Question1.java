
//Has Path:-

import java.util.*;
public class Question1 {
    static class Edge{
        int src;
        int dest;
        int weight;

         Edge(int s,int d,int w){
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
    //time :- 0(V+E)
    public static boolean haspath(ArrayList<Edge>[]graph,int scr,int dest,boolean visit[]){
        if(scr==dest){
            return true;
        }
        visit[scr]=true;
        for(int i=0;i<graph[scr].size();i++){
            Edge e=graph[scr].get(i);
            //e.dest=neighbour;
            if(!visit[e.dest] && haspath(graph, e.dest, dest, visit)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int v=5;
        ArrayList<Edge>[]graph=new ArrayList[v];
        createGraph(graph);
        System.out.println(haspath(graph, 0, 4, new boolean[v]));
    }
}

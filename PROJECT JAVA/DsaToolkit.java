import java.util.*;
public class DsaToolkit {
    static Scanner sc = new Scanner(System.in);

    // ====== Singly Linked List ======
    static class LinkedList {
        class Node { 
            int data;
            Node next;
              Node(int d){
                data=d;
            } 
        } 
        private Node head;
        private int size=0;
        public void addFirst(int x){
             Node n=new Node(x);
             n.next=head;
             head=n; 
             size++; 
         }
        public void addLast(int x){
             Node n=new Node(x); 
             if(head==null){
                head=n;
                size++; 
                return;
            } 
            Node t=head; 
            while(t.next!=null)
            t=t.next; 
            t.next=n; 
            size++; 
        }
        public int removeFirst(){
             if(head==null) 
             return Integer.MIN_VALUE;
              int v=head.data;
               head=head.next;
                size--;
                 return v;
        }
        public void reverse(){
             Node prev=null;
             Node cur=head;
              while(cur!=null){
                 Node nxt=cur.next;
                  cur.next=prev; 
                   prev=cur; 
                   cur=nxt;
                } 
                head=prev; 
        }
        public void display(){
             Node t=head;
              System.out.print("LinkedList: "); 
              while(t!=null){
                 System.out.print(t.data+" -> ");
                  t=t.next; 
                } 
                System.out.println("null"); 
            }
        public int size(){
             return size; 
        }
    }

    // ====== Stack (array) ======
    static class StackArr {
        int[] a; 
        int top=-1;
        StackArr(int cap){
             a=new int[cap];
         }
        public void push(int x){
             if(top+1==a.length) {
                 System.out.println("Stack Overflow");
                  return;
                } 
                a[++top]=x;
            }
        public int pop(){
             if(top==-1) {
                 System.out.println("Stack Empty");
                  return Integer.MIN_VALUE; 
                } 
                return a[top--];
             }
        public int peek(){ 
            if(top==-1)
             return Integer.MIN_VALUE;
              return a[top];
             }
        public boolean isEmpty(){
             return top==-1; 
            }
    }

    // ====== Queue (circular array) ======
    static class CircularQueue{
        int[] a; int head=0, tail=0, size=0;
        CircularQueue(int cap){ 
             a=new int[cap]; }
        public void offer(int x){ 
            if(size==a.length){
                 System.out.println("Queue Full"); 
                 return;
                } 
                a[tail]=x; 
                tail=(tail+1)%a.length;
                 size++;
                 }
        public int poll(){
             if(size==0){
                 System.out.println("Queue Empty");
                  return Integer.MIN_VALUE;
                } 
                int v=a[head]; 
                head=(head+1)%a.length;
                 size--; 
                 return v; }
        public int peek(){
             if(size==0) return Integer.MIN_VALUE; 
             return a[head];
             }
    }

    // ====== Binary Search Tree ======
    static class BST{
        class Node{ int val; Node left,right; Node(int v){val=v;} }
        Node root;
        public void insert(int v){
             root = insertRec(root,v);
             }
        private Node insertRec(Node node,int v){
             if(node==null) 
                return new Node(v);
             if(v<node.val) 
                node.left=insertRec(node.left,v);
             else 
                node.right=insertRec(node.right,v); 
            return node; 
        }
        public boolean search(int v){
             return searchRec(root,v); 
            }
        private boolean searchRec(Node node,int v){
             if(node==null) 
                return false; 
            if(node.val==v) 
                return true; 
            if(v<node.val) 
                return searchRec(node.left,v); 
            return searchRec(node.right,v);
         }
        public void inorder(){
             inorderRec(root); 
             System.out.println();
             }
        private void inorderRec(Node node){
             if(node==null) 
                return; 
            inorderRec(node.left); 
            System.out.print(node.val+" "); 
            inorderRec(node.right);
         }
        public void delete(int v){
             root = deleteRec(root,v);
             }
        private Node deleteRec(Node node,int v){
             if(node==null) 
                return null; 
            if(v<node.val) 
                node.left=deleteRec(node.left,v); 
            else if(v>node.val) 
                node.right=deleteRec(node.right,v);
             else {
            if(node.left==null) 
                return node.right; 
            else if(node.right==null) 
                return node.left; 
            node.val = minValue(node.right); 
            node.right = deleteRec(node.right,node.val);
        } 
        return node; 
    }
        private int minValue(Node node){
             Node cur=node; 
             while(cur.left!=null)
                 cur=cur.left; 
                return cur.val; 
            }
    }

    // ====== Graph (adj list) with BFS/DFS ======
    static class Graph{
        int n; 
        ArrayList<Integer>[] adj;
        Graph(int n){ 
            this.n=n; 
            adj = new ArrayList[n]; 
            for(int i=0;i<n;i++) 
                adj[i]=new ArrayList<>(); 
            }
        void addEdge(int u,int v){
             adj[u].add(v);
             adj[v].add(u);
             }
        void bfs(int start){
    boolean[] vis = new boolean[n];
   Queue<Integer> q = new java.util.LinkedList<>(); // fixed line 
    q.add(start);
    vis[start] = true;
    System.out.print("BFS: ");
    while(!q.isEmpty()){
        int u = q.poll();
        System.out.print(u+" ");
        for(int w: adj[u]){
            if(!vis[w]){
                vis[w] = true;
                q.add(w);
            }
        }
    }
    System.out.println();
}

        void dfs(int start){
             boolean[] vis=new boolean[n]; 
             System.out.print("DFS: ");
             dfsRec(start,vis); 
             System.out.println(); 
        }
        void dfsRec(int u, boolean[] vis){ 
            vis[u]=true; 
            System.out.print(u+" "); 
            for(int w:adj[u]) 
                if(!vis[w]) 
                    dfsRec(w,vis);
        }
    }

    // ====== Sorting Algorithms (utility) ======
    static class Sorts{
        static void bubble(int[] a){
             int n=a.length;
              for(int i=0;i<n-1;i++)
                 for(int j=0;j<n-1-i;j++) 
                if(a[j]>a[j+1])
                     swap(a,j,j+1);
        }
        static void insertion(int[] a){
             for(int i=1;i<a.length;i++){
                 int key=a[i]; int j=i-1; 
                 while(j>=0 && a[j]>key){
                     a[j+1]=a[j]; j--;
                     } 
                     a[j+1]=key;
                     } 
         }
        static void mergeSort(int[] a){
             mergeSortRec(a,0,a.length-1); 
        }
        static void mergeSortRec(int[] a,int l,int r){
             if(l>=r) 
                return;
             int m=(l+r)/2;
              mergeSortRec(a,l,m); 
              mergeSortRec(a,m+1,r); 
              merge(a,l,m,r); 
        }
        static void merge(int[] a,int l,int m,int r){
             int n1=m-l+1, n2=r-m;
              int[] L=new int[n1];
               int[] R=new int[n2];
                for(int i=0;i<n1;i++) 
                    L[i]=a[l+i]; 
                for(int j=0;j<n2;j++) 
                    R[j]=a[m+1+j]; 
                int i=0,j=0,k=l;
                 while(i<n1 && j<n2) 
                    a[k++]= (L[i]<=R[j])?L[i++]:R[j++];
                 while(i<n1) 
                    a[k++]=L[i++];
                 while(j<n2) 
                    a[k++]=R[j++]; 
        }
        static void quickSort(int[] a){
             quickRec(a,0,a.length-1);
        }
        static void quickRec(int[] a,int low,int high){ 
            if(low<high){
                 int p=partition(a,low,high);
                  quickRec(a,low,p-1);
                   quickRec(a,p+1,high);
                }
        }
        static int partition(int[] a,int l,int h){ 
            int pivot=a[h];
             int i=l-1;
              for(int j=l;j<h;j++){
                 if(a[j]<=pivot){
                     i++; 
                     swap(a,i,j);
                     } 
                    } 
                    swap(a,i+1,h); 
                    return i+1; 
        }
        static void swap(int[] a,int i,int j){
             int t=a[i];
                 a[i]=a[j];
                 a[j]=t; 
        }
    }

    // ====== Small demo / menu ======
    public static void main(String[] args){
        while(true){
            System.out.println("\n--- DSA Toolkit Menu ---");
            System.out.println("1) LinkedList demo");
            System.out.println("2) Stack demo");
            System.out.println("3) Queue demo");
            System.out.println("4) BST demo");
            System.out.println("5) Graph demo (BFS/DFS)");
            System.out.println("6) Sorting demo");
            System.out.println("0) Exit");
            System.out.print("Choice: "); 
            int ch = Integer.parseInt(sc.nextLine());
            if(ch==0) {
                 System.out.println("Bye"); break; 
                }
            switch(ch){
                case 1: linkedListDemo(); break;
                case 2: stackDemo(); break;
                case 3: queueDemo(); break;
                case 4: bstDemo(); break;
                case 5: graphDemo(); break;
                case 6: sortingDemo(); break;
                default: System.out.println("Invalid");
            }
        }
    }

    static void linkedListDemo(){
         LinkedList ll = new LinkedList();
          System.out.println("Adding 10,20,30"); 
          ll.addLast(10);
           ll.addLast(20);
            ll.addLast(30);
             ll.display();
              System.out.println("addFirst(5)");
               ll.addFirst(5);
                ll.display();
                 System.out.println("removeFirst -> " + ll.removeFirst());
                  ll.display(); 
                  System.out.println("reverse:");
                   ll.reverse();
                    ll.display();  
                    System.out.println("Size of link list :- "+  ll.size());
                 }

    static void stackDemo(){ 
        StackArr s = new StackArr(5);
         s.push(10); 
         s.push(20);
         s.push(30);
        System.out.println("pop -> "+s.pop()); 
        System.out.println("peek -> "+s.peek()); 
    }

    static void queueDemo(){ 
        CircularQueue q = new CircularQueue(5); 
        q.offer(1); 
        q.offer(2); 
        q.offer(3); 
        System.out.println("poll -> "+q.poll());
        System.out.println("peek -> "+q.peek());
     }

    static void bstDemo(){
         BST t = new BST();
          int[] vals={50,30,70,20,40,60,80}; 
          for(int v:vals) 
            t.insert(v); 
         System.out.print("Inorder: "); 
         t.inorder();
         System.out.println("Search 60: " + t.search(60)); 
         System.out.println("Delete 70");
         t.delete(70);
         System.out.print("Inorder after delete: "); 
         t.inorder(); 
    }

    static void graphDemo(){ 
        Graph g = new Graph(6);
         g.addEdge(0,1); 
         g.addEdge(0,2); 
         g.addEdge(1,3); 
         g.addEdge(2,4); 
         g.addEdge(3,5); 
         g.bfs(0); 
         g.dfs(0);
    }

    static void sortingDemo(){
         int[] a = {5,2,9,1,5,6}; 
         System.out.println("Original: "+Arrays.toString(a));
          int[] b=a.clone();
           Sorts.bubble(b);
            System.out.println("Bubble: "+Arrays.toString(b));
             b=a.clone();
              Sorts.insertion(b);
               System.out.println("Insertion: "+Arrays.toString(b)); 
                b=a.clone();
                 Sorts.mergeSort(b); 
                  System.out.println("Merge: "+Arrays.toString(b));
                   b=a.clone(); 
                    Sorts.quickSort(b); 
                     System.out.println("Quick: "+Arrays.toString(b)); 
    }
}


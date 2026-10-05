
//Build a BST....

import java.util.*;

public class Question1 {
    static class Node{
        int data;
        Node left,right;
        Node (int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    public static Node insert(Node root,int val){
        if(root==null){
            root=new Node(val);
            return root;
        }

        if(root.data>val){
            //left subtree..
            root.left=insert(root.left, val);
        }else{
            //right subtree..
            root.right=insert(root.right, val);
        }
        return root;
    }
    public static void inOrder(Node root){
        if(root==null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    //Question 2 ....
    //Search a Key in BST..
    public static boolean search(Node root,int key){
        if(root==null){
            return false;
        }
        if(root.data==key){
            return true;
        }
        //left SubTree....
        if(root.data>key){
            return search(root.left, key);
        }else{//right SubTree....
            return search(root.right, key);
        }
    }

    //Question 3....
    //Delete a Node:-

    public static Node delete(Node root,int val){
        if(root.data<val){
            root.right=delete(root.right, val);
        }else if(root.data>val){
            root.left=delete(root.left, val);
        }else{
            //voila..
            //Case 1:-(Leaf Node):-
            if(root.left==null&&root.right==null){
                return null;
            }
            //Case 2: (Single child):-
            if(root.left==null){
                return root.right;
            }else if(root.right==null){
                return root.left;
            }
            //Case 3:-(Both Children):-
            Node IS=findInOrderSuccessor(root.right);//right me minim hota hai ..
            root.data=IS.data;
            root.right=delete(root.right, IS.data);
        }
        return root;
    } 
    //findInOrderSuccessor...
    public static Node findInOrderSuccessor(Node root){
        while (root.left!=null) {
            root=root.left;
        }
        return root;
    }

    //Question 4....
    //Print in a Range K1=5 , K2=7.

    public static void printInRange(Node root,int k1,int k2){
        if(root==null){
            return;
        }
        if(root.data>=k1 && root.data<=k2){
            printInRange(root.left, k1, k2);
            System.out.print(root.data+" ");
            printInRange(root.right, k1, k2);
        }else if(root.data<k1){
            printInRange(root.left, k1, k2);
        }else{
            printInRange(root.right, k1, k2);
        }
    }

    //Question 5...
    //Root to Leaf Paths..

    public static void printRoot2Path(Node root,ArrayList<Integer> path){
        if(root==null){
            return;
        }
        path.add(root.data);
        if(root.left==null && root.right==null){
            printPath(path);
        }
        printRoot2Path(root.left, path);
        printRoot2Path(root.right, path);
        path.remove(path.size()-1);
    }
    public static void printPath(ArrayList<Integer> path){
        for(int i=0;i<path.size();i++){
            System.out.print(path.get(i)+"->");
        }
        System.out.println("null");
    }

    //Question 6:-  
    //Validate BST:-

    public static boolean isValidateBST(Node root,Node min,Node max){
        if(root==null){
            return false;
        }
        if(min!=null&&root.data<=min.data){
            return false;
        }else if(max!=null&&root.data>=max.data){
            return false;
        }
        return isValidateBST(root.left, min, root) && isValidateBST(root.right, root, max);
    }
    public static void main(String []args){
        int value[]={2,1,3};
        Node root=null;
        for(int i=0;i<value.length;i++){
            root=insert(root, value[i]);

        }
        inOrder(root);
        System.out.println();
        
        if(isValidateBST(root, null, null)){
            System.out.println("Validate BST");
        }else{
            System.out.println("NOt Valid BST");
        }
    }
}

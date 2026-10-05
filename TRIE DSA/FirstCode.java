
public class FirstCode {
    static class Node{
        Node children[]=new Node[26];
        boolean eow=false;
        Node(){
            for(int i=0;i<26;i++){
                children[i]=null;
            }
        }
    }
    public static Node root=new Node();
    
    //Insert Trie:-

    public static void insert(String word){
        Node curr=root;
        for(int level=0;level<word.length();level++){
            int idx=word.charAt(level)-'a';
            if(curr.children[idx]==null){
                curr.children[idx]=new Node();
            }
            curr=curr.children[idx];
        }
        curr.eow=true;
    }

    //Search Trie:-

    public static boolean search(String key){
        Node curr=root;
        for(int level=0;level<key.length();level++){
            int idx=key.charAt(level)-'a';
            if(curr.children[idx]==null){
                return false;
            }
            curr=curr.children[idx];
        }
        return curr.eow=true;
    }

    //Question 1 :-
    //Word Break problem 

    public static boolean wordBreak(String key){
        if(key.length()==0){
            return true;
        }
        for(int i=1;i<=key.length();i++){
            if(search(key.substring(0,i)) && wordBreak(key.substring(i))){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        //String word[]={"the","a","there","their","any","thee"};
        //for(int i=0;i<word.length;i++){
          //  insert(word[i]);
        //}
        //System.out.println(search("thee"));
        //System.out.println(search("mam"));
        String arr[]={"i","like","sum","sumsung","mobile","ice"};
        for(int i=0;i<arr.length;i++){
            insert(arr[i]);
        }
        String key="ilikesumsung";
        System.out.println("Question 1 :- Word Break Problem :-");
        System.out.println("Output :-"+wordBreak(key));

    }
}

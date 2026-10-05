import java.util.Scanner;
public class Input {
    public static void define(){
       String arr="saurabh singh parmar";
        for(int i=0;i<arr.length(); i++)
        {
            System.out.print(arr.charAt(i)+" ");
        }
    }
    public static void main(String []args){
       Scanner sc=new Scanner(System.in);
       String name=sc.nextLine();
       String name2=sc.next();
       System.out.println(name);
       System.out.println(name2);
        define();
    }
    
}

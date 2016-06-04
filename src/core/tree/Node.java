package core.tree;

/**
 * Created by mma on 6/4/2016.
 */
public class Node {
    private CharSequence chars ;
    private int number ;
    private Node left ;
    private Node right ;

    public Node(CharSequence chars , int number ){
        this.chars = chars ;
        this.number = number ;
    }

    public Node getLeft(){
        return left ;
    }

    public Node getRight(){
        return right ;
    }

    public void setLeft(Node node){
        left = node ;
    }

    public void setRight( Node node ){
        right = right ;
    }
}

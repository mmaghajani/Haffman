package core.tree;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by mma on 6/4/2016.
 */
public class BinaryTree implements Serializable{
    private Node root ;

    public BinaryTree(){

    }

    public static BinaryTree createHaffmanTree(HashMap<Character , Integer> numOfChars ){

        return null ;
    }

    public Node getRoot(){
        return root ;
    }

    public class Node {
        private CharSequence label ;
        private int number ;
        private Node left ;
        private Node right ;

        public Node(CharSequence label , int number ){
            this.label = label ;
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

        public CharSequence getLabel(){
            return label ;
        }

        public int getNum(){
            return number ;
        }
    }

}

package core.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mma on 6/4/2016.
 */
public class BinaryTree implements Serializable {
    private Node root;
    private ArrayList<Node> nodes;

    public BinaryTree() {
        nodes = new ArrayList<>();
    }

    public BinaryTree createHaffmanTree(HashMap<Character, Integer> numOfChars) {
        for (Character c : numOfChars.keySet()) {
            String s = "";
            s += c;
            Node node = new Node(s, numOfChars.get(c));
            nodes.add(node);
        }


        while (isRemainUnMark()) {
            int[] temp = new int[2];
            temp[0] = 0;
            temp[1] = 0;
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            for (int i = 0; i < nodes.size(); i++) {
                if (!nodes.get(i).isMark()) {
                    if (nodes.get(i).getNum() < min1) {
                        min2 = min1;
                        min1 = nodes.get(i).getNum();
                        temp[1] = temp[0];
                        temp[0] = i;
                    } else if (nodes.get(i).getNum() >= min1 && nodes.get(i).getNum() < min2) {
                        min2 = nodes.get(i).getNum();
                        temp[1] = i;
                    }
                }
            }

            nodes.get(temp[0]).setMark(true);
            nodes.get(temp[1]).setMark(true);

            Node node = new Node( nodes.get(temp[0]).getLabel().toString() + nodes.get(temp[1]).getLabel() ,
                    nodes.get(temp[0]).getNum() + nodes.get(temp[1]).getNum()) ;
            node.setLeft(nodes.get(temp[0]));
            node.setRight(nodes.get(temp[1]));

            nodes.add(node);

        }

        for( Node node : nodes ){
            if( node.isMark() == false )
                root = node ;
        }
        return this;
    }

    public ArrayList<Node> getNodes(){
        return nodes ;
    }

    private boolean isRemainUnMark() {
        int counter = 0 ;
        for( Node node : nodes ){
            if( !node.isMark() )
                counter++ ;
        }

        if( counter == 1 )
            return false ;
        else
            return true ;
    }

    public Node getRoot() {
        return root;
    }

    public void print(){
        for( Node node : nodes ){
            if( node.getLeft() != null ){
                System.out.println( node.getLabel() + " => " + node.getLeft().getLabel() + " , " + node.getRight().getLabel() ) ;
            }
        }
    }

    public class Node implements Serializable{
        private CharSequence label;
        private int number;
        private Node left;
        private Node right;
        private boolean mark = false;

        public Node(CharSequence label, int number) {
            this.label = label;
            this.number = number;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setLeft(Node node) {
            left = node;
        }

        public void setRight(Node node) {
            right = node;
        }

        public CharSequence getLabel() {
            return label;
        }

        public int getNum() {
            return number;
        }

        public boolean isMark() {
            return mark;
        }

        public void setMark(boolean b) {
            mark = b;
        }
    }

}

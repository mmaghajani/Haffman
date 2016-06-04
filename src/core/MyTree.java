package core;

import sun.reflect.generics.tree.CharSignature;
import sun.reflect.generics.tree.Tree;

/**
 * Created by mma on 6/4/2016.
 */
public class MyTree{
    private CharSequence chars ;
    private int number ;
    private MyTree left ;
    private MyTree right ;

    public MyTree( CharSequence chars , int number ){
        this.chars = chars ;
        this.number = number ;
    }
}

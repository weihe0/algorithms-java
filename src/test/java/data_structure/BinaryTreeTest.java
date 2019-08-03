package data_structure;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {
    BinaryTree t;
    TreeNode root;
    public BinaryTreeTest(){
        t=new BinaryTree();
        Integer[] a={1,2,3,4,5,null,null};
        root=t.build(a);
    }

    @Test
    public void order(){
        t.preOrder(root);
        t.inOrder(root);
        t.postOrder(root);
    }

    @Test
    public void validate(){
        Integer[] a={2,1,3,null,null,null,null};
        root=t.build(a);
        t.isValidBST(root);
    }
}
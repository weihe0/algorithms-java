package data_structure;

import org.junit.Test;

import static org.junit.Assert.*;

public class CodecTest {
    TreeNode root=new TreeNode(1,
            new TreeNode(2),
            new TreeNode(3,
                    new TreeNode(4),
                    new TreeNode(5))
    );
    TreeNode root2=new TreeNode(1,
            new TreeNode(2),new TreeNode(3));
    Codec c=new Codec();
    @Test
    public void serialize() {
        System.out.println(c.serialize(root));
        TreeNode root3=c.deserialize(c.serialize(root));
        System.out.println(c.serialize(root3));
    }


    @Test
    public void deserialize() {
    }
}
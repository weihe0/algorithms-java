package data_structure;

import java.util.*;
public class BinaryTree {
    int maxSum =Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root){
        maxHalfPathSum(root);
        return maxSum;
    }
    private int maxHalfPathSum(TreeNode node){
        if(node==null){
            return 0;
        }
        int leftSum=Integer.max(maxHalfPathSum(node.left),0);
        int rightSum=Integer.max(maxHalfPathSum(node.right),0);
        maxSum=Integer.max(maxSum,leftSum+node.val+rightSum);
        return Integer.max(leftSum,rightSum)+node.val;
    }

    public void inOrder(TreeNode root){
        Stack<TreeNode> s=new Stack<>();
        TreeNode node=root;
        while(node!=null||!s.empty()){
            if(node!=null){
                s.push(node);
                node=node.left;
            }else {
                node=s.pop();
                System.out.print(node.val+" ");
                node=node.right;
            }
        }
        System.out.println();
    }

    public void preOrder(TreeNode root){
        Stack<TreeNode> s=new Stack<>();
        s.push(root);
        while(!s.empty()){
            TreeNode node=s.pop();
            if(node!=null){
                System.out.print(node.val+" ");
                s.push(node.right);
                s.push(node.left);
            }
        }
        System.out.println();
    }

    public void postOrder(TreeNode root){
        Stack<TreeNode> s=new Stack<>();
        s.push(root);
        TreeNode node,pre=root;
        while(!s.empty()){
            node=s.peek();
            if(node.left!=null&&pre!=node.left&&pre!=node.right){
                s.push(node.left);
            }else if(node.right!=null&&pre!=node.right){
                s.push(node.right);
            }else {
                System.out.print(node.val+" ");
                pre=node;
                s.pop();
            }
        }
    }

    int i;
    public TreeNode build(Integer[] a){
        if(a[i]==null){
            return null;
        }else {
            TreeNode root=new TreeNode(a[i]);
            i++;
            root.left=build(a);
            root.right=build(a);
            return root;
        }
    }

    public boolean isValidBST(TreeNode root){
        Stack<TreeNode> s=new Stack<>();
        TreeNode node=root;
        long preVal=(long)Integer.MIN_VALUE-1;
        while(node!=null||!s.empty()){
            while(node!=null){
                s.push(node);
                node=node.left;
            }
            node=s.pop();
            if(node.val>preVal){
                preVal=node.val;
                node=node.right;
            }else {
                return false;
            }
        }
        return true;
    }

    public int lengthOfLongestSubstring(String s) {
        int[] next=new int[128];
        int len=0;
        for(int i=0,j=0;j<s.length();j++){
            if(next[s.charAt(j)]>i){
                i=next[s.charAt(j)];
            }
            if(j-i+1>len){
                len=j-i+1;
            }
            next[s.charAt(j)]=j+1;
        }
        return len;
    }

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> s=new Stack<>();
        TreeNode n=root;
        int j=0;
        while(n!=null||!s.isEmpty()){
            while (n!=null){
                s.push(n);
                n=n.left;
            }
            n=s.pop();
            j++;
            if(j==k){return n.val;}
            n=n.right;
        }
        return Integer.MIN_VALUE;
    }

    public List<Integer> Morris(TreeNode root){
        List<Integer> l=new LinkedList<>();
        TreeNode n=root;
        while(n!=null){
            if(n.left==null){
                l.add(n.val);
                n=n.right;
            }else {
                TreeNode m=n.left;
                while(m.right!=null){m=m.right;}
                m.right=n;
                TreeNode t=n.left;
                n.left=null;
                n=t;
            }
        }
        return l;
    }
}

class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){this.val=val;}
    TreeNode(int val,TreeNode left,TreeNode right){
        this.val=val;
        this.left=left;
        this.right=right;
    }
}

// reverse Polish notation
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Stack<TreeNode> stk=new Stack<>();
        stk.push(root);
        StringBuffer sb=new StringBuffer();
        while(!stk.isEmpty()){
            TreeNode node=stk.pop();
            if(node==null){
                sb.append("null ");
            }else {
                sb.append(node.val).append(' ');
                stk.push(node.right);
                stk.push(node.left);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    int i=0;
    public TreeNode deserialize(String data) {
        i=0;
        return _deserialize(data);
    }
    private TreeNode _deserialize(String data){
        StringBuffer sb=new StringBuffer();
        int j;
        for(j=i;data.charAt(j)!=' ';j++);
        String nodeStr=data.substring(i,j);
        i=j+1;
        if(nodeStr.compareTo("null")==0){
            return null;
        }else {
            TreeNode node=new TreeNode(Integer.valueOf(nodeStr));
            node.left=_deserialize(data);
            node.right=_deserialize(data);
            return node;
        }
    }

}
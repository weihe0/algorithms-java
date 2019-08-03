package data_structure;
import com.sun.source.tree.Tree;

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
}

class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){this.val=val;}
}

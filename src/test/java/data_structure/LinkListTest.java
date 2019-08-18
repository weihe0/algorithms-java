package data_structure;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkListTest {
    LinkList l=new LinkList();
    @Test
    public void copyRandomList() {
        Node first=new Node(1,null,null);
        Node second=new Node(2,null,null);
        first.next=second;
        first.random=second;
        second.random=second;
        Node newHead=l.copyRandomList(first);
        for(Node n=newHead;n!=null;n=n.next){
            System.out.printf("val:%d ",n.val);
            System.out.printf("next:%s ",n.next==null?"null":n.next.val);
            System.out.printf("random:%s\n",n.random==null?"null":n.random.val);
        }
    }
}
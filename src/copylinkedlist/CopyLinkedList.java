/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
You are given a Double Link List with one pointer of each node pointing to the
next node just like in a single link list. The second pointer however CAN point 
to any node in the list and not just the previous node. Now write a program in
O(n) time to duplicate this list. That is, write a program which will create a
copy of this list.
*/

/* Solution involves using a HashMap to map node in current list to cloned node */

package copylinkedlist;

import java.util.*;

/**
 *
 * @author ashab
 */
public class CopyLinkedList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        LinkedListNode node1 = new LinkedListNode(1);
        LinkedListNode node2 = new LinkedListNode(2);
        LinkedListNode node3 = new LinkedListNode(3);
        LinkedListNode node4 = new LinkedListNode(4);
        LinkedListNode node5 = new LinkedListNode(5);
        
        // Establish links
        
        node1.Next = node2;
        node1.Arbitrary = node3;
        
        node2.Next = node3;
        node2.Arbitrary = node1;
        
        node3.Next = node4;
        node3.Arbitrary = node5;
        
        node4.Next = node5;
        node4.Arbitrary = node3;
        
        node5.Arbitrary = node2;
        
        LinkedListNode newHead = DeepCopy(node1);
        
        LinkedListNode current = newHead;
        
        while (current != null)
        {
            System.out.println("For Node " + current.Data + " Arbitrary node is " + current.Arbitrary.Data);
            current = current.Next;
        }
    }
    
    static LinkedListNode DeepCopy(LinkedListNode head)
    {
       LinkedListNode current = head;

       LinkedListNode copyHead = Clone(current);

       LinkedListNode copyCurrent = copyHead;
       
       while (current.Next != null)
       {
          copyCurrent.Next = Clone(current.Next);
          copyCurrent.Arbitrary = Clone(current.Arbitrary);

          current = current.Next;
          copyCurrent = copyCurrent.Next;    
       }

       copyCurrent.Arbitrary = Clone(current.Arbitrary);
       
       return copyHead;
    }

    static LinkedListNode Clone(LinkedListNode node)
    {
        if (node == null)
        {
            return null;
        }

        HashMap<LinkedListNode, LinkedListNode> cache;

        cache = new HashMap<LinkedListNode, LinkedListNode>();

        if(cache.containsKey(node))
        {
           return cache.get(node);
        }

        LinkedListNode newNode = new LinkedListNode(node.Data);
        cache.put(node, newNode);

        return newNode; 
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd_tax;

/**
 *
 * @author ASUS
 */
public class LinkedList {
    class Node{
        TaxPayer info;
        Node next;

        Node(){
        }

        Node(TaxPayer x, Node n){
            info = x;
            next = n;
        }  
    }
    
    public Node head = null;
    public Node tail = null;

    public boolean isEmpty(){
        return (head == null);
    }
      
    public int length(){
        Node currNode = head;
        int count = 0;
        while(currNode!=null){
            count++;
            currNode = currNode.next;
        }
        return count;
    }

    public void clear(){
        head = tail = null;
    }
    
    public void addToEnd(TaxPayer x){
        if(isEmpty())
            head = tail = new Node(x,null);
        else{
            Node q = new Node(x,null);
            tail.next = q;
            tail = q;
        }
    }
    
    public void display(){
        Node p = head;
        while(p != null){
            p.info.print();
            p = p.next;
        }
    }
    
    public TaxPayer search(String code){
        Node currNode = head;
        while(currNode != null){
            if(currNode.info.getCode().equalsIgnoreCase(code)) return currNode.info; 
            currNode = currNode.next;
        }
        return null;
    }
    public boolean checkExistCode(LinkedList list, String code){
        Node p = head;
        while(p != null){
            if(p.info.getCode().equalsIgnoreCase(code)) return true;
            p = p.next;
        }
        return false;
    }
    
    public void delete(String code){
        Node prevNode = null;
        Node currNode = head;
        //Case 1: not found key        
        if(currNode == null){
            System.err.println("<" + code + " not found>");
        }
        //Case 2: if head node holds the code
        else if(currNode != null && currNode.info.getCode().equalsIgnoreCase(code)){
            head = currNode.next;
            System.err.println("<" + code + " found and deleted>");
        }   
        //Case 3: somewhere among the list
        else{
            //Search for the key to be deleted
            //keep track of the previous
            //as it is needed to change currNode.next
            while(currNode != null && !currNode.info.getCode().equalsIgnoreCase(code)){
                prevNode = currNode;
                currNode = currNode.next;
            }           
            //If the key was present, it should be at currNode
            //Therefore the currNode shall not be null
            if(currNode != null){
                //Since the key is at currNode
                //Unlink currNode from linked list
                prevNode.next = currNode.next;
                System.err.println("<" + code + " found and deleted>");
            }
        }
    }
    
    public void sortCode(){
        for(Node m = head; m != null; m = m.next){
            for(Node n = m.next; n != null;n = n.next){
                if(n.info.getCode().compareToIgnoreCase(m.info.getCode()) < 0){
                    TaxPayer swap = m.info;
                    m.info = n.info;
                    n.info = swap;
                }
            }
        }
    }
    
    public void addToBeginning(TaxPayer x){
        if(isEmpty())
            head = tail = new Node(x,null);
        else{
            Node q = new Node(x,null);
            q.next = head;
            head = q;
        }
    }
    
    public void addAfterPosition(TaxPayer x,int position){
        Node nextNode;
        Node currNode = head;
        Node q = new Node(x,null);
        int count = 1;
        //If list is empty, add the node at position 0
        switch (position) {
            case -1:
                break;
            case 0:
                head = tail = new Node(x,null);
                break;
            default:
                while(currNode != null){
                    nextNode = currNode.next;
                    //If the position is after tail(max), add at end
                    if(currNode != null && nextNode == null){                       
                        tail.next = q;
                        tail = q;
                        break;
                    }
                    //If the position is between min and max, add after the position
                    if(count == position){
                        currNode.next = q;
                        q.next = nextNode ;
                        break;
                    }
                    currNode = currNode.next;
                    count++;
                }   break;
        }
    }
    
    public void deleteByPosition(int position){
        Node prevNode = null;
        Node currNode = head;
        int count;

        //Delete node if list has one node
        if(position == 1 && currNode != null && currNode.next == null){
            head = tail = null;
            System.err.println("<Tax payer info at position " + position + " deleted>");
        }
        else{
            //Delete node at the head
            if(position == 1 && currNode.next != null){
                head = currNode.next;
                System.err.println("<Tax payer info at position " + position + " is deleted>");
                return;
            }
            
            //Delete node at position k among head and tail
            count = 1;
            while(currNode != null && count != position){
                prevNode = currNode;
                currNode = currNode.next;
                count++;
            }            
            
            if(currNode != null){
                prevNode.next = currNode.next;
                System.err.println("<Tax payer info at position " + position + " is deleted>");
            }
        }
    }
}

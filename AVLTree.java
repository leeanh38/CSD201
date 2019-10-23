/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd_Tax2;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author HuyLee
 */
public class AVLTree {
    
    Node root;
    
    class Node{
        TaxPayer key;
        int height;
        Node left, right;

        public Node() {
        }

        public Node(TaxPayer key) {
            this.key = key;
            this.height = 1;
        }        
    }
    
    int height(Node node) { 
        if (node == null) 
            return 0; 
        return node.height; 
    } 
    
    int max(int a, int b) { 
        return (a > b)? a : b;
    } 
    
     Node rightRotate(Node y) { 
        Node x = y.left; 
        Node T2 = x.right;
        x.right = y; 
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
        return x; 
    } 
     
    Node leftRotate(Node y) { 
        Node x = y.right; 
        Node T2 = x.left;
        x.left = y; 
        y.right = T2; 
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1;
        return x; 
    } 
    
    int getBalance(Node node) { 
        if (node == null) 
            return 0; 
        return height(node.left) - height(node.right); 
    } 
    
    Node insert(Node node, TaxPayer key) {
        if (node == null) 
            return (new Node(key)); 
  
        if (key.getCode().compareToIgnoreCase(node.key.getCode()) < 0) 
            node.left = insert(node.left, key); 
        else if (key.getCode().compareToIgnoreCase(node.key.getCode()) > 0) 
            node.right = insert(node.right, key); 
        else 
            return node;
        node.height = 1 + max(height(node.left),height(node.right)); 
  
        int balance = getBalance(node); 
        if (balance > 1 && key.getCode().compareToIgnoreCase(node.left.key.getCode()) < 0) 
            return rightRotate(node);
        
        if (balance < -1 && key.getCode().compareToIgnoreCase(node.right.key.getCode()) > 0) 
            return leftRotate(node);
        
        if (balance > 1 && key.getCode().compareToIgnoreCase(node.left.key.getCode()) > 0) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
        
        if (balance < -1 && key.getCode().compareToIgnoreCase(node.right.key.getCode()) < 0) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
        return node;
    }
    
    Node minValueNode(Node node){  
        Node current = node; 
        while (current.left != null)  
            current = current.left;  
        return current;  
    }
    
    Node delete(Node node, String key){
        if(node == null)
            return node;
        if(node.key.getCode().compareToIgnoreCase(key) > 0){
            node.left = delete(node.left, key);
        }
        if(node.key.getCode().compareToIgnoreCase(key) < 0){
            node.right = delete(node.right, key);
        }
        else{
            if(node.right == null)
                return node.left;
            if(node.left == null)
                return node.right;
            node.key = minValueNode(node.right).key;
        }
        node.height = 1 + max(height(node.left),height(node.right)); 
  
        int balance = getBalance(node); 
        if (balance > 1 && key.compareToIgnoreCase(node.left.key.getCode()) < 0) 
            return rightRotate(node);
        
        if (balance < -1 && key.compareToIgnoreCase(node.right.key.getCode()) > 0) 
            return leftRotate(node);
        
        if (balance > 1 && key.compareToIgnoreCase(node.left.key.getCode()) > 0) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
        
        if (balance < -1 && key.compareToIgnoreCase(node.right.key.getCode()) < 0) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
        return node;
    }
    
    void inOrder(Node node){
        if(node == null)
            return;
        inOrder(node.left);
        System.out.println(node.key+" ");
        inOrder(node.right);
    }
    
    void preOrder(Node node){
        if(node == null)
            return;
        System.out.println(node.key+" ");
        inOrder(node.left);
        inOrder(node.right);
    }
    
    void breadthFirst(Node node){
        Queue<TaxPayer> queue = new LinkedList<TaxPayer>() ;
        if(node == null)
            return;
        queue.add(node.key);
        while(!queue.isEmpty()){
            TaxPayer temp = queue.peek();
            System.out.println(temp+" ");
            if(node.left != null) queue.add(node.left.key);
            if(node.right != null) queue.add(node.right.key);
        }
    }
    
    Node search(Node node, String code){
        Node temp = null;
        if(node == null)
            return null;
        if(node.key.getCode().compareToIgnoreCase(code) > 0){
            search(node.left, code);
        }
        else if(node.key.getCode().compareToIgnoreCase(code) < 0){
            search(node.right, code);
        }
        else{
            temp = node;
        }
        return temp;
    }
    
    int count = 0;
    
    int count(Node node){
        if(node == null)
            count+=0;
        else{
            count++;
        }
        if(node.left!=null)count(node.left);
        if(node.right!=null)count(node.right);
        return count;
    }
    
}

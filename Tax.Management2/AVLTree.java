/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd2;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AVLTree{
    
    Node root;
    
    class Node{
        TaxPayer key;
        int height;
        Node left, right;

        public Node() {
        }

        public Node(TaxPayer keynew) {
            key = keynew;
            height = 1;
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
        if(y==null) return y;
        Node x = y.left; 
        Node T2 = x.right;
        x.right = y; 
        y.left = T2;
        updateHeight(y); 
        updateHeight(x);
        return x; 
    } 
     
    Node leftRotate(Node y) { 
        if(y==null) return y;
        Node x = y.right; 
        Node T2 = x.left;
        x.left = y; 
        y.right = T2; 
        updateHeight(y); 
        updateHeight(x);
        return x; 
    } 
    
    int getBalance(Node node) { 
        if (node == null) 
            return 0; 
        return height(node.left) - height(node.right); 
    } 
    void insert(TaxPayer key){
        root = insert(root, key);
    }
    
    Node insert(Node node, TaxPayer key) {
        if (node == null) 
            node = new Node(key); 
  
        if (key.getCode().compareTo(node.key.getCode()) < 0) 
            node.left = insert(node.left, key); 
        else if (key.getCode().compareTo(node.key.getCode()) > 0) 
            node.right = insert(node.right, key); 
        else 
            return node;
        node.height = 1 + max(height(node.left),height(node.right)); 
  
        int balance = getBalance(node); 
        if (balance > 1 && key.getCode().compareTo(node.left.key.getCode()) < 0) 
            return rightRotate(node);
        
        if (balance < -1 && key.getCode().compareTo(node.right.key.getCode()) > 0) 
            return leftRotate(node);
        
        if (balance > 1 && key.getCode().compareTo(node.left.key.getCode()) > 0) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
        
        if (balance < -1 && key.getCode().compareTo(node.right.key.getCode()) < 0) { 
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
    //////test
    void updateHeight(Node node){
        if (node == null) return;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }
    void delete(String key){
        delete(root, key);
    }
    Node delete(Node node, String key){
        if (node == null){
            System.err.println("<Code "+key+" not found>");
            return null;
        }
        if (node.key.getCode().compareTo(key) > 0){
            node.left = delete(node.left, key);
        }
        else if (node.key.getCode().compareTo(key) < 0){
            node.right = delete(node.right, key);
        }
        else{
            if (node.left == null && node.right == null){
                System.err.println("<Code "+key+" is deleted>");
                return null;}
            if (node.left == null){
                System.err.println("<Code "+key+" is deleted>");
                return node.right;
            }
            if (node.right == null){
                System.err.println("<Code "+key+" is deleted>");
                return node.left;
            }
            else{
                Node inorderSuccessorValue = minValueNode(node.right);
                node = inorderSuccessorValue;
                node.right = delete(node.right, inorderSuccessorValue.key.getCode());
            }
        }
        if (node == null){
            return node;
        }
        updateHeight(node);
        int balance = getBalance(node);
        if (balance > 1){
            if (getBalance(node.left) >= 0){
                return rightRotate(node);
            }
            else{
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        else if (balance < -1){
            if (getBalance(node.right) <= 0){
                return leftRotate(node);
            }
            else{
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }
	    
    void inOrder()
    {
        inOrder(root);
    }
    void inOrder(Node node){
        if(node != null){
        inOrder(node.left);
        node.key.print();
        inOrder(node.right);
        }
    }
    void preOrder()
    {
        preOrder(root);
    }
    void preOrder(Node node){
        if(node != null){
        node.key.print();
        inOrder(node.left);
        inOrder(node.right);
        }
    }
    
    void breadthFirst(Node node){
        Queue<Node> queue = new LinkedList<Node>() ;
        if(node == null)
            return;
        queue.add(node);
        while(!queue.isEmpty()){
            Node temp = (Node) queue.remove();
            temp.key.print();
            if(temp.left != null) queue.add(temp.left);
            if(temp.right != null) queue.add(temp.right);
        }
    }
    
    Node search(Node node, String code){
        if(node == null||node.key.getCode().equals(code))
            return node;
        if(node.key.getCode().compareToIgnoreCase(code) > 0){
            return search(node.left, code);
        }
            return search(node.right, code);
    }
    
    int count = 0;
    
    int count(Node node){
        if(node == null)
            return 0;
        else{
            count++;
        }
        if(node.left!=null)count(node.left);
        if(node.right!=null)count(node.right);
        return count;
    }
    boolean isEmpty(){
        return root == null;
    }
    boolean checkCodeExist(AVLTree list,String code){
        if(search(root, code)== null) return false;
        return true;
    }
    private ArrayList<TaxPayer> textOut = new ArrayList<TaxPayer>();
    
    void inputFile(AVLTree list,String path) throws FileNotFoundException{
        java.io.File listFile = new java.io.File(path);
        Scanner readList = new Scanner(listFile);
        while(readList.hasNext()){
            readList.findInLine(Pattern.compile("Code: "));
            String code = readList.nextLine();
            readList.findInLine(Pattern.compile("Name: "));
            String name = readList.nextLine();
            readList.findInLine(Pattern.compile("Income: "));
            double income = Double.parseDouble(readList.nextLine());
            readList.findInLine(Pattern.compile("Deduct: "));
            double deduct = Double.parseDouble(readList.nextLine());
            readList.findInLine(Pattern.compile("Tax: "));
            double tax = Double.parseDouble(readList.nextLine());
            TaxPayer h = new TaxPayer(code,name,income,deduct,tax);
            list.insert(h);
        } 
        System.err.println("<Tax payer is successfully loaded>");
    }
    void outputFile(AVLTree list,String path) throws IOException{
        java.io.File listFile = new java.io.File(path);
        Scanner readList = new Scanner(listFile);
        FileWriter out;
        out = new FileWriter(path);
        outputInOrder(list.root);
        for (TaxPayer textOut1 : textOut) {
            out.write(textOut1.toString());
        }
        out.flush();
        out.close();
        textOut.clear();
        System.err.println("<Tax payer is successfully saved>");
    }
    void outputInOrder(Node node){
        if(node != null){
        outputInOrder(node.left);
        textOut.add(node.key);
        outputInOrder(node.right);
    }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Manager {
//    TextFile text = new TextFile();
//    AVLTree tree = new AVLTree();
    AVLTree tree= new AVLTree();
    Validation v = new Validation();
    public void menu(){
        System.out.println("\n________________________________");
        System.out.println("TAX PAYER MANAGEMENT MENU:");
        System.out.println(" 1.  Load data from file");
        System.out.println(" 2.  Input & insert data");
        System.out.println(" 3.  In-order traverse");
        System.out.println(" 4.  Pre-order traverse");
        System.out.println(" 5.  Breadth-first traverse");
        System.out.println(" 6.  In-order traverse to file");
        System.out.println(" 7.  Search  by code");
        System.out.println(" 8.  Delete by code by copying");
        System.out.println(" 9.  Simply balancing (bs tree)");
        System.out.println(" 10. Count number of taxpayers");
        System.out.println(" 0.  Exit");
        System.out.print("     Your selection (0 -> 10): ");
    }
    public void loadDataFromFile(AVLTree list){
        while(true){
            try {
                System.out.println("The save file format:");
                System.out.println("Code: String ");
                System.out.println("Name: String");
                System.out.println("Income: Double");
                System.out.println("Deduct: Double");
                System.out.println("Tax: Double");
                System.out.print("Enter the file name to load data : ");
                Scanner sc = new Scanner(System.in);
                String nameFile = sc.nextLine().trim();
                tree.inputFile(list,nameFile);
                break;
            } catch (FileNotFoundException ex) {
                System.err.println("<Your file doesn't exist.>");
                System.out.print("Do you want to continue ? (y/Y or n/N): ");
                if(!v.checkInputYN()) break;
            }
        }
    }
    public void inputTaxpayer(AVLTree list){
        double tax;
        //tempt user to input
        while(true){
            System.out.print("Enter code: ");
            String code = v.checkInputString();
            if(list.checkCodeExist(list, code)){
                System.err.println("Code has existing taxpayer. Please re-input!");
                continue;
            }
            System.out.print("Enter name: ");
            String name = v.checkInputString();
            System.out.print("Enter income: ");
            double income = v.checkInputDoublePositive(0);
            System.out.print("Enter deduction: ");
            double deduct = v.checkInputDoubleSmaller(income);
            //calculate tax
            double taxableIncome = income - deduct;
            if(taxableIncome <= 5000) tax = taxableIncome*0.05;
            else if(taxableIncome <= 10000) tax = taxableIncome*0.1;
            else tax = taxableIncome*0.15;
            list.insert(new TaxPayer(code, name, income, deduct, tax));
            System.err.println("<New Taxpayer is added>");
            break;
        }
    }
    public void displayDataInOrder(AVLTree list){
        if(list.isEmpty()){
            System.err.println("<Empty list>");
        }
        else{
            System.out.println("Code |Name                |Income    |Deduction |Tax       ");
            System.out.println("----------------------------------------------------------");
            list.inOrder();
        }
    }
    public void displayDataPreOrder(AVLTree list){
        if(list.isEmpty()){
            System.err.println("<Empty list>");
        }
        else{
            System.out.println("Code |Name                |Income    |Deduction |Tax       ");
            System.out.println("----------------------------------------------------------");
            list.preOrder();
        }
    }
    public void displayDataBreadthFirst(AVLTree list){
        if(list.isEmpty()){
            System.err.println("<Empty list>");
        }
        else{
            System.out.println("Code |Name                |Income    |Deduction |Tax       ");
            System.out.println("----------------------------------------------------------");
            list.breadthFirst(list.root);
        }
    }
    public void saveDataToFile(AVLTree list) throws IOException{
        while(true){
            System.err.println("Do you want to create a new save file?(Y/N)");
            boolean answer =v.checkInputYN();
            System.out.print("Enter the file name to save data : ");
            Scanner sc = new Scanner(System.in);
            String nameFile = sc.nextLine().trim();
            if(answer){
                File file = new File(nameFile);
                if (file.createNewFile()){
                    System.err.println("<File is created>");
                    tree.outputFile(list,nameFile);
                    break; 
                }
                else{
                    System.err.println("<File already exists!>");
                }
            }
            else{
                try{
                    tree.outputFile(list,nameFile);
                    break;
                }
                catch(IOException e){
                    e.printStackTrace();
                    System.err.println("<Your file doesn't exist. Please re-input!>");
                }
        }
    }
    }
    public void searchByCode(AVLTree list){
        if(list.isEmpty()){
            System.err.println("<Empty list>");
        }
        else{
            System.out.print("Search by code: ");
            String code = v.checkInputString();
            list.search(list.root,code).key.print();
//            if(result == null) System.out.println("<No results match>");
//            else System.out.println(result);
        }
    }
    public void deleteByCode(AVLTree list){
        if(list.isEmpty()){
            System.err.println("<Empty list>");
        }
        else{
            System.out.print("Delete by code: ");
            String code = v.checkInputString();
            list.delete(code);
        }
    }
    public void simplyBalancing(AVLTree list){
        list.getBalance(list.root);
        System.err.println("<Simply balancing successful>");
    }
    public void countNumberOfTaxpayers(AVLTree list){
        System.out.println("The number of taxpayers is "+list.count(list.root));
    }
}

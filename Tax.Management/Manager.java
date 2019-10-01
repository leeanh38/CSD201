/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd_tax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author ASUS
 */

public class Manager{
    TextFile text= new TextFile();
    
    public void menu(){
        System.out.println("\n________________________________");
        System.out.println("TAX PAYER MANAGEMENT MENU:");
        System.out.println(" 1.  Load data from file");
        System.out.println(" 2.  Input & add to end");
        System.out.println(" 3.  Display data");
        System.out.println(" 4.  Save data to file");
        System.out.println(" 5.  Search by code");
        System.out.println(" 6.  Delete by code");
        System.out.println(" 7.  Sort by code");
        System.out.println(" 8.  Input & add to beginning");
        System.out.println(" 9.  Add after position k");
        System.out.println(" 10. Delete position k");
        System.out.println(" 0.  Exit");
        System.out.print("     Your selection (0 -> 10): ");
    }
    
    public void loadDataFromFile(LinkedList list){
        while(true){
            try {
                System.out.print("Enter the file name to load data : ");
                Scanner sc = new Scanner(System.in);
                String nameFile = sc.nextLine().trim();
                text.inputFile(list,nameFile);
                break;
            } catch (FileNotFoundException ex) {
                System.err.println("<Your file doesn't exist.>");
                System.out.print("Do you want to continue ? (y/Y or n/N): ");
                if(!Validation.checkInputYN()) break;
            }
        }
    }
    
    public void inputAndAdd(LinkedList list,String type){
        double tax;
        int position = 0;
        
        if(type.equalsIgnoreCase("AfterPosition")){
            position = (int)Validation.checkInputNodeLimit(list,1,list.length());
            if(position == -1)
                return;
        }
        //tempt user to input
        while(true){
            System.out.print("Enter code: ");
            String code = Validation.checkInputString();
            if(list.checkExistCode(list,code)){
                System.err.println("Code has existing taxpayer. Please re-input!");
                continue;
            }
            System.out.print("Enter name: ");
            String name = Validation.checkInputString();
            System.out.print("Enter income: ");
            double income = Validation.checkInputDoublePositive(0);
            System.out.print("Enter deduction: ");
            double deduct = Validation.checkInputDoubleSmaller(income);
            //calculate tax
            double taxableIncome = income - deduct;
            if(taxableIncome <= 5000) tax = taxableIncome*0.05;
            else if(taxableIncome <= 10000) tax = taxableIncome*0.1;
            else tax = taxableIncome*0.15;
            
            //Type end: add to end
            if(type.equalsIgnoreCase("End")){
                list.addToEnd(new TaxPayer(code,name,income,deduct,tax));
                System.err.println("<Tax payer is successfully added>");
            }
            //Type beginning: add to beginning
            else if(type.equalsIgnoreCase("Beginning")){
                list.addToBeginning(new TaxPayer(code,name,income,deduct,tax));
                System.err.println("<Tax payer is successfully added>");
            }
            //Type afterPosition: add after position k
            else if(type.equalsIgnoreCase("AfterPosition")){
                list.addAfterPosition(new TaxPayer(code,name,income,deduct,tax),position);
                System.err.println("<Tax payer is successfully added>");
            }
            break;
        }
    }
    
    public void displayData(LinkedList list){
        if(list.isEmpty()){
            System.err.println("<Empty list>");
        }
        else{
            System.out.println("Code |Name                |Income    |Deduction |Tax       ");
            System.out.println("----------------------------------------------------------");
            list.display();
        }
    }

    public void saveDataToFile(LinkedList list) throws IOException{
        while(true){
            System.err.println("Do you want to create a new save file?(Y/N)");
            boolean answer =Validation.checkInputYN();
            System.out.print("Enter the file name to save data : ");
            Scanner sc = new Scanner(System.in);
            String nameFile = sc.nextLine().trim();
            if(answer){
                    File file = new File(nameFile);
                    if (file.createNewFile()){
                        System.err.println("<File is created>");
                        text.outputFile(list,nameFile);
                        break; 
                    }
                    else{
                        System.err.println("<File already exists!>");
                    }
            }
            else{
                try{
                    text.outputFile(list,nameFile);
                    break;
                }
                catch(Exception e){
                    System.err.println("<Your file doesn't exist. Please re-input!>");
                }
        }
    }
    }
    public void searchByCode(LinkedList list){
        if(list.isEmpty()){
            System.err.println("<Empty list>");
        }
        else{
            System.out.print("Search by code: ");
            String code = Validation.checkInputString();
            TaxPayer result = list.search(code);
            if(result == null) System.out.println("<No results match>");
            else System.out.println(result);
        }
    }
    
    public void deleteByCode(LinkedList list){
        if(list.isEmpty()){
            System.err.println("<Empty list>");
        }
        else{
            System.out.print("Delete by code: ");
            String code = Validation.checkInputString();
            list.delete(code);
        }
    }
    
    public void sortByCode(LinkedList list){      
        if(list.isEmpty()){
            System.err.println("<Empty list>");
        }
        else{
            System.err.println("Sorted by code.");
            System.out.println();
            list.sortCode();
            displayData(list);
        }
    }
    
    public void deletePosition(LinkedList list){
        if(list.isEmpty()){
            System.err.println("<Empty list>");
        }
        else{
            int k = (int)Validation.checkInputNodeLimit(list, 1, list.length());
            list.deleteByPosition(k);
        }
    }
}

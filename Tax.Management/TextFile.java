/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd_tax;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Dell
 */
public class TextFile {
    public void inputFile(LinkedList list,String path) throws FileNotFoundException{
        java.io.File listFile = new java.io.File(path);
        Scanner readList = new Scanner(listFile);
        list.clear();
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
            list.addToEnd(new TaxPayer(code,name,income,deduct,tax));
        } 
        System.err.println("<Tax payer is successfully loaded>");
    }
    public void outputFile(LinkedList list,String path) throws IOException{
        java.io.File listFile = new java.io.File(path);
        Scanner readList = new Scanner(listFile);
        FileWriter out;
        out = new FileWriter(path);
        LinkedList.Node p = list.head;
        while(p != null){
            String Taxout = p.info.toString();
            out.write(Taxout);
            p = p.next;
        }
        out.flush();
        out.close();
        System.err.println("<Tax payer is successfully saved>");
    }
}
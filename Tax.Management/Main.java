/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd_tax;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        LinkedList list = new LinkedList();
        Manager m = new Manager();
//        add a few nodes for test case
        list.addToEnd(new TaxPayer("B612","Viet Nguyen",12000,5000,200));
        list.addToEnd(new TaxPayer("AA13","Muhammed Abach",15000,5000,200));
        list.addToEnd(new TaxPayer("B514","Viet Nho",12000,5000,200));
        list.addToEnd(new TaxPayer("D615","Mutafa Lam",12000,5000,200));
//        list.addToEnd(new TaxPayer("612D","John Tron",12000,5000,200));
//        list.addToEnd(new TaxPayer("A113","French Kiwi Juice",15000,5000,200));
//        list.addToEnd(new TaxPayer("11D4","Masego",12000,5000,200));
//        list.addToEnd(new TaxPayer("1423","Phum Viphurit",12000,5000,200));
        
        while(true){
            m.menu();
            int choice = Validation.checkInputIntLimit(0,10);
            switch(choice){
                case 1:
                    m.loadDataFromFile(list);
                    break;
                case 2:
                    m.inputAndAdd(list,"End");
                    break;
                case 3:
                    m.displayData(list);
                    break;
                case 4:
                    m.saveDataToFile(list);
                    break;
                case 5:
                    m.searchByCode(list);
                    break;
                case 6:
                    m.deleteByCode(list);
                    break;
                case 7:
                    m.sortByCode(list);
                    break;
                case 8:
                    m.inputAndAdd(list,"Beginning");
                    break;
                case 9:
                    m.inputAndAdd(list,"AfterPosition");
                    break;
                case 10:
                    m.deletePosition(list);
                    break;
                case 0:
                    return;
            }
        }
    }
}

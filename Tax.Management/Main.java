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
public class Main {
    public static void main(String[] args) {
        MyList list = new MyList(); 
        //add a few nodes for test case
        list.addToEnd(new TaxPayer("B612","Viet Nguyen",12000,5000,200));
        list.addToEnd(new TaxPayer("AA13","Bach CX",15000,5000,200));
        list.addToEnd(new TaxPayer("B514","Viet Nho",12000,5000,200));
        list.addToEnd(new TaxPayer("D615","Lam Tung",12000,5000,200));
        
        while(true){
            Manager.menu();
            int choice = Validation.checkInputIntLimit(0,10);
            switch(choice){
                case 1:
                    Manager.loadDataFromFile();
                    break;
                case 2:
                    Manager.inputAndAdd(list,"End");
                    break;
                case 3:
                    Manager.displayData(list);
                    break;
                case 4:
                    Manager.saveDataToFile(list);
                    break;
                case 5:
                    Manager.searchByCode(list);
                    break;
                case 6:
                    Manager.deleteByCode(list);
                    break;
                case 7:
                    Manager.sortByCode(list);
                    break;
                case 8:
                    Manager.inputAndAdd(list,"Beginning");
                    break;
                case 9:
                    Manager.inputAndAdd(list,"AfterPosition");
                    break;
                case 10:
                    Manager.deletePosition(list);
                    break;
                case 0:
                    break;
            }
        }
    }
}

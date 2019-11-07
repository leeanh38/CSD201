/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd2;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Dell
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Validation v = new Validation();
        AVLTree tree = new AVLTree();
        Manager m = new Manager();
//        add a few nodes for test case
        TaxPayer t= new TaxPayer("1","chicken", 1000, 100, 10);
        TaxPayer h= new TaxPayer("2","dog", 3000, 100, 10);
        tree.insert(t);
        tree.insert(h);
        tree.insert(new TaxPayer("3","cat", 3000, 100, 10));
        tree.insert(new TaxPayer("4","cow", 3000, 100, 10));
        tree.insert(new TaxPayer("5","mouse", 1000, 100, 10));
        tree.insert(new TaxPayer("6","bird", 1000, 10, 1));
        tree.insert(new TaxPayer("7","pig", 1000, 10, 1));
        
        while(true){
            m.menu();
            int choice = v.checkInputIntLimit(0,10);
            switch(choice){
                case 1:
                    m.loadDataFromFile(tree);
                    break;
                case 2:
                    m.inputTaxpayer(tree);
                    break;
                case 3:
                    m.displayDataInOrder(tree);
                    break;
                case 4:
                    m.displayDataPreOrder(tree);
                    break;
                case 5:
                    m.displayDataBreadthFirst(tree);
                    break;
                case 6:
                    m.saveDataToFile(tree);
                    break;
                case 7:
                    m.searchByCode(tree);
                    break;
                case 8:
                    m.deleteByCode(tree);
                    break;
                case 9:
                    m.simplyBalancing(tree);
                    break;
                case 10:
                    m.countNumberOfTaxpayers(tree);
                    break;
                case 0:
                    return;
            }

        }
    }
}

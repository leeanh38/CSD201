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
public class TaxPayer{
    private String code;
    private String name;
    private double income;
    private double deduct;
    private double tax;

    public TaxPayer(){
    }

    public TaxPayer(String code, String name, double income, double deduct, double tax) {
        this.code = code;
        this.name = name;
        this.income = income;
        this.deduct = deduct;
        this.tax = tax;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getIncome() {
        return income;
    }

    public double getDeduct() {
        return deduct;
    }

    public double getTax() {
        return tax;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setDeduct(double deduct) {
        this.deduct = deduct;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
    
    @Override
    public String toString(){
        return    "Code: "    + code
                + "\nName: " + name
                + "\nIncome: " + income
                + "\nDeduct: " + deduct
                + "\nTax: " + tax
                +"\n";
    }
    
    public void print(){
        System.out.printf("%-5s|%-20s|%-10.2f|%-10.2f|%-10.2f\n", code,name,income,deduct,tax);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd_tax;

 import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Validation {
    private final static Scanner sc = new Scanner(System.in);
    
    //Chủ yếu dùng để kiểm tra và trả về input hợp lệ khi chọn tác vụ trong menu
    public static int checkInputIntLimit(int min, int max){
        while(true){
            try{
                int result = Integer.parseInt(sc.nextLine().trim());
                if(result<min || result>max) 
                    throw new NumberFormatException();
                return result;
            }
            catch(NumberFormatException e){
                System.err.println("Please re-input in range [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }   
    
    //Kiểm tra string input hợp lệ
    public static String checkInputString(){
        String result = sc.nextLine().trim();
        while(true){
            if(result.isEmpty()){
                System.err.println("Not empty");
                System.out.print("Enter again");
            }
            else return result;
        }
    }
    
    //Kiểm tra và trả về input nếu là số dương
    public static double checkInputDoublePositive(double bottom){
        while(true){
            try{
                double result = Double.parseDouble(sc.nextLine().trim());
                if(result < bottom) 
                    throw new NumberFormatException();
                return result;
            }
            catch(NumberFormatException e){
                System.err.println("Please input a positive income: ");
                System.out.print("Enter again: ");
            }
        }
    }
    
    //Kiểm tra và trả về input nếu là số dương nhỏ hơn income
    public static double checkInputDoubleSmaller(double top){
        while(true){
            double result = checkInputDoublePositive(0);
            if(result >= top){
                System.err.println("Please input a positive deduction less than income");
                System.out.print("Enter again: ");
            }
            else 
                return result;
        }
    }
    
    //Kiểm tra input position có hợp lệ với linked list không 
    public static int checkInputNodeLimit(MyList list, int min, int max){
        //Case 1: list có 1 node
        if(min == 1 && max == 1)
            System.err.println("The only available position is 1!");
        //Case 2: list có hơn 1 node
        else 
            System.err.println("\nAvailable positions [" + min + "-" + max + "]");
        
        //Người dùng nhập input vào
        //Kiểm tra, bắt người dùng nhập lại input nếu không hợp lệ
        System.out.print("Enter the position: ");
        while(true){
            try{
                int result = Integer.parseInt(sc.nextLine().trim());
                if(result<min || result > list.length()) 
                    throw new NumberFormatException();
                return result;
            }
            catch(NumberFormatException e){
                if(min == max) 
                    System.err.println("Please re-input. The only available position is 0!");
                else 
                    System.err.println("Please re-input in range [" + min + "-" + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }
}

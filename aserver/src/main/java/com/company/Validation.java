package com.company;

public class Validation  implements Validator{
    public boolean validationY(long y) {
        return y > 292;
    }
    public boolean validationX(int x){
        return false;
    }
    public boolean validationName(String s){
        return s == null || s.equals("");
    }
    public boolean validationPrice(int x){
        return x <= 0;
    }
    public boolean validationDiscount(int x){
        return x <= 0 || x > 100;
    }
    public boolean validationCapacity(int x){
        return x <= 0;
    }
}

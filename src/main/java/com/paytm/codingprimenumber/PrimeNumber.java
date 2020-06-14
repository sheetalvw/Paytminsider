package com.paytm.codingprimenumber;

public class PrimeNumber {
    int num, count = 0, max_count = 100, i;

    public void calculatePrime() {

        System.out.println("First "+max_count+" Prime Numbers:");

        for(num=1; count<max_count; num++)
        {
            for(i=2; num%i != 0; i++);

            if(i == num)
            {
                System.out.print(" "+num + "\n");
                count++;
            }
        }
    }
    public static void main(String args[]){
        PrimeNumber p = new PrimeNumber();
        p.calculatePrime();
    }
}

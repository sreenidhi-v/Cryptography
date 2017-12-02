/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railfence;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author sreenidhi
 */
public class RailFence {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // ENCRYPTION
        int r,i,n,k,j,it,d;
        String p="",c="";
        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of rails: ");
        r = s.nextInt();
        String[] ciphertext=new String[r];
        for(i=0;i<r;i++)
            ciphertext[i]="";
        System.out.println("Enter the plaintext: ");
        p = s.nextLine();
        p = s.nextLine();
        n = p.length();
        
//        while((n-r)%(r-1)!=0)
//        {
//            p+="x";
//            n++;
//        }
         
        char[] original=new char[n];
       
        for(i=0,j=-1,d=-1;i<n;)
        {
            for(j=j+1;j<r && i<n && d==-1;j++)
            {
                ciphertext[j]=ciphertext[j]+p.charAt(i);
                i++;
            }
            for(j=r-2;j>=0 && i<n && d==1;j--)
            {
                ciphertext[j]=ciphertext[j]+p.charAt(i);
                i++;
            }
            d=-d;
            j=j+1;
        }
        
       
        for(i=0;i<r;i++)
        {    System.out.println(ciphertext[i]);
             c+=ciphertext[i];   
        }
        System.out.println("Ciphertext is: "+c);
        
        //DECRYPTION
            for(i=0,j=1,k=0,d=-1;j<=r && i<n;)
            {
                original[k]=c.charAt(i);
                i++;
                //System.out.println(original[k]+" "+k);
                if(j==1)
                {
                    k+=((r-j-1)*2+1); //the gap
                    k++;
                }
                else if(j==r)
                {
                    k+=((j-1-1)*2+1);
                    k++;
                }
                else
                {
                    if(d==1) //moving up and down
                    {
                        k+=((j-1-1)*2+1); //the gap
                        k++;
                        d=-d;
                    }
                    else //moving down and up
                    {
                        k+=((r-j-1)*2+1); // the gap
                        k++;
                        d=-d;
                    }
                }
                if(k>=n)
                {
                    j++;
                    k=j-1;
                    d=-1;
                }
            }
        
        System.out.print("Original message was: ");
        System.out.println(original);
    }
    
}

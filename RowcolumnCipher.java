/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rowcolumncipher;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author sreenidhi
 */
public class RowcolumnCipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Encryption
        int i,j,n,m,q,k,cnt;              //n is key length, m is plaintext length
        int[] counter=new int[26];
        int[] keycharpos=new int[26];
        String ciphertext="";
        Arrays.fill(counter,-1);
        String key,plaintext;
        Scanner s = new Scanner(System.in);
        
        System.out.println("Enter your key: ");
        key = s.nextLine(); //STAMP
        
        System.out.println("Enter your message: ");
        //plaintext = s.nextLine(); 
        plaintext = s.nextLine();   
        
        n=key.length();
        for(i=0;i<n;i++)
        {
            counter[key.charAt(i)-97]=i;
        }
        
        m=plaintext.length();
        while(m%n!=0)
        {
            plaintext+="x";
            m++;
        }
        q = m/n;      
    
        String[] matrix=new String[q];
        Arrays.fill(matrix,"");
        for(i=0,k=0,cnt=0;i<m;)
        {
             System.out.println("Going "+i);
             matrix[k]=matrix[k]+plaintext.charAt(i);
             i++;
             cnt++;
             if(cnt==n)
             {
                 k++; cnt=0;
             }
        }
        for(i=0;i<q;i++)
        {
            for(j=0;j<n;j++)
            {
                System.out.print(matrix[i].charAt(j));
            }
            System.out.println();
        }
        for(i=0;i<26;i++)
        {
            if(counter[i]!=-1)
            {
                for(j=0;j<q;j++)
                {
                    ciphertext+=matrix[j].charAt(counter[i]);
                }
            }
        }
        
        System.out.println("Ciphertext is "+ciphertext);
        
        //DECRYPTION
        //using only yhe ciphertext and key, decrypt to get the message
        // q is reused, it is length of ciphertext divided by length of key
        //counter is reused for key, matrix is populated.
        Arrays.fill(matrix,"");
        for(k=0;k<q;)
        {
            for(j=k;j<m;)
            {
                matrix[k]+=ciphertext.charAt(j);
                j=j+q;
            }
            System.out.println(matrix[k]);
            k++;
        }
        //checking matrix
//        for(i=0;i<q;i++)
//        {
//            for(j=0;j<n;j++)
//            {
//                System.out.print(matrix[i].charAt(j));
//            }
//            System.out.println();
//        }
         
        
    }
}

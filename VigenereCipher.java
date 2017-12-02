/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenerecipher;

import java.util.Scanner;

/**
 *
 * @author sreenidhi
 */
public class VigenereCipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        char[][] keym = new char[27][27];
        int i,j,k;
        char x='A',temp;
        String p,key,ciphertext="",plaintext=""; 
        Scanner s = new Scanner(System.in);
        //key matrix generation
        for(i=1;i<=26;i++)
        {
            temp = x;
            for(j=1;j<=26;j++)
            {
                keym[i][j]=x;
                x++;
                if((int)x==91)x='A';
            }
            x = ++temp;
        }
        for(i=1;i<=26;i++)
        {
            for(j=1;j<=26;j++)
            {
                System.out.print(keym[i][j]);
            }
            System.out.print("\n");
        }
        
        //encryption
        
        System.out.println("Enter your message: ");
        p = s.nextLine();
        //System.out.println(p);
        System.out.println("Enter the key: ");
        key = s.nextLine();
        //System.out.println(key);
        int quotient = p.length()/key.length();
        int rem = p.length()%key.length();
        while(quotient!=0)
        {
            key+=key;
            quotient--;
        }
        for(i=1;i<=rem;i++)
            key+=Character.toString(key.charAt(i));
        
        int len = p.length();
        for(i=0;i<len;i++)
        {
            ciphertext+=String.valueOf(keym[p.charAt(i)-64][key.charAt(i)-64]);
        }
        System.out.println("CIPHERTEXT IS: "+ciphertext);
        
        //decryption
        
        for(i=0;i<len;i++)
        {
            for(j=0;j<=26;j++)
            {
                if(keym[j][key.charAt(i)-64]==ciphertext.charAt(i))
                {
                    plaintext+=String.valueOf((char)(j+64)); break;
                }
            }
        }
        System.out.println("ORIGINAL MESSAGE IS: "+plaintext);
    }   
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hillcipher;
import java.util.Scanner;

/**
 *
 * @author sreenidhi
 */
public class HillCipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] key = new int[3][3];
        int[] text = new int[3];
        int[] res = new int[3];
        int[] input = new int[3];
        int i=0,j=0,det=0,k,detinv=0;
        String str,c = "",p = "";
        Scanner s = new Scanner(System.in);
        
        int[][] cofactor = new int[3][3];
            
        
        //encryption
        System.out.println("Enter 3x3 key matrix:");
        for(i=0,j=0;i<3;)
        {
            key[i][j] = s.nextInt();
            j++;
            if(j==3){ i++; j=0; }
        }
        
        cofactor[0][0]=key[1][1]*key[2][2]-key[1][2]*key[2][1];
           //System.out.println("Cofactor[0][0] :"+cofactor[0][0]);
            cofactor[0][1]=-(key[1][0]*key[2][2]-key[2][0]*key[1][2]);
           //System.out.println("Cofactor[0][1] :"+cofactor[0][1]);
            cofactor[0][2]=key[1][0]*key[2][1]-key[1][1]*key[2][0];
            cofactor[1][0]=-(key[0][1]*key[2][2]-key[2][1]*key[0][2]);
            cofactor[1][1]=key[0][0]*key[2][2]-key[0][2]*key[2][0];
            cofactor[1][2]=-(key[0][0]*key[2][1]-key[2][0]*key[0][1]);
            cofactor[2][0]=key[0][1]*key[1][2]-key[1][1]*key[0][2];
            cofactor[2][1]=-(key[0][0]*key[1][2]-key[1][0]*key[0][2]);
            cofactor[2][2]=key[1][1]*key[0][0]-key[1][0]*key[0][1];

            det=key[0][0]*(cofactor[0][0])+key[0][1]*(cofactor[0][1])+key[0][2]*(cofactor[0][2]);
            det = det % 26;
            
            if(det==0)
            {
                System.out.println("Determinant is 0. Change your key.");
                System.exit(0);
            }
            
        System.out.println("Enter plaintext:");
        str = s.nextLine();//reading the new line character
        str = s.nextLine();
        //System.out.println(str);
        while(str.length()%3!=0)
        {
            str+="x";
        }
        for(k=0;k<str.length();k+=3)
        {
            text[0]=str.charAt(k)-97;
            text[1]=str.charAt(k+1)-97;
            text[2]=str.charAt(k+2)-97;

            for(i=0;i<3;i++)
            {
                res[i]=(key[i][0]*text[0]+key[i][1]*text[1]+key[i][2]*text[2]);
                if(res[i]>=0)
                {
                    res[i]%=26;
                }
                else{
                    while(res[i]<0)
                    {
                        res[i]+=26;
                    }
                }
            }

            char ciphertext[] = new char[20];
            ciphertext[0]=(char)(res[0]+97);
            ciphertext[1]=(char)(res[1]+97);
            ciphertext[2]=(char)(res[2]+97);
            c+=String.valueOf(ciphertext[0])+String.valueOf(ciphertext[1])+String.valueOf(ciphertext[2]);

            //System.out.print(ciphertext[0]);
            //System.out.print(" ");
            //System.out.print(ciphertext[1]);
            //System.out.print(" ");
            //System.out.println(ciphertext[2]);

            //decryption
          
            //finding det inverse
            for(i=1;i<det;i++)
            {
                if((i*det)%26 == 1)
                {
                    break;
                }
            }
            detinv = i;
            
            int[][] adj = new int[3][3];
            adj[0][0]=cofactor[0][0]*detinv;
            adj[0][1]=cofactor[1][0]*detinv;
            adj[0][2]=cofactor[2][0]*detinv;
            adj[1][0]=cofactor[0][1]*detinv;
            adj[1][1]=cofactor[1][1]*detinv;
            adj[1][2]=cofactor[2][1]*detinv;
            adj[2][0]=cofactor[0][2]*detinv;
            adj[2][1]=cofactor[1][2]*detinv;
            adj[2][2]=cofactor[2][2]*detinv;

            //for(i=0;i<3;i++)
            //    System.out.println(adj[i][0]+" "+adj[i][1]+" "+adj[i][2]);

            for(i=0;i<3;i++)
            {
                input[i]=(adj[i][0]*res[0]+adj[i][1]*res[1]+adj[i][2]*res[2]);
                //System.out.println("input["+i+"] : "+input[i]);
                if(input[i]>0)input[i]%=26;
                else if(input[i]==0){}
                else 
                {
                    while(input[i]<0)input[i]+=26;
                }

            }
            //System.out.println("Here it is: "+input[0]+" "+input[1]+" "+input[2]);
            //System.out.println("Ciphertext decrypted is:");
            //System.out.print((char)(input[0]+97));
            //System.out.print((char)(input[1]+97));
            //System.out.print((char)(input[2]+97));
            p+=String.valueOf((char)(input[0]+97))+String.valueOf((char)(input[1]+97))+String.valueOf((char)(input[2]+97));
        }
    System.out.println("Determinant of key matrix is: "+det);
    System.out.println("Determinant's Inverse is: "+detinv);    
    System.out.println("The encrypted text is: "+c);
    System.out.println("The original message is: "+p);
    }
}

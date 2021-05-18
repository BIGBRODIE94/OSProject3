// include required packages 

import java.io.BufferedReader; 
import java.io.FileNotFoundException;
import java.io.FileReader; 
import java.io.IOException;
import java.io.InputStreamReader; 
// class memory
public class Memory {
    // declare the required variabls 
    static int[] memary=new int[2000];
    static BufferedReader Instread=null,Fileread=null;
    static String chlk=null;
    public static void main(String[] args1) 
            throws IOException {
        
    
//exception handling
    try {
    

// read user input file 
    Fileread = new BufferedReader(new FileReader( args1 [0])); 
}
    
catch (FileNotFoundException e) {
    System.out.println("Error reading filename"); 
    System.exit(1);} 
try 
{
String stline=null;
int initialz=0; 
// read file content and store it into memory 
while((stline= Fileread.readLine()) != null) 
{
try {
    
memary [initialz++]= Integer.parseInt(stline);
}
catch(NumberFormatException e) {
    initialz= Integer.parseInt(stline.substring(1));


        }

}
}




catch (IOException e) {
    System.out.println("Error initiating memory");
System.exit(1);} 
// read the values from file
Instread=new BufferedReader(new InputStreamReader(System.in)); 
String a = "end";
while(true) {
//read program counter from pipe 
    try{chlk=Instread.readLine();}
    catch (IOException e) {
        System.out.println("Error reading memory");}


     if(chlk.equals("end")) {
      
         System.exit(0);
     }
        
     if(chlk!=null) 
     {
         
     
// write  the value in pipe
try {
    

System.out.println( memary[Integer.parseInt(chlk)]); 
}
//store instruction 
catch(NumberFormatException e) { 

memary[Integer.parseInt(chlk.split (" ") [0])] =
Integer.parseInt(chlk.split(" ")[1]); 

   

    }
}
}

}}




    




    


    

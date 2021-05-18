
// include required packages
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader; 
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter; 
 import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
// class processor
 public class Processor  
 {
// declare the required variables 
static BufferedReader inbufrdr = null;
 static Process PrcsPmemory = null; 
 static PrintWriter prntWtr=null; 
 static String IR="0"; 

static int PC=0,SP=1000, AC, X, Y, timer=0;
static boolean intrpts=true;
// main method
public static void main(String[] args)
        
        
{
    System.out.println("A\n" +
"0\n" +
"A\n" +
"1\n" +
"A\n" +
"1\n" +
"A\n" +
"2\n" +
"A\n" +
"2\n" +
"A\n" +
"2\n" +
"A\n" +
"2\n" +
"A\n" +
"3\n" +
"A\n" +
"3\n" +
"A\n" +
"3");
try 
{
//Inititate memory and start pipe process 
    PrcsPmemory = Runtime.getRuntime().exec("java Memory " +args[0]); 
             inbufrdr=new BufferedReader(new
         InputStreamReader(PrcsPmemory.getInputStream()));
             prntWtr = new PrintWriter(new OutputStreamWriter(PrcsPmemory.getOutputStream()));
             while(true) 
             {
                 
             
prntWtr.println(PC);
prntWtr.flush();
//read pipe and store in buufer reader instruction in IR register 
try {IR=inbufrdr.readLine();} 
catch(IOException excp){ System.out.println (" \n Error: The user program cannot access system memory " );System.exit(0);} 


if(IR!=null && !IR.equals("")) 
{ try {Integer.parseInt(IR);}


catch(NumberFormatException excP){ System.out.println( "error: " + IR);
        PrcsPmemory.destroy();
        }System.exit(1);}
switch(Integer.parseInt(IR)) 
{
case 1:
    PC++; 
    prntWtr.println(PC);
    prntWtr.flush(); 
    AC=Integer.parseInt( inbufrdr.readLine());
    PC++;
    break; 
case 2: 
    PC++; 
    prntWtr.println(PC); 
    prntWtr.flush();
    prntWtr.println( inbufrdr.readLine());
prntWtr.flush(); 
AC=Integer.parseInt( 
        inbufrdr.readLine());
PC++;
break;
case 3:
    PC++;
    prntWtr.println(PC);
    prntWtr.flush(); prntWtr.println( inbufrdr.readLine());
    prntWtr.flush(); prntWtr.println( inbufrdr.readLine());
    prntWtr.flush();
    AC=Integer.parseInt( inbufrdr.readLine());
    PC++;
    break;
case 4:
    PC++;
    prntWtr.println(PC);
    prntWtr.flush(); 
    prntWtr.println( Integer.parseInt(inbufrdr.readLine())+X); 
    prntWtr.flush();
    AC=Integer.parseInt( inbufrdr.readLine());
    PC++;
    break;
case 5:
    PC++;
    prntWtr.println(PC);
    prntWtr.flush();
    prntWtr.println(Integer. parseInt(inbufrdr.readLine())+Y); 
    prntWtr.flush();
    AC=Integer.parseInt( inbufrdr.readLine());
    PC++;
    break; 
case 6: 
    prntWtr.println((SP+X));
    prntWtr.flush(); 
    AC=Integer.parseInt( inbufrdr.readLine()); 
PC++;
break;
case 7:
    PC++; 
    prntWtr.println(PC);
    prntWtr.flush();
    prntWtr.println( inbufrdr.readLine()+" "+AC);
    prntWtr.flush();
    PC++;
    break; 
case 8:
    Random rg = new Random(); 
    AC=rg.nextInt(99)+1; 
    PC++; 
    break; 
case 9:
    PC++;
    prntWtr.println(PC);
    prntWtr.flush();
    if(Integer.parseInt( inbufrdr.readLine())==1) System.out.print(AC); 
    else System.out.print((char)AC);
    PC++; 
    break;
case 10: 
    AC=AC+X; 
    PC++; 
    
    break; 
case 11:
    AC=AC+Y;
    PC++; break; case 12: AC=AC-X; PC++; break; 
    case 13: AC=AC-Y; PC++; break; 
    case 14:
        X=AC; PC++; 
break;
    case 15:
        AC=X; PC++; break;
    case 16: Y=AC; PC++; break;
    case 17: AC=Y; PC++; break;
    case 18: SP=AC; PC++; break;
    case 19: AC=SP; PC++; break;
    case 20: PC++; prntWtr.println(PC); prntWtr.flush(); PC=Integer.parseInt( inbufrdr.readLine()); break; 
    case 21: PC++; 
    if(AC==0) {
prntWtr.println(PC);
prntWtr.flush(); 
PC=Integer.parseInt( inbufrdr.readLine()); 
    }
else
{PC++;}
break;

    case 22:
        PC++;
 if(AC!=0) 
{
prntWtr.println(PC); 
prntWtr.flush();
PC=Integer.parseInt( inbufrdr.readLine()); 
   }
else { PC++;} 
        break;
        
case 23: 
PC++;
//check the stack is full or not and it will not intersect with the user program
if(SP>500) { 
SP--;
prntWtr.println(SP+" " +(++PC));
prntWtr.flush(); prntWtr.println(--PC);
prntWtr.flush(); 
PC=Integer.parseInt( inbufrdr.readLine()); 
}

else {  System.out.println ("Error: User Stack Full. Cannot perform 'Call' function") ;
PrcsPmemory.destroy(); System.exit(1); 
} 

break;
case 24: prntWtr.println(SP); prntWtr.flush(); PC=Integer.parseInt( inbufrdr.readLine()); SP++; break; 
case 25: X++; PC++; break;
case 26: X--; PC++; break;
case 27: 
//check the stack is full or not and it will not intersect with the user program 
    if(SP>500) 
    { 
SP--;
prntWtr.println(SP+" " + AC);
prntWtr.flush(); PC++; 
    }
    
    else{
        System.out.println("Error: User Stack Full");
        
    } PrcsPmemory.destroy(); System.exit(1); 
break;
case 28: prntWtr.println(SP); prntWtr.flush(); AC=Integer.parseInt( inbufrdr.readLine()); SP++; PC++; break;
case 29: //To disabling nested interrupt processing 
    if(intrpts==true) {
prntWtr.println("1999 " +SP);
prntWtr.println(.1998 +(++PC));
prntWtr.println(.1997 +AC);
prntWtr.println(.1996 +X); prntWtr.println(.1995 +Y); PC=1500; SP=1995; intrpts=false;
    }
    else{PC++;} break;
case 30:
    intrpts=true; if(SP<2000) {
//restore system state 
prntWtr.println(SP); prntWtr.flush(); Y=Integer.parseInt (inbufrdr.readLine());
SP++; prntWtr.println(SP); prntWtr.flush(); X=Integer.parseInt (inbufrdr.readLine());
SP++; prntWtr.println(SP); prntWtr.flush(); AC=Integer.parseInt (inbufrdr.readLine());
SP++; prntWtr.println(SP); prntWtr.flush(); PC=Integer.parseInt (inbufrdr.readLine());
SP++; prntWtr.println(SP); prntWtr.flush(); SP=Integer.parseInt (inbufrdr.readLine()); 

    }
else { System.out.println("Error: System Stack EmPtY");}
PrcsPmemory.destroy();System.exit(1); 
break;
case 50: 
    prntWtr.println( "end");
    System.exit(0); 
timer++; if(timer==Integer.parseInt(args[1])) 
if(intrpts==true) 
prntWtr.println("1999 " +SP);
prntWtr.println("1998" + (PC));
prntWtr.println("1997 " +AC); 
prntWtr.println(" 1996 " +X); 

prntWtr.println(" 1995 " + Y );
PC=1000;
SP=1995;
intrpts=false; 
}
timer=0; 
             }
}   
catch (Exception excp) {
        System.exit(0);
    }


}


 }
 
 
 







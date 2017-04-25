/***Myclient.java***/

import java.rmi.*;
import java.io.*;
public class MyClient{

public static void main(String args[]){
try{
Adder stub=(Adder)Naming.lookup("rmi://localhost:5000/shack");
String str1;
String str2;

InputStreamReader isr = new InputStreamReader(System.in);
BufferedReader br = new BufferedReader(isr);

System.out.println("enter first string:");
str1=br.readLine();
System.out.println("enter second string:");
str2=br.readLine();
System.out.println(stub.concat(str1,str2));
System.out.println(stub.rev(str1));
System.out.println(stub.com(str1,str2));

}catch(Exception e){System.out.println(e);}
}

}

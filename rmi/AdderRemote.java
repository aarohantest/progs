import java.rmi.*;
import java.rmi.server.*;

public class AdderRemote extends UnicastRemoteObject implements Adder{

AdderRemote()throws RemoteException{
super();
}

public String concat(String x, String y){return "This is Concatenated String:"+x+y;}
public String rev(String x){ String s = new StringBuilder(x).reverse().toString();
return "The reverse of string is :"+s;
}
public String com(String x,String y)
{
//inf flag = 0;
String s1= x;
String s2=y;

if(s1.equals(s2))
return "Strings are equal";
else
return "Strings are not equal";

}
}

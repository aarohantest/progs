/***************ADDER*************/
import java.rmi.*;
public interface Adder extends Remote{

public String concat(String x,String y)throws RemoteException;
public String rev(String x)throws RemoteException;
public String com(String x,String y)throws RemoteException;
}



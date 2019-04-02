package ClientController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import View.GUI;
package ClientController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import View.GUI;

public class Client {
	private Socket theSocket;
	private ObjectInputStream objectIn;
	private PrintWriter writeOut;
	
	public Client(String serverName, int port) {
		try {
		theSocket=new Socket(serverName,port);
		//objectIn=new ObjectInputStream(theSocket.getInputStream());
		writeOut=new PrintWriter(theSocket.getOutputStream());
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void sendString(String s) {
		writeOut.println(s);
		writeOut.flush();
		
	}
	public void listSuppliers() {
		sendString("1");
		System.out.println("list suppliers");
	}
	public void listTools() {
		sendString("2");
		System.out.println("list tools");
	}
	public void searchName(String name) {
		sendString("3");
		System.out.println("Searching name: " + name);
	}
	public void searchID(String ID) {
		sendString("4");
		System.out.println("Searching id: " + ID);
	}
	public void check(String ID) {
		sendString("5");
		System.out.println("Searching id for checking stock: " + ID);
	}
	public void decrease(String ID, String amount) 
	{
		sendString("6");
		System.out.println("Going to decrease item id: " + ID + " by this amount: " + amount);
	}
	public void order() {
		
		sendString("7");
		System.out.println("printing orders...");		
	}
	public static void main(String[] args) {
		Client c=new Client("localhost", 8099);
		GUI test = new GUI(c);
		test.buildAll();
		
		
	}

}
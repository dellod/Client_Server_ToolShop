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
		objectIn=new ObjectInputStream(theSocket.getInputStream());
		writeOut=new PrintWriter(theSocket.getOutputStream());
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void listSuppliers() {
		System.out.println("list suppliers");
	}
	public void listTools() {
		System.out.println("list tools");
	}
	public void searchName(String name) {
		System.out.println("Searching name: " + name);
	}
	public void searchID(String ID) {
		System.out.println("Searching id: " + ID);
	}
	public void check(String ID) {
		System.out.println("Searching id for checking stock: " + ID);
	}
	public void decrease(String ID, String amount) 
	{
		System.out.println("Going to decrease item id: " + ID + " by this amount: " + amount);
	}
	public void order() {
		System.out.println("printing orders...");		
	}
	public static void main(String[] args) {
		Client c=new Client("localhost", 8099);
		//GUI test = new GUI();
		//test.buildAll();
		
		
	}

}

package com.example.tp4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@SpringBootApplication
public class Server {

	public static void main(String[] args) {
		try {
			BankManagerImpl obj = new BankManagerImpl();

			Registry registry = LocateRegistry.createRegistry(1099);

			registry.bind("BankManagerService", obj);

			System.out.println("Serveur RMI prêt.");
		} catch (Exception e) {
			System.err.println("Exception serveur RMI: " + e.toString());
			e.printStackTrace();
		}

	}

}

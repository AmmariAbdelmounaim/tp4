package com.example.tp4;

import com.example.tp4.dtos.BookDto;
import com.example.tp4.dtos.UserDto;
import com.example.tp4.entities.Book;
import com.example.tp4.service.LibrarianService;
import com.example.tp4.utils.UserType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

@SpringBootApplication
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(
			LibrarianService service
	) {
		return args -> {
			//create book
			var book1 = BookDto.builder()
					.title("Boite a Merveille")
					.isbn("677-2-16-132490-0")
					.author("Victor Hugo")
					.publisher("La litterature francaise")
					.publicationYear(2000)
					.build();
			var book2 = BookDto.builder()
					.title("Antigone")
					.isbn("978-3-16-148410-0")
					.author("Moliere")
					.publicationYear(1999)
					.publisher("La litterature francaise")
					.build();
			service.addBook(book1);
			service.addBook(book2);
			var user1 = UserDto.builder()
					.name("Abdelmounaim")
					.address("Fez")
					.userType(UserType.school)
					.build();
			var user2 = UserDto.builder()
					.name("Ismail")
					.address("Grenoble")
					.userType(UserType.worker)
					.build();
			service.addUser(user1);
			service.addUser(user2);
		};
	}

}

package com.redhat.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.redhat.training.books.Book;
import com.redhat.training.books.BookNotAvailableException;
import com.redhat.training.inventory.InMemoryInventory;


public class LibraryTest {

    InMemoryInventory inventory;
    Library library;

    @BeforeEach
    public void setUp() {
        inventory = new InMemoryInventory();
        library = new Library(inventory);
    }

    @Test
    public void checkoutDecreases() throws BookNotAvailableException {

    // Add tests here...
   //Given
   inventory.add(new Book("book1"));
   inventory.add(new Book("book1"));


   //When 
   library.checkOut("studentA","book1");


   //Then
   assertEquals(1,inventory.countCopies("book1"));
    }

    @Test
    public void shouldNotBeAbleToCheckoutUnavailableBook() throws BookNotAvailableException { 
    	inventory.add(new Book("book1"));

	library.checkOut("student1","book1");

	final BookNotAvailableException bnaEx = assertThrows(
				BookNotAvailableException.class,
				() -> {
					library.checkOut("student2","book1");
				}
			);

	assertTrue(bnaEx.getMessage().matches("Book book1 is not available"));


    }

}

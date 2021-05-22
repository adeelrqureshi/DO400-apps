package com.redhat.training;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import com.redhat.training.books.BookNotAvailableException;
import com.redhat.training.inventory.Inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LibraryWithMockedInventoryTest {

    Inventory inventory;
    Library library;

    @BeforeEach
    public void setUp() {
        inventory = mock(Inventory.class);
        library = new Library(inventory);
    }

    // Add tests here...

    @Test
    public void checkingOutCallsWithdrawIfBookIsAvailable() throws BookNotAvailableException {
    
    	when(inventory.isBookAvailable("book1")).thenReturn(true); // instead of inmemory, call a mock instead

	library.checkOut("student1","book1");

	verify(inventory).withdraw("book1");
    
    }


    @Test
    public void checkingOutDoesNotCallsWithdrawIfBookIsNotAvailable() throws BookNotAvailableException {
    
    	when(inventory.isBookAvailable("book1")).thenReturn(false); // instead of inmemory, call a mock instead

	try{
		library.checkOut("student1","book1");
	}
	catch(BookNotAvailableException e){}

	verify(inventory,times(0)).withdraw("book1");
    }

}

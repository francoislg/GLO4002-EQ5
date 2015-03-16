package ca.ulaval.glo4002.GRAISSE.persistence;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.booking.Booking;

@RunWith(MockitoJUnitRunner.class)
public class BookingInMemoryRepositoryTest {

	@Mock
	Booking booking;
	
	@Mock
	Booking bookingThatIsNotInTheRepository;
	
	BookingInMemoryRepository bookingInMemoryRepository;
	
	@Before
	public void setUp() {
		bookingInMemoryRepository = new BookingInMemoryRepository();
	}
	
	@Test
	public void newRepositoryShouldBeEmpty() {
		Collection<Booking> result = bookingInMemoryRepository.retrieveAll();
		assertTrue(result.isEmpty());
	}
	
	@Test 
	public void whenPersistingOneBookingRepositoryShouldContainTheBooking() {
		bookingInMemoryRepository.persist(booking);
		
		Collection<Booking> result = bookingInMemoryRepository.retrieveAll();
		assertTrue(result.contains(booking));
	}
	
	@Test 
	public void whenPersistingOneBookingRepositoryShouldContainOnlyOneBooking() {
		bookingInMemoryRepository.persist(booking);
		
		Collection<Booking> result = bookingInMemoryRepository.retrieveAll();
		assertEquals(1, result.size());
	}
	
	@Test
	public void whenPersistingTwiceTheSameBookingRepositoryShouldContainOneBooking() {
		bookingInMemoryRepository.persist(booking);
		bookingInMemoryRepository.persist(booking);
		
		Collection<Booking> result = bookingInMemoryRepository.retrieveAll();
		assertEquals(1, result.size());
	}
}
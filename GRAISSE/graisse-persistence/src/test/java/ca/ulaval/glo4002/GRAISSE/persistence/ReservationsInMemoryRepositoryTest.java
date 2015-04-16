package ca.ulaval.glo4002.GRAISSE.persistence;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservation;

@RunWith(MockitoJUnitRunner.class)
public class ReservationsInMemoryRepositoryTest {

	ReservationsInMemoryRepository reservationsRepository;

	@Before
	public void setUp() {
		reservationsRepository = new ReservationsInMemoryRepository();
	}

	@Test
	public void newRepositoryShouldBeEmpty() {
		Collection<Reservation> result = reservationsRepository.retrieveAll();
		assertTrue(result.isEmpty());
	}

	@Ignore
	@Test
	public void givenOneReservedBookingExistsShouldReturnTrue() {

	}

	@Ignore
	@Test
	public void givenOneUnassignedBookingExistsShouldReturnFalse() {

	}

	@Ignore
	@Test
	public void givenOneReservedBookingRetrievingShouldReturnTheReservation() {

	}

	@Ignore
	@Test
	public void givenOneUnassignedBookingRetrievingShouldThrow() {

	}

	@Ignore
	@Test
	public void andFriends() {

	}
}
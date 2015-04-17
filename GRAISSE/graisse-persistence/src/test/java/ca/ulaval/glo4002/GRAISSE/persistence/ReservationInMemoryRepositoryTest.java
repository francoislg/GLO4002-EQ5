package ca.ulaval.glo4002.GRAISSE.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationNotFoundException;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

@RunWith(MockitoJUnitRunner.class)
public class ReservationInMemoryRepositoryTest {
	
	private static final int NUMBER_OF_RESERVATIONS = 3;
	
	@Mock
	Reservation reservation1;
	
	@Mock
	Reservation reservation2;
	
	@Mock
	Reservation reservation3;
	
	@Mock
	Reservation retrievedReservation;
	
	@Mock
	AssignedBoardroom assignedBoardroom1;
	
	@Mock
	AssignedBoardroom assignedBoardroom2;
	
	@Mock
	AssignedBoardroom assignedBoardroom3;
	
	@Mock
	AssignedBooking assignedBooking1;

	@Mock
	AssignedBooking assignedBooking2;
	
	@Mock
	AssignedBooking assignedBooking3;
	
	@Mock
	Boardroom boardroom;
	
	@Mock
	Email promoter;
	
	@Mock
	BookingID bookingID;
	
	ReservationInMemoryRepository reservationInMemoryRepository;
	
	@Before
	public void setUp() {
		reservationInMemoryRepository = new ReservationInMemoryRepository();
	}
	
	@Test
	public void givenNewRepositoryShouldBeEmpty() {
		assertTrue(reservationInMemoryRepository.retrieveAll().isEmpty());
	}
	
	@Test
	public void givenMultipleReservationsWhenPersistingShouldAddTheReservationsToTheRepository() {
		setUpRepoWithMultipleReservations();
		
		int result = reservationInMemoryRepository.retrieveAll().size();
		assertEquals(result, NUMBER_OF_RESERVATIONS);
	}
	
	@Test
	public void givenAnReservationAlreadyInRepositoryWhenPersistingShouldNotAddItToTheRepository() {
		setUpRepoWithMultipleReservations();
		reservationInMemoryRepository.persist(reservation1);
		
		int result = reservationInMemoryRepository.retrieveAll().size();
		assertEquals(result, NUMBER_OF_RESERVATIONS);
	}
	
	@Test
	public void givenMultipleReservationsWhenRetrievingByAssignedBookingReservationShouldReturnTheReservation() {
		setUpRepoWithMultipleReservations();
		retrievedReservation = reservationInMemoryRepository.retrieve(assignedBooking2);
		
		assertTrue(retrievedReservation.containsBoardroom(assignedBoardroom2));
		assertTrue(retrievedReservation.containsBooking(assignedBooking2));	
	}
	
	@Test(expected = ReservationNotFoundException.class)
	public void givenAnInexistentReservationWhenRetrievingItByAssignedBookingShouldThrowReservationNotFoundException() {
		setUpOneReservation();
		reservationInMemoryRepository.retrieve(assignedBooking1);
	}
	
	@Test
	public void givenOneReservationWhenRetrievingByPromoterAndBookingIdShoultReturnTheReservation() {
		setUpOneReservation();
		reservationInMemoryRepository.persist(reservation1);
		retrievedReservation = reservationInMemoryRepository.retrieve(promoter, bookingID);
		
		assertTrue(retrievedReservation.hasBookingID(bookingID));
		assertTrue(retrievedReservation.hasPromoter(promoter));
	}
	
	@Test(expected = ReservationNotFoundException.class)
	public void givenAnInexistentReservationWhenRetrievingItByPromoterAndBookingIdShouldThrowReservationNotFoundException() {
		reservationInMemoryRepository.retrieve(promoter, bookingID);
	}
	
	@Test
	public void givenMultipleReservationsWhenRemovingReservationShouldRemoveFromTheRepository() {
		setUpRepoWithMultipleReservations();
		reservationInMemoryRepository.remove(reservation1);
		
		int result = reservationInMemoryRepository.retrieveAll().size();
		assertEquals(result, NUMBER_OF_RESERVATIONS - 1);
	}
	
	@Test
	public void givenAnExistentReservationWhenCheckingIfItExistsByBoardroomShouldReturnTrue() {
		setUpRepoWithMultipleReservations();
		when(reservation1.containsBoardroom(boardroom)).thenReturn(true);
		
		assertTrue(reservationInMemoryRepository.exists(boardroom));
	}
	
	@Test
	public void givenAnInexistentReservationWhenCheckingIfItExistsByBoardroomShouldReturnFalse() {
		setUpRepoWithMultipleReservations();
		assertFalse(reservationInMemoryRepository.exists(boardroom));
	}
	
	@Test
	public void givenAnExistentReservationWhenCheckingIfItExistsByPromoterAndBookingIdShouldReturnTrue() {
		setUpOneReservation();
		setUpRepoWithMultipleReservations();
		
		assertTrue(reservationInMemoryRepository.exists(promoter, bookingID));
	}
	
	@Test
	public void givenAnInexistentReservationWhenCheckingIfItExistsByPromoterAndBookingIdShouldReturnFalse() {
		setUpRepoWithMultipleReservations();
		assertFalse(reservationInMemoryRepository.exists(promoter, bookingID));
	}
	
	private void setUpRepoWithMultipleReservations() {
		setUpMultipleReservations();
		reservationInMemoryRepository.persist(reservation1);
		reservationInMemoryRepository.persist(reservation2);
		reservationInMemoryRepository.persist(reservation3);
	}
	
	private void setUpOneReservation() {
		when(reservation1.containsBoardroom(assignedBoardroom1)).thenReturn(true);
		when(reservation1.containsBooking(assignedBooking1)).thenReturn(true);
		when(reservation1.hasPromoter(promoter)).thenReturn(true);
		when(reservation1.hasBookingID(bookingID)).thenReturn(true);
	}
	
	private void setUpMultipleReservations() {
		when(reservation1.containsBoardroom(assignedBoardroom1)).thenReturn(true);
		when(reservation1.containsBooking(assignedBooking1)).thenReturn(true);
		
		when(reservation2.containsBoardroom(assignedBoardroom2)).thenReturn(true);
		when(reservation2.containsBooking(assignedBooking2)).thenReturn(true);
		
		when(reservation3.containsBoardroom(assignedBoardroom3)).thenReturn(true);
		when(reservation3.containsBooking(assignedBooking3)).thenReturn(true);
	}
}
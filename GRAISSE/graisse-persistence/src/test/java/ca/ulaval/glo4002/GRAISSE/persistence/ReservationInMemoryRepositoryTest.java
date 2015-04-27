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
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservation.ReservationNotFoundException;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

@RunWith(MockitoJUnitRunner.class)
public class ReservationInMemoryRepositoryTest {

	private static final int NUMBER_OF_RESERVATIONS = 3;
	private static final String A_BOARDROOM_NAME = "Boardroom";

	@Mock
	Reservation reservation1;

	@Mock
	Reservation reservation2;

	@Mock
	Reservation reservation3;

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
		Reservation retrievedReservation = reservationInMemoryRepository.retrieve(assignedBooking2);

		assertTrue(retrievedReservation.containsBoardroom(assignedBoardroom2));
		assertTrue(retrievedReservation.containsBooking(assignedBooking2));
	}

	@Test(expected = ReservationNotFoundException.class)
	public void givenAnInexistentReservationWhenRetrievingItByAssignedBookingShouldThrowReservationNotFoundException() {
		setUpOneReservation();
		reservationInMemoryRepository.retrieve(assignedBooking1);
	}

	@Test
	public void givenOneReservationWhenRetrievingByEmailAndBookingIdShoultReturnTheReservation() {
		setUpOneReservation();
		reservationInMemoryRepository.persist(reservation1);
		Reservation retrievedReservation = reservationInMemoryRepository.retrieve(promoter, bookingID);

		assertTrue(retrievedReservation.hasBookingID(bookingID));
		assertTrue(retrievedReservation.hasPromoter(promoter));
	}

	@Test(expected = ReservationNotFoundException.class)
	public void givenAnInexistentReservationWhenRetrievingItByEmailAndBookingIdShouldThrowReservationNotFoundException() {
		reservationInMemoryRepository.retrieve(promoter, bookingID);
	}

	@Test(expected = ReservationNotFoundException.class)
	public void givenReservationWithDifferentEmailWhenRetrievingByEmailAndIdShouldThrowReservationNotFoundException() {
		when(reservation1.hasPromoter(promoter)).thenReturn(true);
		when(reservation1.hasBookingID(bookingID)).thenReturn(false);

		reservationInMemoryRepository.persist(reservation1);

		reservationInMemoryRepository.retrieve(promoter, bookingID);
	}

	@Test(expected = ReservationNotFoundException.class)
	public void givenReservationWithDifferentBookingIdWhenRetrievingByEmailAndIdShouldThrowReservationNotFoundException() {
		when(reservation1.hasPromoter(promoter)).thenReturn(false);
		when(reservation1.hasBookingID(bookingID)).thenReturn(true);

		reservationInMemoryRepository.persist(reservation1);

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
		when(reservation1.containsBoardroom(boardroom)).thenReturn(true);

		reservationInMemoryRepository.persist(reservation1);

		assertTrue(reservationInMemoryRepository.exists(boardroom));
	}

	@Test
	public void givenAnInexistentReservationWhenCheckingIfItExistsByBoardroomShouldReturnFalse() {
		when(reservation1.containsBoardroom(boardroom)).thenReturn(false);

		reservationInMemoryRepository.persist(reservation1);

		assertFalse(reservationInMemoryRepository.exists(boardroom));
	}

	@Test
	public void givenAnExistentReservationWhenCheckingIfItExistsByEmailAndBookingIdShouldReturnTrue() {
		when(reservation1.hasPromoter(promoter)).thenReturn(true);
		when(reservation1.hasBookingID(bookingID)).thenReturn(true);

		reservationInMemoryRepository.persist(reservation1);

		assertTrue(reservationInMemoryRepository.exists(promoter, bookingID));
	}

	@Test
	public void givenAnInexistentReservationWhenCheckingIfItExistsByEmailAndBookingIdShouldReturnFalse() {
		when(reservation1.hasPromoter(promoter)).thenReturn(false);
		when(reservation1.hasBookingID(bookingID)).thenReturn(false);

		reservationInMemoryRepository.persist(reservation1);

		assertFalse(reservationInMemoryRepository.exists(promoter, bookingID));
	}

	@Test
	public void givenReservationWithDifferentEmailWhenCheckingIfItExistsByEmailAndBookingIdShouldReturnFalse() {
		when(reservation1.hasPromoter(promoter)).thenReturn(false);
		when(reservation1.hasBookingID(bookingID)).thenReturn(true);

		reservationInMemoryRepository.persist(reservation1);

		assertFalse(reservationInMemoryRepository.exists(promoter, bookingID));
	}

	@Test
	public void givenReservationWithDifferentBookingIdWhenCheckingIfItExistsByEmailAndBookingIdShouldReturnFalse() {
		when(reservation1.hasPromoter(promoter)).thenReturn(true);
		when(reservation1.hasBookingID(bookingID)).thenReturn(false);

		reservationInMemoryRepository.persist(reservation1);

		assertFalse(reservationInMemoryRepository.exists(promoter, bookingID));
	}

	@Test
	public void givenAnActiveReservationWhenAskingIfExistsWithBoardroomShouldReturnTrue() {
		when(reservation1.isCancelled()).thenReturn(false);
		when(reservation1.hasBoardroomName(A_BOARDROOM_NAME)).thenReturn(true);
		when(boardroom.getName()).thenReturn(A_BOARDROOM_NAME);

		reservationInMemoryRepository.persist(reservation1);

		assertTrue(reservationInMemoryRepository.activeReservationWithBoardroomExist(boardroom));
	}

	@Test
	public void givenAnInactiveReservationWhenAskingIfExistsWithBoardroomShouldReturnFalse() {
		when(reservation1.isCancelled()).thenReturn(true);
		when(reservation1.hasBoardroomName(A_BOARDROOM_NAME)).thenReturn(true);
		when(boardroom.getName()).thenReturn(A_BOARDROOM_NAME);

		reservationInMemoryRepository.persist(reservation1);

		assertFalse(reservationInMemoryRepository.activeReservationWithBoardroomExist(boardroom));
	}

	@Test
	public void givenAnActiveReservationWhenAskingIfExistsWithUnassignedBoardroomNShouldReturnFalse() {
		when(reservation1.isCancelled()).thenReturn(false);
		when(reservation1.hasBoardroomName(A_BOARDROOM_NAME)).thenReturn(false);
		when(boardroom.getName()).thenReturn(A_BOARDROOM_NAME);

		reservationInMemoryRepository.persist(reservation1);

		assertFalse(reservationInMemoryRepository.activeReservationWithBoardroomExist(boardroom));
	}

	@Test
	public void givenAnExistentReservationWithAssignedBookingWhenAskingIfExistsShouldReturnTrue() {
		when(reservation1.containsBooking(assignedBooking1)).thenReturn(true);
		reservationInMemoryRepository.persist(reservation1);

		assertTrue(reservationInMemoryRepository.exists(assignedBooking1));
	}

	@Test
	public void givenAnExistentReservationWithDifferentBookingWhenAskingIfExistsShouldReturnFalse() {
		when(reservation1.containsBooking(assignedBooking1)).thenReturn(false);
		reservationInMemoryRepository.persist(reservation1);

		assertFalse(reservationInMemoryRepository.exists(assignedBooking1));
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
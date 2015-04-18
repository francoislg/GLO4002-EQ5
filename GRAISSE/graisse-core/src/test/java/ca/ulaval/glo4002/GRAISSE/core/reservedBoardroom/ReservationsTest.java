package ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;

public class ReservationsTest {

	private final static String A_BOARDROOM_NAME = "Boardroom";
	@Mock
	ReservationRepository reservationRepository;

	@Mock
	Boardrooms boardrooms;

	@Mock
	Bookings bookings;

	@Mock
	Reservation reservation;

	@Mock
	Boardroom boardroom;

	@Mock
	AssignedBooking assignedBooking;

	@Mock
	AssignedBooking unassignedBooking;

	@Mock
	BookingAssignable assignableBooking;

	@Mock
	AssignedBoardroom assignedBoardroom;

	@Mock
	AssignedBoardroom unassignedBoardroom;

	Reservations reservations;

	Collection<Reservation> reservationRepositoryWithReservations;
	Collection<Reservation> emptyReservationRepository;

	@Before
	public void setUp() {
		setUpReservationMock();
		when(reservationRepository.retrieveAll()).thenReturn(Arrays.asList(reservation));
		reservations = new Reservations(reservationRepository, boardrooms, bookings);
	}

	@Ignore
	@Test
	public void givenOneBoardroomWhenIsAvailableShouldExistsInRepository() {
		when(reservationRepository.exists(boardroom)).thenReturn(true);
		assertTrue(reservations.isAvailable(boardroom));
	}

	private void setUpReservationMock() {
		when(boardroom.hasName(A_BOARDROOM_NAME)).thenReturn(true);
		when(reservation.containsBoardroom(assignedBoardroom)).thenReturn(true);
		when(reservation.containsBooking(assignedBooking)).thenReturn(true);
	}
}

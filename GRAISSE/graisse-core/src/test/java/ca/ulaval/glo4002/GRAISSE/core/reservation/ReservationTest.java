package ca.ulaval.glo4002.GRAISSE.core.reservation;

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
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingState;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

@RunWith(MockitoJUnitRunner.class)
public class ReservationTest {

	private static final String A_BOARDROOM_NAME = "Boardroom";
	private static final String A_PROMOTER_EMAIL = "promoter@ulaval.ca";

	private static final int NUMBER_OF_SEATS = 10;

	@Mock
	AssignedBoardroom assignedBoardroom;

	@Mock
	AssignedBoardroom boardroomNotAssignedToReservation;

	@Mock
	AssignedBooking assignedBooking;

	@Mock
	Email promoter;

	@Mock
	BookingID bookingID;

	Reservation reservation;

	@Before
	public void setUp() {
		reservation = new Reservation(assignedBoardroom, assignedBooking);
	}

	@Test
	public void givenBoardroomAssignedToReservationWhenCheckingIfAssignedShouldReturnTrue() {
		assertTrue(reservation.containsBoardroom(assignedBoardroom));
	}

	@Test
	public void givenBoardroomNotAssignedToReservationWhenCheckingIfAssignedShouldReturnFalse() {
		assertFalse(reservation.containsBoardroom(boardroomNotAssignedToReservation));
	}

	@Test
	public void givenBookingAssignedToReservationWhenCheckingIfAssignedShouldReturnTrue() {
		assertTrue(reservation.containsBooking(assignedBooking));
	}

	@Test
	public void givenAnAssignedBookingWhenCheckingIfReservationHasThePromoterhouldReturnTrue() {
		when(assignedBooking.hasPromoter(promoter)).thenReturn(true);
		assertTrue(reservation.hasPromoter(promoter));
	}

	@Test
	public void givenAnAssignedBoardroomWhenCheckingIfReservationHasTheBoardroomNameShouldReturnTrue() {
		when(assignedBoardroom.hasName(A_BOARDROOM_NAME)).thenReturn(true);
		assertTrue(reservation.hasBoardroomName(A_BOARDROOM_NAME));
	}

	@Test
	public void givenAnAssignedBookingWhenCancellingReservationShouldCancelTheAssignedBooking() {
		reservation.cancel();
		assertTrue(reservation.isCancel());
	}

	@Test
	public void givenAnAssignedBookingWhenAskingForNumberOfSeatsShouldReturnNumberOfSeatsOfAssignedBooking() {
		when(assignedBooking.getNumberOfSeats()).thenReturn(NUMBER_OF_SEATS);
		int result = reservation.getNumberOfSeats();
		assertEquals(result, NUMBER_OF_SEATS);
	}

	@Test
	public void givenAnAssignedBookingWhenAskingForPromoterShouldReturnPromoterOfAssignedBooking() {
		when(assignedBooking.getPromoterEmail()).thenReturn(A_PROMOTER_EMAIL);
		String result = reservation.getPromoterEmail();
		assertEquals(result, A_PROMOTER_EMAIL);
	}

	@Test
	public void givenAnAssignedBookingWhenAskingForStateShouldReturnStateOfAssignedBooking() {
		when(assignedBooking.getState()).thenReturn(BookingState.WAITING);
		assertEquals(reservation.getState(), BookingState.WAITING);
	}

	@Test
	public void givenAnAssignedBoardroomWhenAskingForBoardroomNameShouldReturnAssignedBoardroomName() {
		when(assignedBoardroom.getName()).thenReturn(A_BOARDROOM_NAME);
		assertEquals(reservation.getBoardroomName(), A_BOARDROOM_NAME);
	}

	@Test
	public void givenAnAssignedBookingWhenAskingForBookingIDShouldReturnBookingIDOfAssignedBooking() {
		when(assignedBooking.getID()).thenReturn(bookingID);
		assertEquals(reservation.getBookingID(), bookingID);
	}

	@Test
	public void givenAnAssignedBookingWhenCheckingIfReservationHasBookingIDShouldReturnTrue() {
		when(assignedBooking.hasID(bookingID)).thenReturn(true);
		assertTrue(reservation.hasBookingID(bookingID));
	}
}

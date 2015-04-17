package ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

@RunWith(MockitoJUnitRunner.class)
public class ReservationTest {

	@Mock
	AssignedBoardroom assignedBoardroom;

	@Mock
	AssignedBooking assignedBooking;

	@Mock
	BookingAssignable bookingToAssign;
	
	@Mock
	Email promoter;
	
	@Mock
	BookingID bookingID;

	Reservation reservation;

	@Before
	public void setUp() {
		reservation = new Reservation(assignedBoardroom, bookingToAssign);
	}
	
	@Test
	public void test() {
		
	}
}

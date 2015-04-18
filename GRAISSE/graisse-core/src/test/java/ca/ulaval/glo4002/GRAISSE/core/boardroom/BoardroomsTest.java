package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.booking.BookingsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.shared.Notifyer;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomsTest {

	@Mock
	BoardroomRepository boardroomRepository;

	@Mock
	Notifyer<BookingAssignable> notifyer;

	@Mock
	Notifyer<BookingAssignable> secondNotifyer;

	@Mock
	BookingAssignable assignableBooking;

	@Mock
	BookingAssignable unassignableBooking;

	@Mock
	BookingsSortingStrategy bookingsSortingStrategy;

	@Mock
	BoardroomsSortingStrategy boardroomsSortingStrategy;

	@Mock
	Boardroom boardroom;

	Boardrooms boardrooms;

	@Before
	public void setUp() {
		setUpBoardroomMock();
		when(boardroomsSortingStrategy.sort(any())).thenReturn(Arrays.asList(boardroom));
		boardrooms = new Boardrooms(boardroomRepository);
	}

	@Test
	public void DoTest() {
		assertTrue(true);
	}

	private void setUpBoardroomMock() {
		when(boardroom.canAssign(assignableBooking)).thenReturn(true);
		when(boardroom.canAssign(unassignableBooking)).thenReturn(false);
	}
}

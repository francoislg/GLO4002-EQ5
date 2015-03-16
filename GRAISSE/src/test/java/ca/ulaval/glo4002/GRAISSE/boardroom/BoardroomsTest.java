package ca.ulaval.glo4002.GRAISSE.boardroom;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.boardroom.exceptions.UnableToAssignBookingException;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsStrategy;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomsTest {
	
	@Mock
	BoardroomRepository boardroomRepository;
	
	@Mock
	BookingTrigger trigger;
	
	@Mock
	BookingTrigger secondTrigger;
	
	@Mock
	BookingAssignable assignableBooking;
	
	@Mock
	BookingAssignable unassignableBooking;
	
	@Mock
	BookingsStrategy bookingsStrategy;
	
	@Mock
	BoardroomsStrategy boardroomsStrategy;
	
	@Mock
	Boardroom boardroom;
	
	Boardrooms boardrooms;
	
	@Before
	public void setUp(){
		setUpBoardroomMock();
		when(boardroomsStrategy.sort(any())).thenReturn(Arrays.asList(boardroom));
		boardrooms = new Boardrooms(boardroomRepository);
	}
	
	@Test
	public void givenAnAssignableBookingWhenBoardroomIsAssignedShouldPersistBoardroomInRepository(){
		boardrooms.assignBookingToBoardroom(assignableBooking, boardroomsStrategy);
		
		verify(boardroomRepository).persist(boardroom);
	}
	
	@Test(expected=UnableToAssignBookingException.class)
	public void givenAnUnassignableBookingWhenBoardroomIsNotAssignedShouldThrowException(){
		boardrooms.assignBookingToBoardroom(unassignableBooking, boardroomsStrategy);
	}
	
	@Test
	public void givenATriggerIsAddedWhenBookingAssignedShouldNotifyTrigger(){
		boardrooms.registerBookingTrigger(trigger);
		
		boardrooms.assignBookingToBoardroom(assignableBooking, boardroomsStrategy);
		
		verify(trigger).update(assignableBooking);
	}

	@Test
	public void givenMultipleTriggersAreAddedWhenBookingAssignedShouldNotifyAllTriggers(){
		boardrooms.registerBookingTrigger(trigger);
		boardrooms.registerBookingTrigger(secondTrigger);
		
		boardrooms.assignBookingToBoardroom(assignableBooking, boardroomsStrategy);
		
		verify(trigger).update(assignableBooking);
		verify(secondTrigger).update(assignableBooking);
	}
	
	private void setUpBoardroomMock(){
		when(boardroom.assign(assignableBooking)).thenReturn(true);
		when(boardroom.assign(unassignableBooking)).thenReturn(false);
	}
}

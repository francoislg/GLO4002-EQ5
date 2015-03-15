package ca.ulaval.glo4002.GRAISSE.boardroom;

import static org.mockito.Mockito.*;

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
	BookingAssignedTrigger trigger;
	
	@Mock
	BookingAssignedTrigger secondTrigger;
	
	@Mock
	BookingAssignable assignedBooking;
	
	@Mock
	BookingAssignable notAssignedBooking;
	
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
	public void givenABookingWhenBoardroomIsAssignedShouldPersistBoardroomInRepository(){
		boardrooms.assignBookingToBoardroom(assignedBooking, boardroomsStrategy);
		
		verify(boardroomRepository).persist(boardroom);
	}
	
	@Test(expected=UnableToAssignBookingException.class)
	public void givenABookingWhenBoardroomIsNotAssignedShouldThrowException(){
		boardrooms.assignBookingToBoardroom(notAssignedBooking, boardroomsStrategy);
	}
	
	@Test
	public void givenATriggerIsAddedWhenBookingAssignedShouldNotifyTrigger(){
		boardrooms.registerBookingAssignedTrigger(trigger);
		
		boardrooms.assignBookingToBoardroom(assignedBooking, boardroomsStrategy);
		
		verify(trigger).update(assignedBooking);
	}
	
	@Test
	public void givenMultipleTriggersAreAddedWhenBookingAssignedShouldNotifyAllTriggers(){
		boardrooms.registerBookingAssignedTrigger(trigger);
		boardrooms.registerBookingAssignedTrigger(secondTrigger);
		
		boardrooms.assignBookingToBoardroom(assignedBooking, boardroomsStrategy);
		
		verify(trigger).update(assignedBooking);
		verify(secondTrigger).update(assignedBooking);
	}
	
	private void setUpBoardroomMock(){
		when(boardroom.assign(assignedBooking)).thenReturn(true);
		when(boardroom.assign(notAssignedBooking)).thenReturn(false);
	}
}

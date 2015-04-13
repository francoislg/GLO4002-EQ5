package ca.ulaval.glo4002.GRAISSE.uat.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.apache.commons.lang.time.StopWatch;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.TimedSequentialTrigger;
import ca.ulaval.glo4002.GRAISSE.application.service.shared.ServiceLocator;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationRepository;
import ca.ulaval.glo4002.GRAISSE.uat.fixtures.FakeBookerFactory;
import ca.ulaval.glo4002.GRAISSE.uat.fixtures.FakeBookingFactory;
import ca.ulaval.glo4002.GRAISSE.uat.steps.ReservationsSteps.ReservationStepState;
import ca.ulaval.glo4002.GRAISSE.uat.steps.state.StatefulStep;
import ca.ulaval.glo4002.GRAISSE.uat.steps.state.StepState;


public class ReservationsSteps extends StatefulStep<ReservationStepState> {

	private static final long AN_INTERVAL = 1;
	
	protected ReservationStepState getInitialState() {
        return new ReservationStepState();
    }
	
	@Given("some rooms")
	public void givenSomeRooms() {
        state().firstAvailableBoardroom = new Boardroom("first available boardroom", 5);
        state().secondAvailableBoardroom = new Boardroom("second available boardroom", 5);
 
        BoardroomRepository boardroomRepositoy = getBoardroomRepository();
        boardroomRepositoy.persist(state().firstAvailableBoardroom);
        boardroomRepositoy.persist(state().secondAvailableBoardroom);
    }
	
	@Given("an application to be processed")
    public void givenAnApplicationToBeProcessed() {
        state().booking = FakeBookingFactory.create();
        
        state().booker = FakeBookerFactory.create();
        state().booker.addBooking(state().booking);
    }
	
	@Given("some applications")
    public void givenSomeApplicationsToBeProcessed() {
        state().booking = FakeBookingFactory.create();
        state().secondBooking = FakeBookingFactory.create();
    }
	
	@Given("an interval")
	public void givenAnInterval() {
		state().timedTrigger = new TimedSequentialTrigger(AN_INTERVAL);
		
		state().booker = FakeBookerFactory.create();
	    state().booker.addBooking(state().booking);
		state().booker.registerTrigger(state().timedTrigger);
	}
	
	@When("the application is processed")
    public void whenTheApplicationIsProcessed() {
        state().booker.assignBookings();
    }
	
	@When("an other application to be processed is added")
    public void whenAnOtherApplicationToBeProcessedIsAdded() {
		 state().secondBooking = FakeBookingFactory.create();	   
	     state().booker.addBooking(state().secondBooking);
    }
	
	@When("the first application has been queued for process")
	public void whenTheFirstApplicationHasBeenQueuedForProcess() {
		state().booker.addBooking(state().booking);
	}
	 
	@Then("the application is assigned to the first available room")
	public void thenTheApplicationIsAssignedToTheFirstAvailableRoom() {
		ReservationRepository reservationRepository = getReservationRepository();
		Reservation reservation = reservationRepository.retrieve(state().booking);

	    assertTrue("The reservation should be associated with the first available boardroom",
	    		reservation.containsBoardroom(state().firstAvailableBoardroom));
	}
	
	@Then("two applications are to be processed")
	public void thenTwoApplicationsAreToBeProcessed() {
	    assertEquals("The reservation should be associated with the first available boardroom", 2,
	    		state().booker.numberOfBookingsToAssign());
	}
	
	@Then("the applications are processed at the end of the interval")
	public void thenTheApplicationsAreProcessedAtTheEndOfTheInterval() {
		
		assertTrue("the applications should not be processed yet", state().booker.hasBookingsToAssign());
		
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		
		while(stopwatch.getTime() <  state().timedTrigger.getMilliSecondInterval())
		{
			//waiting
		}
		
		assertFalse("the applications should have been processed", state().booker.hasBookingsToAssign());
	}
	
	private BoardroomRepository getBoardroomRepository(){
		 return ServiceLocator.getInstance().resolve(BoardroomRepository.class);
	}
	
	private BookingRepository getBookingRepository(){
		return ServiceLocator.getInstance().resolve(BookingRepository.class);
	}
	
	private ReservationRepository getReservationRepository(){
		return ServiceLocator.getInstance().resolve(ReservationRepository.class);
	}
	
	public class ReservationStepState extends StepState {
		Boardroom firstAvailableBoardroom;
		Boardroom secondAvailableBoardroom;
		Booking booking;
		Booking secondBooking;
		Booker booker;
		TimedSequentialTrigger timedTrigger;
    }
}

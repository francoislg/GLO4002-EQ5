package ca.ulaval.glo4002.GRAISSE.uat.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Timer;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;
import ca.ulaval.glo4002.GRAISSE.application.service.booking.BookerStrategiesFactory;
import ca.ulaval.glo4002.GRAISSE.application.service.booking.BookerStrategiesFactory.StrategyType;
import ca.ulaval.glo4002.GRAISSE.application.service.booking.BookerStrategy;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.BookerTimerTask;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.BookerTimerTaskFactory;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.TimedSequentialTrigger;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.TimerFactory;
import ca.ulaval.glo4002.GRAISSE.application.service.shared.ServiceLocator;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservation.ReservationRepository;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;
import ca.ulaval.glo4002.GRAISSE.uat.fixtures.FakeBookerFactory;
import ca.ulaval.glo4002.GRAISSE.uat.fixtures.FakeBookingFactory;
import ca.ulaval.glo4002.GRAISSE.uat.steps.ReservationsSteps.ReservationStepState;
import ca.ulaval.glo4002.GRAISSE.uat.steps.state.StatefulStep;
import ca.ulaval.glo4002.GRAISSE.uat.steps.state.StepState;

public class ReservationsSteps extends StatefulStep<ReservationStepState> {

	private static final long AN_INTERVAL = 1;
	private static final long AN_INTERVAL_IN_MILLISECOND = 60000;

	protected ReservationStepState getInitialState() {
		return new ReservationStepState();
	}

	@Given("some rooms")
	public void givenSomeRooms() {
		state().firstAvailableBoardroom = new Boardroom("first available boardroom", 10);
		state().secondAvailableBoardroom = new Boardroom("second available boardroom", 10);

		BoardroomRepository boardroomRepository = getBoardroomRepository();
		boardroomRepository.persist(state().firstAvailableBoardroom);
		boardroomRepository.persist(state().secondAvailableBoardroom);
	}

	@Given("a room with $numberOfSeats seats")
	public void givenARoomWith10Seats(int numberOfSeats) {
		state().lastRoom = new Boardroom("room with " + numberOfSeats + " seats", numberOfSeats);

		BoardroomRepository boardroomRepository = getBoardroomRepository();
		boardroomRepository.persist(state().lastRoom);
	}

	@Given("a sort by seats strategy")
	public void givenSortBySeatsStrategy() {
		state().bookerSortingStrategy = new BookerStrategiesFactory().create(StrategyType.PRIORITY);
	}

	@Given("an application with $numberOfSeats seats")
	public void givenAnApplicationWithSeatsToBeProcessed(int numberOfSeats) {
		state().booking = new Booking(state().user, numberOfSeats);

		state().booker.setBookerStrategy(state().bookerSortingStrategy);
		state().booker.addBooking(state().booking);
	}

	@Given("an application to be processed")
	public void givenAnApplicationToBeProcessed() {
		state().booking = FakeBookingFactory.create();

		state().booker.setBookerStrategy(state().bookerSortingStrategy);
		state().booker.addBooking(state().booking);
	}

	@Given("some applications")
	public void givenSomeApplicationsToBeProcessed() {
		state().booking = FakeBookingFactory.create();
		state().secondBooking = FakeBookingFactory.create();
	}

	@Given("an interval")
	public void givenAnInterval() {
		ensureThatMockedTimerDoesNotStartANewThread();
		doReturn(state().bookerTimerTask).when(state().bookerTimerTaskFactory).createBookerTimerTask(state().booker);
		doReturn(state().timer).when(state().timerFactory).createTimer();
		state().timedTrigger = new TimedSequentialTrigger(AN_INTERVAL, state().timerFactory, state().bookerTimerTaskFactory);

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

	@Then("the reservation should be associated with the first available room")
	public void thenTheReservationShouldBeAssociatedWithTheFirstAvailableRoom() {
		ReservationRepository reservationRepository = getReservationRepository();
		Reservation reservation = reservationRepository.retrieve(state().booking);

		assertTrue("The reservation should be associated with the first available boardroom", reservation.containsBoardroom(state().firstAvailableBoardroom));
	}

	@Then("two applications are to be processed")
	public void thenTwoApplicationsAreToBeProcessed() {
		assertEquals("The reservation should be associated with the first available boardroom", 2, state().booker.numberOfBookingsToAssign());
	}

	@Then("the applications are processed at the end of the interval")
	public void thenTheApplicationsAreProcessedAtTheEndOfTheInterval() {
		verify(state().timer).schedule(state().bookerTimerTask, AN_INTERVAL_IN_MILLISECOND);
	}

	@Then("the reservation should be associated with the first fitting room")
	public void thenTheReservationShouldBeAssociatedWithTheFirstFittingRoom() {
		ReservationRepository reservationRepository = getReservationRepository();
		Reservation reservation = reservationRepository.retrieve(state().booking);

		assertTrue("The reservation should be associated with the first fitting boardroom", reservation.containsBoardroom(state().firstAvailableBoardroom));
	}

	@Then("the reservation should be associated with the last room")
	public void thenTheReservationShouldBeAssociatedWithTheLastRoom() {
		ReservationRepository reservationRepository = getReservationRepository();
		Reservation reservation = reservationRepository.retrieve(state().booking);

		assertTrue("The reservation should be associated with the best fitting boardroom", reservation.containsBoardroom(state().lastRoom));
	}

	private void ensureThatMockedTimerDoesNotStartANewThread() {
		doNothing().when(state().timer).schedule(state().bookerTimerTask, AN_INTERVAL_IN_MILLISECOND);
	}

	private BoardroomRepository getBoardroomRepository() {
		return ServiceLocator.getInstance().resolve(BoardroomRepository.class);
	}

	private BookingRepository getBookingRepository() {
		return ServiceLocator.getInstance().resolve(BookingRepository.class);
	}

	private ReservationRepository getReservationRepository() {
		return ServiceLocator.getInstance().resolve(ReservationRepository.class);
	}

	public class ReservationStepState extends StepState {
		Boardroom lastRoom;
		User user = new User(new Email("random@email.ca"));
		BookerStrategy bookerSortingStrategy = new BookerStrategiesFactory().create(StrategyType.BASIC);
		Boardroom firstAvailableBoardroom;
		Boardroom secondAvailableBoardroom;
		Booking booking;
		Booking secondBooking;
		Booker booker = FakeBookerFactory.create();
		TimedSequentialTrigger timedTrigger;

		BookerTimerTaskFactory bookerTimerTaskFactory = mock(BookerTimerTaskFactory.class);
		BookerTimerTask bookerTimerTask = mock(BookerTimerTask.class);
		Timer timer = mock(Timer.class);
		TimerFactory timerFactory = mock(TimerFactory.class);
	}
}

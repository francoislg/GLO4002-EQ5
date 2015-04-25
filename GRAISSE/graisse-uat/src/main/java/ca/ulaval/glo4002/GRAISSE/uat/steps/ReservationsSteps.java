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
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailMessage;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailSender;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.SimpleMailMessage;
import ca.ulaval.glo4002.GRAISSE.application.service.notification.BookingAssignationSendMailNotifier;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.BookerTimerTask;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.BookerTimerTaskFactory;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.TimedSequentialTrigger;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.TimerFactory;
import ca.ulaval.glo4002.GRAISSE.application.service.shared.ServiceLocator;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.Priority;
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

	private static final int A_NUMBER_OF_SEATS = 15;
	
	private static final String MAIL_SUBJECT = "Update on your booking";
	private static final String BOOKING_IS_ASSIGNED_MESSAGE = "Congratulations ! Assignation succeeded !";
	private static final String BOOKING_IS_NOT_ASSIGNED_MESSAGE = "Assignation failed !";

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
	
	@Given("two rooms with the same number of seats")
	public void givenTwoRoomsWithTheSameNumberOfSeats() {
		state().firstRoom = new Boardroom("first room", A_NUMBER_OF_SEATS);
		state().secondRoom = new Boardroom("second room", A_NUMBER_OF_SEATS);
		
		BoardroomRepository boardroomRepository = getBoardroomRepository();
		boardroomRepository.persist(state().firstRoom);
		boardroomRepository.persist(state().secondRoom);
	}
	
	@Given("two applications to be processed with differents priorities")
	public void givenTwoApplicationsToBeProcessedWithDifferentsPriorities() {
		state().bookingWithVeryHighPriority = new Booking(state().user, A_NUMBER_OF_SEATS, Priority.VERY_HIGH);
		state().bookingWithLowPriority = new Booking(state().user, A_NUMBER_OF_SEATS, Priority.LOW);
		
		BookingRepository bookingRepository = getBookingRepository();
		bookingRepository.persist(state().bookingWithVeryHighPriority);
		bookingRepository.persist(state().bookingWithLowPriority);	
		
		state().booker.addBooking(state().bookingWithVeryHighPriority);
		state().booker.addBooking(state().bookingWithLowPriority);
	}
	
	@Given("an application to be processed with a priority 3")
	public void givenAnApplicationToBeProcessedWithAPriority3() {
		state().booking = new Booking(state().user, A_NUMBER_OF_SEATS, Priority.MEDIUM);
		
		BookingRepository bookingRepository = getBookingRepository();
		bookingRepository.persist(state().booking);
		
		state().booker.addBooking(state().booking);
	}
	
	@Given("a room with $numberOfSeats seats")
	public void givenARoomWithANumberOfSeats(int numberOfSeats) {
		state().lastRoom = new Boardroom("room with " + numberOfSeats + " seats", numberOfSeats);

		BoardroomRepository boardroomRepository = getBoardroomRepository();
		boardroomRepository.persist(state().lastRoom);
	}

	@Given("a sort by seats strategy")
	public void givenSortBySeatsStrategy() {
		state().bookerSortingStrategy = new BookerStrategiesFactory().create(StrategyType.PRIORITY);
	}
	
	@Given("a sort by priority strategy")
	public void givenSortByPriorityStrategy() {
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

	@Given("an application notification system")
	public void givenAnApplicationNotification() {
		state().bookingAssignationSendMailNotifier = new BookingAssignationSendMailNotifier(state().mailSender, state().responsible);
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
	
	@When("the applications are processed")
	public void whenTheApplicationsAreProcessed() {
		state().booker.assignBookings();
	}
	
	@When("another application to be processed with the same priority is added and the applications are processed")
	public void whenAnotherApplicationToBeProcessedWithTheSamePriorityIsAddedAndTheApplicationsAreProcessed() {
		state().secondBooking = new Booking(state().user, A_NUMBER_OF_SEATS, Priority.MEDIUM);
		
		BookingRepository bookingRepository = getBookingRepository();
		bookingRepository.persist(state().secondBooking);
		
		state().booker.addBooking(state().secondBooking);
		state().booker.assignBookings();
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

		assertTrue("The reservation should be associated with the last boardroom", reservation.containsBoardroom(state().lastRoom));
	}
	
	@Then("the reservation with the application with the highest priority should be associated with the first room available")
	public void thenTheReservationWithTheApplicationWithTheHighestPriorityShouldBeAssociatedWithTheFirstRoomAvailable() {
		ReservationRepository reservationRepository = getReservationRepository();
		Reservation reservation = reservationRepository.retrieve(state().bookingWithVeryHighPriority);
		
		assertTrue("The reservation with the application with the highest priority should be associated with the first room available", 
				reservation.containsBoardroom(state().firstRoom));
	}
	
	@Then("the reservation with the first application should be associated with the first room available")
	public void thenTheReservationWithTheFirstApplicationShouldBeAssociatedWithTheFirstRoomAvailable() {
		ReservationRepository reservationRepository = getReservationRepository();
		Reservation reservation = reservationRepository.retrieve(state().booking);
		
		assertTrue("The reservation with the first application should be associated with the first room available",
				reservation.containsBoardroom(state().firstRoom));
	}

	@Then("the promoter is notified by email")
	public void thePromoterIsNotifiedByEmail() {
		state().bookingAssignationSendMailNotifier.notify(state().booking);
		state().mailMessageToPromoter = new SimpleMailMessage(state().booking.getPromoterEmail(), MAIL_SUBJECT, BOOKING_IS_ASSIGNED_MESSAGE);
		state().mailMessageToPromoter.addCarbonCopyRecipient(state().responsible.getEmail());
		verify(state().mailSender).sendMail(state().mailMessageToPromoter);
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
		User user = new User(new Email("random@email.ca"));
		User responsible = new User(new Email("responsible@email.ca"));
		BookerStrategy bookerSortingStrategy = new BookerStrategiesFactory().create(StrategyType.BASIC);
		Boardroom lastRoom;
		Boardroom firstAvailableBoardroom;
		Boardroom secondAvailableBoardroom;
		Boardroom firstRoom;
		Boardroom secondRoom;
		Booking booking;
		Booking secondBooking;
		Booking bookingWithVeryHighPriority;
		Booking bookingWithLowPriority;
		BookingAssignationSendMailNotifier bookingAssignationSendMailNotifier;
		Booker booker = FakeBookerFactory.create();
		TimedSequentialTrigger timedTrigger;
		BookerTimerTaskFactory bookerTimerTaskFactory = mock(BookerTimerTaskFactory.class);
		BookerTimerTask bookerTimerTask = mock(BookerTimerTask.class);
		Timer timer = mock(Timer.class);
		TimerFactory timerFactory = mock(TimerFactory.class);
		MailSender mailSender = mock(MailSender.class);
		MailMessage mailMessageToPromoter;
	}
}

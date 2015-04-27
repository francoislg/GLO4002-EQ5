package ca.ulaval.glo4002.GRAISSE.uat.steps;

import static ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailMatcher.withAMailSentTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import ca.ulaval.glo4002.GRAISSE.application.service.canceling.Canceler;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailSender;
import ca.ulaval.glo4002.GRAISSE.application.service.notification.BookingAssignationSendMailNotifier;
import ca.ulaval.glo4002.GRAISSE.application.service.notification.BookingCancelledSendMailNotifier;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.BookerTimerTask;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.BookerTimerTaskFactory;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.ThresholdTrigger;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.TimedSequentialTrigger;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.TimerFactory;
import ca.ulaval.glo4002.GRAISSE.application.service.shared.ServiceLocator;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingState;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.booking.Priority;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservation.ReservationRepository;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservations;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;
import ca.ulaval.glo4002.GRAISSE.uat.fixtures.FakeBookingFactory;
import ca.ulaval.glo4002.GRAISSE.uat.steps.ReservationsSteps.ReservationStepState;
import ca.ulaval.glo4002.GRAISSE.uat.steps.state.StatefulStep;
import ca.ulaval.glo4002.GRAISSE.uat.steps.state.StepState;

public class ReservationsSteps extends StatefulStep<ReservationStepState> {

	private static final long AN_INTERVAL = 1;
	private static final long AN_INTERVAL_IN_MILLISECOND = 60000;
	
	private static final int THRESHOLD = 2;

	private static final int TWENTY_SEATS = 20;
	private static final int FIFTEEN_SEATS = 15;
	private static final int TEN_SEATS = 10;

	private static final String BOARDROOM_NAME_FIRST = "first available boardroom";
	private static final String BOARDROOM_NAME_SECOND = "second available boardroom";

	protected ReservationStepState getInitialState() {
		return new ReservationStepState();
	}

	@Given("some rooms")
	public void givenSomeRooms() {
		state().firstAvailableBoardroom = new Boardroom(BOARDROOM_NAME_FIRST, TEN_SEATS);
		state().secondAvailableBoardroom = new Boardroom(BOARDROOM_NAME_SECOND, TEN_SEATS);

		BoardroomRepository boardroomRepository = getBoardroomRepository();
		boardroomRepository.persist(state().firstAvailableBoardroom);
		boardroomRepository.persist(state().secondAvailableBoardroom);
	}

	@Given("two rooms with the same number of seats")
	public void givenTwoRoomsWithTheSameNumberOfSeats() {
		state().firstRoom = new Boardroom(BOARDROOM_NAME_FIRST, FIFTEEN_SEATS);
		state().secondRoom = new Boardroom(BOARDROOM_NAME_SECOND, FIFTEEN_SEATS);

		BoardroomRepository boardroomRepository = getBoardroomRepository();
		boardroomRepository.persist(state().firstRoom);
		boardroomRepository.persist(state().secondRoom);
	}

	@Given("two applications to be processed with differents priorities")
	public void givenTwoApplicationsToBeProcessedWithDifferentsPriorities() {
		state().bookingWithVeryHighPriority = new Booking(state().user, FIFTEEN_SEATS, Priority.VERY_HIGH);
		state().bookingWithLowPriority = new Booking(state().user, FIFTEEN_SEATS, Priority.LOW);

		BookingRepository bookingRepository = getBookingRepository();
		bookingRepository.persist(state().bookingWithVeryHighPriority);
		bookingRepository.persist(state().bookingWithLowPriority);

		state().booker.addBooking(state().bookingWithVeryHighPriority);
		state().booker.addBooking(state().bookingWithLowPriority);
	}

	@Given("an application to be processed with a priority 3")
	public void givenAnApplicationToBeProcessedWithAPriority3() {
		state().booking = new Booking(state().user, FIFTEEN_SEATS, Priority.MEDIUM);

		BookingRepository bookingRepository = getBookingRepository();
		bookingRepository.persist(state().booking);

		state().booker.addBooking(state().booking);
	}

	@Given("three rooms with differents numbers of seats")
	public void givenARoomWithANumberOfSeats() {
		state().firstRoom = new Boardroom("room with " + TWENTY_SEATS + " seats", TWENTY_SEATS);
		state().secondRoom = new Boardroom("room with " + FIFTEEN_SEATS + " seats", FIFTEEN_SEATS);
		state().lastRoom = new Boardroom("room with " + TEN_SEATS + " seats", TEN_SEATS);

		BoardroomRepository boardroomRepository = getBoardroomRepository();
		boardroomRepository.persist(state().firstRoom);
		boardroomRepository.persist(state().secondRoom);
		boardroomRepository.persist(state().lastRoom);
	}

	@Given("a sort by seats strategy")
	public void givenSortBySeatsStrategy() {
		state().bookerSortingStrategy = new BookerStrategiesFactory().create(StrategyType.BYSEATS);
	}

	@Given("a sort by priority strategy")
	public void givenSortByPriorityStrategy() {
		state().bookerSortingStrategy = new BookerStrategiesFactory().create(StrategyType.PRIORITY);
	}

	@Given("an application with the same number of seats as the last")
	public void givenAnApplicationWithSeatsToBeProcessed() {
		state().booking = new Booking(state().user, state().lastRoom.getNumberOfSeats());

		BookingRepository bookingRepository = getBookingRepository();
		bookingRepository.persist(state().booking);

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
		state().reservations.registerObserver(state().bookingAssignationSendMailNotifier);
	}

	@Given("a cancelling notification system")
	public void givenACancellingNotification() {
		state().bookingCancelSendMailNotifier = new BookingCancelledSendMailNotifier(state().mailSender, state().responsible);
		state().canceler.registerObserver(state().bookingCancelSendMailNotifier);

	}

	@Given("a reservation assigned to a room")
	public void givenAReservationAssignedToARoom() {
		state().firstRoom = new Boardroom(BOARDROOM_NAME_FIRST, FIFTEEN_SEATS);
		BoardroomRepository boardroomRepository = getBoardroomRepository();
		boardroomRepository.persist(state().firstRoom);

		state().booking = new Booking(state().user, FIFTEEN_SEATS);
		BookingRepository bookingRepository = getBookingRepository();
		bookingRepository.persist(state().booking);

		state().booker.addBooking(state().booking);
		state().booker.assignBookings();

		ReservationRepository reservationRepository = getReservationRepository();
		state().firstReservation = reservationRepository.retrieve(state().booking);
	}

	@Given("two reservations assigned")
	public void givenTwoReservationsAssignedToARoom() {
		state().firstRoom = new Boardroom(BOARDROOM_NAME_FIRST, FIFTEEN_SEATS);
		BoardroomRepository boardroomRepository = getBoardroomRepository();
		boardroomRepository.persist(state().firstRoom);

		state().secondRoom = new Boardroom(BOARDROOM_NAME_SECOND, FIFTEEN_SEATS);
		boardroomRepository.persist(state().secondRoom);

		state().booking = new Booking(state().user, FIFTEEN_SEATS);
		BookingRepository bookingRepository = getBookingRepository();
		bookingRepository.persist(state().booking);

		state().secondBooking = new Booking(state().user, FIFTEEN_SEATS);
		bookingRepository.persist(state().secondBooking);

		state().booker.addBooking(state().booking);
		state().booker.addBooking(state().secondBooking);
		state().booker.assignBookings();

		ReservationRepository reservationRepository = getReservationRepository();
		state().firstReservation = reservationRepository.retrieve(state().booking);
		state().secondReservation = reservationRepository.retrieve(state().secondBooking);
	}

	@Given("a reservation awaiting treatment")
	public void givenAReservationAwaitingTreatment() {
		state().firstRoom = new Boardroom(BOARDROOM_NAME_FIRST, FIFTEEN_SEATS);
		BoardroomRepository boardroomRepository = getBoardroomRepository();
		boardroomRepository.persist(state().firstRoom);

		state().booking = new Booking(state().user, FIFTEEN_SEATS);
		BookingRepository bookingRepository = getBookingRepository();
		bookingRepository.persist(state().booking);

		state().booker.addBooking(state().booking);
	}
	
	@Given("a threshold number of applications")
	public void givenAThresholdNumberOfApplications() {
		state().thresholdTrigger = new ThresholdTrigger(THRESHOLD);
		state().booker.registerTrigger(state().thresholdTrigger);
	}

	@When("the application is processed")
	public void whenTheApplicationIsProcessed() {
		state().booker.assignBookings();
	}

	@When("an other application to be processed is added")
	public void whenAnotherApplicationToBeProcessedIsAdded() {
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
		state().secondBooking = new Booking(state().user, FIFTEEN_SEATS, Priority.MEDIUM);

		BookingRepository bookingRepository = getBookingRepository();
		bookingRepository.persist(state().secondBooking);

		state().booker.addBooking(state().secondBooking);
		state().booker.assignBookings();
	}

	@When("the first reservation is cancelled")
	public void whenTheFirstReservationIsCancelled() {
		state().canceler.cancel(state().booking);
	}

	@When("the reservation awaiting treatment is cancelled")
	public void whenTheReservationAwaitingTreatmentIsCancelled() {
		state().canceler.cancel(state().booking);
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
	public void thenThePromoterIsNotifiedByEmail() {
		verify(state().mailSender).sendMail(withAMailSentTo(state().booking.getPromoterEmail().getValue()));
		verify(state().mailSender).sendMail(withAMailSentTo(state().responsible.getEmail().getValue()));
	}

	@Then("the reservation should be cancelled and the room should be available")
	public void thenTheReservationShouldBeCancelledAndTheRoomShouldBeAvailable() {
		assertTrue(state().firstReservation.isCancelled());

		ReservationRepository reservationRepository = getReservationRepository();
		assertFalse(reservationRepository.activeReservationWithBoardroomExist(state().firstRoom));
	}

	@Then("only the first reservation should be cancelled")
	public void thenOnlyTheFirstReservationShouldBeCancelled() {
		assertTrue(state().firstReservation.isCancelled());

		assertFalse(state().secondReservation.isCancelled());
	}

	@Then("the reservation should be cancelled")
	public void thenTheReservationShouldBeCancelled() {
		assertEquals(state().booking.getState(), BookingState.CANCELLED);
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
		User user;
		User responsible;
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
		BookingCancelledSendMailNotifier bookingCancelSendMailNotifier;
		Bookings bookings;
		Boardrooms boardrooms;
		Reservations reservations;
		Booker booker;
		TimedSequentialTrigger timedTrigger;
		ThresholdTrigger thresholdTrigger;
		Reservation firstReservation;
		Reservation secondReservation;
		Canceler canceler;

		BookerTimerTaskFactory bookerTimerTaskFactory = mock(BookerTimerTaskFactory.class);
		BookerTimerTask bookerTimerTask = mock(BookerTimerTask.class);
		Timer timer = mock(Timer.class);
		TimerFactory timerFactory = mock(TimerFactory.class);
		MailSender mailSender = mock(MailSender.class);

		public ReservationStepState() {
			user = new User(new Email("random@email.ca"));
			responsible = new User(new Email("responsible@email.ca"));
			BookingRepository bookingRepository = ServiceLocator.getInstance().resolve(BookingRepository.class);
			BoardroomRepository boardroomRepository = ServiceLocator.getInstance().resolve(BoardroomRepository.class);
			ReservationRepository reservationRepository = ServiceLocator.getInstance().resolve(ReservationRepository.class);
			bookings = new Bookings(bookingRepository);
			boardrooms = new Boardrooms(boardroomRepository);
			reservations = new Reservations(reservationRepository, boardrooms, bookings);
			booker = new Booker(bookings, boardrooms, reservations);
			canceler = new Canceler(bookingRepository, reservationRepository);
		}
	}
}

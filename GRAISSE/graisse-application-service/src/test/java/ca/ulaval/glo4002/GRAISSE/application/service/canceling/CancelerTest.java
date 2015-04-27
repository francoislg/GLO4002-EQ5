package ca.ulaval.glo4002.GRAISSE.application.service.canceling;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservation.ReservationRepository;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

@RunWith(MockitoJUnitRunner.class)
public class CancelerTest {

	private static final boolean RESERVATION_EXIST = true;
	private static final boolean BOOKING_EXIST = true;

	@Mock
	BookingRepository bookingRepository;

	@Mock
	ReservationRepository reservationRepository;

	@Mock
	Email email;

	@Mock
	BookingID bookingID;

	@Mock
	Reservation reservation;

	@Mock
	Booking booking;

	Canceler canceler;

	@Before
	public void setUp() {
		canceler = new Canceler(bookingRepository, reservationRepository);
		setUpBookingMock();
	}

	@Test
	public void shouldVerifyIfTheBookingToCancelIsStoredInTheBookingRepository() {
		canceler.cancel(booking);
		verify(bookingRepository).exists(email, bookingID);
	}

	@Test
	public void whenBookingIsStoredInBookingRepositoryCancelerShouldAskRepositoryForTheBooking() {
		setUpBookingsRepoMock();

		canceler.cancel(booking);

		verify(bookingRepository).retrieve(email, bookingID);
	}

	@Test
	public void whenBookingIsStoredInBookingRepositoryCancelerShouldCancelTheBooking() {
		setUpBookingsRepoMock();

		canceler.cancel(booking);

		verify(booking).cancel();
	}

	@Test
	public void whenBookingIsStoredInBookingRepositoryCancelerShouldPersistTheCanceledBooking() {
		setUpBookingsRepoMock();

		canceler.cancel(booking);

		verify(bookingRepository).persist(booking);
	}

	@Test
	public void shouldVerifyIfTheBookingToCancelIsStoredInTheReservationRepository() {
		canceler.cancel(booking);
		verify(reservationRepository).exists(email, bookingID);
	}

	@Test
	public void whenBookingIsStoredInReservationRepositoryCancelerShouldAskRepositoryForTheReservation() {
		setUpReservationRepoMock();

		canceler.cancel(booking);

		verify(reservationRepository).retrieve(email, bookingID);
	}

	@Test
	public void whenBookingIsStoredInReservationRepositoryCancelerShouldCancelTheReservation() {
		setUpReservationRepoMock();

		canceler.cancel(booking);

		verify(reservation).cancel();
	}

	@Test
	public void whenBookingIsStoredInReservationRepositoryCancelerShouldRemoveTheCanceledReservation() {
		setUpReservationRepoMock();

		canceler.cancel(booking);

		verify(reservationRepository).remove(reservation);
	}

	private void setUpBookingMock() {
		when(booking.getPromoterEmail()).thenReturn(email);
		when(booking.getID()).thenReturn(bookingID);
	}

	private void setUpReservationRepoMock() {
		when(reservationRepository.exists(email, bookingID)).thenReturn(RESERVATION_EXIST);
		when(reservationRepository.retrieve(email, bookingID)).thenReturn(reservation);
	}

	private void setUpBookingsRepoMock() {
		when(bookingRepository.exists(email, bookingID)).thenReturn(BOOKING_EXIST);
		when(bookingRepository.retrieve(email, bookingID)).thenReturn(booking);
	}
}

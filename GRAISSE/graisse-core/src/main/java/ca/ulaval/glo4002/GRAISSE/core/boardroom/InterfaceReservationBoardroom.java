package ca.ulaval.glo4002.GRAISSE.core.boardroom;


public interface InterfaceReservationBoardroom {

	public boolean isAvailable(Boardroom boardroom);

	void assign(AssignedBoardroom boardroomToAssign, BookingAssignable bookingToAssign);
}
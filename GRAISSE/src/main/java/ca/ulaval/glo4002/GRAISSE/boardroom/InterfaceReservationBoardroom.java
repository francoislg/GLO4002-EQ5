package ca.ulaval.glo4002.GRAISSE.boardroom;

public interface InterfaceReservationBoardroom {

	public boolean isAvailable(Boardroom boardroom);

	void assign(AssignedBoardroom boardroomToAssign, BookingAssignable bookingToAssign);

}

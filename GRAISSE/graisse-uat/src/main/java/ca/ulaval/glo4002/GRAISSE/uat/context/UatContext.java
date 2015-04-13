package ca.ulaval.glo4002.GRAISSE.uat.context;

import ca.ulaval.glo4002.GRAISSE.application.service.shared.ServiceLocator;
import ca.ulaval.glo4002.GRAISSE.context.ContextBase;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.persistence.BoardroomInMemoryRepository;
import ca.ulaval.glo4002.GRAISSE.persistence.BookingInMemoryRepository;

public class UatContext extends ContextBase{

	public void reinitialize() {
        ServiceLocator.reset();
        apply();
    }
	
	@Override
	protected void registerServices() {
		   ServiceLocator.getInstance().register(BoardroomRepository.class, new BoardroomInMemoryRepository());
		   ServiceLocator.getInstance().register(BookingRepository.class, new BookingInMemoryRepository());
	}

	@Override
	protected void applyFillers() {
	}

}

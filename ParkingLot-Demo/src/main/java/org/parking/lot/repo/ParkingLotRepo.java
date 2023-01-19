package org.parking.lot.repo;

import java.util.List;
import java.util.Optional;
import org.parking.lot.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParkingLotRepo extends JpaRepository<ParkingLot, Long> {

	Optional<ParkingLot> findByTicket(int ticket);

	@Query(nativeQuery = true, value = "select * from parking_lot c where c.car_id=0")
	List<ParkingLot> findNearestSlot();
}

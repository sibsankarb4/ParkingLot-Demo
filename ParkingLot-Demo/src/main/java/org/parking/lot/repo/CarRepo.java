package org.parking.lot.repo;

import java.util.List;
import java.util.Optional;
import org.parking.lot.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

	List<Car> findByColour(String colour);

	List<Car> findByRegNo(String regNo);

	@Query(nativeQuery = true, value = "select car_id,colour,reg_no,MAX(slot) as slot from car c")
	Optional<Car> findLastSlot();

	@Query(nativeQuery = true, value = "select * from car c where c.slot=0")
	List<Car> findNearestSlot();

	Optional<Car> findBySlot(int slot);

}

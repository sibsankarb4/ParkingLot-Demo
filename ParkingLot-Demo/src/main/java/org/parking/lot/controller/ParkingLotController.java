package org.parking.lot.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.parking.lot.model.Car;
import org.parking.lot.model.ParkingLot;
import org.parking.lot.repo.CarRepo;
import org.parking.lot.repo.ParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {

	@Autowired
	private CarRepo carRepo;

	@Autowired
	private ParkingLotRepo parkingLotRepo;

	@PostMapping("/assignCar")
	public Optional<?> assignCartTest(@RequestParam String colour) {
		Car car = new Car();
		ParkingLot parkingLot = new ParkingLot();
		car.setColour(colour.toLowerCase());
		car.setRegNo(new Random().nextInt() + System.currentTimeMillis() + "");
		if (carRepo.count() == 0) // For first entry only
			car.setSlot(1);
		else {
			List<ParkingLot> findNearestSlot = parkingLotRepo.findNearestSlot();
			if (findNearestSlot.size() > 0) { // Checking any slot is free in middle
				car.setSlot(Integer.parseInt(findNearestSlot.get(0).getParkingId() + ""));
				parkingLot.setParkingId(findNearestSlot.get(0).getParkingId());
			} else {
				Optional<Car> findLastSlot = carRepo.findLastSlot(); // Adding at last
				car.setSlot(findLastSlot.get().getSlot() + 1);
			}
		}
		Car save = carRepo.save(car);
		parkingLot.setTicket(save.getSlot()); // Slot and ticket is same
		parkingLot.setCarId(save.getCarId());
		parkingLotRepo.save(parkingLot);
		return Optional.ofNullable(save.getSlot());
	}

	@GetMapping("/getAllCarByColour")
	public Optional<?> getAllCarByColour(@RequestParam String colour) {
		List<Car> findByColour = carRepo.findByColour(colour.toLowerCase());
		if (findByColour.size() > 0) {
			long[] array = findByColour.stream().mapToLong(regNo -> Long.parseLong(regNo.getRegNo())).toArray();
			return Optional.ofNullable(array);
		} else
			return Optional.of("No car found with provided color.");
	}

	@GetMapping("/getSlotByRegNo")
	public Optional<?> getSlotByRegNo(@RequestParam String regNo) {
		List<Car> findByRegNo = carRepo.findByRegNo(regNo);
		if (findByRegNo.size() > 0) {
			return Optional.ofNullable(findByRegNo.get(0).getSlot());
		} else
			return Optional.of("No  car found with provided registration number.");

	}

	@GetMapping("/vacateCar")
	public Optional<?> vacateCarTest(@RequestParam int slotId) {
		Optional<ParkingLot> findByTicket = parkingLotRepo.findByTicket(slotId);
		Optional<Car> findBySlot = carRepo.findBySlot(slotId);
		if (findBySlot.isPresent()) {
			carRepo.deleteById(findBySlot.get().getCarId());
		}
		if (findByTicket.isPresent()) {
			ParkingLot parkingLot = findByTicket.get();
			parkingLot.setCarId(0);
			parkingLot.setTicket(0);
			parkingLotRepo.save(parkingLot);
			return Optional.of("Slot No. " + slotId + " is free now");
		} else {
			return Optional.of("No Slot found");
		}

	}

}

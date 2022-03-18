package kr.co.hjsoft.repository;

import kr.co.hjsoft.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

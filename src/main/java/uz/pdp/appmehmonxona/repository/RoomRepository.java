package uz.pdp.appmehmonxona.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appmehmonxona.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    Page<Room> findAllByHotelId(Integer hotel_id, Pageable pageable);
}

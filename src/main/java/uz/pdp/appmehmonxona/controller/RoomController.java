package uz.pdp.appmehmonxona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appmehmonxona.entity.Room;
import uz.pdp.appmehmonxona.payload.RoomDto;
import uz.pdp.appmehmonxona.repository.HotelRepository;
import uz.pdp.appmehmonxona.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    //Read
    @GetMapping("/forHotel/{hotelId}")
    public Page<Room> getRoomIdListForHotel(@PathVariable Integer hotelId, @RequestParam int page) {
        //1-1=0     2-1=1    3-1=2
        //select * from student limit 10 offset (0*5)
        //select * from student limit 10 offset (1*5)
        //select * from student limit 10 offset (2*5)
        Pageable pageable = PageRequest.of(page, 5);
        Page<Room> roomPage = roomRepository.findAllByHotelId(hotelId, pageable);
        return roomPage;
    }

    //Delete
    @DeleteMapping(value = "/byRoomId/{roomId}")
    public String deleteRoom(@PathVariable Integer roomId) {
        roomRepository.deleteById(roomId);
        return " deleted";
    }

    //Create
    @PostMapping("/byRoomId/{roomId}")
    public String addRoom(@RequestBody RoomDto roomDto) {
        Room room = new Room();
        room.setFloor(roomDto.getFloor());
        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());
        room.setHotell(roomDto.getHotell());
        room.setHotel( hotelRepository.findById(roomDto.getHotelId()).get());
        roomRepository.save(room);
        return null;
    }

    //Update
    @PutMapping (value = "/byRoomid/{roomId}")
    public String editRoom(@PathVariable Integer roomId, @RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(roomDto.getRoomId());
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setFloor(roomDto.getFloor());
            room.setSize(roomDto.getSize());
            room.setNumber(roomDto.getNumber());
            room.setHotell(roomDto.getHotell());
            roomRepository.save(room);
        }
        return "edit";
    }
}

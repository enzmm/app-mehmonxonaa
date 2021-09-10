package uz.pdp.appmehmonxona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appmehmonxona.entity.Hotel;
import uz.pdp.appmehmonxona.payload.HotelDto;
import uz.pdp.appmehmonxona.repository.HotelRepository;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    //Read
    @GetMapping
    public List<Hotel> getHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels;
    }

    @PostMapping
    public String addHotel(@RequestBody HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        hotelRepository.save(hotel);
        return null;
    }

    //Delete
    @DeleteMapping(value = "/byHotelid/{hotelId}")
    public String deleteHotel(@PathVariable Integer id) {
        hotelRepository.deleteById(id);
        return " deleted";
    }

    //Update
    @PutMapping(value = "/byHotelid/{hotelId}")
    public String editHotel(@PathVariable Integer hotelId, @RequestBody HotelDto hotelDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelDto.getHotelId());
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            hotel.setName(hotelDto.getName());
            hotelRepository.save(hotel);
            return "edit";
        }
        return "region not found";
    }
}

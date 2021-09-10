package uz.pdp.appmehmonxona.payload;

import lombok.Data;

@Data
public class RoomDto {
    private Integer number;
    private Integer floor;
    private Integer size;
    private String hotell;
    private Integer hotelId;
    private Integer roomId;
}

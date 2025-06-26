
package test2;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import test2.model.TypeRoom;

public class Main {
    public static void main(String[] args) {
        Service hotelService = new Service();
        
        // Add users
        hotelService.setUser(1L, 5000.0);
        hotelService.setUser(2L, 10000.0);
        
        // Add rooms
        TypeRoom standard = TypeRoom.STANDARD;  // Use uppercase enum constants
        TypeRoom junior = TypeRoom.JUNIOR;
        TypeRoom suite = TypeRoom.SUITE;
        
        hotelService.setRoom(1L, standard, 1000.0);
        hotelService.setRoom(2L, junior, 2000.0);
        hotelService.setRoom(3L, suite, 3000.0);

        // Bookings

        // Valid booking
        LocalDate checkIn1 = LocalDate.of(2026, 6, 30);
        LocalDate checkOut1 = checkIn1.plusDays(7);
        hotelService.bookRoom(1L, 2L, 
            Date.from(checkIn1.atStartOfDay(ZoneId.systemDefault()).toInstant()),
            Date.from(checkOut1.atStartOfDay(ZoneId.systemDefault()).toInstant())
        );
        
        // Invalid booking: Check-out before check-in
        LocalDate checkIn2 = LocalDate.of(2026, 7, 7);
        LocalDate checkOut2 = LocalDate.of(2026, 6, 30);  // This is invalid!
        hotelService.bookRoom(1L, 2L, 
            Date.from(checkIn2.atStartOfDay(ZoneId.systemDefault()).toInstant()),
            Date.from(checkOut2.atStartOfDay(ZoneId.systemDefault()).toInstant())
        );
        
        // Valid booking (1 night)
        LocalDate checkIn3 = LocalDate.of(2026, 7, 7);
        LocalDate checkOut3 = checkIn3.plusDays(1);  // Fixed to 1 night
        hotelService.bookRoom(1L, 1L, 
            Date.from(checkIn3.atStartOfDay(ZoneId.systemDefault()).toInstant()),
            Date.from(checkOut3.atStartOfDay(ZoneId.systemDefault()).toInstant())
        );
        
        // Conflicting booking: Same room overlapping dates
        LocalDate checkIn4 = LocalDate.of(2026, 7, 7);
        LocalDate checkOut4 = checkIn4.plusDays(2);
        hotelService.bookRoom(2L, 1L,  // Same room as previous booking!
            Date.from(checkIn4.atStartOfDay(ZoneId.systemDefault()).toInstant()),
            Date.from(checkOut4.atStartOfDay(ZoneId.systemDefault()).toInstant())
        );
        
        // Valid booking for different room
        LocalDate checkIn5 = LocalDate.of(2026, 7, 7);
        LocalDate checkOut5 = checkIn5.plusDays(1);
        hotelService.bookRoom(2L, 3L,  // Changed to room 3 (available)
            Date.from(checkIn5.atStartOfDay(ZoneId.systemDefault()).toInstant()),
            Date.from(checkOut5.atStartOfDay(ZoneId.systemDefault()).toInstant())
        );
        
        
        hotelService.setRoom(1L, suite, 10000.0);  

        // Print information
        hotelService.printAllUsers();
        hotelService.printAll(); 
        
    }
}
package test2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import test2.model.Room;
import test2.model.TypeRoom;
import test2.model.User;
import test2.model.bookRoom; // Renommé bookRoom -> Booking

public class Service {
    private List<Room> rooms = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<bookRoom> bookings = new ArrayList<>();
    
    // Constructeur initialisant les listes
    public Service() {
        this.rooms = new ArrayList<>();
        this.users = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public void setRoom(Long roomId, TypeRoom typeRoom, double pricePerNight) {
        // Vérifier si la chambre existe déjà
        Optional<Room> existingRoom = rooms.stream()
                .filter(r -> r.getId().equals(roomId))
                .findFirst();

        if (existingRoom.isPresent()) {
            // Mettre à jour la chambre existante
            Room room = existingRoom.get();
            room.setType(typeRoom);
            room.setPricePerNight(pricePerNight);
            System.out.println("Room " + roomId + " updated successfully");
        } else {
            // Ajouter une nouvelle chambre
            rooms.add(new Room(roomId, typeRoom, pricePerNight));
            System.out.println("Room " + roomId + " created successfully");
        }
    }

    public void bookRoom(Long userId, Long roomId, Date checkIn, Date checkOut) {
        // Validation des dates
        if (checkIn == null || checkOut == null) {
            System.out.println("Error: Check-in and check-out dates must be provided");
            return;
        }
        
        if (checkOut.before(checkIn) || checkOut.equals(checkIn)) {
            System.out.println("Invalid dates: Check-out must be after check-in");
            return;
        }

        // Vérifier l'existence de l'utilisateur
        Optional<User> userOpt = users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst();

        // Vérifier l'existence de la chambre
        Optional<Room> roomOpt = rooms.stream()
                .filter(r -> r.getId().equals(roomId))
                .findFirst();

        if (userOpt.isEmpty()) {
            System.out.println("User " + userId + " not found");
            return;
        }

        if (roomOpt.isEmpty()) {
            System.out.println("Room " + roomId + " not found");
            return;
        }

        // Vérifier la disponibilité de la chambre
        boolean isAvailable = isRoomAvailable(roomId, checkIn, checkOut);
        
        if (!isAvailable) {
            System.out.println("Room " + roomId + " is not available for the selected dates");
            return;
        }

        // Calculer le prix total
        long diff = checkOut.getTime() - checkIn.getTime();
        int nights = (int) (diff / (1000 * 60 * 60 * 24));
        double totalPrice = nights * roomOpt.get().getPricePerNight();

        // Vérifier le solde de l'utilisateur
        User user = userOpt.get();
        if (user.getBalance() < totalPrice) {
            System.out.println("Insufficient balance for user " + userId + 
                               ". Required: " + totalPrice + 
                               ", Available: " + user.getBalance());
            return;
        }

        // Débiter le solde de l'utilisateur
        user.setBalance(user.getBalance() - totalPrice);

        // Créer la réservation
        bookRoom booking = new bookRoom(userId, roomId, checkIn, checkOut);
        bookings.add(booking);
        System.out.println("Booking successful! Total price: " + totalPrice);
    }

    private boolean isRoomAvailable(Long roomId, Date checkIn, Date checkOut) {
        return bookings.stream()
                .filter(b -> b.getRoomId().equals(roomId))
                .noneMatch(b -> 
                    checkIn.before(b.getCheckOut()) && 
                    checkOut.after(b.getCheckIn())
                );
    }

    public void setUser(Long userId, double balance) {
        // Validation du solde
        if (balance < 0) {
            System.out.println("Error: Balance cannot be negative");
            return;
        }
        
        // Rechercher l'utilisateur existant
        Optional<User> existingUser = users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst();

        if (existingUser.isPresent()) {
            // Mettre à jour le solde
            User user = existingUser.get();
            user.setBalance(balance);
            System.out.println("User " + userId + " updated successfully");
        } else {
            // Créer un nouvel utilisateur
            users.add(new User(userId, balance));
            System.out.println("User " + userId + " created successfully");
        }
    }

    public void printAllUsers() {
        System.out.println("\n===== ALL USERS =====");
        if (users.isEmpty()) {
            System.out.println("No users found");
            return;
        }
        
        users.forEach(user -> {
            System.out.println("User ID: " + user.getId() + 
                            " | Balance: " + user.getBalance());
        });
    }

    public void printAll() {
        System.out.println("\n===== ALL ROOMS =====");
        if (rooms.isEmpty()) {
            System.out.println("No rooms found");
            return;
        }
        
        rooms.forEach(room -> {
            System.out.println("Room #" + room.getId() + 
                             " | Type: " + room.getType() + 
                             " | Price/night: " + room.getPricePerNight());
        });
        printAllBookings();
    }

    public void printAllBookings() {
        System.out.println("\n===== ALL BOOKINGS =====");
        if (bookings.isEmpty()) {
            System.out.println("No bookings found");
            return;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        bookings.forEach(booking -> {
            System.out.println(
                             " | User: " + booking.getUserId() + 
                             " | Room: " + booking.getRoomId() + 
                             " | Check-in: " + sdf.format(booking.getCheckIn()) + 
                             " | Check-out: " + sdf.format(booking.getCheckOut()));
        });
    }
}
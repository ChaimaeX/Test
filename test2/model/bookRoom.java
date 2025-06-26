package test2.model;

import java.util.Date;

public class bookRoom {
    private Long userId;
    private Long roomId;
    private Date checkIn;
    private Date checkOut;

    
    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getRoomId() {
        return roomId;
    }


    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }


    public Date getCheckIn() {
        return checkIn;
    }


    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }


    public Date getCheckOut() {
        return checkOut;
    }


    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }


    public bookRoom(Long userId, Long roomId, Date checkIn, Date checkOut) {
        this.userId = userId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

}

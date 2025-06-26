package test2.model;

public class Room {
    
    private Long id;
    private TypeRoom Type;
    private double PricePerNight;

  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id);
    }


    public Room(Long id, TypeRoom type, double pricePerNight) {
        this.id = id;
        this.Type = type;
        this.PricePerNight = pricePerNight;
    }

       public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeRoom getType() {
        return Type;
    }

    public void setType(TypeRoom type) {
        Type = type;
    }

    public double getPricePerNight() {
        return PricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        PricePerNight = pricePerNight;
    }
}

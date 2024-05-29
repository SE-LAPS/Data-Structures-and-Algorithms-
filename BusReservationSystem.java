
// Customer Class
class Customer {
    int id;
    String name;
    String mobileNumber;
    String email;
    String city;
    int age;

    public Customer(int id, String name, String mobileNumber, String email, String city, int age) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.city = city;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }
}

// Bus Class
class Bus {
    int id;
    String busNumber;
    int totalSeats;
    String startingPoint;
    String endingPoint;
    String startingTime;
    double fare;

    public Bus(int id, String busNumber, int totalSeats, String startingPoint, String endingPoint, String startingTime,
            double fare) {
        this.id = id;
        this.busNumber = busNumber;
        this.totalSeats = totalSeats;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.startingTime = startingTime;
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", busNumber='" + busNumber + '\'' +
                ", totalSeats=" + totalSeats +
                ", startingPoint='" + startingPoint + '\'' +
                ", endingPoint='" + endingPoint + '\'' +
                ", startingTime='" + startingTime + '\'' +
                ", fare=" + fare +
                '}';
    }
}

// Reservation Class
class Reservation {
    int id;
    int customerId;
    int busId;
    int seatNumber;
    String reservationTime;
    String status;

    public Reservation(int id, int customerId, int busId, int seatNumber, String reservationTime, String status) {
        this.id = id;
        this.customerId = customerId;
        this.busId = busId;
        this.seatNumber = seatNumber;
        this.reservationTime = reservationTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", busId=" + busId +
                ", seatNumber=" + seatNumber +
                ", reservationTime='" + reservationTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

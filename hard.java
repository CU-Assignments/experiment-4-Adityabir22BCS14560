import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private boolean[] seats;
    private Lock lock;

    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats]; // false means available, true means booked
        lock = new ReentrantLock();
    }

    public boolean bookSeat(int seatNumber, String user) {
        lock.lock();
        try {
            if (seatNumber < 0 || seatNumber >= seats.length) {
                System.out.println(user + ": Invalid seat number " + seatNumber);
                return false;
            }

            if (!seats[seatNumber]) {
                seats[seatNumber] = true;
                System.out.println(user + " successfully booked seat " + seatNumber);
                return true;
            } else {
                System.out.println(user + ": Seat " + seatNumber + " is already booked.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private int seatNumber;
    private String user;

    public BookingThread(TicketBookingSystem system, int seatNumber, String user, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.user = user;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, user);
    }
}

public class hard {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(10);

        // Creating booking threads with different priorities
        BookingThread vip1 = new BookingThread(system, 3, "VIP User 1", Thread.MAX_PRIORITY);
        BookingThread vip2 = new BookingThread(system, 5, "VIP User 2", Thread.MAX_PRIORITY);
        BookingThread user1 = new BookingThread(system, 3, "Regular User 1", Thread.NORM_PRIORITY);
        BookingThread user2 = new BookingThread(system, 5, "Regular User 2", Thread.NORM_PRIORITY);
        BookingThread user3 = new BookingThread(system, 7, "Regular User 3", Thread.MIN_PRIORITY);

        // Start the threads
        vip1.start();
        vip2.start();
        user1.start();
        user2.start();
        user3.start();
    }
}

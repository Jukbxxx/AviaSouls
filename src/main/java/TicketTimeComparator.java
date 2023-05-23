import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {


    @Override
    public int compare(Ticket o1, Ticket o2) {
        int o1TimeOfFlight = o1.getTimeFrom() - o1.getTimeTo();
        int o2TimeOfFlight = o2.getTimeFrom() - o2.getTimeTo();
        if (o1TimeOfFlight < o2TimeOfFlight) {
            return 1;
        } else if (o1TimeOfFlight > o2TimeOfFlight) {
            return -1;
        } else {
            return 0;
        }
    }
}

package junit.tests.airplane;

import org.airplane.Seat;
import org.airplane.SeatLayout;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import  static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;


public class AirplaneSeatAllocationTest {

    @Test
    public void testFiftyAllocation() throws Exception {

        int[][] seatingLayout = {{3,4}, {4,5},{2,3}, {3,4}};
        SeatLayout seatLayout = new SeatLayout(seatingLayout);
        List<Seat> allocatedSeats = seatLayout.allocateSeat(50);
        assertThat(allocatedSeats.size(), is(equalTo(50)));
    }

    @Test
    public void testException() throws Exception {

        int[][] seatingLayout = {{3,4}, {4,5},{2,3}, {3,4}};
        SeatLayout seatLayout = new SeatLayout(seatingLayout);

        try {
            seatLayout.allocateSeat(51);

            seatLayout.getSeats().stream().forEach(System.out::println);
        }catch (Exception e){
            assertThat(e.getMessage(), is("Seats Not available"));
        }
    }

}

package org.airplane;

import java.util.*;

public class SeatLayout {

    private List<Seat> seats;
    int pointer = 0;
    public SeatLayout(int[][] seatingLayout){
        this.seats = createSeatLayOut(seatingLayout);
        sortByRow();
        sortBySeatType();


        //sortByRowAndSeatType();
    }

    public List<Seat> getSeats(){
        //TODO we can create deep cloning as list should not be modified publicly
        return seats;
    }

   public List<Seat>  allocateSeat(int passengersSize) throws Exception{

        if(pointer+passengersSize > this.seats.size()){
            throw new Exception("Seats Not available");
        }
        List<Seat> allocatedSeats = new ArrayList<>();
        for(int i=0; i < passengersSize; i++){
            this.seats.get(pointer).seatAllocate = pointer+1;
            allocatedSeats.add(this.seats.get(pointer));
            pointer = pointer+1;
        }
        // allocated seats should be cloned
        return allocatedSeats;
    }

    public List<Seat> createSeatLayOut(int[][] inputArray){

        List<Seat> seatList = new ArrayList<Seat>();

        for(int section = 1; section <= inputArray.length; section++){
            for(int column = 1; column <= inputArray[section-1][0]; column++){
                for(int row = 1; row <= inputArray[section-1][1]; row++){
                    // As Horizontal view of section
                    // first section and fist column are window seats(i.e section 1 and column 1)
                    // last section and last columns are window seats
                    // Number of column of that section should be > 0
                    if((section == 1 && column == 1 && inputArray[section-1][0]>1 )
                            ||   ( section == inputArray.length && column == inputArray[section-1][0] && inputArray[section-1][0]>1) ) {
                        //Window Seat
                        Seat newSeat = new Seat(section,  row,column, Seat.SeatType.WINDOW_SEAT);
                        seatList.add(newSeat);
                    }
                    // first column or last column of that section
                    else if(column == 1 || column == (inputArray[section-1][0])) {
                        //Aisle Seat
                        Seat newSeat = new Seat(section, row, column,  Seat.SeatType.AISLE_SEAT);
                        seatList.add(newSeat);
                    }
                    //rest all seats are marked middle
                    else {
                        //Middle Seat
                        Seat newSeat = new Seat(section, row, column,  Seat.SeatType.MIDDLE_SEAT);
                        seatList.add(newSeat);
                    }
                }
            }
        }
        return seatList;
    }


    class ChainedComparator implements Comparator<Seat>{

        List<Comparator> comparators = new ArrayList<>();
        ChainedComparator(){

        }
        @Override
        public int compare(Seat o1, Seat o2) {
            return 0;
        }
    }
    public void sortBySeatType(){
        Comparator c = new Comparator() {
            @Override
            public int compare(Object seat1, Object seat2) {
                return Integer.compare(((Seat)seat1).seatType.value, ((Seat)seat2).seatType.value);
            }
        };
        this.seats.sort(c);
    }

    public void sortByRow(){

        Comparator c = new Comparator() {
            @Override
            public int compare(Object seat1, Object seat2) {
                return Integer.compare(((Seat)seat1).row, ((Seat)seat2).row);
            }
        };
        this.seats.sort(c);
    }


    public void sortByRowAndSeatType(){

        Comparator rowComp = new Comparator() {
            @Override
            public int compare(Object seat1, Object seat2) {
                int rowCompare = Integer.compare(((Seat)seat1).row, ((Seat)seat2).row);
                int seatCompare = Integer.compare(((Seat)seat1).seatType.value, ((Seat)seat2).seatType.value);

                if(seatCompare == 0)
                    return rowCompare;
                else
                    return seatCompare;
            }
        };

        Comparator seatComp = new Comparator() {
            @Override
            public int compare(Object seat1, Object seat2) {
                return Integer.compare(((Seat)seat1).seatType.value, ((Seat)seat2).seatType.value);
            }
        };

        this.seats.sort(rowComp);
    }



    public void sortByColumn(){

        Comparator c = new Comparator() {
            @Override
            public int compare(Object seat1, Object seat2) {
                return Integer.compare(((Seat)seat1).column, ((Seat)seat2).column);
            }
        };
        this.seats.sort(c);
    }

    public List<Seat> sortBySection(List<Seat> seatList){

        Comparator c = new Comparator() {
            @Override
            public int compare(Object seat1, Object seat2) {
                return Integer.compare(((Seat)seat1).section, ((Seat)seat2).section);
            }
        };
        seatList.sort(c);
        return seatList;
    }




    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter Number of sections");
        String noOfSections = input.nextLine();

        noOfSections = noOfSections.replaceAll("\\s","");
        noOfSections = noOfSections.substring(2, noOfSections.length()-2); //remove first and last 2 symbol

        String[] array = noOfSections.split("],\\[");

        int[][] seatingLayout = new int[array.length][];
        for(int i = 0; i < array.length; i++){ //replace strings to numbers
            String row = array[i].split(",")[0];
            String col = array[i].split(",")[1];
            int[] section = {Integer.valueOf(row), Integer.valueOf(col)};
            seatingLayout[i] = section;
        };

        System.out.println("Enter Number of Passengers");
        int numberOfPassengers = Integer.valueOf(input.nextLine());

        //[[3,4],[4,5],[2,3],[3,4]]
       // int[][] seatingLayout = {{3,4}, {4,5},{2,3}, {3,4}};

       // Integer numberOfPassengers= 50;
        SeatLayout obj = new SeatLayout(seatingLayout);
       // List<Seat> l = obj.CreateSeatLayOut(seatingLayout);

       /* l.stream()
                .filter(p ->p.section == 1)   // filtering price
                .map(pm -> pm)          // fetching price
                .forEach(System.out::println);

        l.stream()
                .filter(p ->p.section == 2)   // filtering price
                .map(pm -> pm)          // fetching price
                .forEach(System.out::println);
*/
        /*
        l.stream()
                .filter(p ->p.section == 3)   // filtering price
                .map(pm -> pm)          // fetching price
                .forEach(System.out::println);

        l.stream()
                .filter(p ->p.section == 4)   // filtering price
                .map(pm -> pm)          // fetching price
                .forEach(System.out::println);
*/
       // obj.sortBySection(l);
      //  obj.sortByColumn(l);
        //obj.sortByRow();

       // System.out.println(l.toString());



       // System.out.println(l.toString());

        //obj.sortBySeatType();

       // System.out.println(l.toString());


        try {
            obj.allocateSeat(numberOfPassengers);

            obj.seats.stream().forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println(l.toString());
    }
}

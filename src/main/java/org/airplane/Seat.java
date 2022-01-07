package org.airplane;

public class Seat {

    enum SeatType{
        AISLE_SEAT("Aisle",1), WINDOW_SEAT("Window",2),MIDDLE_SEAT("Middle", 3);

        String type;
        int value;
        SeatType(String type, int value){
            this.type=type;
            this.value=value;
        }
    }
    Integer section;
    Integer row;
    Integer column;
    SeatType seatType;
    Integer seatAllocate;

    public Seat(Integer section, Integer row, Integer column,SeatType seatType){
        this. section = section;
        this.row =row;
        this.column = column;
        this.seatType = seatType;
    }


    @Override
    public String toString() {
        return "Seat{" +
                "section=" + section +
                ", row=" + row +
                ", column=" + column +
                ", seatType=" + seatType.type +
                ", seatAllocate=" + seatAllocate +

                '}';
    }
}

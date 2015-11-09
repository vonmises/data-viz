package ml.dev2dev.droiddata;

public enum Disaster {
    THUNDERSTORM(3),
    FLOOD(12),
    LANDSLIDE(13);

    private final int event_number;

    Disaster(int event_number){
        this.event_number = event_number;
    }

    public int getEventNumber(){
        return event_number;
    }
}
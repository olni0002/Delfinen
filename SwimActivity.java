public class SwimActivity implements Comparable<SwimActivity> {
    private String name;
    private String time;
    private String date;

    public SwimActivity(String name, String time, String date) {
        this.name = name;
        this.time = time;
        this.date = date;
    }

    public String getTime() {
        return time;
    }


    @Override
    public String toString() {
        return "Name: " + name + " Time: " + time + " Date: " + date;
    }


    @Override
    public int compareTo(SwimActivity other) {
        // Sammenlign SwimActivity-objekterne i faldende rækkefølge baseret på tid
        return other.getTime().compareTo(this.getTime());
    }
}

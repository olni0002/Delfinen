import java.time.LocalDate;
import java.time.LocalTime;

public class Begivenheder{
    private LocalDate dag;
    private String begivenhed;
    private LocalTime tid;


    public Begivenheder(LocalDate dag, String begivenhed, LocalTime tid) {
        setBegivenhed(begivenhed);
        setDag(dag);
        setTid(tid);
    }

    public LocalDate getDag() {
        return dag;
    }

    public String getBegivenhed() {
        return begivenhed;
    }

    public void setBegivenhed(String begivenhed) {
        this.begivenhed = begivenhed;
    }

    public void setDag(LocalDate dag) {
        this.dag = dag;
    }

    public void setTid(LocalTime tid) {
        this.tid = tid;
    }

    public LocalTime getTid() {
        return tid;
    }

    @Override
    public String toString() {
        return begivenhed + "  -  " + dag + "  -  " + tid;
    }
}

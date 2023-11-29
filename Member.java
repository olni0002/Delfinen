import java.time.LocalDate;

public class Member {
    private String name;
    private LocalDate birthDate;
    private String activity;
    private LocalDate registrationDate;
    private boolean paymentStatus;

    public Member(String name, LocalDate birthDate, String activity, LocalDate registrationDate, boolean paymentStatus) {
        this.name = name;
        this.birthDate = birthDate;
        this.activity = activity;
        this.registrationDate = registrationDate;
        this.paymentStatus = paymentStatus;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public LocalDate getRegistrationDate() {
        return this.registrationDate;
    }

    public boolean getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
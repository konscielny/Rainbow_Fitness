public class SValues {

    private int number;
    private PunishType type;

    public SValues(int number, PunishType type){
        this.number = number;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public PunishType getType() {
        return type;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setType(PunishType type) {
        this.type = type;
    }
}

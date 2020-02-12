public abstract class StationObject {

    private String navn;
    private boolean vaerdi;

    public StationObject(){}

    public StationObject(String navn){
        this.navn = navn;
    }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }
    public boolean isVaerdi() { return vaerdi; }
    public void setVaerdi(boolean vaerdi) { this.vaerdi = vaerdi; }

    @Override
    public String toString() {
        return navn;
    }
}

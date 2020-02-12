import java.util.ArrayList;

public class SmallTownStation {

    private static SmallTownStation singletonStation;

    private ArrayList<Track> tracks = new ArrayList<>();
    private ArrayList<Switch> switches = new ArrayList<>();
    private ArrayList<Signal> signals = new ArrayList<>();
    private ArrayList<Train> trains = new ArrayList<>();
    int navneTaeller = 3;
    String forrigeNavn = "train" + Integer.toString(navneTaeller);

    private SmallTownStation() {
        lavSignals();
        lavSwitches();
        lavTracks();
    }

    public ArrayList<Track> getTracks() { return tracks; }
    public void setTracks(ArrayList<Track> tracks) { this.tracks = tracks; }
    public ArrayList<Switch> getSwitches() { return switches; }
    public void setSwitches(ArrayList<Switch> switches) { this.switches = switches; }
    public ArrayList<Signal> getSignals() { return signals; }
    public void setSignals(ArrayList<Signal> signals) { this.signals = signals; }


    public static SmallTownStation lavInstans(){
        if(singletonStation == null){
            singletonStation = new SmallTownStation();
        }
        return singletonStation;
    }


    public int getNavneTaeller() {
        return navneTaeller;
    }

    public void setNavneTaeller(int navneTaeller) {
        this.navneTaeller = navneTaeller;
    }
    public String getForrigeNavn() {
        return forrigeNavn;
    }
    public void setForrigeNavn(String forrigeNavn) {
        this.forrigeNavn = forrigeNavn;
    }
    public ArrayList<Train> getTrains() {
        return trains;
    }
    public void setTrains(ArrayList<Train> trains) {
        this.trains = trains;
    }

    public void lavTracks(){
        tracks.add(new Track("venstre"));
        tracks.add(new Track("højre"));
        tracks.add(new Track("straight"));
        tracks.add(new Track("curved"));
    }

    public void lavSwitches(){
        switches.add(new Switch("venstre"));
        switches.add(new Switch("højre"));
    }

    public void lavSignals(){
        signals.add(new Signal("venstre"));
        signals.add(new Signal("højre"));
        signals.add(new Signal("venstreStraight"));
        signals.add(new Signal("højreStraight"));
        signals.add(new Signal("højreCurved"));
        signals.add(new Signal("venstreCurved"));
    }

    public void updateTrack(int index, boolean nyVærdi){
        getTracks().get(index).setVaerdi(nyVærdi);
    }

    public void gemTrain(Train train){
        trains.add(train);
    }

    public void updateTogetsTrack(Train train, Track track){
        int index = trains.indexOf(train);
        trains.get(index).setTrack(track);
    }

}

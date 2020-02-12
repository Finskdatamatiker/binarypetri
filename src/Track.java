public class Track extends StationObject {

    public Track(String navn){
        super(navn);
        setVaerdi(true);
    }

    public Track() {}

    @Override
    public String toString(){
        boolean vaerdi = super.isVaerdi();
        String erTom = "";
       if(vaerdi){
            erTom = " empty";
        }
        else{ erTom = " with train";}
       return super.getNavn() + erTom;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

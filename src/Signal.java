public class Signal extends StationObject {

    public Signal() {
    }

    public Signal(String navn){
        super(navn);
        setVaerdi(false);
    }

    @Override
    public String toString(){
        String værdi = "";
        if(super.isVaerdi() == true){
            værdi = " green";
        }
        værdi = " red";
        return super.getNavn() + værdi;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

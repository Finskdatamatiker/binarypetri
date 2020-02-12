public class Switch extends StationObject {

    public Switch() {
    }

    public Switch(String navn){
        super(navn);
        setVaerdi(true);
    }


    @Override
    public String toString(){
        String værdi = "";
        if(super.isVaerdi() == true){
            værdi = " straight";
        }
        værdi = " curved";
        return super.getNavn() + værdi;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}

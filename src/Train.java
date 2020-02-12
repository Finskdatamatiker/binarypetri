public class Train implements Runnable {

    private String navn;
    SmallTownStation smallTownStation = SmallTownStation.lavInstans();

    Track track;
    //true = mod højre, false = mod venstre
    boolean retning = true;

    public Train() {
    }

    public Train(String navn, Track track, boolean retning) {
        this.navn = navn;
        this.retning = retning;
        this.track = track;
    }

    public void run() {

        for (int i = 0; i < 5; i++) {
            synchronized (smallTownStation) {
                ankomst();
                tilladelseTilAtKoere();
            }
        }
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public boolean isRetning() {
        return retning;
    }

    public void setRetning(boolean retning) {
        this.retning = retning;
    }


    public void tilladelseTilAtKoere() {
        String trackNavn = track.getNavn();
        System.out.println("Jeg hedder " + navn + " og står på track: " + trackNavn + ". Hvor skal jeg køre hen?");
        switch (trackNavn) {
            case "venstre":
                caseVenstreOgHoejreStraight("venstre", 0);
                break;
            case "højre":
                caseVenstreOgHoejreStraight("højre", 1);
                break;
            case "straight":
                caseStraightCurved(2);
                break;
            case "curved":
                caseStraightCurved(3);
                break;
        }
    }

    public void ankomst() {
        smallTownStation.gemTrain(this);
    }

    public boolean tjekTrackRetning(int parallelTrackIndex) {
        boolean andetTogSammeRetning = false;
        for (int i = 0; i < smallTownStation.getTrains().size(); i++) {
            Train train = smallTownStation.getTrains().get(i);
            Track track = train.getTrack();
            if (track.equals(smallTownStation.getTracks().get(parallelTrackIndex))) {
                if (train.isRetning() == retning) {
                    andetTogSammeRetning = true;
                }
            }
        }
        return andetTogSammeRetning;
    }

    // hoejdeEllerVenstre betyder hoejde = true, venstre = false
    public void caseVenstreOgHoejreStraight(String venstreEllerHoejde, int opdatereIndexGamle) {
        boolean isVenstreTom = smallTownStation.getTracks().get(0).isVaerdi();
        boolean isHoejreTom = smallTownStation.getTracks().get(1).isVaerdi();
        boolean hEllerV = false;
        boolean isStraightTom = smallTownStation.getTracks().get(2).isVaerdi();
        boolean isCurvedTom = smallTownStation.getTracks().get(3).isVaerdi();
        boolean andetTogSammeRetningCurved = tjekTrackRetning(3);
        boolean andetTogSammeRetningStraight = tjekTrackRetning(2);

        if (venstreEllerHoejde.equals("venstre")) {
            hEllerV = isHoejreTom;
        }
        else if (venstreEllerHoejde.equals("højre")) {
            hEllerV = isVenstreTom;
        }

        if (isStraightTom && hEllerV || isStraightTom && isCurvedTom || isStraightTom && !andetTogSammeRetningCurved) {
            System.out.println(navn + " må gerne køre ligeud til tracket straight: " + navn + " kører til tracket straight.\n");
            smallTownStation.updateTrack(opdatereIndexGamle, true);
            smallTownStation.updateTrack(2, false);
            smallTownStation.updateTogetsTrack(this, smallTownStation.getTracks().get(2));
            SmallTownStationController.notificer(this);
        } else if (!isStraightTom || !andetTogSammeRetningStraight) {
            System.out.println("Du skal køre til sidespor til tracket curved: " + navn + " kører til tracket curvet.\n");
            smallTownStation.updateTrack(opdatereIndexGamle, true);
            smallTownStation.updateTrack(3, false);
            smallTownStation.updateTogetsTrack(this, smallTownStation.getTracks().get(3));
            SmallTownStationController.notificer(this);
        } else {
            System.out.println(navn + ": Du skal vente.\n");
            Thread.currentThread().setPriority(8);
            SmallTownStationController.notificer(this);
        }

    }

    public void caseStraightCurved(int opdatereIndexGamle) {
        boolean isVenstreTom = smallTownStation.getTracks().get(0).isVaerdi();
        boolean isHoejreTom = smallTownStation.getTracks().get(1).isVaerdi();
        boolean andetTogSammeRetningHoejre = tjekTrackRetning(1);
        boolean andetTogSammeRetningVenstre = tjekTrackRetning(0);

        //true = mod højre, false = mod venstre
        if (retning) {
            if (isHoejreTom || retning == andetTogSammeRetningHoejre) {
                System.out.println(navn + " må gerne køre ligeud til tracket højre: " + navn + " kører UD AF STATIONEN.\n");
                smallTownStation.updateTrack(opdatereIndexGamle, true);
                //modsatte track med et nyt navn
                smallTownStation.updateTogetsTrack(this, smallTownStation.getTracks().get(0));
                track.setVaerdi(false);
                smallTownStation.navneTaeller++;
                String navneT = Integer.toString(smallTownStation.getNavneTaeller());
                setNavn("train" + navneT);
                SmallTownStationController.notificer(this);
              try{
                   Thread.currentThread().sleep(500);
               }catch (InterruptedException io){
                   System.out.println(io);
               }
            }
            else {
                System.out.println(navn + ": Du skal vente.\n");
                Thread.currentThread().setPriority(8);
                SmallTownStationController.notificer(this);
            }

        }
        else if (!retning) {
            if (isVenstreTom || retning == andetTogSammeRetningVenstre) {
                System.out.println(navn + " må gerne køre ligeud til tracket venstre: " + navn + " kører UD AF STATIONEN.\n");
                smallTownStation.updateTrack(opdatereIndexGamle, true);
                smallTownStation.updateTogetsTrack(this, smallTownStation.getTracks().get(1));
                smallTownStation.navneTaeller++;
                String navneT = Integer.toString(smallTownStation.getNavneTaeller());
                setNavn("train" + navneT);
                track.setVaerdi(false);

                SmallTownStationController.notificer(this);
              try{
                   Thread.currentThread().sleep(2000);
               }catch (InterruptedException io){
                   System.out.println(io);
               }


            }
            else {
                System.out.println(navn + ": Du skal vente.\n");
                Thread.currentThread().setPriority(8);
                SmallTownStationController.notificer(this);
            }
        }
        else {
            System.out.println(navn + ": Du skal vente.\n");
            Thread.currentThread().setPriority(8);
            SmallTownStationController.notificer(this);
        }

    }
}


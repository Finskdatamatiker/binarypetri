import java.util.ArrayList;

public class SmallTownStationController {

 private static ArrayList<Thread> threads = new ArrayList<>();

 public static void main(String[] args) {

  SmallTownStation smallTownStation = SmallTownStation.lavInstans();
  Track track1 = smallTownStation.getTracks().get(0);
  Track track2 = smallTownStation.getTracks().get(1);
  Track track3 = smallTownStation.getTracks().get(3);

  Train train1 = new Train("train1", track3, false);
  Train train2 = new Train("train2", track2, false);
  Train train3 = new Train("train3", track1, true);


  Thread thread1 = new Thread(train1);
  Thread thread2 = new Thread(train2);
  Thread thread3 = new Thread(train3);


  threads.add(thread1);
  threads.add(thread2);
  threads.add(thread3);


  thread1.start();
  thread2.start();
  thread3.start();

 }

  public static void notificer(Train callerTrain) {
  for (Thread t : threads) {
   if (t.equals(callerTrain)) {
    try{
    t.wait();
   }catch (InterruptedException ie){
     System.out.println(ie);
    }
    callerTrain.notify();
  }}}}





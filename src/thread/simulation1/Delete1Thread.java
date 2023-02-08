package thread.simulation1;

import domainLogic.Automat;

public class Delete1Thread extends Thread{



    Automat automat ;

    public Delete1Thread(Automat automat){
        this.automat=automat;
    }

    @Override
    public  void run() {
            while (true) {
                synchronized(automat){
                    if (automat.readArrayOfKuchen().length != 0) {
                    int randomIndex = (int) ((Math.random() * automat.readArrayOfKuchen().length));  // generating random index from the list, Source: https://www.baeldung.com/java-random-list-element   //* kv.read().length :random int between 0 and array length
                        automat.deleteKuchen(randomIndex);
                    System.err.println("Deleted Kuchen");
                }
                }
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                }
            }
        }



}
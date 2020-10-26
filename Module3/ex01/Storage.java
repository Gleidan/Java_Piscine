public class Storage {

    private int product = 0;

    public synchronized void get(String name) {
        while (product < 1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product--;
        System.out.println(name);
        notify();
    }

    public synchronized void put() {
        while (product == 1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product++;
        notify();
    }
}

public class Producer implements Runnable {

    private Storage storage;

    public Producer(Storage storage, int count) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            storage.put();
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

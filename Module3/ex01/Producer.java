public class Producer implements Runnable {

    private Storage storage;

    private int count;

    public Producer(Storage storage, int count) {
        this.storage = storage;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count * 2; i++) {
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

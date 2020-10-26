public class Consumer implements Runnable {

    private Storage storage;

    private String name;

    private int count;

    public Consumer(Storage storage, String name, int count) {
        this.storage = storage;
        this.name = name;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            storage.get(name);
            try {
                Thread.sleep(20);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

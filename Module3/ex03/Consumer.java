import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class Consumer implements Runnable {

    private Storage storage;

    private int id;

    public Consumer(Storage storage, int id) {
        this.storage = storage;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            downloadFile(storage.get(), id);
            try {
                Thread.sleep(20);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void downloadFile(String urlStr, int id) {
        int numberOfFile = Integer.parseInt(urlStr.split(" ")[0]);
        System.out.println("Thread-" + id + " start download file number " + numberOfFile);
        try {
            URL url = new URL(urlStr.split(" ")[1]);

            BufferedInputStream bis = new BufferedInputStream(url.openStream());

            FileOutputStream fis = new FileOutputStream("file" + numberOfFile);

            byte[] buffer = new byte[1024];

            int count = 0;

            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
            fis.close();
            bis.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Thread-" + id + " finish download file number " + numberOfFile);
    }
}

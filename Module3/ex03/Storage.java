import java.util.ArrayList;

public class Storage {

    private String url = "";

    private ArrayList<String> urlList;

    public Storage(ArrayList<String> urlList) {
        this.urlList = urlList;
    }

    public synchronized String get() {
        String temp;

        while (url.isEmpty()) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        temp = url;

        url = "";

        notify();

        return temp;
    }

    public synchronized void put() {
        while (!url.isEmpty()) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!urlList.isEmpty()) {
            url = urlList.remove(0);
        }
        notify();
    }
}

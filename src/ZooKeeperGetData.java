import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class ZooKeeperGetData {
    private static ZooKeeper zooKeeper;
    private static ZooKeeperConnectionHelper zooKeeperConnectionHelper;

    public static Stat zonde_exists(String path) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path, true);
    }

    public static void main(String[] args) {
        String path = "/firstznode2";
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            zooKeeperConnectionHelper = new ZooKeeperConnectionHelper();
            zooKeeper = zooKeeperConnectionHelper.connect("localhost");
            Stat stat = zonde_exists(path);
            if (stat != null) {
                byte[] bytes = zooKeeper.getData(path, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        if (event.getType() == Event.EventType.None) {
                            switch (event.getState()) {
                                case Expired:
                                    countDownLatch.countDown();
                                    break;
                            }
                        } else {
                            try {
                                byte[] bytes2 = zooKeeper.getData(path, false, null);
                                String str = new String(bytes2, "utf-8");
                                System.out.println("watch...");
                                System.out.println(str);
                                countDownLatch.countDown();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, null);
                String str = new String(bytes, "utf-8");
                System.out.println("first print...");
                System.out.println(str);
                countDownLatch.countDown();
            } else {
                System.out.println("znode not exists.");
            }
            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class ZooKeeperSetData {
    private static ZooKeeper zooKeeper;
    private static ZooKeeperConnectionHelper zooKeeperConnectionHelper;

    public static void znode_setData(String path, byte[] bytes) throws KeeperException, InterruptedException {
        zooKeeper.setData(path, bytes, zooKeeper.exists(path, true).getVersion());
    }

    public static void main(String[] args) {
        String path = "/firstznode2";
        byte[] bytes = "set data".getBytes();
        try {
            zooKeeperConnectionHelper = new ZooKeeperConnectionHelper();
            zooKeeper = zooKeeperConnectionHelper.connect("localhost");
            znode_setData(path, bytes);
            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

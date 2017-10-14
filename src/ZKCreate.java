import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ZKCreate {
    private static ZooKeeper zooKeeper;
    private static ZooKeeperConnection zooKeeperConnection;

    public static void create(String path, byte[] data) throws InterruptedException, KeeperException {
        zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public static void main(String[] args) {
        String path = "/firstznode";
        byte[] data = "first znode app".getBytes();

        try {
            zooKeeperConnection = new ZooKeeperConnection();
            zooKeeper = zooKeeperConnection.connect("localhost");
            create(path, data);
            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

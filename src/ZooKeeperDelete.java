import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperDelete {
    private static ZooKeeper zooKeeper;
    private static ZooKeeperConnectionHelper zooKeeperConnectionHelper;

    public static void znode_delete(String path) throws KeeperException, InterruptedException {
        zooKeeper.delete(path, zooKeeper.exists(path, true).getVersion());
    }

    public static void main(String[] args) {
        String path = "/firstznode";
        byte[] bytes = "set data".getBytes();
        try {
            zooKeeperConnectionHelper = new ZooKeeperConnectionHelper();
            zooKeeper = zooKeeperConnectionHelper.connect("localhost");
            znode_delete(path);
            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

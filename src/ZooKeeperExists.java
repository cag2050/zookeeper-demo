import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZooKeeperExists {
    private static ZooKeeper zooKeeper;
    private static ZooKeeperConnectionHelper zooKeeperConnectionHelper;

    public static Stat zonde_exists(String path) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path, true);
    }

    public static void main(String[] args) {
        String path = "/firstznode2";

        try {
            zooKeeperConnectionHelper = new ZooKeeperConnectionHelper();
            zooKeeper = zooKeeperConnectionHelper.connect("localhost");
            Stat stat = zonde_exists(path);
            System.out.println(stat);
            if (stat != null) {
                  System.out.println("znode exists and the node version is");
                  System.out.println(stat.getVersion());
            } else {
                System.out.println("znode not exists.");
            }
            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

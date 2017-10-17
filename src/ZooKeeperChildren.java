import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Iterator;
import java.util.List;

public class ZooKeeperChildren {
    private static ZooKeeper zooKeeper;
    private static ZooKeeperConnectionHelper zooKeeperConnectionHelper;

    public static Stat zonde_exists(String path) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path, true);
    }

    public static void main(String[] args) {
        String path = "/";

        try {
            zooKeeperConnectionHelper = new ZooKeeperConnectionHelper();
            zooKeeper = zooKeeperConnectionHelper.connect("localhost");
            Stat stat = zonde_exists(path);
            if (stat != null) {
                System.out.println("znode exists and the node version is");
                System.out.println(stat.getVersion());
                List<String> list = zooKeeper.getChildren(path, false);
                Iterator<String> iterator = list.iterator();
                System.out.println("children===");
                while(iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            } else {
                System.out.println("znode not exists.");
            }
            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

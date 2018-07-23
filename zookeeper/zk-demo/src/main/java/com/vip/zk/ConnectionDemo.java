package com.vip.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 建立连接
 */
public class ConnectionDemo {
    public static void main(String[] args) {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ZooKeeper zk = new ZooKeeper("192.168.131.130:2181,192.168.131.131:2181,192.168.131.132:2181", 4000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                        // 如果收到服务端的响应事件，连接成功
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            System.out.println(zk.getState());

            // 持久化节点
            zk.create("/zk-owen", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            Thread.sleep(1000);

            Stat stat = new Stat();
            byte[] bytes = zk.getData("/zk-owen", null, stat);
            System.out.println(new String(bytes));// 当前节点的值

            // 修改节点值
            zk.setData("/zk-owen", "1".getBytes(), stat.getVersion());

            byte[] bytes1 = zk.getData("/zk-owen", null, stat);
            System.out.println(new String(bytes1));// 当前节点的值

            // 删除
            zk.delete("/zk-owen", stat.getVersion());

            zk.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}

package com.yourtion.zookeeper;


import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;


public class ZKConnect implements Watcher {

    public static final String zkServerPath = "127.0.0.1:2181";
    public static final Integer timeout = 5000;
    final static Logger log = Logger.getLogger(ZKConnect.class);

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper(zkServerPath, timeout, new ZKConnect());

        log.warn("开始连接ZooKeeper服务器...");
        log.warn("连接状态：" + zk.getState());

        Thread.sleep(2000);

        log.warn("连接状态：" + zk.getState());
    }

    public void process(WatchedEvent watchedEvent) {
        log.warn("连接状态：" + watchedEvent);
    }
}

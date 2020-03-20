package com.minwoo.kang;

import java.io.IOException;

import org.apache.hadoop.hdfs.MiniDFSCluster;

public class Launcher {
	public static void main(String[] args) throws IOException {
		final MiniDFSCluster cluster = Clusters.cluster(false);
		cluster.waitClusterUp();
		Clusters.createHdfsSiteXml(cluster);
		Runtime.getRuntime().addShutdownHook(new Thread(cluster::shutdown));
	}
}

package com.minwoo.kang;

import java.io.IOException;

import org.apache.hadoop.hdfs.MiniDFSCluster;

public class Format {
	public static void main(String[] args) throws IOException {
		final MiniDFSCluster cluster = Clusters.cluster(true);
		cluster.waitClusterUp();
		Clusters.createHdfsSiteXml(cluster);
		cluster.shutdown();
	}
}

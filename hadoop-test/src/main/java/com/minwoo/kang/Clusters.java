package com.minwoo.kang;

import static org.apache.hadoop.hdfs.DFSConfigKeys.*;
import static org.apache.hadoop.hdfs.MiniDFSNNTopology.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.HdfsConfiguration;
import org.apache.hadoop.hdfs.MiniDFSCluster;

public class Clusters {
	static final String CONF_DIR_PATH = MiniDFSCluster.getBaseDirectory() + "conf";
	static final String HDFS_SITE_XML = CONF_DIR_PATH + "/hdfs-site.xml";

	static MiniDFSCluster cluster(boolean format) throws IOException {
		return new MiniDFSCluster.Builder(createConf())
			.nnTopology(simpleFederatedTopology(1))
			.enableManagedDfsDirsRedundancy(false)
			.numDataNodes(3)
			.format(format)
			.build();
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	static void createHdfsSiteXml(MiniDFSCluster dfsCluster) throws IOException {
		final File confDir = new File(CONF_DIR_PATH);
		if (!confDir.exists()) {
			confDir.mkdir();
		}

		final File hdfsSiteXml = new File(HDFS_SITE_XML);
		try (FileWriter fw = new FileWriter(hdfsSiteXml, false)) {
			dfsCluster.getConfiguration(0).writeXml(fw);
		}
	}

	private static Configuration createConf() {
		final Configuration conf = new HdfsConfiguration();
		conf.set(DFS_NAMESERVICES, "mw");
		conf.setBoolean(DFS_CLIENT_READ_SHORTCIRCUIT_KEY, false);
		conf.set(DFS_CLIENT_CONTEXT, UUID.randomUUID().toString());
		return conf;
	}

	static Configuration getConf() throws IOException {
		final Configuration conf = new HdfsConfiguration(false);
		conf.addResource(new FileInputStream(HDFS_SITE_XML));
		return conf;
	}
}

package com.minwoo.kang;

import static org.apache.hadoop.fs.FileSystem.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DFSClient;

public class WriteFiles {
	public static void main(String[] args) throws IOException, URISyntaxException {
		final Configuration conf = Clusters.getConf();
		final String defaultFs = conf.get(FS_DEFAULT_NAME_KEY);

		try (DFSClient client = new DFSClient(new URI(defaultFs), conf)) {
			for (int i = 0; i < 10; i++) {
				try (final OutputStream out = client.create(String.format("/files/file%s.txt", i), true)) {
					final String data = String.format("Hello World %s", i);
					final byte[] bytes = data.getBytes();
					out.write(bytes, 0, bytes.length);
				}
			}
		}
	}
}

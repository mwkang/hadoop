package com.minwoo.kang;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

public class ListFiles {
	public static void main(String[] args) throws IOException {
		final Configuration conf = Clusters.getConf();
		try (final FileSystem fs = FileSystem.get(conf)) {
			final RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/"), true);
			while (files.hasNext()) {
				final LocatedFileStatus fileStatus = files.next();
				System.out.println(fileStatus.getPath());
			}
		}
	}
}

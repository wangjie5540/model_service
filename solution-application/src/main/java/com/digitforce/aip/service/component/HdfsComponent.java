package com.digitforce.aip.service.component;

import com.digitforce.aip.config.HdfsProperties;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class HdfsComponent {
    @Resource
    private HdfsProperties hdfsProperties;

    private FileSystem getInstance() {
        try {
            URI hdfsURI = new URI(hdfsProperties.getUri());
            Configuration configuration = new Configuration();
            configuration.addResource(new ClassPathResource(hdfsProperties.getConfigFile()).getInputStream());
            return FileSystem.get(hdfsURI, configuration, "root");
        } catch (Exception e) {
            log.error("get hdfs client filed", e);
            throw new RuntimeException(e);
        }
    }

    public void putLocalToHDFS(String localFullPath, String hdfsFullPath) {
        log.debug("put local to hdfs, localPath={}, hdfsPath={}", localFullPath, hdfsFullPath);
        File localFile = new File(localFullPath);
        Path hdfsPath = new Path(hdfsFullPath);
        OutputStream out = null;
        try (InputStream in = new BufferedInputStream(Files.newInputStream(localFile.toPath()))) {
            FileSystem fileSystem = getInstance();
            out = fileSystem.create(hdfsPath);
            IOUtils.copyBytes(in, out, 4096, true);
        } catch (Exception e) {
            log.warn("put local file to HDFS failed!", e);
        } finally {
            if (out != null) {
                IOUtils.closeStream(out);
            }
        }
    }

    public void getHDFSToLocal(String hdfsFullPath, String localFullPath) {
        log.debug("get hdfs to local, hdfsPath={}, localPath={}", hdfsFullPath, localFullPath);
        File localFile = new File(localFullPath);
        Path hdfsPath = new Path(hdfsFullPath);
        FSDataInputStream in = null;
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(localFile.toPath()))) {
            Configuration configuration = new Configuration();
            FileSystem fileSystem = getInstance();
            in = fileSystem.open(hdfsPath);
            IOUtils.copyBytes(in, out, 4096, true);
        } catch (Exception e) {
            log.warn("get file from HDFS failed!", e);
        } finally {
            if (in != null) {
                IOUtils.closeStream(in);
            }
        }
    }

    public FileStatus[] listStatus(Path hdfsPath) throws IOException {
        return getInstance().listStatus(hdfsPath);
    }

    public List<FileStatus> listFile(String path) throws IOException {
        List<FileStatus> paths = Lists.newArrayList();
        Path hdfsPath = new Path(path);
        FileStatus[] status = listStatus(hdfsPath);
        if (status != null) {
            for (FileStatus f : status) {
                if (f.isFile()) {
                    paths.add(f);
                }

            }
        }
        Collections.sort(paths);
        return paths;
    }

    public String getFileFullDataStr(String filePath) {
        Path hdfsPath = new Path(filePath);
        try (FSDataInputStream in = getInstance().open(hdfsPath)) {
            return new String(IOUtils.readFullyToByteArray(in));
        } catch (Exception e) {
            log.warn("get file full data str failed", e);
            throw new RuntimeException(e);
        }
    }

    public float getFileSizeMB(String filePath) {
        Path hdfsPath = new Path(filePath);
        try {
            DecimalFormat decimalFormat = new DecimalFormat(".00");
            long len = getInstance().getFileStatus(hdfsPath).getLen();
            return Float.parseFloat(decimalFormat.format((float) len / (1024 * 1024)));
        } catch (IOException e) {
            log.error("get file size failed.[filePath={}]", filePath);
            throw new RuntimeException(e);
        }
    }
}

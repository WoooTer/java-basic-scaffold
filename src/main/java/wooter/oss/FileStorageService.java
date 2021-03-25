package wooter.oss;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.lokra.seaweedfs.core.FileSource;
import org.lokra.seaweedfs.core.FileTemplate;
import org.lokra.seaweedfs.core.file.FileHandleStatus;
import org.lokra.seaweedfs.core.http.StreamResponse;
import org.lokra.seaweedfs.util.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class FileStorageService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    protected FileStorageService() {
        // Exists only to defeat instantiation.
    }

    private FileSource fileSource;
    private static FileStorageService instance = null;
    protected FileStorageService(String host,int port,String publicHost) {
        fileSource = new FileSource();
        fileSource.setHost(host);
        fileSource.setPort(port);
        try {
            fileSource.startup();
        } catch (IOException e) {
            log.error("fail to initialize seaweed",e);
        }
    }
    public static FileStorageService getInstance(String host,int port,String  publicHost) {
        if(instance == null) {
            instance = new FileStorageService(host,port,publicHost);
        }
        return instance;
    }


    public FileHandleStatus uploadFile(String fileString , String fileName){
        FileTemplate template = new FileTemplate(fileSource.getConnection());
        try {
            InputStream is = new ByteArrayInputStream( fileString.getBytes( "UTF-8" ) );
            ContentType contentType = ContentType.create("multipart/form-data", Consts.UTF_8);
            return template.saveFileByStream(fileName, is,contentType);
        } catch (IOException e) {
            log.error("fail to upload file",e);
            return null;
        }
    }

    public String getFile(String fileId){
        FileTemplate template = new FileTemplate(fileSource.getConnection());
        try {
            StreamResponse fileStream = template.getFileStream(fileId);
            return fileStream.getOutputStream().toString();
        } catch (IOException e) {
            log.error("fail to download file",e);
            return null;
        }
    }

    public void deleteFile(String fileId) throws IOException {
        FileTemplate template = new FileTemplate(fileSource.getConnection());
        template.deleteFile(fileId);
    }

    public void deleteFiles(ArrayList<String> fileIds) throws IOException {
        FileTemplate template = new FileTemplate(fileSource.getConnection());
        template.deleteFiles(fileIds);
    }

}

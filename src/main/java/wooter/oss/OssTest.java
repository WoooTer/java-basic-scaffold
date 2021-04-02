package wooter.oss;

import org.lokra.seaweedfs.core.FileSource;
import org.lokra.seaweedfs.core.FileTemplate;
import org.lokra.seaweedfs.core.file.FileHandleStatus;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by taota on 2021/3/10.
 */
public class OssTest {

    public static void main(String[] args) throws Exception {
//        FileSource fileSource = new FileSource();
//        // SeaweedFS master server host
//        fileSource.setHost("10.173.1.64");
//        // SeaweedFS master server port
//        fileSource.setPort(9333);
//        // Startup manager and listens for the change
//        fileSource.startup();
//
//        FileTemplate template = new FileTemplate(fileSource.getConnection());
//        InputStream is = new ByteArrayInputStream( "my emr test".getBytes( "UTF-8" ) );
//        template.saveFileByStream("filename.doc", is);


        FileStorageService fileStorageService = FileStorageService.getInstance("10.173.1.64", 9333, "");
        FileHandleStatus fileHandleStatus = fileStorageService.uploadFile("112233", "mytest");
        System.out.println(fileHandleStatus);
    }
}

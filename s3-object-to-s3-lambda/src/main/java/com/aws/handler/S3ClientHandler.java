package com.aws.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class S3ClientHandler {
	private AmazonS3 client;
	
	public S3ClientHandler() {
		this.client = AmazonS3ClientBuilder.defaultClient();
	}
	
	public void downloadObject(String bucketName, String objectPrefix, String location) throws AmazonS3Exception, IOException {
		S3Object s3Object = client.getObject(bucketName, objectPrefix);
		S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();

        File file = new File(location);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
		
        int length = 0;
        byte[] byteSize = new byte[1024];
        do {
            fileOutputStream.write(byteSize, 0, length);
            length = s3ObjectInputStream.read(byteSize);
        } while(length > 0);

        s3ObjectInputStream.close();
        fileOutputStream.close();
	}
}

package com.mbc.hr.hrSys.aws;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Component
public class AWSStorageManagerImpl implements AWSStorageManager {

	private AmazonS3 s3client;

	@Value("${aws.accessKey}")
	private String accessKey;

	@Value("${aws.secretKey}")
	private String secretKey;

	@Value("${aws.bucketName}")
	private String bucketName;

	@PostConstruct
	private void init() {
		connect();
	}

	/**
	 * Initialize s3client by connecting to AWS service 
	 */
	private void connect() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

		s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.DEFAULT_REGION).build();

		if (s3client.doesBucketExist(bucketName)) {
			System.out.println("Target bucket " + bucketName + " already exists, no need to create one");
		} else {
			s3client.createBucket(bucketName);
		}
	}

	/**
	 * Add new CV to S3 AWS storage
	 * @param cvFileName
	 * @param cvFile
	 */
	public void addCandidateCV(String cvFileName, InputStream input, ObjectMetadata metadata) {
//		s3client.putObject(bucketName, cvFileName, cvFile);
		s3client.putObject(bucketName, cvFileName, input, metadata);
	}
	
	/**
	 * Download candidate CV by file name
	 * @param cvFileName
	 * @return
	 * @throws IOException
	 */
	public byte[] downloadCandidateCV(String cvFileName) throws IOException {
		S3Object s3object = s3client.getObject(bucketName, cvFileName);
		S3ObjectInputStream inputStream = s3object.getObjectContent();
		return IOUtils.toByteArray(inputStream);
	}

	@Override
	public ObjectMetadata createAWSMetaData(MultipartFile cvFile) {
		
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(cvFile.getContentType());
		metadata.setContentLength(cvFile.getSize());
		return metadata;
	}

}
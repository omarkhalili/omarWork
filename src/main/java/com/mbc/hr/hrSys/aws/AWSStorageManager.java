package com.mbc.hr.hrSys.aws;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.ObjectMetadata;

public interface AWSStorageManager {

	/**
	 * Add new CV to S3 AWS storage
	 * @param cvFileName
	 * @param cvFile
	 */
	public void addCandidateCV(String cvFileName, InputStream input, ObjectMetadata metadata);

	/**
	 * Download candidate CV by file name
	 * @param cvFileName
	 * @return
	 * @throws IOException
	 */
	public byte[] downloadCandidateCV(String cvFileName) throws IOException;

	/**
	 * Create meta data object for file upload
	 * @param cvFile
	 * @return
	 */
	public ObjectMetadata createAWSMetaData(MultipartFile cvFile);
	
}

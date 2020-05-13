package com.ylc.main;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.aws.handler.S3EventHandler;

public class S3ToS3Lambda {	
	private final String ENV_BUCKET = "DESTINATION_BUCKET";
	private final String ENV_OBJECT = "DESTINATION_OBJECT";
	private final String TEMP_LOCATION = "/tmp/";
	
	public String handleRequest(S3Event event, Context context) {
		String eventType = S3EventHandler.getEventType(event);
		String sourceBucket = S3EventHandler.getBucketName(event);
		String sourcePrefix = S3EventHandler.getObjectPrefix(event);
		
		LambdaLogger logger = context.getLogger();
		logger.log("S3ToS3Lambda triggered by event type '" + eventType + "' from 's3://" + sourceBucket + "/" + sourcePrefix + "'");
		
		String destinationBucket = System.getenv(ENV_BUCKET);
		String destinationObject = System.getenv(ENV_OBJECT);
		logger.log("Retrieved environmental variables for destination bucket '" + destinationBucket + "' and destination prefix '" + destinationObject + "'");
		
		return "S3ToS3Lambda Completed";
	}
	
}

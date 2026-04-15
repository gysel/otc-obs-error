package com.unblu.tests

import org.slf4j.LoggerFactory
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.S3Configuration
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.model.S3Exception
import java.net.URI
import kotlin.system.exitProcess

private val logger = LoggerFactory.getLogger("com.unblu.tests.Main")

fun main() {
    val accessKey = "xxx"
    val secretKey = "xxx"
    val endpoint = "https://obs.eu-ch2.sc.otc.t-systems.com"
    val bucketName = "xxx"
    val keyName = "sample.txt"

    val credentials = AwsBasicCredentials.create(accessKey, secretKey)
    val s3 = S3Client.builder()
        .endpointOverride(URI.create(endpoint))
        .region(Region.of("eu-ch2"))
        .credentialsProvider(StaticCredentialsProvider.create(credentials))
        // activate to disable chunked encoding
        //.serviceConfiguration(S3Configuration.builder().chunkedEncodingEnabled(false).build())
        .build()

    val content = "Hello from obs-s3-auth sample upload!"

    logger.info("Uploading text sample to s3://$bucketName/$keyName ...")

    try {
        s3.putObject(
            PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build(),
            RequestBody.fromString(content)
        )
        logger.info("Upload complete.")
    } catch (e: S3Exception) {
        logger.error(e.awsErrorDetails().errorMessage())
        exitProcess(1)
    }
}

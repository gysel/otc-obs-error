# OTC OBS Http 400 error

1. Edit `src/main/kotlin/Main.kt` and add credentials
2. Run `./gradlew run`

## Observed error

```
DEBUG Request attempt 1 encountered non-retryable failure software.amazon.awssdk.services.s3.model.S3Exception: The provided 'x-amz-content-sha256' header does not match what was computed. (Service: S3, Status Code: 400, Request ID: xxx, Extended Request ID: xxx)
```

## Workaround

The upload works when you activate `.chunkedEncodingEnabled(false)` in the `S3Client`.

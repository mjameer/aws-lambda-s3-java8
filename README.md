## AWS Lambda Triggers on S3 Upload

S3 Upload Triggers AWS Lambda using Java 8 and AWS SDK 1.x.

## Getting Started

Follow the below instructions to get started with the source code:
- [Make sure you have all Requirements](#requirements)
- [Make sure to add AWS SDK for Java 1.x ](#Dependencies)

## Requirements

- [AWS Account](https://aws.amazon.com/console/)
- [IAM user with access-key and secret-access-key (user’s credentials)](https://lightsail.aws.amazon.com/ls/docs/en_us/articles/amazon-lightsail-managing-access-for-an-iam-user)
- [Download mock test file from](https://www.mockaroo.com/)


## Reference

- [Why Spring Boot is not Recommended for Lambda](https://www.reddit.com/r/java/comments/y4kuvr/is_anyone_using_java_spring_boot_in_aws_lambda/)
- [AWS Lambda Example YT 1](https://www.youtube.com/watch?v=3oV4Nj_ruOA)
- [AWS Lambda Example YT 2]([https://www.youtube.com/watch?v=3oV4Nj_ruOA](https://www.youtube.com/watch?v=wk8Lk8R7Pck&t=3s))


## Dependencies

- AWS Java SDK Amazon S3:

```xml
<dependencies>
    <!-- S3 AWS SDK for Java 1.x -->
    <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>aws-java-sdk-s3</artifactId>
        <version>1.12.770</version>
    </dependency>
    
    <!-- Lambda Core, AWS SDK, this is still available only in Java 1.x  -->
    <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>aws-lambda-java-core</artifactId>
        <version>1.2.3</version>
    </dependency>
    
    <!-- Lambda events, AWS SDK, this is still available only in Java 1.x  -->
    <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>aws-lambda-java-events</artifactId>
        <version>3.13.0</version>
    </dependency>
    
</dependencies>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>bom</artifactId>
            <version>${aws.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```


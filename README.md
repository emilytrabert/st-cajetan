# St. Cajetan
*Patron saint of job seekers and the unemployed.*

Tomcat job hunt documentation storage service.

[![Build Status](https://travis-ci.org/emilytrabert/st-cajetan.svg?branch=master)](https://travis-ci.org/emilytrabert/st-cajetan) [![codecov](https://codecov.io/gh/emilytrabert/st-cajetan/branch/master/graph/badge.svg)](https://codecov.io/gh/emilytrabert/st-cajetan)

## Prereqs

* [Maven](https://maven.apache.org/download.cgi)
* [AWS Account](https://aws.amazon.com)

## Setup

1. Create a DynamoDB table named `job-apps`.
2. Set your AWS access credentials. (This can be done [many ways](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html)).
3. In order to run the integration test, you will need an entry with `id` of `test` and `jobStatus` of `INTERVIEWING`.

## Run

1. `mvn tomcat7:run`
2. Jobs page will be available at http://localhost:8080/jobs.


#!/bin/bash
if [ "$BRANCH" == "master" ]; then
  echo "deploying snapshot build to oss.jfrog.org..."
  /bin/bash ./gradlew artifactoryPublish
fi

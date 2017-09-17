#!/bin/bash
if [ "$TRAVIS_BRANCH" == "master" ]; then
  echo "deploying snapshot build to oss.jfrog.org..."
  bash ./gradlew artifactoryPublish --info
fi

#!/bin/bash
if [ "$TRAVIS_BRANCH" == "master" ]; then
  ../gradlew artifactoryPublish --info
fi

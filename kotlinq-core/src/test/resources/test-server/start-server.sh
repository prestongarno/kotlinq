#!/usr/bin/env bash

cd $(dirname $0)

if [ -z "$1" ]
  then
    echo "No arguments!"
    exit 1
fi

#echo 'Running a GraphQL API server at localhost:4000/graphql'

node src/$1/server.js

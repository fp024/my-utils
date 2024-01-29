#!/bin/bash
echo "copy library local install."
source ./set-jdk-env.properties

REPO_ROOT_DIR=$(pwd)
PROJECT_FOLDER_LIST=$(sed 's/\\/\//g' "project-folder-list.txt")


for folder in $PROJECT_FOLDER_LIST;
do
  cd $REPO_ROOT_DIR/$folder
  echo "##########################"
  echo "$folder install"
  echo "##########################"
  ./gradlew clean test publishToMavenLocal
done

echo "install is complete."

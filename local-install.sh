#!/bin/sh
echo "copy library local install."

REPO_ROOT_DIR=$(pwd)
. "$REPO_ROOT_DIR/set-jdk-env.properties"

# sed의 출력을 파이프로 while 루프에 연결, 마지막 출력 뒤에 빈줄 하나 추가해줌 "$a\"
sed "s/\\/\//g" -e '$a\' "project-folder-list.txt" | while IFS= read -r folder; do
  if [ -n "$folder" ]; then # 빈 줄 무시
    cd "$REPO_ROOT_DIR/$folder" || { echo "ERROR: Failed to change directory to $REPO_ROOT_DIR/$folder" >&2; exit 1; }
    echo "##########################"
    echo "$folder install"
    echo "##########################"
    ./gradlew clean test publishToMavenLocal || { echo "ERROR: gradlew command failed for $folder" >&2; exit 1; }
  fi
done

cd "$REPO_ROOT_DIR"

echo "install is complete."
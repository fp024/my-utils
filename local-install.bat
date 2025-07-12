@ECHO copy library local install.
@ECHO OFF

@CALL .\set-jdk-env.bat
SET REPO_ROOT_DIR=%~dp0

for /F "delims=" %%a in (project-folder-list.txt) do (
  @cd %REPO_ROOT_DIR%\%%a
  @ECHO ##########################
  @ECHO %%a install
  @ECHO ##########################
  @CALL gradlew clean test publishToMavenLocal  
)

@cd %REPO_ROOT_DIR%
@ECHO install is complete.
PAUSE
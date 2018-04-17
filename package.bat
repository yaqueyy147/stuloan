@echo off

set CURR_DIR=%cd%
set WEBAPP_NAME=familyTree

call mvn clean
call mvn install -Dmaven.test.skip=true
cd %CURR_DIR%

call mvn clean

rem call mvn eclipse:eclipse

rem webapp 
if exist %WEBAPP_NAME%\src\main\webapp\WEB-INF\lib rd /s /q %WEBAPP_NAME%\src\main\webapp\WEB-INF\lib
if exist %WEBAPP_NAME%\src\main\webapp\WEB-INF\classes rd /s /q %WEBAPP_NAME%\src\main\webapp\WEB-INF\classes

call mvn package -Dmaven.test.skip=true

rem copy webapp 
xcopy /e /q %WEBAPP_NAME%\target\%WEBAPP_NAME%\WEB-INF\lib %WEBAPP_NAME%\src\main\webapp\WEB-INF\lib\
xcopy /e /q %WEBAPP_NAME%\target\%WEBAPP_NAME%\WEB-INF\classes %WEBAPP_NAME%\src\main\webapp\WEB-INF\classes\


:Done
echo ²Ù×÷Íê³É
pause
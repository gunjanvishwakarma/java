    @ECHO OFF
    javac . Test.java
    START /W java Test
    set RETURNCODE=%ERRORLEVEL%
    echo %RETURNCODE%
    exit /B RETURNCODE
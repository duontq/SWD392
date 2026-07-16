@echo off
echo Dang bien dich ma nguon...
javac -encoding UTF-8 -d bin -sourcepath src src/Main.java
if %errorlevel% neq 0 (
    echo Bien dich that bai!
    pause
    exit /b %errorlevel%
)
echo Bien dich thanh cong! Dang chay chuong trinh...
java -cp bin Main

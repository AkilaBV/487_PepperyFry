set projectLocation=C:\Users\vkche\Downloads\PepperFry\487_PepperyFry
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause
set projectLocation=E:\Eclipse_projects\AT_BWV_BOT
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\libs\*
java org.testng.TestNG %projectLocation%\testng.xml

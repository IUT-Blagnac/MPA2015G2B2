"%JAVA_HOME%\bin\java" -classpath .\bin\ MakeOPTIweb sujets2014_2015.csv etudiants2014_2015.csv intervenants2014_2015.csv projets2014_2015.csv
"%JAVA_HOME%\bin\java" -classpath .\bin\ CsvToJson etudiants2014_2015.csv
"%JAVA_HOME%\bin\java" -classpath .\bin\ CsvToJson intervenants2014_2015.csv
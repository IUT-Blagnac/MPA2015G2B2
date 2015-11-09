echo off
set ASCIIDOCDIR=./tools/asciidoc-8.6.9/
set JAVADOCDIR=./javadoc/
set PLANTUMLDIR=./tools/
set SPRINTDIR=%~dp0
set SRCDIR=./src/
set BINDIR=./bin/
set SRCDOCDIR=./srcdoc/
set DOCDIR=./doc/

set MAKETEST=1

echo on

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des executables
@echo ///////////////////////////////////////////////////////

"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%Csv/LibCsv.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%Model/Entity.java 
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%Model/Etudiant.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%Model/Sujet.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%Model/Groupe.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%Model/Intervenant.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%Model/Model.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%View/CreerSujet.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%View/CreerEtu.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%View/CreerInterv.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%View/EtudiantOnglet.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%View/IntervenantOnglet.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%View/SujetOnglet.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%View/PanelJList.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%View/SupprimerSujet.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%View/ListeSujet.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%View/Fenetre.java
"%JAVA_HOME%\bin\javac" -cp %SRCDIR%;%BINDIR%;%BINDIR%Controller/;%BINDIR%Model/;%BINDIR%View/;%BINDIR%Csv/ -d %BINDIR% %SRCDIR%Controller/Controller.java

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des documentations
@echo ///////////////////////////////////////////////////////

python %ASCIIDOCDIR%asciidoc.py -a source-highlighter=pygments -o %DOCDIR%Documentation_Utilisateur.html %SRCDOCDIR%Documentation_Utilisateur.txt

python %ASCIIDOCDIR%asciidoc.py -a source-highlighter=pygments -o %DOCDIR%Documentation_Technique.html %SRCDOCDIR%Documentation_Technique.txt
python %ASCIIDOCDIR%asciidoc.py --backend slidy -a source-highlighter=pygments -o %DOCDIR%presentationFinale.html %SRCDOCDIR%presentationFinale.txt

@echo javadoc -d doc %JAVADOCDIR%Model\*.java %JAVADOCDIR%Controller\Controller.java %JAVADOCDIR%View\*.java %JAVADOCDIR%Csv\*.java

javadoc -d doc %JAVADOCDIR%Model\*.java %JAVADOCDIR%Controller\*.java %JAVADOCDIR%View\*.java %JAVADOCDIR%Csv\*.java >null 2>&1


@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des tests
@echo ///////////////////////////////////////////////////////
if "%MAKETEST%"=="1" (
"%JAVA_HOME%\bin\javac" -cp .;%BINDIR%;./tools/junit.jar -d %BINDIR% %SRCDIR%Csv\LibCsvTest.java
"%JAVA_HOME%\bin\javac" -cp .;%BINDIR%;./tools/junit.jar -d %BINDIR% %SRCDIR%Csv\mainCsv.java
"%JAVA_HOME%\bin\java" -classpath .\bin\ Csv.mainCsv
)

@echo ///////////////////////////////////////////////////////
@echo // EXECUTION des tests
@echo ///////////////////////////////////////////////////////
if "%MAKETEST%"=="1" (
cd %BINDIR%
"%JAVA_HOME%\bin\java" -cp .;../tools/junit.jar Csv.LibCsvTest
 cd %SPRINTDIR%
)

PAUSE
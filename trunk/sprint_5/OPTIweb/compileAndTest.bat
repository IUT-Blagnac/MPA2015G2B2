echo off
set ASCIIDOCDIR=./tools/asciidoc-8.6.9/
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

"%JAVA_HOME%\bin\javac" -d %BINDIR% %SRCDIR%MakeOPTIweb.java
"%JAVA_HOME%\bin\javac" -d %BINDIR% %SRCDIR%CsvToJson.java

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des documentations
@echo ///////////////////////////////////////////////////////

python %ASCIIDOCDIR%asciidoc.py -a source-highlighter=pygments -o %DOCDIR%Documentation_Utilisateur.html %SRCDOCDIR%DocUtilisateur.txt

python %ASCIIDOCDIR%asciidoc.py -a source-highlighter=pygments -o %DOCDIR%Documentation_Technique.html %SRCDOCDIR%DocTechnique.txt >null 2>&1

python %ASCIIDOCDIR%asciidoc.py -a source-highlighter=pygments -o %DOCDIR%Documentation_Utilisateur_MakeOptiWeb.html %SRCDOCDIR%DocUtilisateur_MakeOptiWeb.txt >null 2>&1

python %ASCIIDOCDIR%asciidoc.py -a source-highlighter=pygments -o %DOCDIR%Documentation_Technique_makeOptiWeb.html %SRCDOCDIR%DocTechnique_makeOptiWeb.txt >null 2>&1

del null

@echo ///////////////////////////////////////////////////////
@echo // COMPILATION des tests
@echo ///////////////////////////////////////////////////////

if "%MAKETEST%"=="1" (
"%JAVA_HOME%\bin\javac" -cp .;%BINDIR%;./tools/junit.jar -d %BINDIR% %SRCDIR%TestMakeOPTIwebTest.java
)

@echo ///////////////////////////////////////////////////////
@echo // EXECUTION des tests
@echo ///////////////////////////////////////////////////////

if "%MAKETEST%"=="1" (
"%JAVA_HOME%\bin\java" -cp .;%BINDIR%;./tools/junit.jar TestMakeOPTIwebTest
)



PAUSE
# Github public API automation 
This is a quick project developed for backend automation of Github's public APIs that also contains a jenkinsFile
for CI/CD integration.
Only Read methods (GET) were implemented, no Create, Update, or Delete methods are implemented in this quick project

## Improvements
1. Integrate Jenkins plugins (like Test Results Analyzer) for a simple reporting layout within your CI/CD pipeline
2. Massive automation coverage can be achieved by using Data-Driven-Testing. 

### Project Setup in IntelliJ:
1. Use IntelliJ 2018.X
2. Download JDK 1.8
3. Add JDK 1.8 to IntelliJ (Project Structure -> Platform Settings -> SDKs -> + 1.8)
6. Project Structure -> Project Settings -> Project -> Project SDK: choose 1.8 -> Project language level: choose 8
7. Project Structure -> Project Settings -> Modules -> Language Level: choose 8 ->
8. Use Maven window (on the right) to build: clean -> build
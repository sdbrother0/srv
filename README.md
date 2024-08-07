# Getting Started

### Install Java 21 (for example on Debian Linux):
```
wget https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.deb
sudo dpkg -i jdk-21_linux-x64_bin.deb
```

### Build and run srv (backend for https://github.com/sdbrother0/ui-ng-ant)

```
git clone https://github.com/sdbrother0/srv.git
cd srv
./gradlew build
java -jar build/libs/srv-0.0.1-SNAPSHOT.jar
```
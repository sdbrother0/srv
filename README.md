# Getting Started

### 1. Install Java 21 (for example on Debian Linux):
```
wget https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.deb
sudo dpkg -i jdk-21_linux-x64_bin.deb
```

### 2. Build and run srv (backend for https://github.com/sdbrother0/ui-ng-ant)

```
git clone https://github.com/sdbrother0/srv.git
cd srv
./gradlew build
docker build -t sdbrother/srv:v0 .
docker run -e JAVA_OPTS='-Xmx256m' -p 127.0.0.1:8090:8090 -d sdbrother/srv:v0
```
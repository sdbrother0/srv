# Getting Started

This guide explains how to set up and run the backend service for the Metadata-Driven Web UI for Business Data Management

Frontend: 👉 https://github.com/sdbrother0/ui-ng-ant

---

## 1. Install Java 25 (Debian Linux example)

```bash
wget https://download.oracle.com/java/25/latest/jdk-25_linux-x64_bin.deb
sudo dpkg -i jdk-25_linux-x64_bin.deb
```

### 2. Build and run srv (backend for https://github.com/sdbrother0/ui-ng-ant)

```
git clone https://github.com/sdbrother0/srv.git
cd srv
./gradlew build
docker build -t sdbrother/srv:v0 .
docker run -e JAVA_OPTS='-Xmx256m' -p 0.0.0.0:8090:8090 -d sdbrother/srv:v0
```
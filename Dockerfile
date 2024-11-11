FROM maven:3.8.5-openjdk-11

RUN apt-get update && \
    apt-get install -y firefox-esr && \
    apt-get install -y wget && \
    wget https://github.com/mozilla/geckodriver/releases/download/v0.30.0/geckodriver-v0.30.0-linux64.tar.gz && \
    tar -xvzf geckodriver-v0.30.0-linux64.tar.gz && \
    mv geckodriver /usr/local/bin/ && \
    rm geckodriver-v0.30.0-linux64.tar.gz

WORKDIR /app
COPY . .

CMD ["mvn", "clean", "test"]

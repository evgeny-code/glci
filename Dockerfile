FROM java:8

COPY ./target/glci-app.jar /

CMD ["java", "-server", "-Xmx256m", "-jar", "/glci-app.jar"]

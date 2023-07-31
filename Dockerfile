FROM openjdk
COPY . /app
WORKDIR /app/target/
ADD target/reactive-0.1.jar reactive-0.1.jar
ENTRYPOINT ["java","-jar","reactive-0.1.jar"]

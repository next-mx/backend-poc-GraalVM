FROM oracle/graalvm-ce:19.2.0.1 as graalvm
COPY . /home/app/poc-graalvm-micronaut
WORKDIR /home/app/poc-graalvm-micronaut
RUN gu install native-image
RUN native-image --no-server -cp build/libs/java-micronaut-service-*-all.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/poc-graalvm-micronaut .
ENTRYPOINT ["./poc-graalvm-micronaut"]

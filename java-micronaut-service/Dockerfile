FROM oracle/graalvm-ce:19.2.0.1 as graalvm
COPY . /home/app/pocgraalvm-api-rest
WORKDIR /home/app/pocgraalvm-api-rest
RUN gu install native-image
RUN native-image --no-server -cp build/libs/pocgraalvm-api-rest-*-all.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/pocgraalvm-api-rest .
ENTRYPOINT ["./pocgraalvm-api-rest"]

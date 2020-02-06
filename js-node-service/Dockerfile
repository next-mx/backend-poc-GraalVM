FROM oracle/graalvm-ce:19.2.0.1 as graalvm

WORKDIR /usr/src/app

COPY package*.json ./
COPY app.js .

EXPOSE 8080

RUN npm install
CMD [ "npm", "start" ]
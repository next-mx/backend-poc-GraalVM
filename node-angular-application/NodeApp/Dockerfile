FROM oracle/graalvm-ce:19.2.0.1 as graalvm

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 8080

CMD [ "npm", "start" ]
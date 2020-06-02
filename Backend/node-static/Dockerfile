FROM node:12-alpine

WORKDIR /sources

COPY package.json /sources

RUN npm install

COPY tsconfig.json /sources
COPY src/ /sources/src

RUN ./node_modules/.bin/tsc && mkdir download

ENTRYPOINT [ "node", "dist/index.js" ]
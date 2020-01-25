FROM node:10.18.1-jessie

WORKDIR /app

COPY package.json /app/

RUN npm i npm@latest -g

RUN npm install

COPY ./ /app/

RUN npm run build --prod


FROM httpd:2.4

RUN sed -i '/LoadModule rewrite_module/s/^#//g' /usr/local/apache2/conf/httpd.conf && \
    sed -i 's#AllowOverride [Nn]one#AllowOverride All#' /usr/local/apache2/conf/httpd.conf

COPY --from=0 ./app/dist/movie-recommendation-ui/ /usr/local/apache2/htdocs

COPY ./.htaccess /usr/local/apache2/htdocs
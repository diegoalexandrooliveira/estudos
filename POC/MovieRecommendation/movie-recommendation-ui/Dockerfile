FROM httpd:2.4

RUN sed -i '/LoadModule rewrite_module/s/^#//g' /usr/local/apache2/conf/httpd.conf && \
    sed -i 's#AllowOverride [Nn]one#AllowOverride All#' /usr/local/apache2/conf/httpd.conf

COPY ./dist/movie-recommendation-ui/ /usr/local/apache2/htdocs

COPY ./.htaccess /usr/local/apache2/htdocs
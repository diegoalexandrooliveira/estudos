FROM nginx:1.18.0-alpine

COPY ./dist/angular-devops/ /usr/share/nginx/html

COPY ./env_replace.sh /home

RUN chmod +x /home/env_replace.sh

ENTRYPOINT [ "/home/env_replace.sh" ]

CMD  ["nginx", "-g", "daemon off;"]
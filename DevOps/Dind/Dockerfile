FROM ubuntu:bionic

RUN apt-get update -qq && \
    apt-get install -y -qq \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common && \
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add - && \
    add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable" && \
    apt-get update -qq && \
    apt-get install -y -qq \
    docker-ce-cli

CMD /etc/init.d/docker start
#!/bin/sh
set -e

sed -i "s#API_URL_PLACEHOLDER#$API_URL_PLACEHOLDER#g" /usr/share/nginx/html/assets/variables.json

exec "$@"
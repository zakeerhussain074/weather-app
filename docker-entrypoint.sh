#!/bin/sh
set -e

# Replace API URL in config.json with ENV value
envsubst < /usr/share/nginx/html/assets/config.template.json > /usr/share/nginx/html/assets/config.json

# Start nginx
exec nginx -g "daemon off;"

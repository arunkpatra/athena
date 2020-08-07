#!/bin/sh

set -e

ATHENA_OPTS=

if [ -n "${ATHENA_ENV}" ]; then
  ATHENA_OPTS="$ATHENA_OPTS -Dathena-env=${ATHENA_ENV}"
else
  ATHENA_ENV="dev"
  ATHENA_OPTS="$ATHENA_OPTS -Dathena-env=${ATHENA_ENV}"
fi

echo ATHENA_OPTS : "$ATHENA_OPTS"
java $THINGVERSE_OPTS -jar /app.jar

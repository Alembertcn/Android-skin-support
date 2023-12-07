#!/usr/bin/env bash
./gradlew \
androidx:skin-support:publish --stacktrace \
androidx:skin-support-appcompat:publish --stacktrace \
androidx:skin-support-design:publish --stacktrace \
androidx:skin-support-cardview:publish --stacktrace \
androidx:skin-support-constraint-layout:publish --stacktrace

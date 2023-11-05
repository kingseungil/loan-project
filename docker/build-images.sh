#!/bin/bash

VERSION="1.0.0"
HUB_USER="kingseungil"

cd ..

modules=(
    "module-core"
    "module-user"
    "module-organization"
)

for module in ${modules[@]}; do
    echo "Builing module: $module"
    ./gradlew :$module:clean :$module:build
    echo "Building image for module: $module"
    cd $module
    docker build -t "$module:$VERSION" .
    docker tag "$module:$VERSION" "$HUB_USER/$module:$VERSION"
    docker push "$HUB_USER/$module:$VERSION"
    cd ..
done


echo "All images built successfully."

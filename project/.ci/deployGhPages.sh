#!/usr/bin/env bash
dir="$(dirname "$(realpath "$0")")"
openssl aes-256-cbc -K $encrypted_ffde4cf4eb9b_key -iv $encrypted_ffde4cf4eb9b_iv -in "$dir/deployKey.enc" -out "$dir/deployKey" -d
eval "$(ssh-agent -s)"
chmod 600 "$dir/deployKey"
ssh-add "$dir/deployKey"

git clone --depth 1 -b gh-pages git@github.com:FIUS/JVK-2019.git gh-pages

mvn compile javadoc:javadoc
cp -r target/site/apidocs/* gh-pages

cd gh-pages
git add --all
git commit -m "Update apidoc"
git push

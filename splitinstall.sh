#! /bin/bash

read -p 'Bundle directory: ' filePath

keyStoreFile="keystore.properties"

rm -f app.apks
while IFS='=' read -r key value; do
  key=$(echo $key | tr '.' '_')
  eval ${key}=\${value}
done <"$keyStoreFile"

# /Users/aliff.ridzuan/StudioProjects/biofourmis/FeatureDelivery/app/release/Feature\ Delivery\ POC-v1.1.0\(9\)-release.aab
bundletool build-apks --bundle=/Users/aliff.ridzuan/StudioProjects/biofourmis/FeatureDelivery/app/release/Feature\ Delivery\ POC-v1.1.0\(9\)-release.aab \
  --output=app.apks \
  --ks=$storeFile \
  --ks-pass=pass:$storePassword \
  --ks-key-alias=$keyAlias \
  --key-pass=pass:$storePassword \
  --connected-device \
  --local-testing

adb uninstall com.frestoinc.sample.featuredelivery

bundletool install-apks --apks=app.apks

adb shell monkey -p com.frestoinc.sample.featuredelivery 1

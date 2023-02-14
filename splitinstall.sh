#! /bin/bash

read -p 'Bundle directory: ' filePath

echo $filePath

keyStoreFile="keystore.properties"

apkOutput="app.apks"

if [ -e $apkOutput ]; then
  rm -f $apkOutput
fi

while IFS='=' read -r key value; do
  key=$(echo $key | tr '.' '_')
  eval ${key}=\${value}
done <"$keyStoreFile"

bundletool build-apks \
  --bundle="$filePath" \
  --output=$apkOutput \
  --ks=$storeFile \
  --ks-pass=pass:$storePassword \
  --ks-key-alias=$keyAlias \
  --key-pass=pass:$storePassword \
  --connected-device \
  --local-testing

adb uninstall com.frestoinc.sample.featuredelivery

bundletool install-apks --apks=$apkOutput

adb shell monkey -p com.frestoinc.sample.featuredelivery 1

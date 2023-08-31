#!/bin/bash
# #%L
#  wcm.io
#  %%
#  Copyright (C) 2022 wcm.io
#  %%
#  Licensed under the Apache License, Version 2.0 (the "License");
#  you may not use this file except in compliance with the License.
#  You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.
#  #L%

# Builds the application, deploys it to local author, and then deploys it to local publish as well (without building it again)
# Deployment to author and publish runs in parallel

# Display a pause message (only when the script was executed via double-click on windows)
pause_message() {
  if [ "$DISPLAY_PAUSE_MESSAGE_WRAPPER" = true ]; then
    echo ""
    read -n1 -r -p "Press any key to continue..."
  fi
}

if [[ $0 == *":\\"* ]]; then
  DISPLAY_PAUSE_MESSAGE_WRAPPER=true
fi

# install AEM 6.5 with service pack
mvn --non-recursive wcmio-content-package:install \
    --activate-profiles=fast,classic \
    -Dvault.artifact=adobe.binary.aem.65.servicepack:aem-service-pkg:zip:6.5.18.0 \
    -Dvault.delayAfterInstallSec=30
mvn --non-recursive wcmio-content-package:install \
    --activate-profiles=fast,publish,classic \
    -Dvault.artifact=adobe.binary.aem.65.servicepack:aem-service-pkg:zip:6.5.18.0 \
    -Dvault.delayAfterInstallSec=30

# Build application
./build-deploy.sh build --maven.profiles=fast,classic "$@"
if [ "$?" -ne "0" ]; then
  pause_message
  exit $?
fi

# Deploy to author (in parallel)
./build-deploy.sh deploy --maven.profiles=fast,classic "$@" &

# Deploy to publish (in parallel)
./build-deploy.sh deploy --maven.profiles=fast,publish,classic "$@" &

wait
pause_message

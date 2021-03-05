#!/usr/bin/env bash

# 构建Web
flutter build web
# 拷贝web内容到node目录
cp -r build/web/ node/server/public_flutter_web/
# 启动服务
#node node/server/bin/www

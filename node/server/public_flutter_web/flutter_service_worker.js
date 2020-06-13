'use strict';
const CACHE_NAME = 'flutter-app-cache';
const RESOURCES = {
  "index.html": "3fad5abbb07481f2a5a9cb1d73867835",
"/": "3fad5abbb07481f2a5a9cb1d73867835",
"main.dart.js": "070b20f54c48d8cadec22dacb3b15bf1",
"favicon.png": "5dcef449791fa27946b3d35ad8803796",
"icons/Icon-192.png": "ac9a721a12bbc803b44f645561ecb1e1",
"icons/Icon-512.png": "96e752610906ba2a93c65f8abe1645f1",
"manifest.json": "1216df736b10501215e8204abe443b46",
"assets/LICENSE": "2edc83f3ec8f67637883619e48ebc366",
"assets/images/icon_laomeng.png": "922ed53f3d05366dc7da34e9e1732953",
"assets/images/icon_wendux.jpg": "cb6d5933684337455f438c9858c9a9ad",
"assets/images/icon_wuyilong.jpeg": "7802bfb4d0db82aecaf20c92a797ce0c",
"assets/images/icon__white.png": "bed0073f8abae1049260e6bcb522c4c4",
"assets/images/xiaomo/icon_bg.jpg": "7725b7e06a42d47d8c5e2ce988de4de5",
"assets/images/xiaomo/icon_logo.png": "3ce560eb1cfb41e70e335cc3773f5dcd",
"assets/images/head/head_default05.png": "a56dd6eea5b76bfdd8ccee7cd1e65008",
"assets/images/head/head_default04.png": "54b56b4ab2e87119c955bf2e71994c0a",
"assets/images/head/head_default06.png": "eaec0a1d916f528117d7c4aa31816340",
"assets/images/head/head_default07.png": "a144fbc05705337f876d4ec1ea8a3796",
"assets/images/head/head_default03.png": "020cf3e0d06c40b52ad4ac9ac72b73a4",
"assets/images/head/head_default02.png": "0b6f0e220d2627d916740301b422dd38",
"assets/images/head/head_default01.png": "8f563833d9545074c1d1388c3411f2b5",
"assets/images/head/head_default.png": "903d9d79fd44eba444fb85cd82c13938",
"assets/images/head/head_default09.png": "6dc5ea984a29b344e0b14fd7ff23cad2",
"assets/images/head/head_default08.png": "cdc4eddf8eb26000494f9c6c48a7c138",
"assets/images/icon_wechat.png": "7cdab6c4b0cb65b332ce2a0bab9de8f8",
"assets/images/icon_ixiaozhang.png": "3fe674b5729206388881e03721ec4dfb",
"assets/images/icon_blogger.jpg": "9c86b8041640d804b8ad62e19d836f7a",
"assets/images/icon_pub.jpg": "98903c1cb342496c9196a037c1ae590e",
"assets/images/icon_programer.gif": "578b85b80758d00569414a777eada9c1",
"assets/images/icon_mmmzq.jpg": "34f2a1b386987e97dabb1c711c539912",
"assets/images/icon_about_BY_gentle.jpg": "ac9f256275c240050524b936ade7a09b",
"assets/images/icon_Ashmi_kattel.jpeg": "adbc7ff4cddc6c77f3649f75926f0a4b",
"assets/images/icon_mage.jpg": "9145f40974588098027f14a72d18d0d7",
"assets/AssetManifest.json": "b9f00f836cfa1faa9762568218e78fe0",
"assets/FontManifest.json": "fa2890c02caffe9de626e35f8932e1c3",
"assets/packages/font_awesome_flutter/lib/fonts/fa-solid-900.ttf": "2aa350bd2aeab88b601a593f793734c0",
"assets/packages/font_awesome_flutter/lib/fonts/fa-regular-400.ttf": "2bca5ec802e40d3f4b60343e346cedde",
"assets/packages/font_awesome_flutter/lib/fonts/fa-brands-400.ttf": "5a37ae808cf9f652198acde612b5328d",
"assets/packages/flutter_markdown/assets/logo.png": "67642a0b80f3d50277c44cde8f450e50",
"assets/fonts/Cagliostro-Regular.ttf": "adfb37ac7aac77744585627a080570e7",
"assets/fonts/MaterialIcons-Regular.ttf": "56d3ffdef7a25659eab6a68a3fbfaf16"
};

self.addEventListener('activate', function (event) {
  event.waitUntil(
    caches.keys().then(function (cacheName) {
      return caches.delete(cacheName);
    }).then(function (_) {
      return caches.open(CACHE_NAME);
    }).then(function (cache) {
      return cache.addAll(Object.keys(RESOURCES));
    })
  );
});

self.addEventListener('fetch', function (event) {
  event.respondWith(
    caches.match(event.request)
      .then(function (response) {
        if (response) {
          return response;
        }
        return fetch(event.request);
      })
  );
});

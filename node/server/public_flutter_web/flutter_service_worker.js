'use strict';
const CACHE_NAME = 'flutter-app-cache';
const RESOURCES = {
  "index.html": "3fad5abbb07481f2a5a9cb1d73867835",
"/": "3fad5abbb07481f2a5a9cb1d73867835",
"main.dart.js": "5dad768c1d028a03b1c79da5ce05350e",
"favicon.png": "5dcef449791fa27946b3d35ad8803796",
"icons/Icon-192.png": "ac9a721a12bbc803b44f645561ecb1e1",
"icons/Icon-512.png": "96e752610906ba2a93c65f8abe1645f1",
"manifest.json": "1216df736b10501215e8204abe443b46",
"assets/LICENSE": "75262420e468e4c3737bf7df2fec0483",
"assets/images/wechat.jpeg": "c26410d76bcf47dc2e05856ed7e6f422",
"assets/images/gmail.png": "4830c5e0b5bbbe1c1e24d18ad860489b",
"assets/images/weixin.png": "1858658fb9968a94815473a8a8d33c09",
"assets/images/jianshu.png": "3d6bcefeb8996383c27d47a9b9c46f3b",
"assets/images/tx.png": "3930bb6644debc209eb5e3d6020b0fa6",
"assets/images/programer.gif": "ec597d6f10e80f50529bd112e598577d",
"assets/AssetManifest.json": "78f4e9094ac2a817ff095fa0b4c6af09",
"assets/FontManifest.json": "01700ba55b08a6141f33e168c4a6c22f",
"assets/packages/cupertino_icons/assets/CupertinoIcons.ttf": "115e937bb829a890521f72d2e664b632",
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

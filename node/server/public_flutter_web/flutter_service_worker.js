'use strict';
const CACHE_NAME = 'flutter-app-cache';
const RESOURCES = {
  "index.html": "3fad5abbb07481f2a5a9cb1d73867835",
"/": "3fad5abbb07481f2a5a9cb1d73867835",
"main.dart.js": "ae1db311b41370173675844ade723d4b",
"favicon.png": "5dcef449791fa27946b3d35ad8803796",
"icons/Icon-192.png": "ac9a721a12bbc803b44f645561ecb1e1",
"icons/Icon-512.png": "96e752610906ba2a93c65f8abe1645f1",
"manifest.json": "1216df736b10501215e8204abe443b46",
"assets/LICENSE": "836507ffad68b2de4cfed489f8a6bfa2",
"assets/images/__white.png": "246ba007502b75c9a486ca766819891e",
"assets/images/ad.jpg": "c82d09c1adcf3c243174ba2edc8818ff",
"assets/images/wechat.jpeg": "c26410d76bcf47dc2e05856ed7e6f422",
"assets/images/blogger.jpg": "a718feb53c43f9d59e709b120b2a4460",
"assets/images/about_BY_gentle.jpg": "bfff41ee0131210136634a0ae91dafde",
"assets/images/mmmzq.jpg": "b82095a79532cf6448be594992a4c809",
"assets/images/gmail.png": "4830c5e0b5bbbe1c1e24d18ad860489b",
"assets/images/pub.jpg": "5488981a31ad7a92ee7a8aabdb2c71c0",
"assets/images/weixin.png": "1858658fb9968a94815473a8a8d33c09",
"assets/images/wendux.jpg": "b3d60533680e5d23ad4f0051901e8cab",
"assets/images/ixiaozhang.png": "63e2c01ac6eb01c1d97d53e65580ca89",
"assets/images/laomeng.png": "b47fe360b5df69a03818fa5f09fe566f",
"assets/images/Ashmi_kattel.jpeg": "adbc7ff4cddc6c77f3649f75926f0a4b",
"assets/images/jianshu.png": "3d6bcefeb8996383c27d47a9b9c46f3b",
"assets/images/tx.png": "3930bb6644debc209eb5e3d6020b0fa6",
"assets/images/programer.gif": "ec597d6f10e80f50529bd112e598577d",
"assets/AssetManifest.json": "00737facf1d8040591c25e831a191006",
"assets/FontManifest.json": "01700ba55b08a6141f33e168c4a6c22f",
"assets/packages/cupertino_icons/assets/CupertinoIcons.ttf": "115e937bb829a890521f72d2e664b632",
"assets/packages/flutter_markdown/assets/logo.png": "67642a0b80f3d50277c44cde8f450e50",
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

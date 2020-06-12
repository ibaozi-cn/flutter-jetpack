'use strict';
const CACHE_NAME = 'flutter-app-cache';
const RESOURCES = {
  "index.html": "3fad5abbb07481f2a5a9cb1d73867835",
"/": "3fad5abbb07481f2a5a9cb1d73867835",
"main.dart.js": "e11ccc833ced26d7ef671fc5aac57b47",
"favicon.png": "5dcef449791fa27946b3d35ad8803796",
"icons/Icon-192.png": "ac9a721a12bbc803b44f645561ecb1e1",
"icons/Icon-512.png": "96e752610906ba2a93c65f8abe1645f1",
"manifest.json": "1216df736b10501215e8204abe443b46",
"assets/LICENSE": "c5ecdc54f68e3b7dd7a105596b953966",
"assets/images/__white.png": "246ba007502b75c9a486ca766819891e",
"assets/images/ad.jpg": "c82d09c1adcf3c243174ba2edc8818ff",
"assets/images/blogger.jpg": "a718feb53c43f9d59e709b120b2a4460",
"assets/images/mage.jpg": "728b1f8a2d6cf2a2b80bc0154f641d60",
"assets/images/about_BY_gentle.jpg": "bfff41ee0131210136634a0ae91dafde",
"assets/images/wuyilong.jpeg": "f1127172a57deb6ecf7967e8e17dce55",
"assets/images/mmmzq.jpg": "b82095a79532cf6448be594992a4c809",
"assets/images/gmail.png": "4830c5e0b5bbbe1c1e24d18ad860489b",
"assets/images/pub.jpg": "5488981a31ad7a92ee7a8aabdb2c71c0",
"assets/images/weixin.png": "1858658fb9968a94815473a8a8d33c09",
"assets/images/wendux.jpg": "b3d60533680e5d23ad4f0051901e8cab",
"assets/images/xiaomo/logo.png": "7a742291707e280517665344c4481122",
"assets/images/xiaomo/bg.jpg": "42dc0b8f2247577413847c96f9c37ae9",
"assets/images/head/head_default05.png": "5096e652fd59eb4372180f72f5b10010",
"assets/images/head/head_default04.png": "c195fab9575fdc065f52d79c43bc4a63",
"assets/images/head/head_default06.png": "19ca6893c74c30b975f3e102c2a233a5",
"assets/images/head/head_default07.png": "e41a7eb310c8824ba2b5bc6f0da96016",
"assets/images/head/head_default03.png": "ccafad857f8c5254e2411babddddd2d9",
"assets/images/head/head_default02.png": "632aa602b409f3b3aa3aa691198ee9a6",
"assets/images/head/head_default01.png": "839ced10acfcfbcd8cf882412e0f7b6b",
"assets/images/head/head_default.png": "7aeee421c7460d44be273b2accca1d5a",
"assets/images/head/head_default09.png": "6eaa0e37a37834737bab9710f56c577d",
"assets/images/head/head_default08.png": "3509c4fd9c904d760b948ca3264d63a1",
"assets/images/ixiaozhang.png": "63e2c01ac6eb01c1d97d53e65580ca89",
"assets/images/laomeng.png": "b47fe360b5df69a03818fa5f09fe566f",
"assets/images/Ashmi_kattel.jpeg": "adbc7ff4cddc6c77f3649f75926f0a4b",
"assets/images/jianshu.png": "3d6bcefeb8996383c27d47a9b9c46f3b",
"assets/images/tx.png": "3930bb6644debc209eb5e3d6020b0fa6",
"assets/images/programer.gif": "ec597d6f10e80f50529bd112e598577d",
"assets/images/wechat.png": "f2e393370a41651594fc974d0d7e56bc",
"assets/AssetManifest.json": "cef56deca1aa7217ef83bcf6b7a0be93",
"assets/FontManifest.json": "18eda8e36dfa64f14878d07846d6e17f",
"assets/packages/cupertino_icons/assets/CupertinoIcons.ttf": "115e937bb829a890521f72d2e664b632",
"assets/packages/font_awesome_flutter/lib/fonts/fa-solid-900.ttf": "2aa350bd2aeab88b601a593f793734c0",
"assets/packages/font_awesome_flutter/lib/fonts/fa-regular-400.ttf": "2bca5ec802e40d3f4b60343e346cedde",
"assets/packages/font_awesome_flutter/lib/fonts/fa-brands-400.ttf": "5a37ae808cf9f652198acde612b5328d",
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

'use strict';
const MANIFEST = 'flutter-app-manifest';
const TEMP = 'flutter-temp-cache';
const CACHE_NAME = 'flutter-app-cache';
const RESOURCES = {
  "version.json": "b1bc6398dad209bf7b0d7e9c66052543",
"index.html": "3afc61fba8ba834a337ce914cf8f385e",
"/": "3afc61fba8ba834a337ce914cf8f385e",
"main.dart.js": "ca6878fbeff66fcde4ea1fa93d7ef360",
"favicon.png": "5dcef449791fa27946b3d35ad8803796",
"icons/Icon-192.png": "ac9a721a12bbc803b44f645561ecb1e1",
"icons/Icon-512.png": "96e752610906ba2a93c65f8abe1645f1",
"manifest.json": "1216df736b10501215e8204abe443b46",
"assets/images/icon_laomeng.png": "922ed53f3d05366dc7da34e9e1732953",
"assets/images/icon_wendux.jpg": "cb6d5933684337455f438c9858c9a9ad",
"assets/images/icon_wuyilong.jpeg": "7802bfb4d0db82aecaf20c92a797ce0c",
"assets/images/icon_aiweima.png": "85fdabf7788196c25d8f43ab602e067c",
"assets/images/icon__white.png": "bed0073f8abae1049260e6bcb522c4c4",
"assets/images/icon_state.png": "cd7809cbbf81c4180b7a40b19c978592",
"assets/images/xiaomo/icon_bg.jpg": "7725b7e06a42d47d8c5e2ce988de4de5",
"assets/images/xiaomo/icon_logo.png": "3ce560eb1cfb41e70e335cc3773f5dcd",
"assets/images/icon_widget.png": "1ffccbf6c32421e65fdd25d7330f50ec",
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
"assets/images/icon_kimi.png": "b58d7863cdb43dab77a9b1f761757c81",
"assets/images/icon_about_BY_gentle.jpg": "ac9f256275c240050524b936ade7a09b",
"assets/images/icon_Ashmi_kattel.jpeg": "adbc7ff4cddc6c77f3649f75926f0a4b",
"assets/images/icon_mage.jpg": "9145f40974588098027f14a72d18d0d7",
"assets/AssetManifest.json": "89dcab11a9117b05d40d4bc92292db79",
"assets/NOTICES": "cb43064b81e2bac90407ab82aef9df84",
"assets/FontManifest.json": "1dec2261853d69d01d7f827fbc260495",
"assets/packages/font_awesome_flutter/lib/fonts/fa-solid-900.ttf": "d80ca32233940ebadc5ae5372ccd67f9",
"assets/packages/font_awesome_flutter/lib/fonts/fa-regular-400.ttf": "a126c025bab9a1b4d8ac5534af76a208",
"assets/packages/font_awesome_flutter/lib/fonts/fa-brands-400.ttf": "831eb40a2d76095849ba4aecd4340f19",
"assets/packages/flutter_markdown/assets/logo.png": "67642a0b80f3d50277c44cde8f450e50",
"assets/fonts/Cagliostro-Regular.ttf": "adfb37ac7aac77744585627a080570e7",
"assets/fonts/MaterialIcons-Regular.otf": "1288c9e28052e028aba623321f7826ac"
};

// The application shell files that are downloaded before a service worker can
// start.
const CORE = [
  "/",
"main.dart.js",
"index.html",
"assets/NOTICES",
"assets/AssetManifest.json",
"assets/FontManifest.json"];
// During install, the TEMP cache is populated with the application shell files.
self.addEventListener("install", (event) => {
  self.skipWaiting();
  return event.waitUntil(
    caches.open(TEMP).then((cache) => {
      return cache.addAll(
        CORE.map((value) => new Request(value + '?revision=' + RESOURCES[value], {'cache': 'reload'})));
    })
  );
});

// During activate, the cache is populated with the temp files downloaded in
// install. If this service worker is upgrading from one with a saved
// MANIFEST, then use this to retain unchanged resource files.
self.addEventListener("activate", function(event) {
  return event.waitUntil(async function() {
    try {
      var contentCache = await caches.open(CACHE_NAME);
      var tempCache = await caches.open(TEMP);
      var manifestCache = await caches.open(MANIFEST);
      var manifest = await manifestCache.match('manifest');
      // When there is no prior manifest, clear the entire cache.
      if (!manifest) {
        await caches.delete(CACHE_NAME);
        contentCache = await caches.open(CACHE_NAME);
        for (var request of await tempCache.keys()) {
          var response = await tempCache.match(request);
          await contentCache.put(request, response);
        }
        await caches.delete(TEMP);
        // Save the manifest to make future upgrades efficient.
        await manifestCache.put('manifest', new Response(JSON.stringify(RESOURCES)));
        return;
      }
      var oldManifest = await manifest.json();
      var origin = self.location.origin;
      for (var request of await contentCache.keys()) {
        var key = request.url.substring(origin.length + 1);
        if (key == "") {
          key = "/";
        }
        // If a resource from the old manifest is not in the new cache, or if
        // the MD5 sum has changed, delete it. Otherwise the resource is left
        // in the cache and can be reused by the new service worker.
        if (!RESOURCES[key] || RESOURCES[key] != oldManifest[key]) {
          await contentCache.delete(request);
        }
      }
      // Populate the cache with the app shell TEMP files, potentially overwriting
      // cache files preserved above.
      for (var request of await tempCache.keys()) {
        var response = await tempCache.match(request);
        await contentCache.put(request, response);
      }
      await caches.delete(TEMP);
      // Save the manifest to make future upgrades efficient.
      await manifestCache.put('manifest', new Response(JSON.stringify(RESOURCES)));
      return;
    } catch (err) {
      // On an unhandled exception the state of the cache cannot be guaranteed.
      console.error('Failed to upgrade service worker: ' + err);
      await caches.delete(CACHE_NAME);
      await caches.delete(TEMP);
      await caches.delete(MANIFEST);
    }
  }());
});

// The fetch handler redirects requests for RESOURCE files to the service
// worker cache.
self.addEventListener("fetch", (event) => {
  if (event.request.method !== 'GET') {
    return;
  }
  var origin = self.location.origin;
  var key = event.request.url.substring(origin.length + 1);
  // Redirect URLs to the index.html
  if (key.indexOf('?v=') != -1) {
    key = key.split('?v=')[0];
  }
  if (event.request.url == origin || event.request.url.startsWith(origin + '/#') || key == '') {
    key = '/';
  }
  // If the URL is not the RESOURCE list then return to signal that the
  // browser should take over.
  if (!RESOURCES[key]) {
    return;
  }
  // If the URL is the index.html, perform an online-first request.
  if (key == '/') {
    return onlineFirst(event);
  }
  event.respondWith(caches.open(CACHE_NAME)
    .then((cache) =>  {
      return cache.match(event.request).then((response) => {
        // Either respond with the cached resource, or perform a fetch and
        // lazily populate the cache.
        return response || fetch(event.request).then((response) => {
          cache.put(event.request, response.clone());
          return response;
        });
      })
    })
  );
});

self.addEventListener('message', (event) => {
  // SkipWaiting can be used to immediately activate a waiting service worker.
  // This will also require a page refresh triggered by the main worker.
  if (event.data === 'skipWaiting') {
    self.skipWaiting();
    return;
  }
  if (event.data === 'downloadOffline') {
    downloadOffline();
    return;
  }
});

// Download offline will check the RESOURCES for all files not in the cache
// and populate them.
async function downloadOffline() {
  var resources = [];
  var contentCache = await caches.open(CACHE_NAME);
  var currentContent = {};
  for (var request of await contentCache.keys()) {
    var key = request.url.substring(origin.length + 1);
    if (key == "") {
      key = "/";
    }
    currentContent[key] = true;
  }
  for (var resourceKey of Object.keys(RESOURCES)) {
    if (!currentContent[resourceKey]) {
      resources.push(resourceKey);
    }
  }
  return contentCache.addAll(resources);
}

// Attempt to download the resource online before falling back to
// the offline cache.
function onlineFirst(event) {
  return event.respondWith(
    fetch(event.request).then((response) => {
      return caches.open(CACHE_NAME).then((cache) => {
        cache.put(event.request, response.clone());
        return response;
      });
    }).catch((error) => {
      return caches.open(CACHE_NAME).then((cache) => {
        return cache.match(event.request).then((response) => {
          if (response != null) {
            return response;
          }
          throw error;
        });
      });
    })
  );
}

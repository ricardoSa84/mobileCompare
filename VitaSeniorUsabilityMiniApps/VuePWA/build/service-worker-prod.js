(function () {
  'use strict';

  const applicationServerPublicKey = 'BE-ZMb7Q-w0QLj6dRjTfNZQVaS-5A5G8NUG0aULUq1kExe18OAQu3_tJSHFWVEZ9Pxulkvwd7id1sNMmrrbNS9A';

  let isSubscribed = false;
  let swRegistration = null;

  function urlB64ToUint8Array(base64String) {
    const padding = '='.repeat((4 - base64String.length % 4) % 4);
    const base64 = (base64String + padding)
      .replace(/\-/g, '+')
      .replace(/_/g, '/');

    const rawData = window.atob(base64);
    const outputArray = new Uint8Array(rawData.length);

    for (let i = 0; i < rawData.length; ++i) {
      outputArray[i] = rawData.charCodeAt(i);
    }
    return outputArray;
  }

  function updateSubscriptionOnServer(subscription) {
    // TODO: Send subscription to application server
    if (subscription) {
      console.log(JSON.stringify(subscription));
    }
  }

  function subscribeUser() {
    const applicationServerKey = urlB64ToUint8Array(applicationServerPublicKey);
    swRegistration.pushManager.subscribe({
      userVisibleOnly: true,
      applicationServerKey: applicationServerKey
    })
      .then(function (subscription) {
        console.log('User is subscribed',subscription);

        updateSubscriptionOnServer(subscription);

        isSubscribed = true;

      })
      .catch(function (err) {
        console.log('Failed to subscribe the user: ', err);
      });
  }

  function initializeUI() {
    // Set the initial subscription value
    swRegistration.pushManager.getSubscription()
      .then(function (subscription) {
        console.log(JSON.stringify(subscription));
        isSubscribed = !(subscription === null);
        if (isSubscribed) {
          console.log('User IS subscribed.');
        } else {
          subscribeUser();
        }

      });
  }
  // Check to make sure service workers are supported in the current browser,
  // and that the current page is accessed from a secure origin. Using a
  // service worker from an insecure origin will trigger JS console errors.
  var isLocalhost = Boolean(window.location.hostname === 'localhost' ||
    // [::1] is the IPv6 localhost address.
    window.location.hostname === '[::1]' ||
    // 127.0.0.1/8 is considered localhost for IPv4.
    window.location.hostname.match(
      /^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/
    )
  );

  window.addEventListener('load', function () {
    if ('serviceWorker' in navigator &&
      (window.location.protocol === 'https:' || isLocalhost)) {
      navigator.serviceWorker.register('service-worker.js')
        .then(function (registration) {
          // updatefound is fired if service-worker.js changes.
          registration.onupdatefound = function () {
            // updatefound is also fired the very first time the SW is installed,
            // and there's no need to prompt for a reload at that point.
            // So check here to see if the page is already controlled,
            // i.e. whether there's an existing service worker.
            if (navigator.serviceWorker.controller) {
              // The updatefound event implies that registration.installing is set
              var installingWorker = registration.installing;

              installingWorker.onstatechange = function () {
                switch (installingWorker.state) {
                  case 'installed':
                    // At this point, the old content will have been purged and the
                    // fresh content will have been added to the cache.
                    // It's the perfect time to display a "New content is
                    // available; please refresh." message in the page's interface.
                    break;

                  case 'redundant':
                    throw new Error('The installing ' +
                      'service worker became redundant.');

                  default:
                  // Ignore
                }
              };
            }
          };
          if ('PushManager' in window) {
            swRegistration = registration;
            initializeUI();
          }
        }).catch(function (e) {
          console.error('Error during service worker registration:', e);
        });
    }
  });
})();

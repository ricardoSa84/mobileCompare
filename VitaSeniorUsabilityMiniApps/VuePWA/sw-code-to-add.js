
self.addEventListener('push', function (event) {
  console.log(event)
  const title = event.data.title
  const options = {
    body: event.data.text,
    vibrate: [100, 50, 100],
    data: { primaryKey: 1 },
    icon: './static/img/icons/android-chrome-192x192.png',
    badge: './static/img/icons/favicon-32x32.png'
  }

  event.waitUntil(self.registration.showNotification(title, options))
})

self.addEventListener('notificationclick', function (event) {
  event.notification.close()

  event.waitUntil(
    clients.openWindow('http://localhost:5000/')
  )
})

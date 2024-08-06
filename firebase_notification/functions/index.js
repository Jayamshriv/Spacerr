/**
 * Import function triggers from their respective submodules:
 *
 * const {onCall} = require("firebase-functions/v2/https");
 * const {onDocumentWritten} = require("firebase-functions/v2/firestore");
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */
const { onRequest } = require("firebase-functions/v2/https");
const logger = require("firebase-functions/logger");
const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();

exports.sendPushNotifications = functions.firestore.document("notifications/{docID}").onCreate(
  (snapshot, context) => {
    logger.info("New notification document created", { structuredData: true });

    const notificationData = snapshot.data();
    const title = typeof notificationData.title === 'string' ? notificationData.title : 'Default Title';
    const body = typeof notificationData.body === 'string' ? notificationData.body : 'Default Body';

    const message = {
      notification: {
        title: title,
        body: body,
      },
    //   ,
    //   topic: "new_user",
    };

    admin.messaging().sendToTopic("new_user",message)
    .then((response) => {
      logger.info("Successfully sent message:", response);
    })
    .catch((error) => {
      logger.error("Error sending message:", error);
    });

    // admin.messaging().send(message)
  }
);

exports.sendNewApodNotification = functions.firestore.document("apod/{docID}").onCreate(
  (snapshot,context)=>{
    logger.info("New notification document created", { structuredData: true });

    const notificationData = snapshot.data();
    const title = typeof notificationData.title === 'string' ? notificationData.title : 'Default Title';
    const body = typeof notificationData.body === 'string' ? notificationData.body : 'Default Body';
    const imageUrl = typeof notificationData.image === 'string' ? notificationData.image : '';

    const message = {
      notification: {
        title: title,
        body: body,
        

      },
      android: {
        notification: {
          imageUrl: imageUrl
        }
      },
      apns: {
        payload: {
          aps: {
            'mutable-content': 1
          }
        },
        fcm_options: {
          image: imageUrl
        }
      },
      webpush: {
        headers: {
          image: imageUrl
        }
      },
      topic: 'apod',
    };

    admin.messaging().send(message)
    .then((response) => {
      logger.info("Successfully sent message:", response);
    })
    .catch((error) => {
      logger.error("Error sending message:", error);
    });
  
  }
);

// Create and deploy your first functions
// https://firebase.google.com/docs/functions/get-started

// exports.helloWorld = onRequest((request, response) => {
//   logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });

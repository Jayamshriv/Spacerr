/**
 * Import function triggers from their respective submodules:
 *
 * const {onCall} = require("firebase-functions/v2/https");
 * const {onDocumentWritten} = require("firebase-functions/v2/firestore");
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */
const axios = require('axios');
const logger = require("firebase-functions/logger");
const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();
const db = admin.firestore();

exports.sendPushNotifications = functions.firestore.document("device_token/{docID}").onCreate(
  (snapshot, context) => {
    logger.info("New notification document created", { structuredData: true });

    const docID = context.params.docID;
    const deviceToken = snapshot.data().token;

    const notificationData = snapshot.data();
    // const title = typeof notificationData.title === 'string' ? notificationData.title : 'Default Title';
    // const body = typeof notificationData.body === 'string' ? notificationData.body : 'Default Body';

    const message = {
      notification: {
        title: "Hii Spacerr",
        body:"Be Ready to die after watching the beauty of Space"
      },
      token: deviceToken,
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
    const timestamp = typeof notificationData.timestamp === 'string' ? notificationData.timestamp : '';

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
      data: { 
        timestamp: timestamp.toString()
      }
    };
    logger.info("Message :", message);
    admin.messaging().send(message)
    .then((response) => {
      logger.info("Successfully sent message:", response);
    })
    .catch((error) => {
      logger.error("Error sending message:", error);
    });
  
  }
);

exports.fetchAndWriteDataOnFirestore = functions.pubsub.schedule('0 0 * * *').onRun(
  async (context)=> {
    try {
      const response = await axios.get('https://api.nasa.gov/planetary/apod?api_key=noMFHR9VTfElRHpLoPVcHaJNZsFU9hvtiIQyDS8m');
      const newData = response.data;

      // Transform the data to include only the title and URL
    const transformedData = transformArrayData(newData);
  
      // Reference to Firestore document
    const docRef = db.collection('apod').doc();
    // const doc = await docRef.get();

    // if (doc.exists) {
    //     const lastData = doc.data();
    //     if (isEqual(transformedData, lastData)) {
    //       console.log('No change in data.');
    //     } else {
    //       await docRef.set(transformedData);
    //       console.log('Data updated:', transformedData);
    //     }
    // } else {
      await docRef.set(transformedData);
      console.log('Data written', transformedData);
    // }
  } catch (error) {
    console.error('Error fetching data from API or writing to Firestore:', error);
  }
});

exports.sendRandomFact = functions.pubsub.schedule('0 * * * *').onRun(
    async (context)=> {
      try {
        const response = await axios.get('https://api.nasa.gov/planetary/apod?count=1&api_key=noMFHR9VTfElRHpLoPVcHaJNZsFU9hvtiIQyDS8m');
        const newData = response.data;
        logger.info("Send Random Fact 111:", newData);
        // Transform the data to include only the title and URL
      const transformedData = transformData(newData[0]);
        logger.info("Send Random Fact 000:", transformedData);
        // Reference to Firestore document
      const docRef = db.collection('apod').doc();
      // const doc = await docRef.get();
  
      // if (doc.exists) {
      //     const lastData = doc.data();
      //     if (isEqual(transformedData, lastData)) {
      //       console.log('No change in data.');
      //     } else {
      //       await docRef.set(transformedData);
      //       console.log('Data updated:', transformedData);
      //     }
      // } else {
        await docRef.set(transformedData);
        console.log('Data written', transformedData);
      // }
    } catch (error) {
      console.error('Error fetching data from API or writing to Firestore:', error);
    }
  });


function transformData(data) {
    if(data.title === undefined){
        title ="default title"
    }
  return {
    title: data.title,
    body:data.explanation,
    image: data.url,
    timestamp: data.date,
    mediatype: data.media_type
  };
}


// Create and deploy your first functions
// https://firebase.google.com/docs/functions/get-started

// exports.helloWorld = onRequest((request, response) => {
//   logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });

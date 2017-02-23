package push;


import javapns.communication.ConnectionToAppleServer;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Devices;
import javapns.notification.*;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.List;

@Component
public class ApnsClient {

	public static final Logger log = LoggerFactory.getLogger(ApnsClient.class);

	/**
	 * @param apnsJSON
	 * @param tokens
	 * @return
	 * @throws org.json.JSONException
	 */
	public PushedNotifications sendPush(String apnsJSON, List<String> tokens) throws JSONException {
		return sendPush(null, apnsJSON, tokens);
	}

	/**
	 * @param payload
	 * @param tokens
	 */
	public PushedNotifications sendPush(PushNotificationPayload payload, List<String> tokens) {
		return sendPush(null, payload, tokens);
	}

	/**
	 * @param appId
	 * @param apnsJSON
	 * @param tokens
	 * @throws org.json.JSONException
	 */
	public PushedNotifications sendPush(String appId, String apnsJSON, List<String> tokens) throws JSONException {
		return sendPush(null, PushNotificationPayload.fromJSON(apnsJSON), tokens);
	}

	/**
	 * https://code.google.com/p/javapns/wiki/MultipleClientsExample
	 *
	 * @param appId
	 * @param tokens
	 * @throws Exception
	 * @throws javapns.communication.exceptions.CommunicationException
	 * @throws javapns.communication.exceptions.KeystoreException
	 * @throws java.io.IOException
	 * @throws java.security.NoSuchProviderException
	 * @throws java.io.FileNotFoundException
	 * @throws java.security.cert.CertificateException
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.KeyStoreException
	 * @throws java.security.KeyManagementException
	 * @throws java.security.UnrecoverableKeyException
	 * @throws org.json.JSONException
	 */
	public PushedNotifications sendPush(String appId, PushNotificationPayload payload, List<String> tokens) {

		PushedNotifications result = null;
		try {

			log.info("[APNS] ==================== START PUSH ==================== ");
			log.info("[APNS] PAYLOAD : {}", payload);
			log.info("[APNS] TOKENS : {}", tokens.toString());
			log.info("[APNS] TOKENS SIZE : {}", tokens.size());

			PushNotificationManager pushManager = initAPNSConnetionV4(appId);
			result = pushManager.sendNotifications(payload, Devices.asDevices(tokens));


			log.info("[APNS] successful : {}", result.getSuccessfulNotifications());
			if (result.getFailedNotifications()!=null && !result.getFailedNotifications().isEmpty()){
				log.error("[APNS] Failed : {}", result.getFailedNotifications());
			}

			log.info("[APNS] ==================== END PUSH   ==================== ");

		} catch (Exception e) {
			log.error("[APNS] {}", printStackTrace(e));
		}

		return result;
	}

	/**
	 * V4 APNS Server Config
	 *
	 * @return
	 * @throws java.security.UnrecoverableKeyException
	 * @throws java.security.KeyManagementException
	 * @throws java.security.KeyStoreException
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.cert.CertificateException
	 * @throws java.io.FileNotFoundException
	 * @throws java.security.NoSuchProviderException
	 * @throws java.io.IOException
	 * @throws javapns.communication.exceptions.KeystoreException
	 * @throws javapns.communication.exceptions.CommunicationException
	 */
	public PushNotificationManager initAPNSConnetionV4(String appId) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, NoSuchProviderException, IOException, KeystoreException, CommunicationException {

        String sslPath   = "/app/tvingmanager/WEB-INF/classes/push_certificates/tving2_iPhonePush.p12";
//        String sslPath   = "/Users/kth79/tving2_iPhonePush.p12_oldv20";   // jwizard's test
        String password  = "tving";
        String appleHost = "gateway.push.apple.com";
        int    applePort = 2195;

		/* Gather communication details for your custom server */
        AppleNotificationServer customServer = new AppleNotificationServerBasicImpl(new File(sslPath).toString(), password, ConnectionToAppleServer.KEYSTORE_TYPE_PKCS12, appleHost, applePort);

        /* Create a push notification manager */
        PushNotificationManager pushManager = new PushNotificationManager();

        /* Initialize the push manager's connection to the custom server */
        pushManager.initializeConnection(customServer);

		return pushManager;
	}

	/**
	 * Throwable to String
	 *
	 * @param e
	 * @return
	 */
	public static String printStackTrace(Throwable e) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(out);
		e.printStackTrace(ps);
		return out.toString();
	}
}

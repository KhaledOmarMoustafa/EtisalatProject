package org.example;

import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;

public class ZAPSecurityTest {
    private static final String ZAP_ADDRESS = "localhost";
    private static final int ZAP_PORT = 8080;
    private static final String API_KEY = "mls5jcqtjn39ogtn3qpgkbavqq"; // Set this if required

    public static void scan(String targetUrl) throws Exception {
        ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, API_KEY);

        System.out.println("Starting OWASP ZAP Spider Scan on: " + targetUrl);
        String scanId = api.spider.scan(targetUrl, null, null, null, null).toString();

        // Wait for the spider to complete
        int progress;
        while (true) {
            Thread.sleep(1000);
            progress = Integer.parseInt(((ApiResponseElement) api.spider.status(scanId)).getValue());
            System.out.println("Spider progress: " + progress + "%");
            if (progress >= 100) {
                break;
            }
        }
        System.out.println("Spider scan completed.");

        System.out.println("Starting OWASP ZAP Active Scan on: " + targetUrl);
        api.ascan.scan(targetUrl, "True", "False", null, null, null);

        System.out.println("Security Scan Completed.");
    }
}


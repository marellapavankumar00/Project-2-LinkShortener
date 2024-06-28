import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class LinkShortener {
    private Map<String, String> urlMap;
    private Map<String, String> reverseUrlMap;
    private static final String BASE_URL = "http://short.url/";
    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    public LinkShortener() {
        urlMap = new HashMap<>();
        reverseUrlMap = new HashMap<>();
    }

    public String shortenURL(String longUrl) {
        if (reverseUrlMap.containsKey(longUrl)) {
            return BASE_URL + reverseUrlMap.get(longUrl);
        }

        String shortUrl;
        do {
            shortUrl = generateShortURL();
        } while (urlMap.containsKey(shortUrl));

        urlMap.put(shortUrl, longUrl);
        reverseUrlMap.put(longUrl, shortUrl);

        return BASE_URL + shortUrl;
    }

    public String expandURL(String shortUrl) {
        String key = shortUrl.replace(BASE_URL, "");
        return urlMap.getOrDefault(key, "Invalid URL");
    }

    private String generateShortURL() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = (int) (Math.random() * CHAR_SET.length());
            sb.append(CHAR_SET.charAt(index));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Link Shortener System");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter the long URL:");
                    String longUrl = scanner.nextLine();
                    String shortUrl = linkShortener.shortenURL(longUrl);
                    System.out.println("Shortened URL: " + shortUrl);
                    break;
                case 2:
                    System.out.println("Enter the short URL:");
                    String shortUrlToExpand = scanner.nextLine();
                    String expandedUrl = linkShortener.expandURL(shortUrlToExpand);
                    System.out.println("Expanded URL: " + expandedUrl);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
public class ChatBot{
@SuppressWarnings("ConvertToTryWithResources")
public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
        System.out.println("Hello! I am your chatbot. Ask me anything!");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            switch (userInput) {
                case "hello" -> System.out.println("Chatbot: hello!");
                case "youtube" -> {
                    System.out.println("Sure! You can search for anything on YouTube: https://www.youtube.com");
                    openLink("https://www.youtube.com");
                }
                case "google" -> {
                    System.out.println("Sure! You can search for anything on Google: https://www.google.com");
                    openLink("https://www.google.com");
                }
                default -> {
                    if (userInput.contains("weather")) {
                        System.out.println("Chatbot: You can check the weather here: https://www.accuweather.com/");
                        openLink("https://www.accuweather.com/");
                    } else if (userInput.contains("calculator")) {
                        System.out.println("Chatbot: You can use a calculator here: https://www.calculator.net/");
                        openLink("https://www.calculator.net/");
                    } else {
                        System.out.println("Chatbot: I'm not sure how to respond to that. Can you ask something else?");
                    }
                }
            }
        }
    }
}

public static String respond(String input) {
    return "I'm here to chat! You said: " + input;
}

public static void openLink(String urlString) {
    if (Desktop.isDesktopSupported()) {
        try {
            Desktop.getDesktop().browse(new URI(urlString));
        } catch (IOException e) {
            System.out.println("Failed to open link: " + e.getMessage());
        } catch (java.net.URISyntaxException ex) {
        }
    } else {
        System.out.println("Desktop is not Desktop.getDesktop().browse(new URI(urlString)");
    }
}
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AIChatBotAPI {
    private static final Map<String, String> responseMap = new HashMap<>();
    private static final Map<String, String> urlMap = new HashMap<>();

    public static void main(String[] args) {
        // Initialize predefined responses and URLs
        initializeResponses();

        // Create main frame
        JFrame frame = new JFrame("Simple AI ChatBot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setResizable(false);

        // Chat area
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 16));
        chatArea.setBackground(new Color(240, 248, 255));
        chatArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Input field
        JTextField userInput = new JTextField();
        userInput.setFont(new Font("Arial", Font.PLAIN, 16));
        userInput.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192), 2));

        // Send button
        JButton sendButton = new JButton("Send");
        sendButton.setFont(new Font("Arial", Font.BOLD, 14));
        sendButton.setBackground(new Color(0, 123, 255));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Panel for input field and button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(userInput, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Add action listener for the send button
        sendButton.addActionListener(e -> {
            String input = userInput.getText().trim();
            if (input.isEmpty()) {
                return;
            }
            chatArea.append("You: " + input + "\n");

            // Process input for response
            String response = generateResponse(input);
            chatArea.append("Chatbot: " + response + "\n");

            userInput.setText("");
        });

        // Allow user to hit "Enter" to submit input
        userInput.addActionListener(e -> {
            String input = userInput.getText().trim();
            if (!input.isEmpty()) {
                chatArea.append("You: " + input + "\n");

                // Process input for response
                String response = generateResponse(input);
                chatArea.append("Chatbot: " + response + "\n");

                userInput.setText("");
            }
        });

        // Set layout and add components
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Set frame visible
        frame.setVisible(true);
    }

    // Initialize responses and URLs
    private static void initializeResponses() {
        responseMap.put("hello", "Hello! How can I assist you today?");
        responseMap.put("how are you", "I'm doing great, thank you for asking!");
        responseMap.put("bye", "Goodbye! Have a great day!");
        responseMap.put("name", "I'm your friendly chatbot!");
        responseMap.put("thanks", "You're welcome!");
        responseMap.put("weather", "You can check the weather here: https://www.accuweather.com");
        responseMap.put("google", "Search anything on Google: https://www.google.com");
        responseMap.put("youtube", "Watch videos on YouTube: https://www.youtube.com");
        responseMap.put("calculator", "Use this calculator: https://www.calculatorsoup.com");
        responseMap.put("shopping", "Check out shopping websites like Amazon: https://www.amazon.com");
        responseMap.put("food delivery", "Order food online from Zomato: https://www.zomato.com or Swiggy: https://www.swiggy.com");

        // Map URLs to keywords
        urlMap.put("weather", "https://www.accuweather.com");
        urlMap.put("google", "https://www.google.com");
        urlMap.put("youtube", "https://www.youtube.com");
        urlMap.put("calculator", "https://www.calculatorsoup.com");
        urlMap.put("shopping", "https://www.amazon.com");
        urlMap.put("food delivery", "https://www.zomato.com");
    }

    // Generate response based on user input
    private static String generateResponse(String input) {
        String lowerInput = input.toLowerCase();

        // Check for keyword presence in the input and open corresponding link
        for (String keyword : responseMap.keySet()) {
            if (lowerInput.contains(keyword)) {
                openLinkIfNeeded(keyword);
                return responseMap.get(keyword);
            }
        }

        // Default response if no keyword is found
        return "Sorry, I didn't understand that. Can you try asking something else?";
    }

    // Open URL if a keyword is detected
    private static void openLinkIfNeeded(String keyword) {
        String url = urlMap.get(keyword);
        if (url != null) {
            openLink(url);
        }
    }

    // Method to open a URL in the default browser
    private static void openLink(String urlString) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(urlString));
            } catch (IOException | java.net.URISyntaxException e) {
                System.err.println("Error opening the URL: " + urlString);
                e.printStackTrace();
            }
        } else {
            System.err.println("Desktop is not supported. Cannot open links.");
        }
    }
}

# Function Time Complexity Analyzer with OpenAI ChatGPT APIs

The Function Time Complexity Analyzer is a web application that allows users to analyze the time complexity of a given function using Chat GPT API. It is built using Spring Boot MVC with Thymeleaf as the templating engine to render the user interface.

## Features

- Input a function in the text area.
- Click the "Analyze" button to calculate the time complexity of the function.
- The application will send the function to Chat GPT API for analysis.
- Display the analyzed time complexity result in the response box.

## Technologies Used

- Spring Boot: A powerful and popular framework for building web applications in Java.
- Thymeleaf: A Java-based templating engine used for rendering the user interface.
- Chat GPT API: An external API used for analyzing the time complexity of the input function.

## Getting Started

To run the Function Time Complexity Analyzer application on your local machine, follow these steps:

1. Make sure you have Java and Maven installed on your system.
2. Clone this repository to your local machine using Git.
3. Replace the `api_key` and `openai_url` values in the `application.properties` file with your Chat GPT API credentials.
4. Build the project using Maven: `mvn clean install`.
5. Run the application using Maven: `mvn spring-boot:run`.

The application will be accessible at `http://localhost:8080` in your web browser.

## Usage

1. Open your web browser and navigate to `http://localhost:8080`.
2. Enter the function you want to analyze in the provided text area.
3. Click the "Analyze" button to initiate the analysis.
4. The application will display the analyzed time complexity result in the response box.

## Note

Please make sure you have a stable internet connection to access the Chat GPT API and receive accurate time complexity analysis results.

## Contributing

If you want to contribute to this project or report any issues, please create a pull request or open an issue on the GitHub repository.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

- The Function Time Complexity Analyzer was inspired by the need to analyze the efficiency of algorithms and functions in programming.

Thank you for using the Function Time Complexity Analyzer! If you have any questions or feedback, feel free to contact us. Happy coding!
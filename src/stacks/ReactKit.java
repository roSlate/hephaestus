package stacks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Encapsulates the configuration layout rules for generating React client web applications.
 *
 * @version 1.0
 */
public class ReactKit implements StackKit {

    @Override
    public void generateBlueprint(Path targetRoot) throws IOException {
        System.out.println("⚛️ Injecting modular React template blueprint...");

        Path srcDir = targetRoot.resolve("src");
        Files.createDirectories(srcDir);

        Path packageJsonPath = targetRoot.resolve("package.json");
        String packageJsonContent = """
                {
                  "name": "scaffolded-react-app",
                  "private": true,
                  "version": "1.0.0",
                  "type": "module",
                  "dependencies": {
                    "react": "^18.2.0",
                    "react-dom": "^18.2.0"
                  }
                }
                """;
        Files.writeString(packageJsonPath, packageJsonContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        Path htmlPath = targetRoot.resolve("index.html");
        String htmlContent = """
                <!DOCTYPE html>
                <html lang="en">
                  <head>
                    <meta charset="UTF-8" />
                    <title>React App</title>
                  </head>
                  <body>
                    <div id="root"></div>
                    <script type="module" src="/src/main.jsx"></script>
                  </body>
                </html>
                """;
        Files.writeString(htmlPath, htmlContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        Path mainJsxPath = srcDir.resolve("main.jsx");
        String mainJsxContent = """
                import React from 'react'
                import ReactDOM from 'react-dom/client'
                import App from './App.jsx'
                
                ReactDOM.createRoot(document.getElementById('root')).render(
                  <React.StrictMode>
                    <App />
                  </React.StrictMode>,
                )
                """;
        Files.writeString(mainJsxPath, mainJsxContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        Path appJsxPath = srcDir.resolve("App.jsx");
        String appJsxContent = """
                import React from 'react';
                
                function App() {
                  return (
                    <div>
                      <h1>Hello from your modularly scaffolded React Application!</h1>
                    </div>
                  );
                }
                
                export default App;
                """;
        Files.writeString(appJsxPath, appJsxContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
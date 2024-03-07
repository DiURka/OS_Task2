import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        // Check if the correct number of command-line arguments is provided
        if (args.length != 1) {
            System.out.println("Usage: java Main <directory>");
            return;
        }

        // Get the directory path provided as a command-line argument
        String directory = args[0];

        try {
            // Execute the ls -l command to list directory contents
            @SuppressWarnings("deprecation")
            Process process = Runtime.getRuntime().exec("ls -l " + directory);

            // Read output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                // Print each line of the command output
                System.out.println(line);
            }

            // Wait for the process to complete and get the exit code
            int exitCode = process.waitFor();
            System.out.println("Exit code: " + exitCode);

            // Close the reader
            reader.close();

        } catch (IOException e) {
            // Handle IOException if executing the command fails
            System.out.println("Error executing command: " + e.getMessage());
        } catch (InterruptedException e) {
            // Handle InterruptedException if waiting for the command to complete is interrupted
            System.out.println("Interrupted while waiting for command to complete: " + e.getMessage());
        }
    }
}
/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ds_code;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        CameraSubsystem camProc = new CameraSubsystem();
        while (true) {
            camProc.process();
        }
    }
}
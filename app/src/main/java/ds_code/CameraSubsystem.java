package ds_code;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;;

public class CameraSubsystem {
  private VideoCapture camera;
  private VideoWriter outputStream;
  /** Creates a new VideoOverlaySubsystem. */
  public CameraSubsystem() {
    // Retrieves each individual frame from the robot camera, adds a vertical black line through the middle (might be updated if LIDAR is added to intake), 
    // then puts frame into the output stream to be retrieved in Shuffleboard
    // Taken from the Advanced Camera Server Program on WPILIB
    // https://docs.wpilib.org/en/stable/docs/software/vision-processing/roborio/using-the-cameraserver-on-the-roborio.html#advanced-camera-server-program
        camera = new VideoCapture("http://roboRIO-2158-frc.local:1181"); // Get the UsbCamera from CameraServer
        //camera.setResolution(640, 480);  // Set the resolution
        //CvSink cvSink = CameraServer.getVideo(); // Get a CvSink. This will capture Mats from the camera
        //CvSource outputStream = CameraServer.putVideo("Line", 640, 480); // Setup a CvSource. This will send images back to Shuffleboard.
        outputStream = new VideoWriter("http://127.0.0.1:8080", VideoWriter.fourcc('M','J','P','G'), 30, new Size(640,480));
  }
  public void process() {
        Mat mat = new Mat(); // Mat is an array of pixels of an image
        // Tell the CvSink to grab a frame from the camera and put it in the source mat.  If there is an error notify the output.
        if (!camera.read(mat)) {
        // Send the output the error.
            System.err.println("Failed to grab & decode frame");
        } else {
        // Put a line on the image
        Imgproc.line(mat, new Point((mat.size().width / 2), mat.size().height), new Point((mat.size().width / 2), 0), new Scalar(0, 0, 0), 2); // TODO: Implement sensor distance into width
        outputStream.write(mat);  // Give the output stream a new image to display
        }
    }
}
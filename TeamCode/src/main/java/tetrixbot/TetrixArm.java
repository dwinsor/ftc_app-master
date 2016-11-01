
package tetrixbot;

import android.util.Log;
import android.util.StringBuilderPrinter;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.HardwareDriveBot;

/**
 * TetrixArm - Robot with 6-axis arm
 *
 * The name "TetrixArm" refers to the instructor's robotic arm built from Tetrix part.
 * There is no difference in programming on either the Actobotics or Tetrix platforme.
 *
 * @author Jochen Fischer
 * @version 1.0, 10/31/2016
 *
 * -------------------------------------------------------------------------
 * Gamepad 1 controls:
 *
 * torso:    X button moves left (CCW), B button right (CW)
 * shoulder: left trigger and left bumper
 *
 * other:
 * reset arm encoders: Start button
 * back to zero position: Y button
 * -------------------------------------------------------------------------
*/
@TeleOp(name="Tetrix Arm", group="Robotic Arms")
public class TetrixArm extends LinearOpMode {

    // define the robot hardware:
    HardwareTetrixBot robot   = new HardwareTetrixBot();   // Use the Terix arm

    // keep track of the timing throughout the program:
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        // initialize the hardware
        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the user to press the PLAY button on the DS:
        waitForStart();

        while(opModeIsActive()) {
            //------------------------------------------------------------------
            // reset shoulder and elbow endoders by pressing the Start button
            //------------------------------------------------------------------
            // TODO: reset the shoulder and elbow encoders

            //----------------------------------------
            // move shoulder up and down
            //----------------------------------------
            // TODO: add code that moves the shoulder

            //----------------------------------------
            // move torso left and right:
            //----------------------------------------
            if(gamepad1.x) {
                // move left, CCW
                robot.posTorso = Range.clip(robot.posTorso + robot.DELTA_TORSO,
                                    robot.MIN_TORSO, robot.MAX_TORSO);
            }
            if(gamepad1.b) {
                // move right, CC
                robot.posTorso = Range.clip(robot.posTorso - robot.DELTA_TORSO,
                                    robot.MIN_TORSO, robot.MAX_TORSO);
            }
            robot.servoTorso.setPosition(robot.posTorso);
            telemetry.addData("torso", String.format("%.4f",robot.posTorso));

            //--------------------------------------------
            // housekeeping at the end of the main loop
            //--------------------------------------------
            // update the telemetry with all data from this loop:
            telemetry.update();

            // run the loop in 50ms increments (and give up the CPU for the rest of a cycle)
            robot.waitForTick(50);
        }
    }
}

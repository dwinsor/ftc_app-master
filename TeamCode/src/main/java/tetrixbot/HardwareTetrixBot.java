package tetrixbot;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 *  @author Jochen Fischer
 *  @version 1.0 - 10/31/2016
 *
 *  Hardware definition for the Tetrix arm
 */
public class HardwareTetrixBot
{
    /* Public OpMode members. */

    //--------------------------------------
    // motors
    //--------------------------------------
    public DcMotor  motorShoulder   = null;

    final static int DELTA_SHOULDER = 40;
    final static int MIN_SHOULDER   = 0;
    final static int MAX_SHOULDER   = 0;
    final static int INIT_SHOULDER  = 0;
    int posShoulder = INIT_SHOULDER;
    final static double POWER_SHOULDER = 0.2;

    // hardware specific constants:
    public static final int ENC_ROTATION_40 = 1120;
    public static final int ENC_ROTATION_60 = 1680;     // 1120 * 60 / 40

    // useful constants:
    public static final double STOP       =  0.0;

    //--------------------------------------
    // servos
    //--------------------------------------
    Servo servoTorso;

    final static double DELTA_TORSO = 0.004;
    final static double MIN_TORSO   = 0.170;
    final static double MAX_TORSO   = 0.562;
    final static double INIT_TORSO  = 0.374;
    double posTorso = INIT_TORSO;

    Servo servoTilt;

    // TODO: add code for the servo that moves the hand up and down

    Servo servoTurn;

    // TODO: add code for the servo that turns the hand

    Servo servoGripper;

    // TODO: add code for the gripper servo


    //--------------------------------------
    // local OpMode members
    //--------------------------------------
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    boolean robotIsInitialized = false;

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        //--------------------------------------
        // Define and Initialize Motors
        //--------------------------------------
        motorShoulder = hwMap.dcMotor.get("motorShoulder");
        motorShoulder.setDirection(DcMotor.Direction.REVERSE);
        resetShoulderEncoder();

        // TODO: define the motor for the elbow here

        //--------------------------------------
        // Define and initialize servos:
        //--------------------------------------
        servoTorso = hwMap.servo.get("servoTorso");
        servoTorso.setPosition(posTorso);

        // TODO: initialize the tilt, turn and gripper servo here
    }

    /**
     * resetShoulderEncoder - stops and resets the shoulder encoder
     *
     * @author Jochen Fischer
     * @version 1.0 - 10/31/2016
     */
    public void resetShoulderEncoder() {
        motorShoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorShoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    // TODO: resetElbowEncoder()

     /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}


package org.team3128.main;

import org.team3128.common.NarwhalRobot;
import org.team3128.common.drive.TankDrive;
import org.team3128.common.hardware.motor.MotorGroup;
import org.team3128.common.listener.ListenerManager;
import org.team3128.common.listener.controllers.ControllerExtreme3D;
import org.team3128.common.util.Log;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 * Main class for our demonstration robot
 * @author Narwhal
 *
 */
public class MainKitbot extends NarwhalRobot 
{
	private static final String TAG = "MainKitbot";
	
	private MotorGroup leftMotors, rightMotors;
	
	TankDrive drive;
	
	ListenerManager lm;
	
	@Override
	protected void constructHardware() 
	{
		leftMotors = new MotorGroup(new Talon(2), new Talon(3));
		rightMotors = new MotorGroup(new Talon(0), new Talon(1));
		
		leftMotors.invert();
		
		drive = new TankDrive(leftMotors, rightMotors, null, null, 0, 1, 0, 0);
		
		lm = new ListenerManager(new Joystick(0), new Joystick(1));
		
		Log.info(TAG, "Activated!  Lets do some sick jumps!");
	}

	@Override
	protected void setupListeners() 
	{
		lm.nameControl(ControllerExtreme3D.JOYY, "DriveForwards");
		lm.nameControl(ControllerExtreme3D.TWIST, "DriveTurn");
		lm.nameControl(ControllerExtreme3D.THROTTLE, "Throttle");
		
		lm.addMultiListener(() -> 
		{
			drive.arcadeDrive(lm.getAxis("DriveTurn"), lm.getAxis("DriveForwards"), -1 * lm.getAxis("Throttle"), true);
		}, "DriveForwards", "DriveTurn", "Throttle");
		
		addListenerManager(lm);
	}

	@Override
	protected void teleopInit() {
	}

	@Override
	protected void autonomousInit() {
		
	}
	
	@Override
	protected void updateDashboard() {
	}

}

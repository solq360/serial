package org.solq.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SimpleRead implements SerialPortEventListener {
    private static final int RATE = 9600;
    private static final String COM_NAME = "COM4";
    static CommPortIdentifier portId;
    InputStream inputStream;
    OutputStream outputStream;
    SerialPort serialPort;

    public SimpleRead(String comName, int rate) {
	try {
	    serialPort = (SerialPort) portId.open("SerialReader", 2000);
	    inputStream = serialPort.getInputStream();
	    outputStream = serialPort.getOutputStream();
	    serialPort.addEventListener(this);
	    serialPort.notifyOnDataAvailable(true);
	    serialPort.setSerialPortParams(rate, SerialPort.DATABITS_8, // 设置串口读写参数
		    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public void serialEvent(SerialPortEvent event) {// SerialPortEventListener
						    // 的方�?,监听的时候会不断执行
	switch (event.getEventType()) {
	case SerialPortEvent.BI:
	case SerialPortEvent.OE:
	case SerialPortEvent.FE:
	case SerialPortEvent.PE:
	case SerialPortEvent.CD:
	case SerialPortEvent.CTS:
	case SerialPortEvent.DSR:
	case SerialPortEvent.RI:
	case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
	    break;
	case SerialPortEvent.DATA_AVAILABLE:
	    byte[] readBuffer = new byte[1024];
	    try {
		int numBytes = 0;
		while (inputStream.available() > 0) {
		    numBytes = inputStream.read(readBuffer);
		}
		outputStream.write("xiaogang".getBytes());
		System.out.println(new String(readBuffer));
	    } catch (IOException e) {
	    }
	    break;
	}
    }

    public static void main(String[] args) {
	try {
	    portId = CommPortIdentifier.getPortIdentifier(COM_NAME);
	    System.out.println("open : " + portId.getName());
	    SimpleRead reader = new SimpleRead(COM_NAME, RATE);
	    while (true) {
		Thread.sleep(500);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main1(String[] args) {
	try {
	    Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
	    while (portList.hasMoreElements()) {
		portId = (CommPortIdentifier) portList.nextElement();
		System.out.println("name : " + portId.getName());
		if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
		    if (portId.getName().equals(COM_NAME)) {
			System.out.println("open : " + portId.getName());
			SimpleRead reader = new SimpleRead(COM_NAME, RATE);
		    }
		}
	    }
	    while (true) {
		Thread.sleep(500);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
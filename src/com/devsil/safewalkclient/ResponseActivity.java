package com.devsil.safewalkclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import edu.purdue.cs.cs180.channel.ChannelException;
import edu.purdue.cs.cs180.channel.MessageListener;
import edu.purdue.cs.cs180.channel.TCPChannel;

public class ResponseActivity extends Activity implements MessageListener {

	TCPChannel channel = null;
	Handler mHandler = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_response);

		// the ready button.
		final Button button = (Button) findViewById(R.id.ready_button);
		final TextView status = (TextView) findViewById(R.id.status_textview);

		//TODO: add channel initialization code.

		// A handler is needed since the message received is called from a
		// different Thread, and only the main thread can update the UI.
		// As a workaround, we create a handler in the main thread that displays
		// whatever it receives from the message received.
		mHandler = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				Message safeWalkMessage = (Message) msg.obj;
				//TODO: handle received message.
			}
		};

		// The on click event.
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				button.setEnabled(false);
				//TODO: send a message to the server.
			}
		});
	}

	@Override
	public void messageReceived(String message, int clientID) {
		// Create a handler message, and send it to the Main Thread.
		Message safeWalkMessage = new Message(message, clientID);
		android.os.Message msg = new android.os.Message();
		msg.obj = safeWalkMessage;
		mHandler.sendMessage(msg);
	}

	/**
	 * Close the application if sent to the background.
	 */
	@Override
	protected void onPause() {
	    super.onPause();
	    System.exit(0);
	}
}

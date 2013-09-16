package com.example.mora;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener {

	private ToggleButton toggleButton1 = null;
	private ToggleButton toggleButton2 = null;
	private ImageButton imageButton1 = null;
	private ImageButton imageButton2 = null;
	private ImageButton imageButton3 = null;
	private ImageButton imageButton4 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toggleButton1 = (ToggleButton) this.findViewById(R.id.toggleButton1);
		toggleButton1.setChecked(false);
		toggleButton1.setEnabled(false);
		toggleButton2 = (ToggleButton) this.findViewById(R.id.toggleButton2);
		toggleButton2.setChecked(false);
		toggleButton2.setEnabled(false);

		imageButton1 = (ImageButton) this.findViewById(R.id.imageButton1);
		imageButton1.setOnClickListener(this);
		imageButton2 = (ImageButton) this.findViewById(R.id.imageButton2);
		imageButton2.setOnClickListener(this);
		imageButton3 = (ImageButton) this.findViewById(R.id.imageButton3);
		imageButton3.setOnClickListener(this);
		imageButton4 = (ImageButton) this.findViewById(R.id.imageButton4);
		imageButton4.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void sendMessage(View view) {
		// Intent intent = new Intent(this, MainActivity.class);
		// EditText editText = (EditText) findViewById(R.id.editText1);
		// String message = editText.getText().toString();
		// System.out.println("input message:" + message);
		// intent.putExtra("xxxx", message);
		//
		// TextView textView = (TextView) findViewById(R.id.textView2);
		// textView.setText("You send message:" + message);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButton1: {
			setToggleButtonState(true);
			break;
		}
		case R.id.imageButton2: {
			setToggleButtonState(true);
			break;
		}
		case R.id.imageButton3: {
			setToggleButtonState(true);
			break;
		}
		case R.id.imageButton4: {
			setToggleButtonState(false);
			break;
		}
		}

	}

	private void setToggleButtonState(boolean isChecked) {
		if (isChecked) {
			if (toggleButton1.isChecked()) {
				toggleButton2.setChecked(isChecked);

				setImageButtonState(false);
			} else {
				toggleButton1.setChecked(isChecked);
			}
		} else {
			toggleButton1.setChecked(isChecked);
			toggleButton2.setChecked(isChecked);
			setImageButtonState(true);
		}
	}

	private void setImageButtonState(boolean isEnable) {
		imageButton1.setEnabled(isEnable);
		imageButton2.setEnabled(isEnable);
		imageButton3.setEnabled(isEnable);
	}

}

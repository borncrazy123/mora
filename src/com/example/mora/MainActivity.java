package com.example.mora;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

@SuppressLint({ "NewApi", "HandlerLeak" })
public class MainActivity extends Activity implements OnClickListener {

	private ToggleButton toggleButton1 = null;
	private ToggleButton toggleButton2 = null;
	private ImageButton imageButton1 = null;
	private ImageButton imageButton2 = null;
	private ImageButton imageButton3 = null;
	private ImageButton imageButton4 = null;
	private ImageView imageView2 = null;

	private static final Map<String, String> USER_SELECT_MAP = new HashMap<String, String>();
	private static final Map<String, String> COMPARE_MAP = new HashMap<String, String>();
	{
		COMPARE_MAP.put("1", "3");
		COMPARE_MAP.put("2", "1");
		COMPARE_MAP.put("3", "2");
	}

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

		imageView2 = (ImageView) this.findViewById(R.id.imageView2);
		imageView2.setTop(-300);

	}

	class myThread implements Runnable {

		public void run() {
			int i = imageView2.getHeight();
			while (i < 0) {
				i--;
				Message message = new Message();
				message.what = 1;
				myHandler.sendMessage(message);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			Message message = new Message();
			message.what = 2;
			myHandler.sendMessage(message);
		}
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				try {
					imageView2.setTop(imageView2.getTop() + 1);
					imageView2.invalidate();
				} catch (Exception e) {
					Log.e("myHandler", e.getMessage());
				}

				break;
			}
			case 2: {
				try {
					imageView2.setTop(0);
					imageView2.invalidate();
				} catch (Exception e) {
					Log.e("myHandler", e.getMessage());
				}

				break;
			}
			}
			super.handleMessage(msg);

		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButton1: {
			setToggleButtonState(true, "1");
			break;
		}
		case R.id.imageButton2: {
			setToggleButtonState(true, "2");
			break;
		}
		case R.id.imageButton3: {
			setToggleButtonState(true, "3");
			break;
		}
		case R.id.imageButton4: {
			setToggleButtonState(false, "0");
			break;
		}
		}

	}

	// 设置开关按钮状态
	private void setToggleButtonState(boolean isChecked, String select) {
		if (isChecked) {
			if (toggleButton1.isChecked()) {
				toggleButton2.setChecked(isChecked);
				USER_SELECT_MAP.put("B", select);
				setImageButtonState(false);
				new Thread(new myThread()).start();
			} else {
				USER_SELECT_MAP.put("A", select);
				toggleButton1.setChecked(isChecked);
			}
		} else {
			toggleButton1.setChecked(isChecked);
			toggleButton2.setChecked(isChecked);
			setImageButtonState(true);
			USER_SELECT_MAP.clear();
			imageView2.setTop(0 - imageView2.getHeight());
		}
	}

	// 设置用户按钮使用状态
	private void setImageButtonState(boolean isEnable) {
		imageButton1.setEnabled(isEnable);
		imageButton2.setEnabled(isEnable);
		imageButton3.setEnabled(isEnable);

		// 判断到底哪个用户赢了？
		if (isEnable) {
			String retStr = "";
			if (USER_SELECT_MAP.size() == 2) {
				String aSelect = USER_SELECT_MAP.get("A");
				String bSelect = USER_SELECT_MAP.get("B");

				if (aSelect.equals(bSelect)) {
					// 平局
					retStr = "Is Draw";
				} else {
					String pre = COMPARE_MAP.get(aSelect);
					if (pre.equals(bSelect)) {
						// A赢了
						retStr = "Andy Win!";
					} else {
						// A输了
						retStr = "Bell Win!";
					}
				}
				retStr = retStr + "\nAndy=" + translateSelectStr(aSelect)
						+ ",Bell=" + translateSelectStr(bSelect);
			} else {
				retStr = "Please select.";
			}

			new AlertDialog.Builder(this).setTitle("Victories")
					.setMessage(retStr).setPositiveButton("ok", null).show();
		}

	}

	private String translateSelectStr(String select) {
		if ("1".equals(select)) {
			return "剪刀";
		} else if ("2".equals(select)) {
			return "石头";
		} else if ("3".equals(select)) {
			return "白布";
		} else {
			return "非法值";
		}
	}

}

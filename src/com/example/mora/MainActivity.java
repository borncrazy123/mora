package com.example.mora;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	// ���ÿ��ذ�ť״̬
	private void setToggleButtonState(boolean isChecked, String select) {
		if (isChecked) {
			if (toggleButton1.isChecked()) {
				toggleButton2.setChecked(isChecked);
				USER_SELECT_MAP.put("B", select);
				setImageButtonState(false);
			} else {
				USER_SELECT_MAP.put("A", select);
				toggleButton1.setChecked(isChecked);
			}
		} else {
			toggleButton1.setChecked(isChecked);
			toggleButton2.setChecked(isChecked);
			setImageButtonState(true);
			USER_SELECT_MAP.clear();
		}
	}

	// �����û���ťʹ��״̬
	private void setImageButtonState(boolean isEnable) {
		imageButton1.setEnabled(isEnable);
		imageButton2.setEnabled(isEnable);
		imageButton3.setEnabled(isEnable);

		// �жϵ����ĸ��û�Ӯ�ˣ�
		if (isEnable) {
			String aSelect = USER_SELECT_MAP.get("A");
			String bSelect = USER_SELECT_MAP.get("B");
			String retStr = "";
			if (aSelect.equals(bSelect)) {
				// ƽ��
				retStr = "Is Draw";
			} else {
				String pre = COMPARE_MAP.get(aSelect);
				if (pre.equals(bSelect)) {
					// AӮ��
					retStr = "Andy Win!";
				} else {
					// A����
					retStr = "Bell Win!";
				}
			}

			new AlertDialog.Builder(this)
					.setTitle("Victories")
					.setMessage(
							retStr + "\nAndy=" + translateSelectStr(aSelect)
									+ ",Bell=" + translateSelectStr(bSelect))
					.setPositiveButton("ok", null).show();
		}

	}

	private String translateSelectStr(String select) {
		if ("1".equals(select)) {
			return "����";
		} else if ("2".equals(select)) {
			return "ʯͷ";
		} else if ("3".equals(select)) {
			return "�ײ�";
		} else {
			return "�Ƿ�ֵ";
		}
	}
}

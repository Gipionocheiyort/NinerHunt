package com.mbad.ninerhunt;

import java.lang.reflect.UndeclaredThrowableException;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

public class GoalScanActivity extends Activity implements ILocationReceiver {
	NfcAdapter adapter;
	PendingIntent pendingIntent;
	IntentFilter writeTagFilters[];
	boolean writeMode;
	Tag mytag;
	Context ctx;
	Intent currentIntent;
	String tagText;
	int huntId;
	int goalId;
	Double currentLat;
	Double currentLon;
	int userId;

	public void setLocation(Double lat, Double lon) {
		currentLat = lat;
		currentLon = lon;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal_scan);

		ctx = this;
		adapter = NfcAdapter.getDefaultAdapter(ctx);
		currentIntent = getIntent();

		// TODO: Get Username
		User currentUser = new User("Phil");
		userId = currentUser.getUserId();

		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(currentIntent.getAction())) {
			mytag = currentIntent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			processTag();
			completeGoal();
		}

	}

	private void completeGoal() {
		Hunt currentHunt = new Hunt(huntId);

		// Todo: Make sure user has started hunt or that this is the first goal
		// in a hunt.

		// Todo: Set hunt to active

		HuntLogItem completedItem = new HuntLogItem(goalId, userId, huntId);
		completedItem.setCompleted(true);
		completedItem.Save();
	}

	private void processTag() {
		Parcelable[] messages = currentIntent
				.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

		if (messages != null) {
			NdefMessage[] ndefMessages = new NdefMessage[messages.length];
			for (int i = 0; i < messages.length; i++) {
				ndefMessages[i] = (NdefMessage) messages[i];
			}
			NdefRecord record = ndefMessages[0].getRecords()[0];

			byte[] payload = record.getPayload();
			tagText = new String(payload).replace("en", "");

			String huntText = tagText.substring(0, tagText.indexOf(";"));

			huntId = Integer.parseInt(huntText
					.substring(huntText.indexOf(":") + 1).replace(";", "")
					.trim());

			String goalText = tagText.substring(tagText.indexOf(";"));

			goalId = Integer.parseInt(goalText
					.substring(goalText.indexOf(":") + 1).replace(";", "")
					.trim());

		}

	}

}

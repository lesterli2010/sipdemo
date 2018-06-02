package com.ehome.sipdemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ehome.sipservice.BroadcastEventReceiver;
import com.ehome.sipservice.Logger;
import com.ehome.sipservice.SipAccountData;
import com.ehome.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjsip_inv_state;
import org.pjsip.pjsua2.pjsip_status_code;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    @BindView(R.id.account_number)
    EditText accountNumber;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.called_number)
    EditText calledNumber;
    @BindView(R.id.dial)
    Button dial;
    @BindView(R.id.dtmf_number)
    EditText dtmfNumber;
    @BindView(R.id.send_dtmf)
    Button sendDtmf;
    @BindView(R.id.answer)
    Button answer;
    @BindView(R.id.hang_up)
    Button hangUp;
    @BindView(R.id.register_status)
    TextView registerStatus;
    @BindView(R.id.call_status)
    TextView callStatus;
    @BindView(R.id.output_strings)
    ListView outputStrings;
    @BindView(R.id.clear_log)
    Button clearLog;

    private SipAccountData mSipAccount;
    private static final String KEY_SIP_ACCOUNT = "sip_account";

    private int mCurrentCallId = -1; // -1 means no call
    private String mAccountId;

    private ArrayAdapter<String> mStatusAdapter;
    private List<String> mStatusStrings = new ArrayList<String>();

    SimpleDateFormat mDateFormat   =   new   SimpleDateFormat   ("HH:mm:ss");

    // hard code for register info
    private static final String PASSWORD = "yihuijia123?";
    private static final String HOST = "218.57.8.93";
    private static final long PORT = 5060;
    private static String USER = "9527001";

    private BroadcastEventReceiver sipEvents = new BroadcastEventReceiver() {
        @Override
        public void onRegistration(String accountID, pjsip_status_code registrationStateCode) {
            Log.d(TAG, "onRegistration, accountID:" + accountID + "," + registrationStateCode);
            if (registrationStateCode == pjsip_status_code.PJSIP_SC_OK) {
                Toast.makeText(MainActivity.this, "User:" + accountID + " Registered", Toast.LENGTH_SHORT).show();
                registerStatus.setText("User:" + accountID + " 注册成功！");
            } else {
                Toast.makeText(MainActivity.this, "User:" + accountID + "Unregistered", Toast.LENGTH_SHORT).show();
                registerStatus.setText("User:" + accountID + " 注册失败, code:" + registrationStateCode);
            }
        }

        @Override
        public void onIncomingCall(String accountID, int callID, String displayName, String remoteUri) {
            mAccountId = accountID;
            mCurrentCallId = callID;
            //super.onIncomingCall(accountID, callID, displayName, remoteUri);
            //Logger.debug(TAG, "accountId:" + accountID + ", callID:" + callID + ", displayName:" + displayName);
            //mStatusStrings.add("IncomingCall, accountId:" + accountID + ", callID:" + callID + " displayName:" + displayName);
            //mStatusAdapter.notifyDataSetChanged();
            callStatus.setText("收到来电 ：" + displayName);
            addLog("收到来电：" + displayName);
        }

        @Override
        public void onCallState(String accountID, int callID, pjsip_inv_state callStateCode, pjsip_status_code statusCode, long connectTimestamp, boolean isLocalHold, boolean isLocalMute) {
            //super.onCallState(accountID, callID, callStateCode, connectTimestamp, isLocalHold, isLocalMute);
            callStatus.setText("onCallState accountID:" + accountID + ", callID:" + callID + ", callStateCode:" + callStateCode);
            String log = null;
            if (callStateCode == pjsip_inv_state.PJSIP_INV_STATE_CALLING) {
                log = "正在拨号";

            } else if (callStateCode == pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED) {
                log = "开始通话，通话中...";

            } else if (callStateCode == pjsip_inv_state.PJSIP_INV_STATE_CONNECTING) {
                log = "正在建立通话";

            } else if (callStateCode == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED) {
                log = "通话结束";
                if (statusCode != null) {
                    if (statusCode == pjsip_status_code.PJSIP_SC_OK) {
                        log = log + " 正常结束";
                    } else {
                        log = log + " 非正常结束";
                    }
                }
                mCurrentCallId = -1;
            } else if (callStateCode == pjsip_inv_state.PJSIP_INV_STATE_EARLY) {
                log = "对方已振铃";
            }

            addLog(log + " - " + callStateCode + ", " + (statusCode != null ? statusCode : "null"));// + ", onCallState callStateCode" + callStateCode);
        }

        @Override
        public void onOutgoingCall(String accountID, int callID, String number) {
            mAccountId = accountID;
            mCurrentCallId = callID;
            //super.onOutgoingCall(accountID, callID, number);
            callStatus.setText("正在拨号：" + number);
            addLog("正在拨号：" + number);
        }

        @Override
        public void onStackStatus(boolean started) {
            super.onStackStatus(started);
            //addLog("Stack Status:" + started);
        }

        @Override
        public void onReceiveDtmf(String accountID, int callID, String dtmfDigit) {
            super.onReceiveDtmf(accountID, callID, dtmfDigit);
            addLog("收到DTMF:" + dtmfDigit);
        }
    };

    private void addLog(String log) {
        mStatusStrings.add(0, mDateFormat.format(new Date()) + " " + log);
        mStatusAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Logger.setLogLevel(Logger.LogLevel.DEBUG);

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_SIP_ACCOUNT)) {
            mSipAccount = savedInstanceState.getParcelable(KEY_SIP_ACCOUNT);
        }

        mStatusAdapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, mStatusStrings);
        outputStrings.setAdapter(mStatusAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permission = checkSelfPermission(Manifest.permission.RECORD_AUDIO);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 1);
            }
        }

        AudioManager audioManager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setSpeakerphoneOn(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mSipAccount != null) {
            outState.putParcelable(KEY_SIP_ACCOUNT, mSipAccount);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sipEvents.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sipEvents.register(this);
    }

    @OnClick(R.id.register)
    public void onRegister() {
        // we can set sip stack log level to debug
        SipServiceCommand.setSipStackLogLevel(this, 5);

        mSipAccount = new SipAccountData();

        String user = accountNumber.getText().toString().trim();
        if (TextUtils.isEmpty(user)) {
            user = USER;
        }
        mSipAccount.setHost(HOST)
                .setPort(PORT)
                .setUsername(user)
                .setPassword(PASSWORD)
                .setRealm(HOST)
                .setAuthenticationType(SipAccountData.AUTH_TYPE_DIGEST);

        SipServiceCommand.setAccount(this, mSipAccount);
        //SipServiceCommand.getCodecPriorities(this);
        Log.d(TAG, "register: " + mSipAccount.getRegistrarUri() + ", " + mSipAccount.getIdUri());
    }

    @OnClick(R.id.dial)
    public void onCall() {
        if (mSipAccount == null) {
            Toast.makeText(this, "Add an account and register it first", Toast.LENGTH_LONG).show();
            return;
        }

        String number = calledNumber.getText().toString().trim();

        if (number.isEmpty()) {
            number = "*9000";
            //Toast.makeText(this, "Provide a number to call", Toast.LENGTH_SHORT).show();
            //return;
        }

        SipServiceCommand.makeCall(this, mSipAccount.getIdUri(), number);
        Log.d(TAG, "make call:" + number);

        //AudioManager am = (AudioManager)getSystemService(Service.AUDIO_SERVICE);
        //am.setSpeakerphoneOn(true);
    }

    @OnClick(R.id.hang_up)
    public void onTerminate() {
        SipServiceCommand.hangUpActiveCalls(this, mSipAccount.getIdUri());
    }

    @OnClick(R.id.answer)
    public void onAnswer() {
        if (mCurrentCallId == -1) {
            Toast.makeText(this, "answer call no current call", Toast.LENGTH_LONG).show();
            return;
        }
        SipServiceCommand.acceptIncomingCall(this, mAccountId, mCurrentCallId);
        //startActivity(new Intent(this, CliActivity.class));
    }

    @OnClick(R.id.send_dtmf)
    public void sendDTMF() {
        if (mCurrentCallId == -1) {
            Toast.makeText(this, "sendDtmf no current call", Toast.LENGTH_LONG).show();
            return;
        }
        //Toast.makeText(this, "sendDtmf", Toast.LENGTH_LONG).show();
        SipServiceCommand.sendDTMF(this, mAccountId, mCurrentCallId, dtmfNumber.getText().toString().trim());
    }

    @OnClick(R.id.clear_log)
    public void clearLOG() {
        mStatusStrings.clear();
        mStatusAdapter.notifyDataSetChanged();
    }
}

// Generated code from Butter Knife. Do not modify!
package com.ehome.sipdemo;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131165284;

  private View view2131165238;

  private View view2131165304;

  private View view2131165214;

  private View view2131165252;

  private View view2131165229;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.accountNumber = Utils.findRequiredViewAsType(source, R.id.account_number, "field 'accountNumber'", EditText.class);
    view = Utils.findRequiredView(source, R.id.register, "field 'register' and method 'onRegister'");
    target.register = Utils.castView(view, R.id.register, "field 'register'", Button.class);
    view2131165284 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onRegister();
      }
    });
    target.calledNumber = Utils.findRequiredViewAsType(source, R.id.called_number, "field 'calledNumber'", EditText.class);
    view = Utils.findRequiredView(source, R.id.dial, "field 'dial' and method 'onCall'");
    target.dial = Utils.castView(view, R.id.dial, "field 'dial'", Button.class);
    view2131165238 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCall();
      }
    });
    target.dtmfNumber = Utils.findRequiredViewAsType(source, R.id.dtmf_number, "field 'dtmfNumber'", EditText.class);
    view = Utils.findRequiredView(source, R.id.send_dtmf, "field 'sendDtmf' and method 'sendDTMF'");
    target.sendDtmf = Utils.castView(view, R.id.send_dtmf, "field 'sendDtmf'", Button.class);
    view2131165304 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendDTMF();
      }
    });
    view = Utils.findRequiredView(source, R.id.answer, "field 'answer' and method 'onAnswer'");
    target.answer = Utils.castView(view, R.id.answer, "field 'answer'", Button.class);
    view2131165214 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAnswer();
      }
    });
    view = Utils.findRequiredView(source, R.id.hang_up, "field 'hangUp' and method 'onTerminate'");
    target.hangUp = Utils.castView(view, R.id.hang_up, "field 'hangUp'", Button.class);
    view2131165252 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onTerminate();
      }
    });
    target.registerStatus = Utils.findRequiredViewAsType(source, R.id.register_status, "field 'registerStatus'", TextView.class);
    target.callStatus = Utils.findRequiredViewAsType(source, R.id.call_status, "field 'callStatus'", TextView.class);
    target.outputStrings = Utils.findRequiredViewAsType(source, R.id.output_strings, "field 'outputStrings'", ListView.class);
    view = Utils.findRequiredView(source, R.id.clear_log, "field 'clearLog' and method 'clearLOG'");
    target.clearLog = Utils.castView(view, R.id.clear_log, "field 'clearLog'", Button.class);
    view2131165229 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clearLOG();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.accountNumber = null;
    target.register = null;
    target.calledNumber = null;
    target.dial = null;
    target.dtmfNumber = null;
    target.sendDtmf = null;
    target.answer = null;
    target.hangUp = null;
    target.registerStatus = null;
    target.callStatus = null;
    target.outputStrings = null;
    target.clearLog = null;

    view2131165284.setOnClickListener(null);
    view2131165284 = null;
    view2131165238.setOnClickListener(null);
    view2131165238 = null;
    view2131165304.setOnClickListener(null);
    view2131165304 = null;
    view2131165214.setOnClickListener(null);
    view2131165214 = null;
    view2131165252.setOnClickListener(null);
    view2131165252 = null;
    view2131165229.setOnClickListener(null);
    view2131165229 = null;
  }
}

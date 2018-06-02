/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.pjsip.pjsua2;

public class ToneDigitMapDigit {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected ToneDigitMapDigit(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ToneDigitMapDigit obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pjsua2JNI.delete_ToneDigitMapDigit(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setDigit(String value) {
    pjsua2JNI.ToneDigitMapDigit_digit_set(swigCPtr, this, value);
  }

  public String getDigit() {
    return pjsua2JNI.ToneDigitMapDigit_digit_get(swigCPtr, this);
  }

  public void setFreq1(int value) {
    pjsua2JNI.ToneDigitMapDigit_freq1_set(swigCPtr, this, value);
  }

  public int getFreq1() {
    return pjsua2JNI.ToneDigitMapDigit_freq1_get(swigCPtr, this);
  }

  public void setFreq2(int value) {
    pjsua2JNI.ToneDigitMapDigit_freq2_set(swigCPtr, this, value);
  }

  public int getFreq2() {
    return pjsua2JNI.ToneDigitMapDigit_freq2_get(swigCPtr, this);
  }

  public ToneDigitMapDigit() {
    this(pjsua2JNI.new_ToneDigitMapDigit(), true);
  }

}
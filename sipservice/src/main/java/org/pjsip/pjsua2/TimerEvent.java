/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.pjsip.pjsua2;

public class TimerEvent {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected TimerEvent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(TimerEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pjsua2JNI.delete_TimerEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setEntry(SWIGTYPE_p_void value) {
    pjsua2JNI.TimerEvent_entry_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getEntry() {
    long cPtr = pjsua2JNI.TimerEvent_entry_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public TimerEvent() {
    this(pjsua2JNI.new_TimerEvent(), true);
  }

}
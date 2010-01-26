/*
 * Copyright 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.speedtracer.client.model;

import com.google.gwt.core.client.JsArrayNumber;

/**
 * This class stores a profile for a single top level event. It references into
 * data stored in the {@link JavaScriptProfileModel}.
 */
public class JavaScriptProfile {

  public static final int STATE_JS = 0;
  public static final int STATE_GC = 1;
  public static final int STATE_COMPILER = 2;
  public static final int STATE_OTHER = 3;
  public static final int STATE_EXTERNAL = 4;
  public static final int STATE_UNKNOWN = 5;
  public static final int NUM_STATES = 6;

  public static String stateToString(int state) {
    switch (state) {
      case JavaScriptProfile.STATE_COMPILER:
        return "Compiler";
      case JavaScriptProfile.STATE_JS:
        return "JavaScript";
      case JavaScriptProfile.STATE_GC:
        return "Garbage Collection";
      case JavaScriptProfile.STATE_EXTERNAL:
        return "External";
      case JavaScriptProfile.STATE_OTHER:
        return "Other";
      case JavaScriptProfile.STATE_UNKNOWN:
      default:
        return "Unknown";
    }
  }

  private JavaScriptProfileNode bottomUpProfile = null;
  private JavaScriptProfileNode topDownProfile = null;

  private final JsArrayNumber stateTimes = JsArrayNumber.createArray().cast();

  public JavaScriptProfile() {
    for (int i = 0; i < NUM_STATES; ++i) {
      stateTimes.push(0.0);
    }
  }

  public void addStateTime(int stateIndex, double msecs) {
    Double found = stateTimes.get(stateIndex);
    stateTimes.set(stateIndex, found + msecs);
  }

  public JavaScriptProfileNode getBottomUpProfile() {
    return bottomUpProfile;
  }

  public JavaScriptProfileNode getOrCreateBottomUpProfile() {
    if (bottomUpProfile == null) {
      this.bottomUpProfile = new JavaScriptProfileNode("(root)");
    }
    return bottomUpProfile;
  }

  public JavaScriptProfileNode getOrCreateTopDownProfile() {
    if (topDownProfile == null) {
      this.bottomUpProfile = new JavaScriptProfileNode("(root)");
    }
    return topDownProfile;
  }

  public double getStateTime(int stateIndex) {
    return stateTimes.get(stateIndex);
  }

  public double getTotalTime() {
    if (bottomUpProfile == null) {
      return 0.0;
    }

    // The total time is stored in the root node of the profile.
    return bottomUpProfile.getTime();
  }

  public JavaScriptProfileNode topDownProfile() {
    return topDownProfile;
  }
}

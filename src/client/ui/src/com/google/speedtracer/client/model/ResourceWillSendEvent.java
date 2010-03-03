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

/**
 * Overlay for data payload for NetworkResourceStart events.
 */
public class ResourceWillSendEvent extends ResourceRecord {
  public static final int TYPE = EventRecordType.RESOURCE_SEND_REQUEST;

  protected ResourceWillSendEvent() {
  }

  public final String getHttpMethod() {
    return getData().getStringProperty("requestMethod");
  }

  public final String getUrl() {
    return getData().getStringProperty("url");
  }

  public final boolean isMainResource() {
    return getData().getBooleanProperty("isMainResource");
  }
}

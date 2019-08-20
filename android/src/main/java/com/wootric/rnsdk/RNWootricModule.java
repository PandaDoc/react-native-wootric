package com.wootric.rnsdk;

import android.app.Activity;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.wootric.androidsdk.Wootric;

import java.util.HashMap;

public class RNWootricModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private Wootric wootric;

  public RNWootricModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  private static HashMap<String, String> toHashMap(ReadableMap readableMap) {
    HashMap<String, String> map = new HashMap<>();
    ReadableMapKeySetIterator iterator = readableMap.keySetIterator();

    while (iterator.hasNextKey()) {
      String key = iterator.nextKey();
      ReadableType type = readableMap.getType(key);

      switch (type) {
        case Null:
          map.put(key, null);
          break;
        case Boolean:
          map.put(key, Boolean.toString(readableMap.getBoolean(key)));
          break;
        case Number:
          map.put(key, Double.toString(readableMap.getDouble(key)));
          break;
        case String:
          map.put(key, readableMap.getString(key));
          break;
      }
    }

    return map;
  }

  @Override
  public String getName() {
    return "RNWootric";
  }

  @ReactMethod
  public void configureWithClientID(String clientId, String accountToken, Promise promise) {
    Activity currentActivity = getCurrentActivity();
    if (currentActivity != null) {
      wootric = Wootric.init(currentActivity, clientId, accountToken);
      promise.resolve(true);
    }
    else {
      promise.reject(new Error("RNWootric: Activity not available"));
    }
  }

  @ReactMethod
  public void setEndUserEmail(String email) {
    if (wootric != null) {
      wootric.setEndUserEmail(email);
    }
  }

  @ReactMethod
  public void setSurveyImmediately(boolean surveyImmediately) {
    if (wootric != null) {
      wootric.setSurveyImmediately(surveyImmediately);
    }
  }

  @ReactMethod
  public void setEndUserCreatedAt(double createdAt) {
    if (wootric != null) {
      wootric.setEndUserCreatedAt((long) createdAt);
    }
  }

  @ReactMethod
  public void setEndUserExternalId(String externalId) {
    if (wootric != null) {
      wootric.setEndUserExternalId(externalId);
    }
  }

  @ReactMethod
  public void setEndUserPhoneNumber(String phoneNumber) {
    if (wootric != null) {
      wootric.setEndUserPhoneNumber(phoneNumber);
    }
  }

  @ReactMethod
  public void setEndUserProperties(ReadableMap properties) {
    if (wootric != null) {
      wootric.setProperties(toHashMap(properties));
    }
  }

  @ReactMethod
  public void showOptOut(boolean flag) {
    if (wootric != null) {
      wootric.setShowOptOut(flag);
    }
  }

  @ReactMethod
  public void setFirstSurveyAfter(int value) {
    if (wootric != null) {
      wootric.setFirstSurveyDelay(value);
    }
  }

  @ReactMethod
  public void setCustomLanguage(String language) {
    if (wootric != null) {
      wootric.setLanguageCode(language);
    }
  }

  @ReactMethod
  public void setCustomProductName(String productName) {
    if (wootric != null) {
      wootric.setProductName(productName);
    }
  }

  @ReactMethod
  public void setCustomAudience(String audience) {
    if (wootric != null) {
      wootric.setRecommendTarget(audience);
    }
  }

  @ReactMethod
  public void showSurvey() {
    if (wootric != null) {
      wootric.survey();
    }
  }
}

// Generated by view binder compiler. Do not edit!
package com.example.uts_lec.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.uts_lec.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEditAccountBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText confirmPasswordInput;

  @NonNull
  public final EditText emailInput;

  @NonNull
  public final EditText nameInput;

  @NonNull
  public final EditText passwordInput;

  @NonNull
  public final ImageView profileImage;

  @NonNull
  public final Button saveButton;

  private ActivityEditAccountBinding(@NonNull RelativeLayout rootView,
      @NonNull EditText confirmPasswordInput, @NonNull EditText emailInput,
      @NonNull EditText nameInput, @NonNull EditText passwordInput, @NonNull ImageView profileImage,
      @NonNull Button saveButton) {
    this.rootView = rootView;
    this.confirmPasswordInput = confirmPasswordInput;
    this.emailInput = emailInput;
    this.nameInput = nameInput;
    this.passwordInput = passwordInput;
    this.profileImage = profileImage;
    this.saveButton = saveButton;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEditAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEditAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_edit_account, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEditAccountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.confirmPasswordInput;
      EditText confirmPasswordInput = ViewBindings.findChildViewById(rootView, id);
      if (confirmPasswordInput == null) {
        break missingId;
      }

      id = R.id.emailInput;
      EditText emailInput = ViewBindings.findChildViewById(rootView, id);
      if (emailInput == null) {
        break missingId;
      }

      id = R.id.nameInput;
      EditText nameInput = ViewBindings.findChildViewById(rootView, id);
      if (nameInput == null) {
        break missingId;
      }

      id = R.id.passwordInput;
      EditText passwordInput = ViewBindings.findChildViewById(rootView, id);
      if (passwordInput == null) {
        break missingId;
      }

      id = R.id.profileImage;
      ImageView profileImage = ViewBindings.findChildViewById(rootView, id);
      if (profileImage == null) {
        break missingId;
      }

      id = R.id.saveButton;
      Button saveButton = ViewBindings.findChildViewById(rootView, id);
      if (saveButton == null) {
        break missingId;
      }

      return new ActivityEditAccountBinding((RelativeLayout) rootView, confirmPasswordInput,
          emailInput, nameInput, passwordInput, profileImage, saveButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

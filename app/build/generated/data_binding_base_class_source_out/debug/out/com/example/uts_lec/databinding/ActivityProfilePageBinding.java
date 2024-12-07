// Generated by view binder compiler. Do not edit!
package com.example.uts_lec.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.uts_lec.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityProfilePageBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView backButton;

  @NonNull
  public final TextView birthdayTextView;

  @NonNull
  public final TextView editProfile;

  @NonNull
  public final TextView emailTextView;

  @NonNull
  public final Button friendsButton;

  @NonNull
  public final TextView nameTextView;

  @NonNull
  public final ImageView profileImage;

  @NonNull
  public final Button settingsButton;

  @NonNull
  public final TextView username;

  private ActivityProfilePageBinding(@NonNull LinearLayout rootView, @NonNull ImageView backButton,
      @NonNull TextView birthdayTextView, @NonNull TextView editProfile,
      @NonNull TextView emailTextView, @NonNull Button friendsButton,
      @NonNull TextView nameTextView, @NonNull ImageView profileImage,
      @NonNull Button settingsButton, @NonNull TextView username) {
    this.rootView = rootView;
    this.backButton = backButton;
    this.birthdayTextView = birthdayTextView;
    this.editProfile = editProfile;
    this.emailTextView = emailTextView;
    this.friendsButton = friendsButton;
    this.nameTextView = nameTextView;
    this.profileImage = profileImage;
    this.settingsButton = settingsButton;
    this.username = username;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProfilePageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProfilePageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_profile_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProfilePageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backButton;
      ImageView backButton = ViewBindings.findChildViewById(rootView, id);
      if (backButton == null) {
        break missingId;
      }

      id = R.id.birthdayTextView;
      TextView birthdayTextView = ViewBindings.findChildViewById(rootView, id);
      if (birthdayTextView == null) {
        break missingId;
      }

      id = R.id.editProfile;
      TextView editProfile = ViewBindings.findChildViewById(rootView, id);
      if (editProfile == null) {
        break missingId;
      }

      id = R.id.emailTextView;
      TextView emailTextView = ViewBindings.findChildViewById(rootView, id);
      if (emailTextView == null) {
        break missingId;
      }

      id = R.id.friendsButton;
      Button friendsButton = ViewBindings.findChildViewById(rootView, id);
      if (friendsButton == null) {
        break missingId;
      }

      id = R.id.nameTextView;
      TextView nameTextView = ViewBindings.findChildViewById(rootView, id);
      if (nameTextView == null) {
        break missingId;
      }

      id = R.id.profileImage;
      ImageView profileImage = ViewBindings.findChildViewById(rootView, id);
      if (profileImage == null) {
        break missingId;
      }

      id = R.id.settingsButton;
      Button settingsButton = ViewBindings.findChildViewById(rootView, id);
      if (settingsButton == null) {
        break missingId;
      }

      id = R.id.username;
      TextView username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      return new ActivityProfilePageBinding((LinearLayout) rootView, backButton, birthdayTextView,
          editProfile, emailTextView, friendsButton, nameTextView, profileImage, settingsButton,
          username);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

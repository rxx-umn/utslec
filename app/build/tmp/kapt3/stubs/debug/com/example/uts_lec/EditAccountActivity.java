package com.example.uts_lec;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/uts_lec/EditAccountActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "confirmPasswordInput", "Landroid/widget/EditText;", "databaseRef", "Lcom/google/firebase/database/DatabaseReference;", "emailInput", "nameInput", "originalEmail", "", "originalName", "passwordInput", "profileImage", "Landroid/widget/ImageView;", "saveButton", "Landroid/widget/Button;", "addTextWatchers", "", "checkForChanges", "loadUserData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveChanges", "app_debug"})
public final class EditAccountActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.widget.EditText nameInput;
    private android.widget.EditText emailInput;
    private android.widget.EditText passwordInput;
    private android.widget.EditText confirmPasswordInput;
    private android.widget.Button saveButton;
    private android.widget.ImageView profileImage;
    private com.google.firebase.database.DatabaseReference databaseRef;
    private com.google.firebase.auth.FirebaseAuth auth;
    @org.jetbrains.annotations.NotNull
    private java.lang.String originalEmail = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String originalName = "";
    
    public EditAccountActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadUserData() {
    }
    
    private final void addTextWatchers() {
    }
    
    private final void checkForChanges() {
    }
    
    private final void saveChanges() {
    }
}
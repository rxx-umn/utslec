package com.example.uts_lec;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0018\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\rH\u0002J\u0018\u0010 \u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/uts_lec/AddCaptionActivity;", "Landroidx/activity/ComponentActivity;", "()V", "cancelButton", "Landroid/widget/ImageView;", "captionText", "Landroid/widget/TextView;", "capturedImageView", "databaseRef", "Lcom/google/firebase/database/DatabaseReference;", "getDatabaseRef", "()Lcom/google/firebase/database/DatabaseReference;", "imageBitmap", "Landroid/graphics/Bitmap;", "imageUri", "Landroid/net/Uri;", "progressBar", "Landroid/widget/ProgressBar;", "saveButton", "sendButton", "storageRef", "Lcom/google/firebase/storage/StorageReference;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "saveDataToDatabase", "imageUrl", "", "caption", "saveImageToGallery", "image", "uploadImageToFirebase", "app_debug"})
public final class AddCaptionActivity extends androidx.activity.ComponentActivity {
    private android.widget.ImageView capturedImageView;
    private android.widget.TextView captionText;
    private android.widget.ImageView cancelButton;
    private android.widget.ImageView sendButton;
    private android.widget.ImageView saveButton;
    private android.widget.ProgressBar progressBar;
    @org.jetbrains.annotations.NotNull
    private final com.google.firebase.storage.StorageReference storageRef = null;
    @org.jetbrains.annotations.NotNull
    private final com.google.firebase.database.DatabaseReference databaseRef = null;
    @org.jetbrains.annotations.Nullable
    private android.net.Uri imageUri;
    private android.graphics.Bitmap imageBitmap;
    
    public AddCaptionActivity() {
        super(0);
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.google.firebase.database.DatabaseReference getDatabaseRef() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void uploadImageToFirebase(android.graphics.Bitmap image, java.lang.String caption) {
    }
    
    private final void saveDataToDatabase(java.lang.String imageUrl, java.lang.String caption) {
    }
    
    private final void saveImageToGallery(android.graphics.Bitmap image) {
    }
}
package com.pdfrenderer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.pdfviewer.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PdfLoad extends AppCompatActivity {

    private static final String FILENAME = "sample.pdf";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OpenfileFromAsset();
//        Pickpdfstorage();

    }

    private void Pickpdfstorage() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, 10);
    }

    private void OpenfileFromAsset() {
        File file = new File(getCacheDir(), FILENAME);
        if (!file.exists()) {

            try {
                InputStream asset = getAssets().open(FILENAME);
                FileOutputStream output = null;
                output = new FileOutputStream(file);
                final byte[] buffer = new byte[1024];
                int size;
                while ((size = asset.read(buffer)) != -1) {
                    output.write(buffer, 0, size);
                }
                asset.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        OpenPdfActivity(file.getAbsolutePath());
    }

    private void OpenPdfActivity(String absolutePath) {

        PDFView.with(PdfLoad.this)
                .fromfilepath(absolutePath)
                .swipeHorizontal(false)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 10:
                // Get the Uri of the selected file
                if (resultCode == RESULT_OK) {

                    if (null != data.getData()) {

                        Uri uri = data.getData();
                        File file;

                        if (uri.getScheme().equals("content")) {

                            file = new File(getCacheDir(), data.getData().getLastPathSegment());

                            try {
                                InputStream iStream = getContentResolver().openInputStream(uri);
                                FileOutputStream output = null;
                                output = new FileOutputStream(file);
                                final byte[] buffer = new byte[1024];
                                int size;
                                while ((size = iStream.read(buffer)) != -1) {
                                    output.write(buffer, 0, size);
                                }
                                iStream.close();
                                output.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else
                            file = new File(uri.getPath());


                        OpenPdfActivity(file.getAbsolutePath());
                    }
                }
                break;
        }
    }
}

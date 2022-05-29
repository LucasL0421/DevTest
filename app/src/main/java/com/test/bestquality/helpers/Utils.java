package com.test.bestquality.helpers;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {
    //create folder in storage named "Notes" in this folder = storage/emulated/0/android/data/com.test.bestquality/files/
    public static File generateNotesFile(Context context) {
        File rootFile = context.getExternalFilesDir("Notes");
        if (!rootFile.exists()) rootFile.mkdirs();
        return rootFile;
    }

    //delete specific folder "Notes"
    public static void deleteNotesFile(Context context) {
        File rootFile = context.getExternalFilesDir("Notes");
        if (rootFile.exists()) rootFile.delete();
    }

    //add text to internal storage folder
    //storage/emulated/0/android/data/com.test.bestquality/files/Notes/Test.txt
    public static void generateNoteOnSD(Context context, String sBody) {
        try {
            File gpxfile = new File(generateNotesFile(context), "Test.txt");
            FileOutputStream overWrite = new FileOutputStream(gpxfile, true);
            overWrite.write(sBody.getBytes());
            overWrite.flush();
            overWrite.close();
//            FileWriter writer = new FileWriter(gpxfile);
//            writer.append(sBody);
//            writer.flush();
//            writer.close();
//            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
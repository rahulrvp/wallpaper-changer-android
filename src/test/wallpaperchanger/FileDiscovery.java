package test.wallpaperchanger;

import android.util.Log;
import java.io.File;
import java.util.ArrayList;

public class FileDiscovery {

    private String _rootFileName = null;
    public ArrayList<String> _filePathList = new ArrayList<String>();

    public FileDiscovery(String rootFileName){
        _rootFileName = rootFileName;
    }

    public void startSearch(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                File RootDirSD = new File(_rootFileName);
                processDirectory(RootDirSD);
            }
        }).start();
    }

    private void processDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] listOfFiles = directory.listFiles();
            if (listOfFiles != null) {
                for (File item : listOfFiles) {
                    if (item.isDirectory()){
                        processDirectory(item);
                    } else{
                        processFile(item);
                    }

                }
            } else {
                Log.v("LOG_TAG", "[ACCESS DENIED]");
            }
        }
    }

    private void processFile(File aFile)
    {
        String FileName = aFile.getName();
        if (FileName.toUpperCase().endsWith(".JPG")) _filePathList.add(aFile.getAbsolutePath());
    }
}

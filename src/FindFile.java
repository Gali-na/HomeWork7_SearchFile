import java.io.File;
import java.util.concurrent.Callable;

public class FindFile implements Callable<String> {
    private String nameFile;
    private File file;

    public FindFile(String nameDirectory, File file) {
        this.nameFile = nameDirectory;
        this.file = file;
    }

    public FindFile() {

    }

    public String findFile(String nameFile, File file) {
        String rezult = "";
        if (file.isFile()) {
            if (this.nameFile.equalsIgnoreCase(file.getName())) {
                rezult = file.getName();
            }
        }
        File[] list = this.file.listFiles();
        if (list != null) {
            for (File fil : list) {
                if (fil.isDirectory()) {
                    findFile(nameFile, fil);

                } else if (this.nameFile.equalsIgnoreCase(fil.getName())) {
                    rezult = fil.getPath();
                }
            }
        }
        return rezult;
    }

    @Override
    public String call() throws Exception {
        return findFile(this.nameFile, this.file);
    }
}

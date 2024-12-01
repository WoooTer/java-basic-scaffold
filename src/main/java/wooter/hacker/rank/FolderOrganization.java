package wooter.hacker.rank;

import java.util.Arrays;

@Deprecated
public class FolderOrganization {

    public static void main(String[] args) {
        int count = minFolders(5, 0, 2, 2);
        System.out.println(count);
    }

    public static int minFolders(int cssFiles, int jsFiles, int readMeFiles, int capacity) {
        if (capacity == 1) {
            return cssFiles + jsFiles + readMeFiles;
        }

        if (capacity == 2) {
            int differ = Math.abs(cssFiles - jsFiles);
            int least = Math.min(cssFiles, jsFiles);
            if (differ > readMeFiles) {
                return readMeFiles + least + (differ - readMeFiles);
            } else {
                return readMeFiles + least;
            }
        }

        if (capacity % 2 == 0) {
            int differ = Math.abs(cssFiles - jsFiles);
            int least = Math.min(cssFiles, jsFiles);
            if (least % 2 != 0) {
                least --;
            }

            if (differ > readMeFiles) {
                return readMeFiles + least + (differ - readMeFiles);
            } else {
                return readMeFiles + least / (capacity / 2);
            }
        }

        return 0;
    }
}

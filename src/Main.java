import java.io.*;
import java.nio.channels.FileChannel;

// ------------------------------------------------------------
// Main Method
public class Main {

    //oldString  需要替换的文字，比如编码替换 gb2312
    public static final String oldString = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">";
    //newString  替换成新的文字
    public static final String newString = "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">";

    // executeDirectory  需要操作的文件夹  不要放多级目录，只支持一级目录
    private static final String executeDirectory ="D:\\html";

    //destinationDirectory 替换成功后的文件夹，不要放executeDirectory里面，会死循环
    private static final String destinationDirectory ="D:\\html2";

    public static void main(String[] args) {
        new BatchEncoder(executeDirectory);
        File file = new File("");
        try {
            BatchEncoder.copyDirectory(file.getCanonicalPath(), destinationDirectory);
        }catch (IOException exception){

        }
    }
}

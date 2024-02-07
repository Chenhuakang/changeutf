import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

class HTMLFilter implements FilenameFilter {
    public boolean accept(File directory, String name) {
        if (name.endsWith(".html")) return true;
        if (name.endsWith(".htm")) return true;
        if (name.endsWith(".HTML")) return true;
        if (name.endsWith(".HTM")) return true;
        return false;
    }
}

class BatchEncoder {
    String oldFile = null;
    public BatchEncoder(String oldFile) {
        this.oldFile = oldFile;
        File f = new File(oldFile);
        tree(f);
    }

    public void tree(File f) {
        File[] childs = f.listFiles(new HTMLFilter());
        if (childs == null) {
            return;
        }
        for (int i = 0; i < childs.length; i++) {
            if (childs[i].isDirectory()) {
                tree(childs[i]);
            } else if (childs[i].isFile()) {
                parse(childs[i]);
            }
        }

    }

    public void parse(File f) {
        String s = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f, UTF_8)); // 打开原始文件进行读取

            StringBuilder contentBuilder = new StringBuilder(); // 构造新的文件内容字符串
            String line;

            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
            String oldString = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">";
            String newString = "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">";
            String modifiedContent = contentBuilder.toString().replace(oldString, newString); 
            reader.close(); // 关闭原始文件

            f.deleteOnExit();

            BufferedWriter writer = new BufferedWriter(new FileWriter(f.getName(),UTF_8)); // 重写同名文件

            writer.write(modifiedContent.toString()); // 将修改后的内容写入文件

            writer.flush(); // 清空输出流
            writer.close(); // 关闭文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }

}


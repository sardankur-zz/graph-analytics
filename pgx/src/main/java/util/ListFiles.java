import java.io.File;

public class ListFiles {

    public static void main(String[] args) {
        File folder = new File("/home/ankursarda/Software/pgx-2.5.0-20170324-fixed2/shared-memory/embedded");
        File [] list = folder.listFiles();
        for(File file : list) {
            String name = file.getName();
            String prename = name.substring(0, name.lastIndexOf("-"));
            System.out.printf("        <dependency>\n" +
                    "            <groupId>%s</groupId>\n" +
                    "            <artifactId>%s</artifactId>\n" +
                    "            <version>1.0</version>\n" +
                    "            <scope>system</scope>\n" +
                    "            <systemPath>${pgx.location}/${pgx.jarlocation}/embedded/%s</systemPath>\n" +
                    "        </dependency>\n\n", prename, prename, name);
        }
    }
}

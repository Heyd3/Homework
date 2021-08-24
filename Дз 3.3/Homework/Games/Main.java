package Homework.Games;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        StringBuilder str = new StringBuilder();

        File src = new File("D:\\Games\\src");
        check (src, str);

        File res = new File("D:\\Games\\res");
        check (res, str);

        File savegames = new File("D:\\Games\\savegames");
        check (savegames, str);

        File temp = new File("D:\\Games\\temp");
        check (temp, str);

        File main = new File("D:\\Games\\src\\main");
        check (main, str);

        File test = new File("D:\\Games\\src\\test");
        check (test, str);

        File myFileMain = new File("D:\\Games\\src\\main\\Main.java");
        checkFile (myFileMain, str);

        File myFileUtils = new File("D:\\Games\\src\\main\\Utils.java");
        checkFile (myFileUtils, str);

        File drawables = new File("D:\\Games\\res\\drawables");
        check (drawables, str);

        File vectors = new File("D:\\Games\\res\\vectors");
        check (vectors, str);

        File icons = new File("D:\\Games\\res\\icons");
        check (icons, str);

        File temp1 = new File("D:\\Games\\temp\\temp.txt");
        checkFile (temp1, str);



        try (FileWriter writer = new FileWriter("D:\\Games\\temp\\temp.txt", false)) {
            writer.write(str.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        GameProgress gameProgress = new GameProgress(96, 11, 5, 266.22);
        GameProgress gameProgress1 = new GameProgress(92, 12, 7, 286.22);
        GameProgress gameProgress2 = new GameProgress(88, 13, 11, 366.22);


//        try (FileOutputStream fos = new FileOutputStream("D:\\Games\\savegames\\save.dat");
//             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//            oos.writeObject(gameProgress);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//
//        try (FileOutputStream fos1 = new FileOutputStream("D:\\Games\\savegames\\save1.dat");
//             ObjectOutputStream oos1 = new ObjectOutputStream(fos1)) {
//            oos1.writeObject(gameProgress1);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        try (FileOutputStream fos2 = new FileOutputStream("D:\\Games\\savegames\\save2.dat");
//             ObjectOutputStream oos = new ObjectOutputStream(fos2)) {
//            oos.writeObject(gameProgress2);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }

        saveGame ("D:\\Games\\savegames\\save.dat", gameProgress);
        saveGame ("D:\\Games\\savegames\\save1.dat", gameProgress1);
        saveGame ("D:\\Games\\savegames\\save2.dat", gameProgress2);

        List<String> objects = new ArrayList<>();
        objects.add("D:\\Games\\savegames\\save.dat");
        objects.add("D:\\Games\\savegames\\save1.dat");
        objects.add("D:\\Games\\savegames\\save2.dat");







        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("D:\\Games\\savegames\\zip_output.zip")))
        {
            for (String path : objects) {

                try(FileInputStream fis = new FileInputStream(path)) {
                    ZipEntry entry = new ZipEntry(path);
                    zipOutputStream.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zipOutputStream.write(buffer);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        File file = new File("D:\\Games\\savegames\\save.dat");
        file.delete();




    }
    public static StringBuilder check (File file, StringBuilder str){
        if (file.mkdir())
            str.append("Каталог " + file + " создан \n ");
        return str;
    }

    public static void checkFile (File file, StringBuilder str){
        try {
            if (file.createNewFile())
                str.append("Файл " + file + " создан \n ");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void saveGame (String address, GameProgress gameProgress){
        try (FileOutputStream fos = new FileOutputStream(address);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles (String address, List<String> objects){
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(address));

             FileInputStream fis = new FileInputStream("save.dat")){

                ZipEntry entry = new ZipEntry("save.dat");
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();

//            ZipEntry entry1 = new ZipEntry("save1.dat");
//            zout.putNextEntry(entry1);
//            byte[] buffer1 = new byte[fis.available()];
//            fis.read(buffer1);
//            zout.write(buffer1);
//            zout.closeEntry();
//
//            ZipEntry entry2 = new ZipEntry("save2.dat");
//            zout.putNextEntry(entry2);
//            byte[] buffer2 = new byte[fis.available()];
//            fis.read(buffer2);
//            zout.write(buffer2);
//            zout.closeEntry();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

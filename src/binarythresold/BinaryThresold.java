/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarythresold;

import Funciones.MyListArgs;
import Funciones.MySintaxis;
import java.awt.image.BufferedImage;
import java.io.File;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.ADAPTIVE_THRESH_MEAN_C;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.adaptiveThreshold;

/**
 *-Djava.library.path=/usr/local/share/OpenCV/java/
 * -Djava.library.path="/usr/local/Cellar/opencv3/3.0.0/share/OpenCV/java"
 * @author miguel
 */
public class BinaryThresold {

    /**
     * @param args the command line arguments
     */
    public BinaryThresold(String[] args) {
        // TODO code application logic here7
        MyListArgs    Param         ;
        BufferedImage image         ;
        String        ConfigFile    ;
        
        Param      = new MyListArgs(args)                  ;
        ConfigFile = Param.ValueArgsAsString("-CONFIG", "");
        
        if (!ConfigFile.equals("")) 
        {
            Param.AddArgsFromFile(ConfigFile);
           }//fin if
        
        String Sintaxis   = "-IN:str -OUT:str [-UMBRAL:int] [-SENS:int]";
        //System.out.println(Sintaxis);
        //MySintaxis Review = new MySintaxis(Sintaxis, Param);
        
        String IN  = Param.ValueArgsAsString ("-IN"    , "");
        String OUT = Param.ValueArgsAsString ("-OUT"   , "");
        int UMBRAL = Param.ValueArgsAsInteger("-UMBRAL", 15 );
        int SENS   = Param.ValueArgsAsInteger("-SENS"  , 32);
        
        System.out.println("IN: "+IN);
        File tempDIR = new File(OUT.substring(0, OUT.lastIndexOf(File.separator)));
        //System.out.println("DIR OUT: "+OUT.substring(0, OUT.lastIndexOf(File.separator)));
        if(!tempDIR.exists())
        {
            tempDIR.mkdirs();
            //System.out.println("DIR: "+tempDIR.getPath());
        }
            
            
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //System.loadLibrary("opencv3.1");
        //System.out.println(System.getProperty("java.library.path"));
        //System.out.println("BINARIZE THRESOLD COMPONENTE");
        //System.out.println("IN : "+IN);
        //System.out.println("OUT:"+OUT);
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //try{
        Mat gray   = imread(IN, IMREAD_GRAYSCALE);
        Mat result = new Mat();
        adaptiveThreshold(gray, result, 255, ADAPTIVE_THRESH_MEAN_C, THRESH_BINARY, UMBRAL, SENS);
        //System.out.println("save: "+OUT);
        imwrite((OUT), result);
        //}catch(Exception e){System.out.println("error OPENCV!!!!!");}
    }
}

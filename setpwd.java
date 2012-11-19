//autor: @wfranck
import com.powerup.crypt.PU_Pwd;

public class setpwd{

public static void main(String[] args)
{
 System.out.println();
 System.out.println("Softing password manager");
 System.out.println("------------------------");
 boolean f = true;
 f = (args.length>2);
 if (f) {f = args[0].equals("-a")||args[0].equals("-d")||args[0].equals("-r")||args[0].equals("-l");}
 if (f) {f = args[0].equals("-l")||args.length>3; } // only l can have 3 args
 if (f) {f = !(args[0].equals("-a"))||args.length>4; } // a must have 5 args
 if (!f)
 {
  System.out.println("Uso de : setpwd ");
  System.out.println("                -a <fname> <encrypt_password> <alias> <password> [algorithm]  --- para AGREGAR un par alias / password ");
  System.out.println("                -d <fname> <encrypt_password> <alias> [algorithm]             --- para BORRAR  un par alias / password ");
  System.out.println("                -r <fname> <encrypt_password> <alias> [algorithm]             --- para LEER to read an existing password");
  System.out.println("                -l <fname> <encrypt_password>  [algorithm]                    --- para LISTAR los pares existentes");
  System.out.println();
  System.out.println(" Cuidado: Agregar un par con un algoritmo o llave mala puede hacer ilegible todo el archivo!!\n");
  System.out.println(" Son algoritmos válidos :  \nPBEWithMD5AndDES\nPBEWithSHAAndBlowfish\nPBEWithSHAAnd128BitRC4\nPBEWithSHAAndIDEA-CBC\nPBEWithSHAAnd3-KeyTripleDES-CBC\no otros instalados en java.crypto");
  return;
 }
 PU_Pwd pw = null;
 if (
     ((args[0].equals("-a"))&&(args.length==6)) ||
     (((args[0].equals("-d"))||(args[0].equals("-r")))&&(args.length==5)) ||
     ((args[0].equals("-l"))&&args.length==4)
    )
 { 
  String algo = args[args.length-1];
  pw = new PU_Pwd(args[1],args[2],algo);
 } else
 {
  pw = new PU_Pwd(args[1],args[2]);
 }
 if (args[0].equals("-a"))
 try { 
  pw.appendPair(args[3],args[4]); 
  System.out.println("par seteado "+args[3]+"="+args[4]);
 }
 catch (Exception e) {System.out.println("No se puede agregar/modificar el par en "+args[1]);}
 if (args[0].equals("-d"))
 try
 {
  pw.readBuf();
  System.out.println("borrado el par "+args[3]);
  if (pw.deletePair(args[3])==-1) System.out.println("No se encuentra el par en el archivo"); 
  pw.writeBuf();
 } catch (Exception e) {}
 if (args[0].equals("-r"))
 try
 {
  System.out.println("recuperando el password para "+args[3]);
  System.out.println(pw.getPwd(args[3])); 
 } catch (Exception e) {}
 if (args[0].equals("-l"))
 try
 {
  pw.readBuf();
  System.out.println("Listando el par en "+args[1]);
  int i =0;
  String pr = pw.getPairFromBuf(i++);
  while (pr!=null)
  { 
   System.out.println(""+i+": "+pr);
   pr = pw.getPairFromBuf(i++);
  } 
 } catch (Exception e) {}

}

}
